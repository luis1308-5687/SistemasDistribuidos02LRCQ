<?php

namespace App\GraphQL\Mutations;

use App\Models\Pronostico;
use Rebing\GraphQL\Support\Mutation;
use GraphQL\Type\Definition\Type;
use GraphQL;

class CreatePronosticoMutation extends Mutation
{
    protected $attributes = [
        'name' => 'createPronostico'
    ];

    public function type(): Type
    {
        return GraphQL::type('Pronostico');
    }

    public function args(): array
    {
        return [
            'fecha' => ['type' => Type::nonNull(Type::string())],
            'cantidad_estimada' => ['type' => Type::nonNull(Type::int())],
        ];
    }

    public function resolve($root, $args)
    {
        return Pronostico::create($args);
    }
}