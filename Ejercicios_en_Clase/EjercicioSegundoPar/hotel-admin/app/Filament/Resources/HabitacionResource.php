<?php

namespace App\Filament\Resources;

use App\Filament\Resources\HabitacionResource\Pages;
use App\Filament\Resources\HabitacionResource\RelationManagers;
use App\Models\Habitacion;
use Filament\Forms;
use Filament\Forms\Form;
use Filament\Resources\Resource;
use Filament\Tables;
use Filament\Tables\Table;
use Illuminate\Database\Eloquent\Builder;
use Illuminate\Database\Eloquent\SoftDeletingScope;
use Filament\Forms\Components\TextInput;
use Filament\Forms\Components\Select;
use Filament\Forms\Components\Textarea;
use Filament\Tables\Columns\TextColumn;   // <-- Esta es la que te da el error
use Filament\Tables\Filters\SelectFilter; // <-- La necesitarás para los filtros

class HabitacionResource extends Resource
{
    protected static ?string $model = Habitacion::class;

    protected static ?string $navigationIcon = 'heroicon-o-rectangle-stack';

    public static function form(Form $form): Form
    {
        return $form
            ->schema([
            TextInput::make('numero_habitacion')
                ->required()
                ->maxLength(255),
            Select::make('tipo_habitacion_id')
                ->relationship('tipoHabitacion', 'nombre') // Carga la relación
                ->required(),
            TextInput::make('precio_por_noche')
                ->required()
                ->numeric()
                ->prefix('$'),
            Select::make('estado')
                ->options([
                    'disponible' => 'Disponible',
                    'ocupada' => 'Ocupada',
                    'mantenimiento' => 'En Mantenimiento',
                ])
                ->required(),
            Textarea::make('descripcion')
                ->columnSpanFull(),
        ]);
    }

    public static function table(Table $table): Table
    {
        return $table
        ->columns([
            TextColumn::make('numero_habitacion')
                ->searchable(), // Habilita la búsqueda para esta columna
            TextColumn::make('tipoHabitacion.nombre') // Muestra el nombre del tipo
                ->sortable(),
            TextColumn::make('precio_por_noche')
                ->money('usd')
                ->sortable(),
            TextColumn::make('estado')
                ->searchable()
                ->badge() // Muestra como una "pastilla"
                ->color(fn (string $state): string => match ($state) {
                    'disponible' => 'success',
                    'ocupada' => 'danger',
                    'mantenimiento' => 'warning',
                }),
        ])
        ->filters([
            SelectFilter::make('tipo_habitacion_id')
                ->relationship('tipoHabitacion', 'nombre')
                ->label('Tipo de Habitación'),
        ])
        ->actions([
            Tables\Actions\EditAction::make(),
        ])
        ->bulkActions([
            Tables\Actions\BulkActionGroup::make([
                Tables\Actions\DeleteBulkAction::make(),
            ]),
        ]);
    }

    public static function getRelations(): array
    {
        return [
            //
        ];
    }

    public static function getPages(): array
    {
        return [
            'index' => Pages\ListHabitacions::route('/'),
            'create' => Pages\CreateHabitacion::route('/create'),
            'edit' => Pages\EditHabitacion::route('/{record}/edit'),
        ];
    }
}
