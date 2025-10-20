using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using MySql.Data.MySqlClient;


namespace Cotizar
{
    public class CotizacionDAO
    {
        public decimal ObtenerPorFecha(string fecha)
        {
            decimal cotizacion = 0.0m;

            // Usamos nuestra clase de conexion
            using (MySqlConnection con = ConexionBD.ObtenerConexion())
            {
                try
                {
                    con.Open();
                    string query = "SELECT Cotizacion FROM cotizaciones WHERE Fecha = @fecha";
                    MySqlCommand cmd = new MySqlCommand(query, con);
                    cmd.Parameters.AddWithValue("@fecha", fecha);

                    object result = cmd.ExecuteScalar();

                    if (result != null && result != DBNull.Value)
                    {
                        cotizacion = Convert.ToDecimal(result);
                    }
                }
                catch (Exception ex)
                {
                    // Aquí podrías registrar el error
                    cotizacion = 0.0m;
                }
            } // "using" se encarga de cerrar la conexión (con.Close())
            return cotizacion;
        }

        // Método para la lógica de REGISTRAR cotización
        public string RegistrarNueva(string fecha, decimal monto)
        {
            string mensaje = "";
            using (MySqlConnection con = ConexionBD.ObtenerConexion())
            {
                try
                {
                    con.Open();
                    string query = "INSERT INTO cotizaciones (Fecha, Cotizacion, Cotizacion_oficial) VALUES (@fecha, @monto, 6.97)";
                    MySqlCommand cmd = new MySqlCommand(query, con);
                    cmd.Parameters.AddWithValue("@fecha", fecha);
                    cmd.Parameters.AddWithValue("@monto", monto);

                    int rowsAffected = cmd.ExecuteNonQuery();

                    if (rowsAffected > 0)
                    {
                        mensaje = "La cotización fue registrada.";
                    }
                    else
                    {
                        mensaje = "No se pudo registrar la cotizacion.";
                    }
                }
                catch (MySqlException ex)
                {
                    if (ex.Number == 1062)
                    {
                        mensaje = "fecha ya registrada.";
                    }
                    else
                    {
                        mensaje = "Error de base de datos: " + ex.Message;
                    }
                }
                catch (Exception ex)
                {
                    mensaje = "Error general: " + ex.Message;
                }
            } // "using" se encarga de cerrar la conexión
            return mensaje;
        }
    }
}