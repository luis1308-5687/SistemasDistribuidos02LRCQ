const { 
    GraphQLObjectType, 
    GraphQLString, 
    GraphQLSchema, 
    GraphQLList, 
    GraphQLNonNull 
} = require('graphql');

const { participantesDB } = require('./db'); 

const ParticipanteType = new GraphQLObjectType({
  name: 'Participante',
  fields: {
    ci: { type: GraphQLString },
    nombres: { type: GraphQLString },
    primerApellido: { type: GraphQLString },
    segundoApellido: { type: GraphQLString },
    sexo: { type: GraphQLString }
  }
});


const RootQuery = new GraphQLObjectType({
  name: 'RootQueryType',
  fields: {
    
    participante: {
      type: ParticipanteType,
      args: { ci: { type: new GraphQLNonNull(GraphQLString) } },
      resolve(parent, args) {
        // Lógica estática: usa .find() en el array
        return participantesDB.find(p => p.ci === args.ci);
      }
    },

    // Query para buscar TODOS los participantes
    participantes: {
      type: new GraphQLList(ParticipanteType),
      resolve(parent, args) {
        // Lógica estática: simplemente retorna el array completo
        return participantesDB;
      }
    }
  }
});

// 3. Define las MUTACIONES (aquí cambia el 'resolve')
const Mutation = new GraphQLObjectType({
  name: 'Mutation',
  fields: {
    addParticipante: {
      type: ParticipanteType,
      args: {
        ci: { type: new GraphQLNonNull(GraphQLString) },
        nombres: { type: new GraphQLNonNull(GraphQLString) },
        primerApellido: { type: GraphQLString },
        segundoApellido: { type: GraphQLString },
        sexo: { type: GraphQLString }
      },
      resolve(parent, args) {
        // Lógica estática: crea un objeto y haz .push() al array
        const nuevoParticipante = {
          ci: args.ci,
          nombres: args.nombres,
          primerApellido: args.primerApellido,
          segundoApellido: args.segundoApellido,
          sexo: args.sexo
        };
        participantesDB.push(nuevoParticipante);
        return nuevoParticipante; 
      }
    }
  }
});


module.exports = new GraphQLSchema({
  query: RootQuery,
  mutation: Mutation
});