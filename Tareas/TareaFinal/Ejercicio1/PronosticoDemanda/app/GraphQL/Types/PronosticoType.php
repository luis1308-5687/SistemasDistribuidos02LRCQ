<?php

namespace App\GraphQL\Types;

use App\Models\Pronostico;
use GraphQL\Type\Definition\Type;
use Rebing\GraphQL\Support\Type as GraphQLType;

class PronosticoType extends GraphQLType
{
    protected $attributes = [
        'name' => 'Pronostico',
        'description' => 'Tipo de dato para los pronósticos de demanda',
        'model' => Pronostico::class,
    ];

    public function fields(): array
    {
        return [
            'id' => [
                'type' => Type::nonNull(Type::int()),
                'description' => 'ID del pronostico',
            ],
            'fecha' => [
                'type' => Type::nonNull(Type::string()),
                'description' => 'Fecha del pronostico',
            ],
            'cantidad_estimada' => [
                'type' => Type::nonNull(Type::int()),
                'description' => 'Cantidad estimada de demanda',
            ],
            // Agregamos timestamps por si acaso
            'created_at' => ['type' => Type::string()],
            'updated_at' => ['type' => Type::string()],
        ];
    }
}