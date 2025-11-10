const participantesDB = [
  { ci: "123456", nombres: "Juan", primerApellido: "Perez", segundoApellido: "Sosa", sexo: "M" },
  { ci: "555666", nombres: "Maria", primerApellido: "Gomez", segundoApellido: "Lopez", sexo: "F" },
  { ci: "111222", nombres: "Carlos", primerApellido: "Vaca", segundoApellido: "Diez", sexo: "M" }
]

module.exports = { participantesDB };

//tambien se puede trabajar con baseDeDatos con const { Sequelize } = require('sequelize')