using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

using System;

namespace SegipBackend
{
    // La plantilla para nuestros datos
    public class Persona
    {
        public int id { get; set; }
        public string ci { get; set; }
        public string nombres { get; set; }
        public string primer_apellido { get; set; }
        public string segundo_apellido { get; set; }

        // Constructor vacío necesario para SOAP
        public Persona() { }
    }
}