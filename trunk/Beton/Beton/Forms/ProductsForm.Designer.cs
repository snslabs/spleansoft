namespace Beton.Forms
{
    partial class ProductsForm
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
            this.btnAdd = new System.Windows.Forms.Button();
            this.btnCancel = new System.Windows.Forms.Button();
            this.btnOK = new System.Windows.Forms.Button();
            this.productsGridView = new System.Windows.Forms.DataGridView();
            this.productBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.idDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.nameDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.pricePerCubeDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.pricePerTonnDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colComponents = new System.Windows.Forms.DataGridViewButtonColumn();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.productsGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.panel1.Controls.Add(this.btnAdd);
            this.panel1.Controls.Add(this.btnCancel);
            this.panel1.Controls.Add(this.btnOK);
            this.panel1.Controls.Add(this.productsGridView);
            this.panel1.Location = new System.Drawing.Point(12, 12);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(965, 344);
            this.panel1.TabIndex = 0;
            // 
            // btnAdd
            // 
            this.btnAdd.Location = new System.Drawing.Point(0, 321);
            this.btnAdd.Name = "btnAdd";
            this.btnAdd.Size = new System.Drawing.Size(75, 23);
            this.btnAdd.TabIndex = 3;
            this.btnAdd.Text = "Добавить";
            this.btnAdd.UseVisualStyleBackColor = true;
            this.btnAdd.Click += new System.EventHandler(this.btnAdd_Click);
            // 
            // btnCancel
            // 
            this.btnCancel.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.btnCancel.Location = new System.Drawing.Point(890, 321);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(75, 23);
            this.btnCancel.TabIndex = 2;
            this.btnCancel.Text = "Отмена";
            this.btnCancel.UseVisualStyleBackColor = true;
            // 
            // btnOK
            // 
            this.btnOK.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnOK.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.btnOK.Location = new System.Drawing.Point(809, 321);
            this.btnOK.Name = "btnOK";
            this.btnOK.Size = new System.Drawing.Size(75, 23);
            this.btnOK.TabIndex = 1;
            this.btnOK.Text = "OK";
            this.btnOK.UseVisualStyleBackColor = true;
            this.btnOK.Click += new System.EventHandler(this.btnOK_Click);
            // 
            // productsGridView
            // 
            this.productsGridView.AllowUserToAddRows = false;
            this.productsGridView.AllowUserToResizeRows = false;
            this.productsGridView.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.productsGridView.AutoGenerateColumns = false;
            this.productsGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.productsGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.idDataGridViewTextBoxColumn,
            this.nameDataGridViewTextBoxColumn,
            this.pricePerCubeDataGridViewTextBoxColumn,
            this.pricePerTonnDataGridViewTextBoxColumn,
            this.colComponents});
            this.productsGridView.DataSource = this.productBindingSource;
            this.productsGridView.Location = new System.Drawing.Point(0, 3);
            this.productsGridView.Name = "productsGridView";
            this.productsGridView.Size = new System.Drawing.Size(962, 297);
            this.productsGridView.TabIndex = 0;
            this.productsGridView.RowEnter += new System.Windows.Forms.DataGridViewCellEventHandler(this.productsGridView_RowEnter);
            this.productsGridView.RowValidated += new System.Windows.Forms.DataGridViewCellEventHandler(this.productsGridView_RowValidated);
            this.productsGridView.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.productsGridView_CellContentClick);
            // 
            // productBindingSource
            // 
            this.productBindingSource.DataSource = typeof(Beton.Model.Product);
            // 
            // idDataGridViewTextBoxColumn
            // 
            this.idDataGridViewTextBoxColumn.DataPropertyName = "Id";
            this.idDataGridViewTextBoxColumn.HeaderText = "#";
            this.idDataGridViewTextBoxColumn.Name = "idDataGridViewTextBoxColumn";
            // 
            // nameDataGridViewTextBoxColumn
            // 
            this.nameDataGridViewTextBoxColumn.DataPropertyName = "Name";
            this.nameDataGridViewTextBoxColumn.HeaderText = "Наименование";
            this.nameDataGridViewTextBoxColumn.Name = "nameDataGridViewTextBoxColumn";
            this.nameDataGridViewTextBoxColumn.Width = 200;
            // 
            // pricePerCubeDataGridViewTextBoxColumn
            // 
            this.pricePerCubeDataGridViewTextBoxColumn.DataPropertyName = "PricePerCube";
            this.pricePerCubeDataGridViewTextBoxColumn.HeaderText = "Цена за кубометр";
            this.pricePerCubeDataGridViewTextBoxColumn.Name = "pricePerCubeDataGridViewTextBoxColumn";
            this.pricePerCubeDataGridViewTextBoxColumn.ReadOnly = true;
            this.pricePerCubeDataGridViewTextBoxColumn.Width = 140;
            // 
            // pricePerTonnDataGridViewTextBoxColumn
            // 
            this.pricePerTonnDataGridViewTextBoxColumn.DataPropertyName = "PricePerTonn";
            this.pricePerTonnDataGridViewTextBoxColumn.HeaderText = "Цена за тонну";
            this.pricePerTonnDataGridViewTextBoxColumn.Name = "pricePerTonnDataGridViewTextBoxColumn";
            this.pricePerTonnDataGridViewTextBoxColumn.ReadOnly = true;
            this.pricePerTonnDataGridViewTextBoxColumn.Width = 140;
            // 
            // colComponents
            // 
            this.colComponents.HeaderText = "Состав";
            this.colComponents.Name = "colComponents";
            this.colComponents.ReadOnly = true;
            this.colComponents.Text = "Изменить";
            // 
            // ProductsForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(989, 368);
            this.Controls.Add(this.panel1);
            this.Name = "ProductsForm";
            this.Text = "Продукты";
            this.Load += new System.EventHandler(this.ProductsForm_Load);
            this.panel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.productsGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.DataGridView productsGridView;
        private System.Windows.Forms.BindingSource productBindingSource;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.Button btnOK;
        private System.Windows.Forms.Button btnAdd;
        private System.Windows.Forms.DataGridViewTextBoxColumn idDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn nameDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn pricePerCubeDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn pricePerTonnDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewButtonColumn colComponents;
    }
}