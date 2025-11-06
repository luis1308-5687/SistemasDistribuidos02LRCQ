const express = require('express');
const mongoose = require('mongoose'); // ¡El protagonista de este archivo!
const jwt = require('jsonwebtoken');
const cors = require('cors');

const app = express();
app.use(express.json());
app.use(cors());

// --- Configuración ---

// Exactamente el mismo secreto que en los otros servicios
const JWT_SECRET = process.env.JWT_SECRET || 'TU_SECRET0_MUY_SEGURO_Y_LARGO';

// ¡Nueva configuración! Esta es la "cadena de conexión" para MongoDB.
// 'mongo_db' será el nombre del servicio de MongoDB en nuestro Docker-Compose.
// 'reservas_db' es el nombre de la base de datos que se creará.
const MONGO_URI = process.env.MONGO_URI || 'mongodb://127.0.0.1:27017/reservas_db';

// --- Conexión a MongoDB ---
// 
mongoose.connect(MONGO_URI)
    .then(() => console.log('Conexión a MongoDB exitosa.'))
    .catch(err => console.error('Error al conectar a MongoDB:', err));

// --- Esquema y Modelo de Mongoose ---
// Esto es como la "migración" y el "modelo" de Laravel, pero en un solo lugar.
// Define la estructura de tus documentos en la colección 'reservas'.
const ReservaSchema = new mongoose.Schema({
    habitacion_id: { type: Number, required: true }, // ID de la habitación en MySQL
    usuario_id: { type: Number, required: true },    // ID del usuario en MySQL
    fecha_reserva: { type: Date, default: Date.now },
    fecha_entrada: { type: Date, required: true },
    fecha_salida: { type: Date, required: true },
    estado_reserva: { 
        type: String, 
        default: 'confirmada', 
        enum: ['confirmada', 'pendiente', 'cancelada'] // Solo permite estos valores
    },
    total_a_pagar: { type: Number, required: true }
});

// Creamos el modelo "Reserva" basado en el esquema.
// Mongoose automáticamente buscará la colección "reservas" (plural en minúsculas).
const Reserva = mongoose.model('Reserva', ReservaSchema);

// --- Middleware de Autenticación (El Guardia) ---
// Es exactamente el mismo que usamos en el servicio de Habitaciones.
const authMiddleware = (req, res, next) => {
    const authHeader = req.headers['authorization'];
    const token = authHeader && authHeader.split(' ')[1];

    if (token == null) {
        return res.status(401).json({ message: 'Acceso denegado. No se proporcionó token.' });
    }

    jwt.verify(token, JWT_SECRET, (err, userPayload) => {
        if (err) {
            return res.status(403).json({ message: 'Token inválido.' });
        }
        req.user = userPayload; // Adjuntamos los datos del usuario (id, email, etc.)
        next();
    });
};

// Aplicamos el middleware a TODAS las rutas de reservas
app.use(authMiddleware);

// --- Rutas de Reservas (Protegidas) ---

// GET /reservas (Listar SÓLO mis reservas)
app.get('/reservas', async (req, res) => {
    try {
        // Gracias al middleware, tenemos 'req.user'.
        // Buscamos en MongoDB solo las reservas que coincidan con el ID del usuario del token.
        const misReservas = await Reserva.find({ usuario_id: req.user.id });
        res.json(misReservas);
    } catch (error) {
        console.error('Error en GET /reservas:', error);
        res.status(500).json({ message: 'Error interno del servidor' });
    }
});

// POST /reservas (Crear una nueva reserva)
app.post('/reservas', async (req, res) => {
    try {
        const { habitacion_id, fecha_entrada, fecha_salida, total_a_pagar } = req.body;

        // ¡Importante! Forzamos el 'usuario_id' a ser el del token.
        // Esto es por seguridad, para que un usuario no pueda
        // hacer una reserva a nombre de otro.
        const nuevaReserva = new Reserva({
            habitacion_id,
            fecha_entrada,
            fecha_salida,
            total_a_pagar,
            usuario_id: req.user.id // ID viene del token JWT
        });

        // Guardamos el nuevo documento en MongoDB
        await nuevaReserva.save();
        
        res.status(201).json({ message: 'Reserva creada exitosamente', reserva: nuevaReserva });

    } catch (error) {
        console.error('Error en POST /reservas:', error);
        // Si faltan campos requeridos (ej. 'total_a_pagar'), Mongoose dará un error
        res.status(400).json({ message: 'Error al crear la reserva', error: error.message });
    }
});

// --- Iniciar el servidor ---
const PORT = process.env.PORT || 3003; // Este servicio correrá en el puerto 3003
app.listen(PORT, () => {
    console.log(`Microservicio de Reservas (MongoDB) corriendo en http://localhost:${PORT}`);
});