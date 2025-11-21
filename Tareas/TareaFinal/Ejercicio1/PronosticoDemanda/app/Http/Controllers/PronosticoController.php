<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Pronostico;

class PronosticoController extends Controller
{
    /**
     * Display a listing of the resource.
     */
 // GET /api/pronosticos (Obtener todos)
    public function index()
    {
        return response()->json(Pronostico::all());
    }

    // POST /api/pronosticos (Crear uno nuevo)
    public function store(Request $request)
    {
        // Validamos que envíen los datos necesarios
        $request->validate([
            'fecha' => 'required',
            'cantidad_estimada' => 'required|integer',
        ]);

        $pronostico = Pronostico::create($request->all());
        return response()->json($pronostico, 201);
    }

    // GET /api/pronosticos/{id} (Obtener uno solo)
    public function show(string $id)
    {
        $pronostico = Pronostico::find($id);
        if (!$pronostico) {
            return response()->json(['message' => 'No encontrado'], 404);
        }
        return response()->json($pronostico);
    }

    // PUT /api/pronosticos/{id} (Actualizar)
    public function update(Request $request, string $id)
    {
        $pronostico = Pronostico::find($id);
        if (!$pronostico) {
            return response()->json(['message' => 'No encontrado'], 404);
        }

        $pronostico->update($request->all());
        return response()->json($pronostico);
    }

    // DELETE /api/pronosticos/{id} (Eliminar)
    public function destroy(string $id)
    {
        if (Pronostico::destroy($id)) {
            return response()->json(null, 204); 
        }
        return response()->json(['message' => 'No encontrado'], 404);
    }
}
