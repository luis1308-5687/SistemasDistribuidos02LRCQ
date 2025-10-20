namespace SegipFrontend
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
            this.txtCI = new System.Windows.Forms.TextBox();
            this.btnBuscarCI = new System.Windows.Forms.Button();
            this.txtNombres = new System.Windows.Forms.TextBox();
            this.txtPrimerApellido = new System.Windows.Forms.TextBox();
            this.txtSegundoApellido = new System.Windows.Forms.TextBox();
            this.btnBuscarNombres = new System.Windows.Forms.Button();
            this.dataGridViewResultados = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewResultados)).BeginInit();
            this.SuspendLayout();
            // 
            // txtCI
            // 
            this.txtCI.Location = new System.Drawing.Point(428, 178);
            this.txtCI.Name = "txtCI";
            this.txtCI.Size = new System.Drawing.Size(100, 22);
            this.txtCI.TabIndex = 0;
            // 
            // btnBuscarCI
            // 
            this.btnBuscarCI.Location = new System.Drawing.Point(560, 178);
            this.btnBuscarCI.Name = "btnBuscarCI";
            this.btnBuscarCI.Size = new System.Drawing.Size(75, 23);
            this.btnBuscarCI.TabIndex = 1;
            this.btnBuscarCI.Text = "Buscar CI";
            this.btnBuscarCI.UseVisualStyleBackColor = true;
            this.btnBuscarCI.Click += new System.EventHandler(this.btnBuscarCI_Click);
            // 
            // txtNombres
            // 
            this.txtNombres.Location = new System.Drawing.Point(156, 125);
            this.txtNombres.Name = "txtNombres";
            this.txtNombres.Size = new System.Drawing.Size(100, 22);
            this.txtNombres.TabIndex = 2;
            // 
            // txtPrimerApellido
            // 
            this.txtPrimerApellido.Location = new System.Drawing.Point(156, 153);
            this.txtPrimerApellido.Name = "txtPrimerApellido";
            this.txtPrimerApellido.Size = new System.Drawing.Size(100, 22);
            this.txtPrimerApellido.TabIndex = 3;
            // 
            // txtSegundoApellido
            // 
            this.txtSegundoApellido.Location = new System.Drawing.Point(156, 181);
            this.txtSegundoApellido.Name = "txtSegundoApellido";
            this.txtSegundoApellido.Size = new System.Drawing.Size(100, 22);
            this.txtSegundoApellido.TabIndex = 4;
            // 
            // btnBuscarNombres
            // 
            this.btnBuscarNombres.Location = new System.Drawing.Point(280, 177);
            this.btnBuscarNombres.Name = "btnBuscarNombres";
            this.btnBuscarNombres.Size = new System.Drawing.Size(126, 23);
            this.btnBuscarNombres.TabIndex = 5;
            this.btnBuscarNombres.Text = "Buscar Nombre";
            this.btnBuscarNombres.UseVisualStyleBackColor = true;
            this.btnBuscarNombres.Click += new System.EventHandler(this.btnBuscarNombres_Click);
            // 
            // dataGridViewResultados
            // 
            this.dataGridViewResultados.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewResultados.Location = new System.Drawing.Point(156, 251);
            this.dataGridViewResultados.Name = "dataGridViewResultados";
            this.dataGridViewResultados.RowHeadersWidth = 51;
            this.dataGridViewResultados.RowTemplate.Height = 24;
            this.dataGridViewResultados.Size = new System.Drawing.Size(479, 150);
            this.dataGridViewResultados.TabIndex = 6;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(87, 128);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(63, 16);
            this.label1.TabIndex = 7;
            this.label1.Text = "Nombres";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(51, 153);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(99, 16);
            this.label2.TabIndex = 8;
            this.label2.Text = "Primer Apellido";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(35, 185);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(115, 16);
            this.label3.TabIndex = 9;
            this.label3.Text = "Segundo Apellido";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.dataGridViewResultados);
            this.Controls.Add(this.btnBuscarNombres);
            this.Controls.Add(this.txtSegundoApellido);
            this.Controls.Add(this.txtPrimerApellido);
            this.Controls.Add(this.txtNombres);
            this.Controls.Add(this.btnBuscarCI);
            this.Controls.Add(this.txtCI);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewResultados)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox txtCI;
        private System.Windows.Forms.Button btnBuscarCI;
        private System.Windows.Forms.TextBox txtNombres;
        private System.Windows.Forms.TextBox txtPrimerApellido;
        private System.Windows.Forms.TextBox txtSegundoApellido;
        private System.Windows.Forms.Button btnBuscarNombres;
        private System.Windows.Forms.DataGridView dataGridViewResultados;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
    }
}

