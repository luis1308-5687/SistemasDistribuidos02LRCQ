const express = require('express');
const mysql = require('mysql2/promise'); 
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs'); 
const cors = require('cors');

const app = express();
app.use(express.json()); 
app.use(cors()); 

// --- Configuración ---

// Esta será tu clave secreta para firmar los tokens.
// Debería ser algo largo y aleatorio, y guardado en variables de entorno.
// El docker-compose se encargará de esto luego.
const JWT_SECRET = process.env.JWT_SECRET || 'TU_SECRET0_MUY_SEGURO_Y_LARGO';

// Configuración de la base de datos.
// Usamos variables de entorno para que Docker pueda configurarlo.
// Como "fallback" (valor por defecto), usamos los de XAMPP.
const dbConfig = {
    host: process.env.DB_HOST || '127.0.0.1', // O 'localhost'
    user: process.env.DB_USERNAME || 'root',
    password: process.env.DB_PASSWORD || '', // La contraseña de tu XAMPP (suele estar vacía)
    database: process.env.DB_DATABASE || 'hotel_admin', // El nombre de tu BD de Laravel
    port: process.env.DB_PORT || 3310 // O 3307 si lo cambiaste
};

// --- Ruta de Login ---

app.post('/login', async (req, res) => {
    try {
        const { email, password } = req.body;

        // 1. Validación simple de entrada
        if (!email || !password) {
            return res.status(400).json({ message: 'Email y contraseña son requeridos.' });
        }

        // 2. Conectar a la base de datos de Laravel (MySQL)
        let connection;
        try {
            connection = await mysql.createConnection(dbConfig);
        } catch (dbError) {
            console.error('Error al conectar a la BD:', dbError);
            return res.status(500).json({ message: 'Error interno del servidor (BD)' });
        }

        // 3. Buscar al usuario por email
        // (Usamos 'name' como lo cambiaste en la Parte 1)
        const [rows] = await connection.execute('SELECT * FROM users WHERE email = ?', [email]);
        await connection.end(); // Cerrar la conexión

        if (rows.length === 0) {
            // Usuario no encontrado
            return res.status(401).json({ message: 'Credenciales inválidas' });
        }

        const user = rows[0];

        // 4. Comparar la contraseña
        // Usamos bcrypt.compare para comparar la contraseña en texto plano (del req.body)
        // con el hash guardado en la base de datos por Laravel.
        const isMatch = await bcrypt.compare(password, user.password);

        if (!isMatch) {
            // Contraseña incorrecta
            return res.status(401).json({ message: 'Credenciales inválidas' });
        }

        // 5. ¡Éxito! Crear el Token JWT
        const payload = {
            id: user.id,
            email: user.email,
            name: user.name, // Enviamos el nombre (antes 'nombre')
            tipo: user.tipo_usuario // Importante para saber si es 'admin' o 'cliente'
        };

        const token = jwt.sign(
            payload,
            JWT_SECRET,
            { expiresIn: '1h' } // El token será válido por 1 hora
        );

        // 6. Enviar el token al cliente
        res.json({
            message: 'Login exitoso',
            token: token
        });

    } catch (error) {
        console.error('Error en el endpoint /login:', error);
        res.status(500).json({ message: 'Error interno del servidor' });
    }
});

// --- Iniciar el servidor ---
const PORT = process.env.PORT || 3001; // El servicio de login correrá en el puerto 3001
app.listen(PORT, () => {
    console.log(`Microservicio de Login corriendo en http://localhost:${PORT}`);
});