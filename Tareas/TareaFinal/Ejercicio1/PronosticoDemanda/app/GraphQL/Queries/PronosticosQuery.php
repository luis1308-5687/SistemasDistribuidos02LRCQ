<?php

namespace App\GraphQL\Queries;

use App\Models\Pronostico;
use GraphQL\Type\Definition\Type;
use Rebing\GraphQL\Support\Query;
use GraphQL;

class PronosticosQuery extends Query
{
    protected $attributes = [
        'name' => 'pronosticos', // Nombre de la consulta
    ];

    public function type(): Type
    {
        // Devuelve una LISTA de tipos Pronostico
        return Type::listOf(GraphQL::type('Pronostico'));
    }

    public function resolve($root, $args)
    {
        return Pronostico::all();
    }
}