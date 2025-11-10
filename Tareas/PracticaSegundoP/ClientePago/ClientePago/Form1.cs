using ClientePago.PagosServiceRef;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClientePago
{
    public partial class Form1 : Form
    {
        private PagosServiceRef.PagosServiceSoapClient clienteSOAP;
        public Form1()
        {
            InitializeComponent();
            clienteSOAP = new PagosServiceRef.PagosServiceSoapClient();
            btnPagar.Enabled = false; 
        }

        private void btnObtener_Click(object sender, EventArgs e)
        {
            try
            {
                Deuda[] deudas = clienteSOAP.verDeuda(
                    txtCI.Text,
                    txtPrimerApellido.Text,
                    txtSegundoApellido.Text,
                    txtNombres.Text
                );

                dgvDeudas.DataSource = deudas;
                btnPagar.Enabled = true; 
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message, "Error de Validación", MessageBoxButtons.OK, MessageBoxIcon.Error);
                dgvDeudas.DataSource = null;
                btnPagar.Enabled = false;
            }
        }

        private void btnPagar_Click(object sender, EventArgs e)
        {
            if (dgvDeudas.CurrentRow == null)
            {
                MessageBox.Show("Por favor, seleccione una deuda de la lista para pagar.");
                return;
            }

            try
            {
                Deuda deudaSeleccionada = (Deuda)dgvDeudas.CurrentRow.DataBoundItem;

                clienteSOAP.PagarDeuda(deudaSeleccionada);

                MessageBox.Show("¡Pago realizado con éxito!");
                btnObtener.PerformClick();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al procesar el pago: " + ex.Message, "Error de Pago", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}
