# Usar una imagen base de Node.js (ligera)
FROM node:18-alpine

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /usr/src/app

# Copiar el package.json y package-lock.json primero
# (Docker guarda esto en caché, para no reinstalar todo cada vez)
COPY package*.json ./

# Instalar las dependencias del proyecto
RUN npm install

# Copiar el resto del código de la aplicación (el index.js)
COPY . .

# Exponer el puerto en el que corre la app
# (Esto es solo informativo, el docker-compose lo maneja)
# Dejaremos 3000 como un genérico, ya que nuestros index.js
# leen el puerto real desde process.env.PORT
EXPOSE 3000 

# El comando para iniciar la aplicación cuando arranque el contenedor
CMD [ "node", "index.js" ]