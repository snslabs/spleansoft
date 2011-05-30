namespace Beton.DxForms
{
    partial class ContractCalculationUIComponent
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
            this.grid = new DevExpress.XtraGrid.GridControl();
            this.positionBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colProduct = new DevExpress.XtraGrid.Columns.GridColumn();
            this.repositoryItemGridLookUpEdit1 = new DevExpress.XtraEditors.Repository.RepositoryItemGridLookUpEdit();
            this.productBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.repositoryItemGridLookUpEdit1View = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colId1 = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colName = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colVolume = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colSelfPricePerCube = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colAddedPrice = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colFinalPrice = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colTotalPrice = new DevExpress.XtraGrid.Columns.GridColumn();
            this.teTotalVolume = new DevExpress.XtraEditors.TextEdit();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.teSelfSum = new DevExpress.XtraEditors.TextEdit();
            this.label3 = new System.Windows.Forms.Label();
            this.teTotalSum = new DevExpress.XtraEditors.TextEdit();
            ((System.ComponentModel.ISupportInitialize)(this.grid)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.positionBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit1View)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.teTotalVolume.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.teSelfSum.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.teTotalSum.Properties)).BeginInit();
            this.SuspendLayout();
            // 
            // grid
            // 
            this.grid.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.grid.DataSource = this.positionBindingSource;
            this.grid.Location = new System.Drawing.Point(0, 0);
            this.grid.MainView = this.gridView1;
            this.grid.Name = "grid";
            this.grid.RepositoryItems.AddRange(new DevExpress.XtraEditors.Repository.RepositoryItem[] {
            this.repositoryItemGridLookUpEdit1});
            this.grid.Size = new System.Drawing.Size(751, 261);
            this.grid.TabIndex = 0;
            this.grid.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            this.grid.Validated += new System.EventHandler(this.grid_Validated);
            // 
            // positionBindingSource
            // 
            this.positionBindingSource.DataSource = typeof(Beton.Model.Position);
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colId,
            this.colProduct,
            this.colVolume,
            this.colSelfPricePerCube,
            this.colAddedPrice,
            this.colFinalPrice,
            this.colTotalPrice});
            this.gridView1.GridControl = this.grid;
            this.gridView1.Name = "gridView1";
            this.gridView1.OptionsView.NewItemRowPosition = DevExpress.XtraGrid.Views.Grid.NewItemRowPosition.Bottom;
            this.gridView1.CellValueChanged += new DevExpress.XtraGrid.Views.Base.CellValueChangedEventHandler(this.gridView1_CellValueChanged);
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
            // 
            // colProduct
            // 
            this.colProduct.Caption = "Продукт";
            this.colProduct.ColumnEdit = this.repositoryItemGridLookUpEdit1;
            this.colProduct.FieldName = "Product";
            this.colProduct.Name = "colProduct";
            this.colProduct.Visible = true;
            this.colProduct.VisibleIndex = 1;
            // 
            // repositoryItemGridLookUpEdit1
            // 
            this.repositoryItemGridLookUpEdit1.AutoHeight = false;
            this.repositoryItemGridLookUpEdit1.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.repositoryItemGridLookUpEdit1.DataSource = this.productBindingSource;
            this.repositoryItemGridLookUpEdit1.DisplayMember = "Name";
            this.repositoryItemGridLookUpEdit1.Name = "repositoryItemGridLookUpEdit1";
            this.repositoryItemGridLookUpEdit1.NullText = "<Выберите продукт>";
            this.repositoryItemGridLookUpEdit1.View = this.repositoryItemGridLookUpEdit1View;
            // 
            // productBindingSource
            // 
            this.productBindingSource.DataSource = typeof(Beton.Model.Product);
            // 
            // repositoryItemGridLookUpEdit1View
            // 
            this.repositoryItemGridLookUpEdit1View.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colId1,
            this.colName});
            this.repositoryItemGridLookUpEdit1View.FocusRectStyle = DevExpress.XtraGrid.Views.Grid.DrawFocusRectStyle.RowFocus;
            this.repositoryItemGridLookUpEdit1View.Name = "repositoryItemGridLookUpEdit1View";
            this.repositoryItemGridLookUpEdit1View.OptionsSelection.EnableAppearanceFocusedCell = false;
            this.repositoryItemGridLookUpEdit1View.OptionsView.ShowGroupPanel = false;
            // 
            // colId1
            // 
            this.colId1.Caption = "#";
            this.colId1.FieldName = "Id";
            this.colId1.Name = "colId1";
            this.colId1.Visible = true;
            this.colId1.VisibleIndex = 0;
            // 
            // colName
            // 
            this.colName.Caption = "Наименование";
            this.colName.FieldName = "Name";
            this.colName.Name = "colName";
            this.colName.Visible = true;
            this.colName.VisibleIndex = 1;
            // 
            // colVolume
            // 
            this.colVolume.Caption = "Объём (куб)";
            this.colVolume.FieldName = "Volume";
            this.colVolume.Name = "colVolume";
            this.colVolume.Visible = true;
            this.colVolume.VisibleIndex = 2;
            // 
            // colSelfPricePerCube
            // 
            this.colSelfPricePerCube.Caption = "Себестоимость за куб";
            this.colSelfPricePerCube.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colSelfPricePerCube.FieldName = "SelfPricePerCube";
            this.colSelfPricePerCube.Name = "colSelfPricePerCube";
            this.colSelfPricePerCube.OptionsColumn.AllowEdit = false;
            this.colSelfPricePerCube.OptionsColumn.ReadOnly = true;
            this.colSelfPricePerCube.Visible = true;
            this.colSelfPricePerCube.VisibleIndex = 3;
            // 
            // colAddedPrice
            // 
            this.colAddedPrice.Caption = "Надбавка за куб";
            this.colAddedPrice.FieldName = "AddedPrice";
            this.colAddedPrice.Name = "colAddedPrice";
            this.colAddedPrice.Visible = true;
            this.colAddedPrice.VisibleIndex = 4;
            // 
            // colFinalPrice
            // 
            this.colFinalPrice.Caption = "Итоговая цена за куб";
            this.colFinalPrice.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colFinalPrice.FieldName = "FinalPrice";
            this.colFinalPrice.Name = "colFinalPrice";
            this.colFinalPrice.OptionsColumn.AllowEdit = false;
            this.colFinalPrice.OptionsColumn.ReadOnly = true;
            this.colFinalPrice.Visible = true;
            this.colFinalPrice.VisibleIndex = 5;
            // 
            // colTotalPrice
            // 
            this.colTotalPrice.Caption = "Итоговая стоимость";
            this.colTotalPrice.DisplayFormat.FormatString = "N2";
            this.colTotalPrice.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Custom;
            this.colTotalPrice.FieldName = "TotalPrice";
            this.colTotalPrice.Name = "colTotalPrice";
            this.colTotalPrice.OptionsColumn.AllowEdit = false;
            this.colTotalPrice.OptionsColumn.ReadOnly = true;
            this.colTotalPrice.Visible = true;
            this.colTotalPrice.VisibleIndex = 6;
            // 
            // teTotalVolume
            // 
            this.teTotalVolume.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.teTotalVolume.Enabled = false;
            this.teTotalVolume.Location = new System.Drawing.Point(64, 289);
            this.teTotalVolume.Name = "teTotalVolume";
            this.teTotalVolume.Size = new System.Drawing.Size(100, 20);
            this.teTotalVolume.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(14, 292);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(44, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Объём:";
            // 
            // label2
            // 
            this.label2.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(169, 292);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(88, 13);
            this.label2.TabIndex = 4;
            this.label2.Text = "Себестоимость:";
            // 
            // teSelfSum
            // 
            this.teSelfSum.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.teSelfSum.Enabled = false;
            this.teSelfSum.Location = new System.Drawing.Point(258, 289);
            this.teSelfSum.Name = "teSelfSum";
            this.teSelfSum.Size = new System.Drawing.Size(100, 20);
            this.teSelfSum.TabIndex = 3;
            // 
            // label3
            // 
            this.label3.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(364, 292);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(114, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Итоговая стоимость:";
            // 
            // teTotalSum
            // 
            this.teTotalSum.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.teTotalSum.Enabled = false;
            this.teTotalSum.Location = new System.Drawing.Point(484, 289);
            this.teTotalSum.Name = "teTotalSum";
            this.teTotalSum.Size = new System.Drawing.Size(100, 20);
            this.teTotalSum.TabIndex = 5;
            // 
            // ContractCalculationUIComponent
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.label3);
            this.Controls.Add(this.teTotalSum);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.teSelfSum);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.teTotalVolume);
            this.Controls.Add(this.grid);
            this.Name = "ContractCalculationUIComponent";
            this.Size = new System.Drawing.Size(751, 312);
            ((System.ComponentModel.ISupportInitialize)(this.grid)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.positionBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit1View)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.teTotalVolume.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.teSelfSum.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.teTotalSum.Properties)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DevExpress.XtraGrid.GridControl grid;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private System.Windows.Forms.BindingSource positionBindingSource;
        private DevExpress.XtraGrid.Columns.GridColumn colId;
        private DevExpress.XtraGrid.Columns.GridColumn colProduct;
        private DevExpress.XtraGrid.Columns.GridColumn colVolume;
        private DevExpress.XtraGrid.Columns.GridColumn colSelfPricePerCube;
        private DevExpress.XtraGrid.Columns.GridColumn colAddedPrice;
        private DevExpress.XtraGrid.Columns.GridColumn colFinalPrice;
        private DevExpress.XtraGrid.Columns.GridColumn colTotalPrice;
        private DevExpress.XtraEditors.Repository.RepositoryItemGridLookUpEdit repositoryItemGridLookUpEdit1;
        private System.Windows.Forms.BindingSource productBindingSource;
        private DevExpress.XtraGrid.Views.Grid.GridView repositoryItemGridLookUpEdit1View;
        private DevExpress.XtraGrid.Columns.GridColumn colId1;
        private DevExpress.XtraGrid.Columns.GridColumn colName;
        private DevExpress.XtraEditors.TextEdit teTotalVolume;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private DevExpress.XtraEditors.TextEdit teSelfSum;
        private System.Windows.Forms.Label label3;
        private DevExpress.XtraEditors.TextEdit teTotalSum;
    }
}
