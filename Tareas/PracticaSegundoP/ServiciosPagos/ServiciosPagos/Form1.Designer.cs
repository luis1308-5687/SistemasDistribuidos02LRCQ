namespace ServiciosPagos
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            label1 = new Label();
            label2 = new Label();
            label3 = new Label();
            label4 = new Label();
            textCI = new TextBox();
            textNombres = new TextBox();
            textPrimerApellido = new TextBox();
            textSegundoApellido = new TextBox();
            btnEmitir = new Button();
            label5 = new Label();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(241, 57);
            label1.Name = "label1";
            label1.Size = new Size(22, 20);
            label1.TabIndex = 0;
            label1.Text = "CI";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(241, 102);
            label2.Name = "label2";
            label2.Size = new Size(70, 20);
            label2.TabIndex = 1;
            label2.Text = "Nombres";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(202, 152);
            label3.Name = "label3";
            label3.Size = new Size(113, 20);
            label3.TabIndex = 2;
            label3.Text = "Primer Apellido";
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new Point(186, 193);
            label4.Name = "label4";
            label4.Size = new Size(129, 20);
            label4.TabIndex = 3;
            label4.Text = "Segundo Apellido";
            // 
            // textCI
            // 
            textCI.Location = new Point(339, 50);
            textCI.Name = "textCI";
            textCI.Size = new Size(125, 27);
            textCI.TabIndex = 5;
            // 
            // textNombres
            // 
            textNombres.Location = new Point(339, 102);
            textNombres.Name = "textNombres";
            textNombres.Size = new Size(125, 27);
            textNombres.TabIndex = 6;
            // 
            // textPrimerApellido
            // 
            textPrimerApellido.Location = new Point(339, 152);
            textPrimerApellido.Name = "textPrimerApellido";
            textPrimerApellido.Size = new Size(125, 27);
            textPrimerApellido.TabIndex = 7;
            // 
            // textSegundoApellido
            // 
            textSegundoApellido.Location = new Point(339, 193);
            textSegundoApellido.Name = "textSegundoApellido";
            textSegundoApellido.Size = new Size(125, 27);
            textSegundoApellido.TabIndex = 8;
            // 
            // btnEmitir
            // 
            btnEmitir.Location = new Point(359, 258);
            btnEmitir.Name = "btnEmitir";
            btnEmitir.Size = new Size(94, 29);
            btnEmitir.TabIndex = 9;
            btnEmitir.Text = "Obtener";
            btnEmitir.UseVisualStyleBackColor = true;
            btnEmitir.Click += btnEmitir_Click;
            // 
            // textBox5

            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new Point(378, 300);
            label5.Name = "label5";
            label5.Size = new Size(63, 20);
            label5.TabIndex = 11;
            label5.Text = "Detalles";
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(label5);
            Controls.Add(btnEmitir);
            Controls.Add(textSegundoApellido);
            Controls.Add(textPrimerApellido);
            Controls.Add(textNombres);
            Controls.Add(textCI);
            Controls.Add(label4);
            Controls.Add(label3);
            Controls.Add(label2);
            Controls.Add(label1);
            Name = "Form1";
            Text = "Form1";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label label1;
        private Label label2;
        private Label label3;
        private Label label4;
        private TextBox textCI;
        private TextBox textNombres;
        private TextBox textPrimerApellido;
        private TextBox textSegundoApellido;
        private Button btnEmitir;
        private Label label5;
    }
}
