using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Configuration;
using MySql.Data.MySqlClient;


namespace Cotizar
{
    public class ConexionBD
    {
        public static MySqlConnection ObtenerConexion()
        {
            // Lee la cadena de conexión "MySQLConn" desde tu archivo Web.config
            string connectionString = ConfigurationManager.ConnectionStrings["MySQLConn"].ConnectionString;

            MySqlConnection con = new MySqlConnection(connectionString);
            return con;
        }
    }
}