using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Configuration;
using System.Collections.Generic;

namespace SegipBackend
{

    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]

    public class Segip : System.Web.Services.WebService
    {
        private readonly PersonaDAO miPersonaDAO = new PersonaDAO();

        [WebMethod]
        public Persona BuscarPersonaCI(string NumeroDocumento)
        {
            return miPersonaDAO.BuscarPersonaCI(NumeroDocumento);
        }

        [WebMethod]
        public Persona[] BuscarPersonas(string PrimerApellido, string SegundoApellido, string Nombres)
        {
            return miPersonaDAO.BuscarPersonas(PrimerApellido, SegundoApellido, Nombres).ToArray();
        }
    }
}
