using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

using System.Configuration;
using MySql.Data.MySqlClient;

namespace SegipBackend
{
    public class ConexionBD
    {
        public static MySqlConnection ObtenerConexion()
        {
            string cs = ConfigurationManager.ConnectionStrings["PersonasConn"].ConnectionString;
            return new MySqlConnection(cs);
        }
    }
}