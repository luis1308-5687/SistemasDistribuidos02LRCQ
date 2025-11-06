<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class TipoHabitacionSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        \App\Models\TipoHabitacion::create([
        'nombre' => 'simple',
        'descripcion' => 'Habitación para una persona.',
        'precio' => 80.00
        ]);
        \App\Models\TipoHabitacion::create([
            'nombre' => 'doble',
            'descripcion' => 'Habitación con dos camas individuales.',
            'precio' => 120.00
        ]);
        \App\Models\TipoHabitacion::create([
            'nombre' => 'triple',
            'descripcion' => 'Habitación con tres camas individuales.',
            'precio' => 150.00
        ]);
        \App\Models\TipoHabitacion::create([
            'nombre' => 'matrimonio',
            'descripcion' => 'Habitación con cama matrimonial.',
            'precio' => 130.00
        ]);
    }
}
