<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Habitacion>
 */
class HabitacionFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            //
            'numero_habitacion' => $this->faker->unique()->numerify('H-###'),
            'tipo_habitacion_id' => \App\Models\TipoHabitacion::factory(), // Asignará uno aleatorio
            'precio_por_noche' => $this->faker->randomFloat(2, 100, 500),
            'estado' => $this->faker->randomElement(['disponible', 'mantenimiento']),
            'descripcion' => $this->faker->paragraph(),
        ];
    }
}
