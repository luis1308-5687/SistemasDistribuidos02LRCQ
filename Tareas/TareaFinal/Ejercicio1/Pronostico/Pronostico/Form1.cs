using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net.Http;
using System.Net.Http.Headers;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace Pronostico
{

    public partial class Form1 : Form
    {

        public Form1()
        {
            InitializeComponent();
        }

        private const string URL_REST = "http://127.0.0.1:8000/api/pronosticos";
        private const string URL_GRAPHQL = "http://127.0.0.1:8000/graphql";

        private readonly HttpClient client = new HttpClient();


        private async void btnCargar_Click(object sender, EventArgs e)
        {
            try
            {
                if (rbREST.Checked)
                    await CargarDatosRest();
                else
                    await CargarDatosGraphQL();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message);
            }
        }



        private async Task CargarDatosRest()
        {
            try
            {
                var response = await client.GetStringAsync(URL_REST);

                var lista = JsonConvert.DeserializeObject<List<PronosticoDatos>>(response);

                if (lista == null || lista.Count == 0)
                {
                    MessageBox.Show("La lista está vacía o es NULL después de convertir.");
                    return;
                }

                dataGridView1.DataSource = null; 
                dataGridView1.AutoGenerateColumns = true;
                dataGridView1.DataSource = lista;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error CRÍTICO: " + ex.Message);
            }
        }

        private async Task CargarDatosGraphQL()
        {
            var queryObj = new { query = "{ pronosticos { id fecha cantidad_estimada } }" };
            var jsonBody = JsonConvert.SerializeObject(queryObj);
            var content = new StringContent(jsonBody, Encoding.UTF8, "application/json");

            var response = await client.PostAsync(URL_GRAPHQL, content);
            var responseString = await response.Content.ReadAsStringAsync();

            var jsonObject = JObject.Parse(responseString);
            var lista = jsonObject["data"]["pronosticos"].ToObject<List<PronosticoDatos>>();

            dataGridView1.DataSource = lista;
        }

        private async void btnCrear_Click(object sender, EventArgs e)
        {
            try
            {
                string fecha = txtfecha.Text;
                int cantidad = int.Parse(txtCantidad.Text);

                if (rbREST.Checked)
                {
                    var nuevo = new { fecha = fecha, cantidad_estimada = cantidad };
                    var json = JsonConvert.SerializeObject(nuevo);
                    var content = new StringContent(json, Encoding.UTF8, "application/json");
                    await client.PostAsync(URL_REST, content);
                }
                else
                {
                    var query = new
                    {
                        query = "mutation Create($f: String!, $c: Int!) { createPronostico(fecha: $f, cantidad_estimada: $c) { id } }",
                        variables = new { f = fecha, c = cantidad }
                    };
                    var json = JsonConvert.SerializeObject(query);
                    var content = new StringContent(json, Encoding.UTF8, "application/json");
                    await client.PostAsync(URL_GRAPHQL, content);
                }
                btnCargar_Click(sender, e);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al crear: " + ex.Message);
            }
        }

        private async void btnActualizar_Click(object sender, EventArgs e)
        {
            try
            {
                int id = int.Parse(txtId.Text);
                string fecha = txtfecha.Text;
                int cantidad = int.Parse(txtCantidad.Text);

                if (rbREST.Checked)
                {
                    var datos = new { fecha = fecha, cantidad_estimada = cantidad };
                    var json = JsonConvert.SerializeObject(datos);
                    var content = new StringContent(json, Encoding.UTF8, "application/json");
                    await client.PutAsync($"{URL_REST}/{id}", content);
                }
                else
                {
                    var query = new
                    {
                        query = "mutation Update($id: Int!, $f: String, $c: Int) { updatePronostico(id: $id, fecha: $f, cantidad_estimada: $c) { id } }",
                        variables = new { id = id, f = fecha, c = cantidad }
                    };
                    var json = JsonConvert.SerializeObject(query);
                    var content = new StringContent(json, Encoding.UTF8, "application/json");
                    await client.PostAsync(URL_GRAPHQL, content);
                }
                btnCargar_Click(sender, e);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al actualizar: " + ex.Message);
            }
        }

        private void dataGridView1_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                var row = dataGridView1.Rows[e.RowIndex];
                txtId.Text = row.Cells["id"].Value.ToString();
                txtfecha.Text = row.Cells["fecha"].Value.ToString();
                txtCantidad.Text = row.Cells["cantidad_estimada"].Value.ToString();
            }
        }


    }

    public class PronosticoDatos
    {
        public int id { get; set; }
        public string fecha { get; set; }
        public int cantidad_estimada { get; set; }
    }
}
