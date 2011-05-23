namespace Beton.Forms
{
    partial class MatherialsForm
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
            this.matherialsPanel = new System.Windows.Forms.Panel();
            this.btnCancel = new System.Windows.Forms.Button();
            this.btnOK = new System.Windows.Forms.Button();
            this.matherialsGridView = new System.Windows.Forms.DataGridView();
            this.idDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.nameDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.densityDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.descriptionDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.orderPricePerTonnDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.orderPricePerCubeDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.matherialBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.matherialsPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.matherialsGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.matherialBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // matherialsPanel
            // 
            this.matherialsPanel.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.matherialsPanel.Controls.Add(this.btnCancel);
            this.matherialsPanel.Controls.Add(this.btnOK);
            this.matherialsPanel.Controls.Add(this.matherialsGridView);
            this.matherialsPanel.Location = new System.Drawing.Point(12, 12);
            this.matherialsPanel.Name = "matherialsPanel";
            this.matherialsPanel.Size = new System.Drawing.Size(885, 261);
            this.matherialsPanel.TabIndex = 0;
            // 
            // btnCancel
            // 
            this.btnCancel.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.btnCancel.Location = new System.Drawing.Point(807, 238);
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
            this.btnOK.Location = new System.Drawing.Point(726, 238);
            this.btnOK.Name = "btnOK";
            this.btnOK.Size = new System.Drawing.Size(75, 23);
            this.btnOK.TabIndex = 1;
            this.btnOK.Text = "OK";
            this.btnOK.UseVisualStyleBackColor = true;
            this.btnOK.Click += new System.EventHandler(this.btnClose_Click);
            // 
            // matherialsGridView
            // 
            this.matherialsGridView.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.matherialsGridView.AutoGenerateColumns = false;
            this.matherialsGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.matherialsGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.idDataGridViewTextBoxColumn,
            this.nameDataGridViewTextBoxColumn,
            this.densityDataGridViewTextBoxColumn,
            this.descriptionDataGridViewTextBoxColumn,
            this.orderPricePerTonnDataGridViewTextBoxColumn,
            this.orderPricePerCubeDataGridViewTextBoxColumn});
            this.matherialsGridView.DataSource = this.matherialBindingSource;
            this.matherialsGridView.Location = new System.Drawing.Point(3, 3);
            this.matherialsGridView.Name = "matherialsGridView";
            this.matherialsGridView.Size = new System.Drawing.Size(879, 229);
            this.matherialsGridView.TabIndex = 0;
            this.matherialsGridView.UserAddedRow += new System.Windows.Forms.DataGridViewRowEventHandler(this.matherialsGridView_UserAddedRow);
            this.matherialsGridView.RowEnter += new System.Windows.Forms.DataGridViewCellEventHandler(this.matherialsGridView_RowEnter);
            this.matherialsGridView.CellValidated += new System.Windows.Forms.DataGridViewCellEventHandler(this.matherialsGridView_CellValidated);
            this.matherialsGridView.RowsAdded += new System.Windows.Forms.DataGridViewRowsAddedEventHandler(this.matherialsGridView_RowsAdded);
            // 
            // idDataGridViewTextBoxColumn
            // 
            this.idDataGridViewTextBoxColumn.DataPropertyName = "Id";
            this.idDataGridViewTextBoxColumn.HeaderText = "#";
            this.idDataGridViewTextBoxColumn.Name = "idDataGridViewTextBoxColumn";
            this.idDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // nameDataGridViewTextBoxColumn
            // 
            this.nameDataGridViewTextBoxColumn.DataPropertyName = "Name";
            this.nameDataGridViewTextBoxColumn.HeaderText = "Наименование";
            this.nameDataGridViewTextBoxColumn.Name = "nameDataGridViewTextBoxColumn";
            // 
            // densityDataGridViewTextBoxColumn
            // 
            this.densityDataGridViewTextBoxColumn.DataPropertyName = "Density";
            this.densityDataGridViewTextBoxColumn.HeaderText = "Плотность (т/куб.м)";
            this.densityDataGridViewTextBoxColumn.Name = "densityDataGridViewTextBoxColumn";
            this.densityDataGridViewTextBoxColumn.Width = 150;
            // 
            // descriptionDataGridViewTextBoxColumn
            // 
            this.descriptionDataGridViewTextBoxColumn.DataPropertyName = "Description";
            this.descriptionDataGridViewTextBoxColumn.HeaderText = "Описание";
            this.descriptionDataGridViewTextBoxColumn.Name = "descriptionDataGridViewTextBoxColumn";
            // 
            // orderPricePerTonnDataGridViewTextBoxColumn
            // 
            this.orderPricePerTonnDataGridViewTextBoxColumn.DataPropertyName = "OrderPricePerTonn";
            this.orderPricePerTonnDataGridViewTextBoxColumn.HeaderText = "Цена за тонну";
            this.orderPricePerTonnDataGridViewTextBoxColumn.Name = "orderPricePerTonnDataGridViewTextBoxColumn";
            this.orderPricePerTonnDataGridViewTextBoxColumn.Width = 150;
            // 
            // orderPricePerCubeDataGridViewTextBoxColumn
            // 
            this.orderPricePerCubeDataGridViewTextBoxColumn.DataPropertyName = "OrderPricePerCube";
            this.orderPricePerCubeDataGridViewTextBoxColumn.HeaderText = "Цена за куб. м.";
            this.orderPricePerCubeDataGridViewTextBoxColumn.Name = "orderPricePerCubeDataGridViewTextBoxColumn";
            this.orderPricePerCubeDataGridViewTextBoxColumn.Width = 150;
            // 
            // matherialBindingSource
            // 
            this.matherialBindingSource.DataSource = typeof(Beton.Model.Matherial);
            // 
            // MatherialsForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(909, 285);
            this.Controls.Add(this.matherialsPanel);
            this.Name = "MatherialsForm";
            this.Text = "Материалы";
            this.Load += new System.EventHandler(this.MatherialsForm_Load);
            this.matherialsPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.matherialsGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.matherialBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel matherialsPanel;
        private System.Windows.Forms.DataGridView matherialsGridView;
        private System.Windows.Forms.BindingSource matherialBindingSource;
        private System.Windows.Forms.Button btnOK;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.DataGridViewTextBoxColumn idDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn nameDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn densityDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn descriptionDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn orderPricePerTonnDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn orderPricePerCubeDataGridViewTextBoxColumn;
    }
}