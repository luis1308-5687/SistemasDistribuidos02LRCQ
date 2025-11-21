<?php

namespace App\GraphQL\Mutations;

use App\Models\Pronostico;
use Rebing\GraphQL\Support\Mutation;
use GraphQL\Type\Definition\Type;
use GraphQL;

class UpdatePronosticoMutation extends Mutation
{
    protected $attributes = [
        'name' => 'updatePronostico'
    ];

    public function type(): Type
    {
        return GraphQL::type('Pronostico');
    }

    public function args(): array
    {
        return [
            'id' => ['type' => Type::nonNull(Type::int())], 
            'fecha' => ['type' => Type::string()],
            'cantidad_estimada' => ['type' => Type::int()],
        ];
    }

    public function resolve($root, $args)
    {
        $pronostico = Pronostico::findOrFail($args['id']);
        $pronostico->update($args);
        return $pronostico;
    }
}