const express = require('express');
const mysql = require('mysql2/promise');
const jwt = require('jsonwebtoken'); // Lo usamos para *verificar*
const cors = require('cors');

const app = express();
app.use(express.json());
app.use(cors());

// --- Configuración ---

// ¡IMPORTANTE! Debe ser exactamente el mismo secreto que usaste en micro-login
const JWT_SECRET = process.env.JWT_SECRET || 'TU_SECRET0_MUY_SEGURO_Y_LARGO';

// Configuración de la base de datos (igual que en micro-login)
const dbConfig = {
    host: process.env.DB_HOST || '127.0.0.1',
    user: process.env.DB_USERNAME || 'root',
    password: process.env.DB_PASSWORD || '',
    database: process.env.DB_DATABASE || 'hotel_admin',
    port: process.env.DB_PORT || 3310 // O 3307 si lo cambiaste
};

// --- Middleware de Autenticación (El Guardia de Seguridad) ---
// 
// Este "middleware" se ejecutará antes que cualquier ruta.
// Su trabajo es revisar el token.
const authMiddleware = (req, res, next) => {
    // 1. Obtener el token del encabezado 'Authorization'
    const authHeader = req.headers['authorization'];
    // El formato es "Bearer TOKEN"
    const token = authHeader && authHeader.split(' ')[1];

    // 2. Si no hay token, rechazar la petición
    if (token == null) {
        return res.status(401).json({ message: 'Acceso denegado. No se proporcionó token.' });
    }

    // 3. Verificar el token
    jwt.verify(token, JWT_SECRET, (err, userPayload) => {
        if (err) {
            // El token es inválido o expiró
            return res.status(403).json({ message: 'Token inválido.' });
        }

        // 4. ¡Token válido! Adjuntamos los datos del usuario a la 'request'
        // para que las siguientes rutas puedan usarlo (ej. req.user.id)
        req.user = userPayload;
        next(); // ¡Luz verde! Deja pasar a la siguiente función (la ruta)
    });
};

// --- Aplicar el Middleware ---
// Le decimos a Express que use nuestro "guardia" (authMiddleware)
// para TODAS las rutas definidas DESPUÉS de esta línea.
app.use(authMiddleware);


// --- Rutas de Habitaciones (Protegidas) ---

// GET /habitaciones (Listar todas las habitaciones disponibles)
app.get('/habitaciones', async (req, res) => {
    try {
        const connection = await mysql.createConnection(dbConfig);
        
        // Unimos con tipos_habitaciones para obtener el nombre del tipo
        const [rows] = await connection.execute(
            `SELECT h.*, th.nombre as tipo_nombre 
             FROM habitaciones h
             JOIN tipos_habitaciones th ON h.tipo_habitacion_id = th.id
             WHERE h.estado = 'disponible'`
        );
        
        await connection.end();
        res.json(rows);
    } catch (error) {
        console.error('Error en GET /habitaciones:', error);
        res.status(500).json({ message: 'Error interno del servidor' });
    }
});

// GET /habitaciones/:id (Ver una habitación específica)
app.get('/habitaciones/:id', async (req, res) => {
    try {
        const { id } = req.params;
        const connection = await mysql.createConnection(dbConfig);
        
        const [rows] = await connection.execute(
            `SELECT h.*, th.nombre as tipo_nombre 
             FROM habitaciones h
             JOIN tipos_habitaciones th ON h.tipo_habitacion_id = th.id
             WHERE h.id = ?`, [id]
        );
        
        await connection.end();

        if (rows.length === 0) {
            return res.status(404).json({ message: 'Habitación no encontrada' });
        }
        
        res.json(rows[0]);
    } catch (error) {
        console.error('Error en GET /habitaciones/:id:', error);
        res.status(500).json({ message: 'Error interno del servidor' });
    }
});

/*
// --- Rutas de Admin (Ejemplo) ---
// Aquí podrías añadir rutas POST, PUT, DELETE
// y protegerlas para que solo el 'administrador' pueda usarlas

// Middleware extra solo para Admins
const adminOnly = (req, res, next) => {
    if (req.user && req.user.tipo === 'administrador') {
        next(); // Es admin, puede pasar
    } else {
        res.status(403).json({ message: 'Acción no permitida. Requiere privilegios de administrador.' });
    }
};

// POST /habitaciones (Crear habitación - Solo Admin)
app.post('/habitaciones', adminOnly, async (req, res) => {
    // ...lógica para crear habitación...
    res.json({ message: 'Habitación creada por admin' });
});
*/

// --- Iniciar el servidor ---
const PORT = process.env.PORT || 3002; // Este servicio correrá en el puerto 3002
app.listen(PORT, () => {
    console.log(`Microservicio de Habitaciones corriendo en http://localhost:${PORT}`);
});