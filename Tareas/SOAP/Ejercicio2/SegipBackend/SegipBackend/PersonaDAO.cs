using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System;
using System.Collections.Generic; // ¡Importante para las listas!
using MySql.Data.MySqlClient;

namespace SegipBackend
{
    public class PersonaDAO
    {
        public Persona BuscarPersonaCI(string ci)
        {
            Persona personaEncontrada = null;
            using (MySqlConnection con = ConexionBD.ObtenerConexion())
            {
                try
                {
                    con.Open();
                    string query = "SELECT * FROM persona WHERE ci = @ci LIMIT 1";
                    MySqlCommand cmd = new MySqlCommand(query, con);
                    cmd.Parameters.AddWithValue("@ci", ci);

                    using (MySqlDataReader reader = cmd.ExecuteReader())
                    {
                        if (reader.Read()) 
                        {
                            personaEncontrada = new Persona();
                            personaEncontrada.id = reader.GetInt32("id");
                            personaEncontrada.ci = reader.GetString("ci");
                            personaEncontrada.nombres = reader.GetString("nombres");
                            personaEncontrada.primer_apellido = reader.GetString("primer_apellido");
                            personaEncontrada.segundo_apellido = reader.GetString("segundo_apellido");
                        }
                    }
                }
                catch (Exception ex) { /* Manejar error */ }
            }
            return personaEncontrada;
        }

        public List<Persona> BuscarPersonas(string primerApellido, string segundoApellido, string nombres)
        {
            List<Persona> listaPersonas = new List<Persona>();
            using (MySqlConnection con = ConexionBD.ObtenerConexion())
            {
                try
                {
                    con.Open();
                    string query = @"SELECT * FROM persona 
                                     WHERE primer_apellido LIKE @paterno 
                                     AND segundo_apellido LIKE @materno 
                                     AND nombres LIKE @nombres";

                    MySqlCommand cmd = new MySqlCommand(query, con);
                    cmd.Parameters.AddWithValue("@paterno", "%" + primerApellido + "%");
                    cmd.Parameters.AddWithValue("@materno", "%" + segundoApellido + "%");
                    cmd.Parameters.AddWithValue("@nombres", "%" + nombres + "%");

                    using (MySqlDataReader reader = cmd.ExecuteReader())
                    {
                        while (reader.Read()) 
                        {
                            Persona p = new Persona();
                            p.id = reader.GetInt32("id");
                            p.ci = reader.GetString("ci");
                            p.nombres = reader.GetString("nombres");
                            p.primer_apellido = reader.GetString("primer_apellido");
                            p.segundo_apellido = reader.GetString("segundo_apellido");
                            listaPersonas.Add(p);
                        }
                    }
                }
                catch (Exception ex) { /* Manejar error */ }
            }
            return listaPersonas;
        }
    }
}