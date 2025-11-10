<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Factura extends Model
{
    use HasFactory;

    /**
     * El nombre de la tabla asociada con el modelo.
     *
     * @var string
     */
    protected $table = 'facturas';

    /**
     * Los atributos que se pueden asignar masivamente.
     * (Buena práctica, aunque no lo usemos en las rutas simples)
     *
     * @var array<int, string>
     */
    protected $fillable = [
        'user_ci',
        'empresa',
        'concepto',
        'monto',
        'pagada',
    ];
}