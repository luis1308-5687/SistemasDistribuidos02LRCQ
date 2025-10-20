using SegipFrontend.ServicioSegip;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace SegipFrontend
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnBuscarCI_Click(object sender, EventArgs e)
        {
            ServicioSegip.SegipSoapClient cliente = new ServicioSegip.SegipSoapClient();

            try
            {
                // 2. Llamamos al método
                Persona resultado = cliente.BuscarPersonaCI(txtCI.Text);

                // 3. Mostramos el resultado
                if (resultado != null)
                {
                    // Lo metemos en un array para que el DataGridView lo muestre
                    Persona[] listaTemporal = new Persona[] { resultado };
                    dataGridViewResultados.DataSource = listaTemporal;
                }
                else
                {
                    MessageBox.Show("Ninguna persona con ese CI.");
                    dataGridViewResultados.DataSource = null; // Limpiamos la tabla
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("error conectar con el servicio: " + ex.Message);
            }

        }

        private void btnBuscarNombres_Click(object sender, EventArgs e)
        {
            ServicioSegip.SegipSoapClient cliente = new ServicioSegip.SegipSoapClient();

            try
            {
                Persona[] resultados = cliente.BuscarPersonas(
                    txtPrimerApellido.Text,
                    txtSegundoApellido.Text,
                    txtNombres.Text
                );

                if (resultados != null && resultados.Length > 0)
                {
                    dataGridViewResultados.DataSource = resultados;
                }
                else
                {
                    MessageBox.Show("No se encontraron coincidencias.");
                    dataGridViewResultados.DataSource = null; 
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al conectar con el servicio: " + ex.Message);
            }

        }
    }
}
