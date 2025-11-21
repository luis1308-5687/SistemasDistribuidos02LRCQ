<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class PronosticoSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        $datos = [
            ['fecha' => '01-06-25', 'cantidad_estimada' => 150],
            ['fecha' => '02-06-25', 'cantidad_estimada' => 145],
            ['fecha' => '03-06-25', 'cantidad_estimada' => 165],
            ['fecha' => '04-06-25', 'cantidad_estimada' => 170],
            ['fecha' => '05-06-25', 'cantidad_estimada' => 180],
            ['fecha' => '06-06-25', 'cantidad_estimada' => 130],
            ['fecha' => '07-06-25', 'cantidad_estimada' => 160],
            ['fecha' => '08-06-25', 'cantidad_estimada' => 190],
            ['fecha' => '09-06-25', 'cantidad_estimada' => 200],
        ];

        DB::table('pronosticos')->insert($datos);
    }
}
