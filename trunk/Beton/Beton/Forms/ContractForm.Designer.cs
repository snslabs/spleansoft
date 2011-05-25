namespace Beton.Forms
{
    partial class ContractForm
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
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle1 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle2 = new System.Windows.Forms.DataGridViewCellStyle();
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.positionsDataGrid = new System.Windows.Forms.DataGridView();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.Id = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colPositionName = new System.Windows.Forms.DataGridViewComboBoxColumn();
            this.colVolume = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colSelfPricePerCube = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colAddedPricePerCube = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colReleasePricePercCube = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colTotalSum = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.productBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.tbDistance = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.tbTotalVolume = new System.Windows.Forms.TextBox();
            this.tbTotalSum = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.positionsDataGrid)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // splitContainer1
            // 
            this.splitContainer1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer1.Location = new System.Drawing.Point(0, 0);
            this.splitContainer1.Name = "splitContainer1";
            this.splitContainer1.Orientation = System.Windows.Forms.Orientation.Horizontal;
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.Controls.Add(this.tbTotalSum);
            this.splitContainer1.Panel1.Controls.Add(this.label5);
            this.splitContainer1.Panel1.Controls.Add(this.tbTotalVolume);
            this.splitContainer1.Panel1.Controls.Add(this.label4);
            this.splitContainer1.Panel1.Controls.Add(this.label3);
            this.splitContainer1.Panel1.Controls.Add(this.tbDistance);
            this.splitContainer1.Panel1.Controls.Add(this.label1);
            this.splitContainer1.Panel1.Controls.Add(this.positionsDataGrid);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.Controls.Add(this.label2);
            this.splitContainer1.Panel2.Controls.Add(this.dataGridView1);
            this.splitContainer1.Size = new System.Drawing.Size(976, 486);
            this.splitContainer1.SplitterDistance = 325;
            this.splitContainer1.TabIndex = 0;
            // 
            // positionsDataGrid
            // 
            this.positionsDataGrid.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.positionsDataGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.positionsDataGrid.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.Id,
            this.colPositionName,
            this.colVolume,
            this.colSelfPricePerCube,
            this.colAddedPricePerCube,
            this.colReleasePricePercCube,
            this.colTotalSum});
            this.positionsDataGrid.Location = new System.Drawing.Point(3, 32);
            this.positionsDataGrid.Name = "positionsDataGrid";
            this.positionsDataGrid.Size = new System.Drawing.Size(968, 231);
            this.positionsDataGrid.TabIndex = 0;
            this.positionsDataGrid.UserAddedRow += new System.Windows.Forms.DataGridViewRowEventHandler(this.positionsDataGrid_UserAddedRow);
            this.positionsDataGrid.CellValidated += new System.Windows.Forms.DataGridViewCellEventHandler(this.positionsDataGrid_CellValidated);
            // 
            // dataGridView1
            // 
            this.dataGridView1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(3, 25);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.Size = new System.Drawing.Size(971, 127);
            this.dataGridView1.TabIndex = 0;
            // 
            // Id
            // 
            this.Id.HeaderText = "#";
            this.Id.Name = "Id";
            this.Id.ReadOnly = true;
            this.Id.Width = 50;
            // 
            // colPositionName
            // 
            this.colPositionName.DataSource = this.productBindingSource;
            this.colPositionName.DisplayMember = "Name";
            this.colPositionName.HeaderText = "Наименование";
            this.colPositionName.Name = "colPositionName";
            this.colPositionName.ValueMember = "Id";
            this.colPositionName.Width = 250;
            // 
            // colVolume
            // 
            this.colVolume.HeaderText = "Объём";
            this.colVolume.Name = "colVolume";
            // 
            // colSelfPricePerCube
            // 
            this.colSelfPricePerCube.HeaderText = "Себестоимость за куб";
            this.colSelfPricePerCube.Name = "colSelfPricePerCube";
            this.colSelfPricePerCube.ReadOnly = true;
            // 
            // colAddedPricePerCube
            // 
            this.colAddedPricePerCube.HeaderText = "Надбавка за куб";
            this.colAddedPricePerCube.Name = "colAddedPricePerCube";
            // 
            // colReleasePricePercCube
            // 
            dataGridViewCellStyle1.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleRight;
            dataGridViewCellStyle1.Format = "N2";
            dataGridViewCellStyle1.NullValue = null;
            this.colReleasePricePercCube.DefaultCellStyle = dataGridViewCellStyle1;
            this.colReleasePricePercCube.HeaderText = "Отпускная цена за куб";
            this.colReleasePricePercCube.Name = "colReleasePricePercCube";
            this.colReleasePricePercCube.ReadOnly = true;
            // 
            // colTotalSum
            // 
            dataGridViewCellStyle2.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleRight;
            dataGridViewCellStyle2.Format = "N2";
            dataGridViewCellStyle2.NullValue = null;
            this.colTotalSum.DefaultCellStyle = dataGridViewCellStyle2;
            this.colTotalSum.HeaderText = "Общая сумма позиции";
            this.colTotalSum.Name = "colTotalSum";
            this.colTotalSum.ReadOnly = true;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(3, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(54, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "Позиции:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 9);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(64, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Транспорт:";
            // 
            // productBindingSource
            // 
            this.productBindingSource.DataSource = typeof(Beton.Model.Product);
            // 
            // tbDistance
            // 
            this.tbDistance.Location = new System.Drawing.Point(81, 269);
            this.tbDistance.Name = "tbDistance";
            this.tbDistance.Size = new System.Drawing.Size(100, 20);
            this.tbDistance.TabIndex = 2;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(5, 272);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(70, 13);
            this.label3.TabIndex = 3;
            this.label3.Text = "Расстояние:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(256, 272);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(81, 13);
            this.label4.TabIndex = 5;
            this.label4.Text = "Общий объём:";
            // 
            // tbTotalVolume
            // 
            this.tbTotalVolume.Location = new System.Drawing.Point(343, 269);
            this.tbTotalVolume.Name = "tbTotalVolume";
            this.tbTotalVolume.ReadOnly = true;
            this.tbTotalVolume.Size = new System.Drawing.Size(100, 20);
            this.tbTotalVolume.TabIndex = 6;
            // 
            // tbTotalSum
            // 
            this.tbTotalSum.Location = new System.Drawing.Point(740, 269);
            this.tbTotalSum.Name = "tbTotalSum";
            this.tbTotalSum.ReadOnly = true;
            this.tbTotalSum.Size = new System.Drawing.Size(100, 20);
            this.tbTotalSum.TabIndex = 8;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(653, 272);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(81, 13);
            this.label5.TabIndex = 7;
            this.label5.Text = "Общая сумма:";
            // 
            // ContractForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(976, 486);
            this.Controls.Add(this.splitContainer1);
            this.Name = "ContractForm";
            this.Text = "ContractForm";
            this.Load += new System.EventHandler(this.ContractForm_Load);
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel1.PerformLayout();
            this.splitContainer1.Panel2.ResumeLayout(false);
            this.splitContainer1.Panel2.PerformLayout();
            this.splitContainer1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.positionsDataGrid)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.DataGridView positionsDataGrid;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.BindingSource productBindingSource;
        private System.Windows.Forms.DataGridViewTextBoxColumn Id;
        private System.Windows.Forms.DataGridViewComboBoxColumn colPositionName;
        private System.Windows.Forms.DataGridViewTextBoxColumn colVolume;
        private System.Windows.Forms.DataGridViewTextBoxColumn colSelfPricePerCube;
        private System.Windows.Forms.DataGridViewTextBoxColumn colAddedPricePerCube;
        private System.Windows.Forms.DataGridViewTextBoxColumn colReleasePricePercCube;
        private System.Windows.Forms.DataGridViewTextBoxColumn colTotalSum;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox tbDistance;
        private System.Windows.Forms.TextBox tbTotalSum;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox tbTotalVolume;
    }
}