using System.Data;
using Beton.Model;

namespace Beton.Forms
{
    partial class ComponentsForm
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
            this.components = new System.ComponentModel.Container();
            this.panel1 = new System.Windows.Forms.Panel();
            this.componentsDataGridView = new System.Windows.Forms.DataGridView();
            this.idDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.matherialDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewComboBoxColumn();
            this.matherialBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.amountTonnDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.amountCubeDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colPrice = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.productComponentBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.btnCancel = new System.Windows.Forms.Button();
            this.btnOK = new System.Windows.Forms.Button();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.componentsDataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.matherialBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productComponentBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.panel1.Controls.Add(this.componentsDataGridView);
            this.panel1.Controls.Add(this.btnCancel);
            this.panel1.Controls.Add(this.btnOK);
            this.panel1.Location = new System.Drawing.Point(12, 12);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(954, 369);
            this.panel1.TabIndex = 0;
            // 
            // componentsDataGridView
            // 
            this.componentsDataGridView.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.componentsDataGridView.AutoGenerateColumns = false;
            this.componentsDataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.componentsDataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.idDataGridViewTextBoxColumn,
            this.matherialDataGridViewTextBoxColumn,
            this.amountTonnDataGridViewTextBoxColumn,
            this.amountCubeDataGridViewTextBoxColumn,
            this.colPrice});
            this.componentsDataGridView.DataSource = this.productComponentBindingSource;
            this.componentsDataGridView.Location = new System.Drawing.Point(3, 3);
            this.componentsDataGridView.Name = "componentsDataGridView";
            this.componentsDataGridView.Size = new System.Drawing.Size(951, 334);
            this.componentsDataGridView.TabIndex = 2;
            this.componentsDataGridView.CellValidated += new System.Windows.Forms.DataGridViewCellEventHandler(this.componentsDataGridView_CellValidated);
            this.componentsDataGridView.RowValidated += new System.Windows.Forms.DataGridViewCellEventHandler(this.componentsDataGridView_RowValidated);
            // 
            // idDataGridViewTextBoxColumn
            // 
            this.idDataGridViewTextBoxColumn.DataPropertyName = "Id";
            this.idDataGridViewTextBoxColumn.HeaderText = "#";
            this.idDataGridViewTextBoxColumn.Name = "idDataGridViewTextBoxColumn";
            // 
            // matherialDataGridViewTextBoxColumn
            // 
            this.matherialDataGridViewTextBoxColumn.DataPropertyName = "Matherial";
            this.matherialDataGridViewTextBoxColumn.DataSource = this.matherialBindingSource;
            this.matherialDataGridViewTextBoxColumn.DisplayMember = "Name";
            this.matherialDataGridViewTextBoxColumn.HeaderText = "Материал";
            this.matherialDataGridViewTextBoxColumn.Name = "matherialDataGridViewTextBoxColumn";
            this.matherialDataGridViewTextBoxColumn.Resizable = System.Windows.Forms.DataGridViewTriState.True;
            this.matherialDataGridViewTextBoxColumn.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.Automatic;
            this.matherialDataGridViewTextBoxColumn.ValueMember = "Id";
            // 
            // matherialBindingSource
            // 
            this.matherialBindingSource.DataSource = typeof(Beton.Model.Matherial);
            // 
            // amountTonnDataGridViewTextBoxColumn
            // 
            this.amountTonnDataGridViewTextBoxColumn.DataPropertyName = "AmountTonn";
            this.amountTonnDataGridViewTextBoxColumn.HeaderText = "Количество на тонну";
            this.amountTonnDataGridViewTextBoxColumn.Name = "amountTonnDataGridViewTextBoxColumn";
            this.amountTonnDataGridViewTextBoxColumn.Width = 180;
            // 
            // amountCubeDataGridViewTextBoxColumn
            // 
            this.amountCubeDataGridViewTextBoxColumn.DataPropertyName = "AmountCube";
            this.amountCubeDataGridViewTextBoxColumn.HeaderText = "Количество на кубометр";
            this.amountCubeDataGridViewTextBoxColumn.Name = "amountCubeDataGridViewTextBoxColumn";
            this.amountCubeDataGridViewTextBoxColumn.Width = 180;
            // 
            // colPrice
            // 
            this.colPrice.DataPropertyName = "Price";
            this.colPrice.HeaderText = "Стоимость";
            this.colPrice.Name = "colPrice";
            this.colPrice.ReadOnly = true;
            // 
            // productComponentBindingSource
            // 
            this.productComponentBindingSource.DataSource = typeof(Beton.Model.ProductComponent);
            // 
            // btnCancel
            // 
            this.btnCancel.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.btnCancel.Location = new System.Drawing.Point(879, 343);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(75, 23);
            this.btnCancel.TabIndex = 1;
            this.btnCancel.Text = "Отмена";
            this.btnCancel.UseVisualStyleBackColor = true;
            // 
            // btnOK
            // 
            this.btnOK.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnOK.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.btnOK.Location = new System.Drawing.Point(798, 343);
            this.btnOK.Name = "btnOK";
            this.btnOK.Size = new System.Drawing.Size(75, 23);
            this.btnOK.TabIndex = 0;
            this.btnOK.Text = "OK";
            this.btnOK.UseVisualStyleBackColor = true;
            this.btnOK.Click += new System.EventHandler(this.btnOK_Click);
            // 
            // ComponentsForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(978, 393);
            this.Controls.Add(this.panel1);
            this.Name = "ComponentsForm";
            this.Text = "ComponentsForm";
            this.Activated += new System.EventHandler(this.ComponentsForm_Load);
            this.Load += new System.EventHandler(this.ComponentsForm_Load);
            this.panel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.componentsDataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.matherialBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productComponentBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.DataGridView componentsDataGridView;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.Button btnOK;
        private System.Windows.Forms.BindingSource productComponentBindingSource;
        private System.Windows.Forms.BindingSource matherialBindingSource;
        private System.Windows.Forms.DataGridViewTextBoxColumn idDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewComboBoxColumn matherialDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn amountTonnDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn amountCubeDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn colPrice;
    }
}