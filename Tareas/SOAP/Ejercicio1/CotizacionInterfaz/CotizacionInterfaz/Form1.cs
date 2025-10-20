using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CotizacionInterfaz
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnObtener1_Click(object sender, EventArgs e)
        {
            // 1. Crear una instancia del cliente del servicio web
            ServicioCotizaciones.WebService1SoapClient cliente = new ServicioCotizaciones.WebService1SoapClient();

            // 2. Formatear la fecha del DateTimePicker al formato 'yyyy-MM-dd'
            string fechaSeleccionada = dtpFecha1.Value.ToString("yyyy-MM-dd");

            try
            {
                // 3. Llamar al método del servicio web
                decimal cotizacion = cliente.obtenerCotizacion(fechaSeleccionada);

                // 4. Mostrar el resultado
                if (cotizacion == 0.0m)
                {
                    txtResultado1.Text = "No se encontró cotización para esta fecha.";
                }
                else
                {
                    txtResultado1.Text = cotizacion.ToString();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al conectar con el servicio: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

        }

        private void btnRegistrar1_Click(object sender, EventArgs e)
        {
            if (!decimal.TryParse(txtMontoRegistro1.Text, out decimal monto))
            {
                MessageBox.Show("Por favor, ingrese un monto válido.", "Entrada inválida", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            // 2. Crear una instancia del cliente del servicio
            ServicioCotizaciones.WebService1SoapClient cliente = new ServicioCotizaciones.WebService1SoapClient();

            // 3. Formatear la fecha
            string fechaSeleccionada = dtpFecha1.Value.ToString("yyyy-MM-dd");

            try
            {
                // 4. Llamar al método de registro del servicio
                string respuesta = cliente.registrarCotizacion(fechaSeleccionada, monto);

                // 5. Mostrar la respuesta del servicio
                MessageBox.Show(respuesta, "Resultado del Registro", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al conectar con el servicio: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

        }
    }
}
