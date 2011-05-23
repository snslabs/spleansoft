namespace Beton
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.btnMatherials = new System.Windows.Forms.Button();
            this.btnProducts = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // btnMatherials
            // 
            this.btnMatherials.Location = new System.Drawing.Point(12, 12);
            this.btnMatherials.Name = "btnMatherials";
            this.btnMatherials.Size = new System.Drawing.Size(183, 23);
            this.btnMatherials.TabIndex = 0;
            this.btnMatherials.Text = "Материалы";
            this.btnMatherials.UseVisualStyleBackColor = true;
            this.btnMatherials.Click += new System.EventHandler(this.button1_Click);
            // 
            // btnProducts
            // 
            this.btnProducts.Location = new System.Drawing.Point(12, 41);
            this.btnProducts.Name = "btnProducts";
            this.btnProducts.Size = new System.Drawing.Size(183, 23);
            this.btnProducts.TabIndex = 1;
            this.btnProducts.Text = "Продукты";
            this.btnProducts.UseVisualStyleBackColor = true;
            this.btnProducts.Click += new System.EventHandler(this.btnProducts_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(762, 251);
            this.Controls.Add(this.btnProducts);
            this.Controls.Add(this.btnMatherials);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btnMatherials;
        private System.Windows.Forms.Button btnProducts;
    }
}

