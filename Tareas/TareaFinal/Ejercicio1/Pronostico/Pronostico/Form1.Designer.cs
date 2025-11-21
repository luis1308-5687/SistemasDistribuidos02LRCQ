namespace Pronostico
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
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.txtId = new System.Windows.Forms.TextBox();
            this.txtfecha = new System.Windows.Forms.TextBox();
            this.txtCantidad = new System.Windows.Forms.TextBox();
            this.btnCargar = new System.Windows.Forms.Button();
            this.btnCrear = new System.Windows.Forms.Button();
            this.btnActualizar = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.rbREST = new System.Windows.Forms.RadioButton();
            this.rbGraphQL = new System.Windows.Forms.RadioButton();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(64, 254);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowHeadersWidth = 51;
            this.dataGridView1.RowTemplate.Height = 24;
            this.dataGridView1.Size = new System.Drawing.Size(602, 150);
            this.dataGridView1.TabIndex = 0;
            // 
            // txtId
            // 
            this.txtId.Location = new System.Drawing.Point(406, 42);
            this.txtId.Name = "txtId";
            this.txtId.Size = new System.Drawing.Size(100, 22);
            this.txtId.TabIndex = 3;
            // 
            // txtfecha
            // 
            this.txtfecha.Location = new System.Drawing.Point(406, 83);
            this.txtfecha.Name = "txtfecha";
            this.txtfecha.Size = new System.Drawing.Size(100, 22);
            this.txtfecha.TabIndex = 4;
            // 
            // txtCantidad
            // 
            this.txtCantidad.Location = new System.Drawing.Point(406, 128);
            this.txtCantidad.Name = "txtCantidad";
            this.txtCantidad.Size = new System.Drawing.Size(100, 22);
            this.txtCantidad.TabIndex = 5;
            // 
            // btnCargar
            // 
            this.btnCargar.Location = new System.Drawing.Point(591, 31);
            this.btnCargar.Name = "btnCargar";
            this.btnCargar.Size = new System.Drawing.Size(75, 42);
            this.btnCargar.TabIndex = 6;
            this.btnCargar.Text = "Cargar lista";
            this.btnCargar.UseVisualStyleBackColor = true;
            this.btnCargar.Click += new System.EventHandler(this.btnCargar_Click);
            // 
            // btnCrear
            // 
            this.btnCrear.Location = new System.Drawing.Point(591, 82);
            this.btnCrear.Name = "btnCrear";
            this.btnCrear.Size = new System.Drawing.Size(75, 49);
            this.btnCrear.TabIndex = 7;
            this.btnCrear.Text = "Crear Nuevo";
            this.btnCrear.UseVisualStyleBackColor = true;
            this.btnCrear.Click += new System.EventHandler(this.btnCrear_Click);
            // 
            // btnActualizar
            // 
            this.btnActualizar.Location = new System.Drawing.Point(591, 137);
            this.btnActualizar.Name = "btnActualizar";
            this.btnActualizar.Size = new System.Drawing.Size(75, 23);
            this.btnActualizar.TabIndex = 8;
            this.btnActualizar.Text = "Actualizar";
            this.btnActualizar.UseVisualStyleBackColor = true;
            this.btnActualizar.Click += new System.EventHandler(this.btnActualizar_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(334, 42);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(18, 16);
            this.label1.TabIndex = 9;
            this.label1.Text = "Id";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(334, 82);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(45, 16);
            this.label2.TabIndex = 10;
            this.label2.Text = "Fecha";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(334, 128);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(61, 16);
            this.label3.TabIndex = 11;
            this.label3.Text = "Cantidad";
            // 
            // rbREST
            // 
            this.rbREST.AutoSize = true;
            this.rbREST.Checked = true;
            this.rbREST.Location = new System.Drawing.Point(43, 40);
            this.rbREST.Name = "rbREST";
            this.rbREST.Size = new System.Drawing.Size(56, 20);
            this.rbREST.TabIndex = 0;
            this.rbREST.TabStop = true;
            this.rbREST.Text = "Rest";
            this.rbREST.UseVisualStyleBackColor = true;
            // 
            // rbGraphQL
            // 
            this.rbGraphQL.AutoSize = true;
            this.rbGraphQL.Location = new System.Drawing.Point(43, 80);
            this.rbGraphQL.Name = "rbGraphQL";
            this.rbGraphQL.Size = new System.Drawing.Size(74, 20);
            this.rbGraphQL.TabIndex = 12;
            this.rbGraphQL.TabStop = true;
            this.rbGraphQL.Text = "GraplQl";
            this.rbGraphQL.UseVisualStyleBackColor = true;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.rbGraphQL);
            this.Controls.Add(this.rbREST);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.btnActualizar);
            this.Controls.Add(this.btnCrear);
            this.Controls.Add(this.btnCargar);
            this.Controls.Add(this.txtCantidad);
            this.Controls.Add(this.txtfecha);
            this.Controls.Add(this.txtId);
            this.Controls.Add(this.dataGridView1);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.TextBox txtId;
        private System.Windows.Forms.TextBox txtfecha;
        private System.Windows.Forms.TextBox txtCantidad;
        private System.Windows.Forms.Button btnCargar;
        private System.Windows.Forms.Button btnCrear;
        private System.Windows.Forms.Button btnActualizar;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.RadioButton rbREST;
        private System.Windows.Forms.RadioButton rbGraphQL;
    }
}

