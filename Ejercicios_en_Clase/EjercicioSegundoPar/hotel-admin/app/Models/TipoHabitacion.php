<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class TipoHabitacion extends Model
{
    /** @use HasFactory<\Database\Factories\TipoHabitacionFactory> */
    use HasFactory;

    /**
     * El nombre de la tabla asociada con el modelo.
     *
     * @var string
     */
    protected $table = 'tipos_habitaciones';

    protected $fillable = [
        'nombre',
        'descripcion',
        'precio',
    ];

    public function habitaciones()
    {
        return $this->hasMany(Habitacion::class);   
    }
}
