<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class HabitacionSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        $tipoSimple = \App\Models\TipoHabitacion::where('nombre', 'simple')->first();
        $tipoDoble = \App\Models\TipoHabitacion::where('nombre', 'doble')->first();
        $tipoTriple = \App\Models\TipoHabitacion::where('nombre', 'triple')->first();
        $tipoMatrimonio = \App\Models\TipoHabitacion::where('nombre', 'matrimonio')->first();
        
        // 10 individuales (simples)
        \App\Models\Habitacion::factory()->count(10)->create([
            'tipo_habitacion_id' => $tipoSimple->id,
            'precio_por_noche' => $tipoSimple->precio
        ]);
    
        // 5 dobles
        \App\Models\Habitacion::factory()->count(5)->create([
            'tipo_habitacion_id' => $tipoDoble->id,
            'precio_por_noche' => $tipoDoble->precio
        ]);
    
        // 5 triples
        \App\Models\Habitacion::factory()->count(5)->create([
            'tipo_habitacion_id' => $tipoTriple->id,
            'precio_por_noche' => $tipoTriple->precio
        ]);
    
        // 8 matrimoniales
        \App\Models\Habitacion::factory()->count(8)->create([
            'tipo_habitacion_id' => $tipoMatrimonio->id,
            'precio_por_noche' => $tipoMatrimonio->precio
        ]);
    }
}
