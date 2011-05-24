﻿namespace Beton
{
    partial class MainForm
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
            this.btnSave = new System.Windows.Forms.Button();
            this.btnLoad = new System.Windows.Forms.Button();
            this.btnFinalCalculation = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // btnMatherials
            // 
            this.btnMatherials.Location = new System.Drawing.Point(12, 12);
            this.btnMatherials.Name = "btnMatherials";
            this.btnMatherials.Size = new System.Drawing.Size(265, 23);
            this.btnMatherials.TabIndex = 0;
            this.btnMatherials.Text = "Материалы";
            this.btnMatherials.UseVisualStyleBackColor = true;
            this.btnMatherials.Click += new System.EventHandler(this.button1_Click);
            // 
            // btnProducts
            // 
            this.btnProducts.Location = new System.Drawing.Point(12, 41);
            this.btnProducts.Name = "btnProducts";
            this.btnProducts.Size = new System.Drawing.Size(265, 23);
            this.btnProducts.TabIndex = 1;
            this.btnProducts.Text = "Продукты";
            this.btnProducts.UseVisualStyleBackColor = true;
            this.btnProducts.Click += new System.EventHandler(this.btnProducts_Click);
            // 
            // btnSave
            // 
            this.btnSave.Enabled = false;
            this.btnSave.Location = new System.Drawing.Point(12, 216);
            this.btnSave.Name = "btnSave";
            this.btnSave.Size = new System.Drawing.Size(265, 23);
            this.btnSave.TabIndex = 2;
            this.btnSave.Text = "Сохранить данные";
            this.btnSave.UseVisualStyleBackColor = true;
            this.btnSave.Click += new System.EventHandler(this.btnSave_Click);
            // 
            // btnLoad
            // 
            this.btnLoad.Enabled = false;
            this.btnLoad.Location = new System.Drawing.Point(12, 187);
            this.btnLoad.Name = "btnLoad";
            this.btnLoad.Size = new System.Drawing.Size(265, 23);
            this.btnLoad.TabIndex = 3;
            this.btnLoad.Text = "Загрузить данные";
            this.btnLoad.UseVisualStyleBackColor = true;
            this.btnLoad.Click += new System.EventHandler(this.btnLoad_Click);
            // 
            // btnFinalCalculation
            // 
            this.btnFinalCalculation.Location = new System.Drawing.Point(12, 70);
            this.btnFinalCalculation.Name = "btnFinalCalculation";
            this.btnFinalCalculation.Size = new System.Drawing.Size(265, 23);
            this.btnFinalCalculation.TabIndex = 4;
            this.btnFinalCalculation.Text = "Финальный расчёт";
            this.btnFinalCalculation.UseVisualStyleBackColor = true;
            this.btnFinalCalculation.Click += new System.EventHandler(this.btnFinalCalculation_Click);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(289, 251);
            this.Controls.Add(this.btnFinalCalculation);
            this.Controls.Add(this.btnLoad);
            this.Controls.Add(this.btnSave);
            this.Controls.Add(this.btnProducts);
            this.Controls.Add(this.btnMatherials);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "MainForm";
            this.Text = "Бетономешалка версия 1.0";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.MainForm_FormClosing);
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btnMatherials;
        private System.Windows.Forms.Button btnProducts;
        private System.Windows.Forms.Button btnSave;
        private System.Windows.Forms.Button btnLoad;
        private System.Windows.Forms.Button btnFinalCalculation;
    }
}
