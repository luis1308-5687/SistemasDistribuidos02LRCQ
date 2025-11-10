<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Models\Factura; 

// EJEMPLO: GET http://localhost:8000/api/facturas/123456
Route::get('/facturas/{ci}', function ($ci) {
    
    // Busca todas las facturas de este CI que NO estén pagadas
    $deudas = Factura::where('user_ci', $ci)
                     ->where('pagada', false)
                     ->get();
                     
    return response()->json($deudas);
});


// Endpoint 2: Pagar una factura
// EJEMPLO: POST http://localhost:8000/api/facturas/pagar/1
Route::post('/facturas/pagar/{id}', function ($id) {
    
    $factura = Factura::find($id);

    // Si la factura existe
    if ($factura) {
        // La marca como pagada y guarda en la BD
        $factura->pagada = true;
        $factura->save();
        
        // Devuelve un mensaje de éxito
        return response()->json(['message' => 'Factura (ID: ' . $id . ') pagada con exito']);
    }

    // Si no la encuentra, devuelve un error 404
    return response()->json(['message' => 'Factura no encontrada'], 404);
});


// Esta es la ruta de 'test' que viene por defecto, puedes borrarla o dejarla
Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});