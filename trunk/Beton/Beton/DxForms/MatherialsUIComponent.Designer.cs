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
            this.gridControl1 = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.matherialBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.colId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colName = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colPricePerTonn = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colPricePerCube = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colDensity = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colDescription = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colOrderPricePerTonn = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colOrderPricePerCube = new DevExpress.XtraGrid.Columns.GridColumn();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.matherialBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // gridControl1
            // 
            this.gridControl1.DataSource = this.matherialBindingSource;
            this.gridControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.gridControl1.Location = new System.Drawing.Point(0, 0);
            this.gridControl1.MainView = this.gridView1;
            this.gridControl1.Name = "gridControl1";
            this.gridControl1.Size = new System.Drawing.Size(566, 243);
            this.gridControl1.TabIndex = 0;
            this.gridControl1.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colId,
            this.colName,
            this.colPricePerTonn,
            this.colPricePerCube,
            this.colDensity,
            this.colDescription,
            this.colOrderPricePerTonn,
            this.colOrderPricePerCube});
            this.gridView1.GridControl = this.gridControl1;
            this.gridView1.Name = "gridView1";
            this.gridView1.OptionsView.NewItemRowPosition = DevExpress.XtraGrid.Views.Grid.NewItemRowPosition.Bottom;
            // 
            // matherialBindingSource
            // 
            this.matherialBindingSource.DataSource = typeof(Beton.Model.Matherial);
            // 
            // colId
            // 
            this.colId.Caption = "#";
            this.colId.FieldName = "Id";
            this.colId.Name = "colId";
            this.colId.Visible = true;
            this.colId.VisibleIndex = 0;
            // 
            // colName
            // 
            this.colName.Caption = "Наименование";
            this.colName.FieldName = "Name";
            this.colName.Name = "colName";
            this.colName.Visible = true;
            this.colName.VisibleIndex = 1;
            // 
            // colPricePerTonn
            // 
            this.colPricePerTonn.Caption = "Цена за тонну";
            this.colPricePerTonn.FieldName = "PricePerTonn";
            this.colPricePerTonn.Name = "colPricePerTonn";
            this.colPricePerTonn.OptionsColumn.ReadOnly = true;
            this.colPricePerTonn.Visible = true;
            this.colPricePerTonn.VisibleIndex = 2;
            // 
            // colPricePerCube
            // 
            this.colPricePerCube.Caption = "Цена за куб";
            this.colPricePerCube.FieldName = "PricePerCube";
            this.colPricePerCube.Name = "colPricePerCube";
            this.colPricePerCube.OptionsColumn.ReadOnly = true;
            this.colPricePerCube.Visible = true;
            this.colPricePerCube.VisibleIndex = 3;
            // 
            // colDensity
            // 
            this.colDensity.Caption = "Плотность т/куб";
            this.colDensity.FieldName = "Density";
            this.colDensity.Name = "colDensity";
            this.colDensity.Visible = true;
            this.colDensity.VisibleIndex = 4;
            // 
            // colDescription
            // 
            this.colDescription.Caption = "Описание";
            this.colDescription.FieldName = "Description";
            this.colDescription.Name = "colDescription";
            this.colDescription.Visible = true;
            this.colDescription.VisibleIndex = 5;
            // 
            // colOrderPricePerTonn
            // 
            this.colOrderPricePerTonn.Caption = "Закупочная цена за тонну";
            this.colOrderPricePerTonn.FieldName = "OrderPricePerTonn";
            this.colOrderPricePerTonn.Name = "colOrderPricePerTonn";
            this.colOrderPricePerTonn.Visible = true;
            this.colOrderPricePerTonn.VisibleIndex = 6;
            // 
            // colOrderPricePerCube
            // 
            this.colOrderPricePerCube.Caption = "Закупочная цена за куб";
            this.colOrderPricePerCube.FieldName = "OrderPricePerCube";
            this.colOrderPricePerCube.Name = "colOrderPricePerCube";
            this.colOrderPricePerCube.Visible = true;
            this.colOrderPricePerCube.VisibleIndex = 7;
            // 
            // MatherialsUIComponent
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.gridControl1);
            this.Name = "MatherialsUIComponent";
            this.Size = new System.Drawing.Size(566, 243);
            this.Load += new System.EventHandler(this.MatherialsUIComponent_Load);
            ((System.ComponentModel.ISupportInitialize)(this.gridControl1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.matherialBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private DevExpress.XtraGrid.GridControl gridControl1;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private System.Windows.Forms.BindingSource matherialBindingSource;
        private DevExpress.XtraGrid.Columns.GridColumn colId;
        private DevExpress.XtraGrid.Columns.GridColumn colName;
        private DevExpress.XtraGrid.Columns.GridColumn colPricePerTonn;
        private DevExpress.XtraGrid.Columns.GridColumn colPricePerCube;
        private DevExpress.XtraGrid.Columns.GridColumn colDensity;
        private DevExpress.XtraGrid.Columns.GridColumn colDescription;
        private DevExpress.XtraGrid.Columns.GridColumn colOrderPricePerTonn;
        private DevExpress.XtraGrid.Columns.GridColumn colOrderPricePerCube;
    }
}
