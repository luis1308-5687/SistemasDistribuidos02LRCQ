namespace CotizacionInterfaz
{
    partial class Form1
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.label3 = new System.Windows.Forms.Label();
            this.dtpFecha1 = new System.Windows.Forms.DateTimePicker();
            this.btnObtener1 = new System.Windows.Forms.Button();
            this.txtResultado1 = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.txtMontoRegistro1 = new System.Windows.Forms.TextBox();
            this.btnRegistrar1 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(110, 62);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(139, 16);
            this.label3.TabIndex = 0;
            this.label3.Text = "Seleccione una fecha:";
            // 
            // dtpFecha1
            // 
            this.dtpFecha1.Location = new System.Drawing.Point(279, 62);
            this.dtpFecha1.Name = "dtpFecha1";
            this.dtpFecha1.Size = new System.Drawing.Size(200, 22);
            this.dtpFecha1.TabIndex = 1;
            // 
            // btnObtener1
            // 
            this.btnObtener1.Location = new System.Drawing.Point(145, 109);
            this.btnObtener1.Name = "btnObtener1";
            this.btnObtener1.Size = new System.Drawing.Size(75, 23);
            this.btnObtener1.TabIndex = 2;
            this.btnObtener1.Text = "Obtener";
            this.btnObtener1.UseVisualStyleBackColor = true;
            this.btnObtener1.Click += new System.EventHandler(this.btnObtener1_Click);
            // 
            // txtResultado1
            // 
            this.txtResultado1.Location = new System.Drawing.Point(297, 109);
            this.txtResultado1.Name = "txtResultado1";
            this.txtResultado1.Size = new System.Drawing.Size(100, 22);
            this.txtResultado1.TabIndex = 3;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(132, 209);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(99, 16);
            this.label4.TabIndex = 4;
            this.label4.Text = "RegistrarMonto";
            // 
            // txtMontoRegistro1
            // 
            this.txtMontoRegistro1.Location = new System.Drawing.Point(279, 202);
            this.txtMontoRegistro1.Name = "txtMontoRegistro1";
            this.txtMontoRegistro1.Size = new System.Drawing.Size(100, 22);
            this.txtMontoRegistro1.TabIndex = 5;
            // 
            // btnRegistrar1
            // 
            this.btnRegistrar1.Location = new System.Drawing.Point(154, 250);
            this.btnRegistrar1.Name = "btnRegistrar1";
            this.btnRegistrar1.Size = new System.Drawing.Size(75, 23);
            this.btnRegistrar1.TabIndex = 6;
            this.btnRegistrar1.Text = "Registrar";
            this.btnRegistrar1.UseVisualStyleBackColor = true;
            this.btnRegistrar1.Click += new System.EventHandler(this.btnRegistrar1_Click);
            // 
            // Form1
            // 
            this.ClientSize = new System.Drawing.Size(717, 457);
            this.Controls.Add(this.btnRegistrar1);
            this.Controls.Add(this.txtMontoRegistro1);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.txtResultado1);
            this.Controls.Add(this.btnObtener1);
            this.Controls.Add(this.dtpFecha1);
            this.Controls.Add(this.label3);
            this.Name = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.DateTimePicker dtpFecha;
        private System.Windows.Forms.Button btnObtener;
        private System.Windows.Forms.TextBox txtResultado;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox txtMontoRegistro;
        private System.Windows.Forms.Button btnRegistrar;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.DateTimePicker dtpFecha1;
        private System.Windows.Forms.Button btnObtener1;
        private System.Windows.Forms.TextBox txtResultado1;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txtMontoRegistro1;
        private System.Windows.Forms.Button btnRegistrar1;
    }
}

