<?php
use App\Models\Factura; // <-- Importante
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::get('/facturas/{ci}', function ($ci) {
    $deudas = Factura::where('user_ci', $ci)
                     ->where('pagada', false)
                     ->get();
    return response()->json($deudas);
});

Route::post('/facturas/pagar/{id}', function ($id) {
    $factura = Factura::find($id);
    if ($factura) {
        $factura->pagada = true;
        $factura->save();
        return response()->json(['message' => 'Factura (ID: ' . $id . ') pagada con exito']);
    }
    return response()->json(['message' => 'Factura no encontrada'], 404);
});

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});