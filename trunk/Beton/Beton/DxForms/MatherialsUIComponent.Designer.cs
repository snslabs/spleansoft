namespace Beton.DxForms
{
    partial class MatherialsUIComponent
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.matherialBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.gridControl1 = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colName = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colDensity = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colDescription = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colOrderPricePerTonn = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colOrderPricePerCube = new DevExpress.XtraGrid.Columns.GridColumn();
            this.labelControl1 = new DevExpress.XtraEditors.LabelControl();
            ((System.ComponentModel.ISupportInitialize)(this.matherialBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // matherialBindingSource
            // 
            this.matherialBindingSource.AllowNew = true;
            this.matherialBindingSource.DataSource = typeof(Beton.Model.Matherial);
            this.matherialBindingSource.AddingNew += new System.ComponentModel.AddingNewEventHandler(this.matherialBindingSource_AddingNew);
            // 
            // gridControl1
            // 
            this.gridControl1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.gridControl1.DataSource = this.matherialBindingSource;
            this.gridControl1.Location = new System.Drawing.Point(0, 30);
            this.gridControl1.MainView = this.gridView1;
            this.gridControl1.Name = "gridControl1";
            this.gridControl1.Size = new System.Drawing.Size(566, 213);
            this.gridControl1.TabIndex = 0;
            this.gridControl1.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colId,
            this.colName,
            this.colDensity,
            this.colDescription,
            this.colOrderPricePerTonn,
            this.colOrderPricePerCube});
            this.gridView1.GridControl = this.gridControl1;
            this.gridView1.Name = "gridView1";
            this.gridView1.NewItemRowText = "Введите новый материал";
            this.gridView1.OptionsView.NewItemRowPosition = DevExpress.XtraGrid.Views.Grid.NewItemRowPosition.Bottom;
            this.gridView1.OptionsView.ShowGroupPanel = false;
            // 
            // colId
            // 
            this.colId.Caption = "#";
            this.colId.FieldName = "Id";
            this.colId.Name = "colId";
            this.colId.OptionsColumn.AllowEdit = false;
            this.colId.OptionsColumn.ReadOnly = true;
            this.colId.Visible = true;
            this.colId.VisibleIndex = 0;
            this.colId.Width = 35;
            // 
            // colName
            // 
            this.colName.Caption = "Наименование";
            this.colName.FieldName = "Name";
            this.colName.Name = "colName";
            this.colName.Visible = true;
            this.colName.VisibleIndex = 1;
            this.colName.Width = 102;
            // 
            // colDensity
            // 
            this.colDensity.Caption = "Плотность (т/куб)";
            this.colDensity.DisplayFormat.FormatString = "N4";
            this.colDensity.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colDensity.FieldName = "Density";
            this.colDensity.Name = "colDensity";
            this.colDensity.Visible = true;
            this.colDensity.VisibleIndex = 2;
            this.colDensity.Width = 102;
            // 
            // colDescription
            // 
            this.colDescription.Caption = "Описание";
            this.colDescription.FieldName = "Description";
            this.colDescription.Name = "colDescription";
            this.colDescription.Visible = true;
            this.colDescription.VisibleIndex = 3;
            this.colDescription.Width = 102;
            // 
            // colOrderPricePerTonn
            // 
            this.colOrderPricePerTonn.Caption = "Цена за тонну";
            this.colOrderPricePerTonn.DisplayFormat.FormatString = "N2";
            this.colOrderPricePerTonn.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colOrderPricePerTonn.FieldName = "OrderPricePerTonn";
            this.colOrderPricePerTonn.Name = "colOrderPricePerTonn";
            this.colOrderPricePerTonn.Visible = true;
            this.colOrderPricePerTonn.VisibleIndex = 4;
            this.colOrderPricePerTonn.Width = 102;
            // 
            // colOrderPricePerCube
            // 
            this.colOrderPricePerCube.Caption = "Цена за куб";
            this.colOrderPricePerCube.DisplayFormat.FormatString = "N2";
            this.colOrderPricePerCube.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colOrderPricePerCube.FieldName = "OrderPricePerCube";
            this.colOrderPricePerCube.Name = "colOrderPricePerCube";
            this.colOrderPricePerCube.Visible = true;
            this.colOrderPricePerCube.VisibleIndex = 5;
            this.colOrderPricePerCube.Width = 105;
            // 
            // labelControl1
            // 
            this.labelControl1.Appearance.Font = new System.Drawing.Font("Tahoma", 14F);
            this.labelControl1.Location = new System.Drawing.Point(3, 3);
            this.labelControl1.Name = "labelControl1";
            this.labelControl1.Size = new System.Drawing.Size(100, 23);
            this.labelControl1.TabIndex = 1;
            this.labelControl1.Text = "Материалы";
            // 
            // MatherialsUIComponent
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.labelControl1);
            this.Controls.Add(this.gridControl1);
            this.Name = "MatherialsUIComponent";
            this.Size = new System.Drawing.Size(566, 243);
            ((System.ComponentModel.ISupportInitialize)(this.matherialBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.BindingSource matherialBindingSource;
        private DevExpress.XtraGrid.GridControl gridControl1;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private DevExpress.XtraGrid.Columns.GridColumn colId;
        private DevExpress.XtraGrid.Columns.GridColumn colName;
        private DevExpress.XtraGrid.Columns.GridColumn colDensity;
        private DevExpress.XtraGrid.Columns.GridColumn colDescription;
        private DevExpress.XtraGrid.Columns.GridColumn colOrderPricePerTonn;
        private DevExpress.XtraGrid.Columns.GridColumn colOrderPricePerCube;
        private DevExpress.XtraEditors.LabelControl labelControl1;
    }
}
