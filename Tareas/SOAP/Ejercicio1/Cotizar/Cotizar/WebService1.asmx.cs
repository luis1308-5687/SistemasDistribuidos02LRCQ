using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;



namespace Cotizar
{
    /// <summary>
    /// Descripción breve de WebService1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
    // [System.Web.Script.Services.ScriptService]
    public class WebService1 : System.Web.Services.WebService
    {
        // 1. Creamos una instancia de nuestro especialista (el DAO)

        public readonly CotizacionDAO miDAO = new CotizacionDAO();

        [WebMethod]
        public decimal obtenerCotizacion(string fecha)
        {
            // 2. El recepcionista solo llama al especialista
            return miDAO.ObtenerPorFecha(fecha);
        }

        [WebMethod]
        public string registrarCotizacion(string fecha, decimal monto)
        {
            // 2. El recepcionista solo llama al especialista
            return miDAO.RegistrarNueva(fecha, monto);
        }
    }
}
