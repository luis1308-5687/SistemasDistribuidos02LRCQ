namespace ServiciosPagos
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnEmitir_Click(object sender, EventArgs e)
        {
            string ci = textCI.Text.Trim();
            string nombres = textNombres.Text.Trim();
            string primerApellido = textPrimerApellido.Text.Trim();
            string segundoApellido = textSegundoApellido.Text.Trim();


        }
    }
}
