﻿namespace Beton.DxForms
{
    partial class ProductsUIComponent
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
            DevExpress.XtraGrid.GridLevelNode gridLevelNode1 = new DevExpress.XtraGrid.GridLevelNode();
            this.gridView2 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colMatherial1 = new DevExpress.XtraGrid.Columns.GridColumn();
            this.repositoryItemGridLookUpEdit1 = new DevExpress.XtraEditors.Repository.RepositoryItemGridLookUpEdit();
            this.matherialBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.repositoryItemGridLookUpEdit1View = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colAmountTonn1 = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colAmountCube1 = new DevExpress.XtraGrid.Columns.GridColumn();
            this.gridControl1 = new DevExpress.XtraGrid.GridControl();
            this.productBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colName = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colPricePerTonn = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colPricePerCube = new DevExpress.XtraGrid.Columns.GridColumn();
            this.repositoryItemComboBox1 = new DevExpress.XtraEditors.Repository.RepositoryItemComboBox();
            this.componentsBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.gridBand1 = new DevExpress.XtraGrid.Views.BandedGrid.GridBand();
            this.colId1 = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colMatherial = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colAmountTonn = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colAmountCube = new DevExpress.XtraGrid.Columns.GridColumn();
            ((System.ComponentModel.ISupportInitialize)(this.gridView2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.matherialBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit1View)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemComboBox1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.componentsBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // gridView2
            // 
            this.gridView2.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colMatherial1,
            this.colAmountTonn1,
            this.colAmountCube1});
            this.gridView2.GridControl = this.gridControl1;
            this.gridView2.Name = "gridView2";
            this.gridView2.OptionsBehavior.AllowAddRows = DevExpress.Utils.DefaultBoolean.True;
            this.gridView2.OptionsBehavior.AllowDeleteRows = DevExpress.Utils.DefaultBoolean.True;
            this.gridView2.OptionsView.NewItemRowPosition = DevExpress.XtraGrid.Views.Grid.NewItemRowPosition.Top;
            this.gridView2.InitNewRow += new DevExpress.XtraGrid.Views.Grid.InitNewRowEventHandler(this.gridView2_InitNewRow);
            // 
            // colMatherial1
            // 
            this.colMatherial1.Caption = "Материал";
            this.colMatherial1.ColumnEdit = this.repositoryItemGridLookUpEdit1;
            this.colMatherial1.FieldName = "Matherial";
            this.colMatherial1.Name = "colMatherial1";
            this.colMatherial1.Visible = true;
            this.colMatherial1.VisibleIndex = 0;
            // 
            // repositoryItemGridLookUpEdit1
            // 
            this.repositoryItemGridLookUpEdit1.AutoHeight = false;
            this.repositoryItemGridLookUpEdit1.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.repositoryItemGridLookUpEdit1.DataSource = this.matherialBindingSource;
            this.repositoryItemGridLookUpEdit1.DisplayMember = "Name";
            this.repositoryItemGridLookUpEdit1.Name = "repositoryItemGridLookUpEdit1";
            this.repositoryItemGridLookUpEdit1.NullText = "<Выберите материал>";
            this.repositoryItemGridLookUpEdit1.View = this.repositoryItemGridLookUpEdit1View;
            // 
            // matherialBindingSource
            // 
            this.matherialBindingSource.DataSource = typeof(Beton.Model.Matherial);
            // 
            // repositoryItemGridLookUpEdit1View
            // 
            this.repositoryItemGridLookUpEdit1View.FocusRectStyle = DevExpress.XtraGrid.Views.Grid.DrawFocusRectStyle.RowFocus;
            this.repositoryItemGridLookUpEdit1View.Name = "repositoryItemGridLookUpEdit1View";
            this.repositoryItemGridLookUpEdit1View.OptionsSelection.EnableAppearanceFocusedCell = false;
            this.repositoryItemGridLookUpEdit1View.OptionsView.ShowGroupPanel = false;
            // 
            // colAmountTonn1
            // 
            this.colAmountTonn1.Caption = "Вес (тонн)";
            this.colAmountTonn1.FieldName = "AmountTonn";
            this.colAmountTonn1.Name = "colAmountTonn1";
            this.colAmountTonn1.Visible = true;
            this.colAmountTonn1.VisibleIndex = 1;
            // 
            // colAmountCube1
            // 
            this.colAmountCube1.Caption = "Объём (куб)";
            this.colAmountCube1.FieldName = "AmountCube";
            this.colAmountCube1.Name = "colAmountCube1";
            this.colAmountCube1.Visible = true;
            this.colAmountCube1.VisibleIndex = 2;
            // 
            // gridControl1
            // 
            this.gridControl1.DataMember = null;
            this.gridControl1.DataSource = this.productBindingSource;
            this.gridControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            gridLevelNode1.LevelTemplate = this.gridView2;
            gridLevelNode1.RelationName = "Components";
            this.gridControl1.LevelTree.Nodes.AddRange(new DevExpress.XtraGrid.GridLevelNode[] {
            gridLevelNode1});
            this.gridControl1.Location = new System.Drawing.Point(0, 0);
            this.gridControl1.MainView = this.gridView1;
            this.gridControl1.Name = "gridControl1";
            this.gridControl1.RepositoryItems.AddRange(new DevExpress.XtraEditors.Repository.RepositoryItem[] {
            this.repositoryItemComboBox1,
            this.repositoryItemGridLookUpEdit1});
            this.gridControl1.Size = new System.Drawing.Size(1131, 458);
            this.gridControl1.TabIndex = 0;
            this.gridControl1.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1,
            this.gridView2});
            this.gridControl1.Click += new System.EventHandler(this.gridControl1_Click);
            // 
            // productBindingSource
            // 
            this.productBindingSource.AllowNew = true;
            this.productBindingSource.DataSource = typeof(Beton.Model.Product);
            this.productBindingSource.AddingNew += new System.ComponentModel.AddingNewEventHandler(this.productBindingSource_AddingNew);
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colId,
            this.colName,
            this.colPricePerTonn,
            this.colPricePerCube});
            this.gridView1.GridControl = this.gridControl1;
            this.gridView1.Name = "gridView1";
            this.gridView1.OptionsView.EnableAppearanceEvenRow = true;
            this.gridView1.OptionsView.NewItemRowPosition = DevExpress.XtraGrid.Views.Grid.NewItemRowPosition.Bottom;
            // 
            // colId
            // 
            this.colId.Caption = "#";
            this.colId.FieldName = "Id";
            this.colId.Name = "colId";
            this.colId.OptionsColumn.AllowEdit = false;
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
            this.colPricePerTonn.OptionsColumn.AllowEdit = false;
            this.colPricePerTonn.OptionsColumn.ReadOnly = true;
            this.colPricePerTonn.Visible = true;
            this.colPricePerTonn.VisibleIndex = 2;
            // 
            // colPricePerCube
            // 
            this.colPricePerCube.Caption = "Цена за куб";
            this.colPricePerCube.FieldName = "PricePerCube";
            this.colPricePerCube.Name = "colPricePerCube";
            this.colPricePerCube.OptionsColumn.AllowEdit = false;
            this.colPricePerCube.OptionsColumn.ReadOnly = true;
            this.colPricePerCube.Visible = true;
            this.colPricePerCube.VisibleIndex = 3;
            // 
            // repositoryItemComboBox1
            // 
            this.repositoryItemComboBox1.AutoHeight = false;
            this.repositoryItemComboBox1.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.repositoryItemComboBox1.Name = "repositoryItemComboBox1";
            // 
            // componentsBindingSource
            // 
            this.componentsBindingSource.DataMember = "Components";
            this.componentsBindingSource.DataSource = this.productBindingSource;
            // 
            // gridBand1
            // 
            this.gridBand1.Caption = "gridBand1";
            this.gridBand1.Name = "gridBand1";
            // 
            // colId1
            // 
            this.colId1.FieldName = "Id";
            this.colId1.Name = "colId1";
            this.colId1.Visible = true;
            this.colId1.VisibleIndex = 0;
            // 
            // colMatherial
            // 
            this.colMatherial.FieldName = "Matherial";
            this.colMatherial.Name = "colMatherial";
            this.colMatherial.Visible = true;
            this.colMatherial.VisibleIndex = 1;
            // 
            // colAmountTonn
            // 
            this.colAmountTonn.FieldName = "AmountTonn";
            this.colAmountTonn.Name = "colAmountTonn";
            this.colAmountTonn.Visible = true;
            this.colAmountTonn.VisibleIndex = 2;
            // 
            // colAmountCube
            // 
            this.colAmountCube.FieldName = "AmountCube";
            this.colAmountCube.Name = "colAmountCube";
            this.colAmountCube.Visible = true;
            this.colAmountCube.VisibleIndex = 3;
            // 
            // ProductsUIComponent
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.gridControl1);
            this.Name = "ProductsUIComponent";
            this.Size = new System.Drawing.Size(1131, 458);
            ((System.ComponentModel.ISupportInitialize)(this.gridView2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.matherialBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit1View)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemComboBox1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.componentsBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private DevExpress.XtraGrid.GridControl gridControl1;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private System.Windows.Forms.BindingSource productBindingSource;
        private DevExpress.XtraGrid.Columns.GridColumn colId;
        private DevExpress.XtraGrid.Columns.GridColumn colName;
        private DevExpress.XtraGrid.Columns.GridColumn colPricePerTonn;
        private DevExpress.XtraGrid.Columns.GridColumn colPricePerCube;
        private DevExpress.XtraGrid.Views.BandedGrid.GridBand gridBand1;
        private DevExpress.XtraGrid.Columns.GridColumn colId1;
        private DevExpress.XtraGrid.Columns.GridColumn colMatherial;
        private DevExpress.XtraGrid.Columns.GridColumn colAmountTonn;
        private DevExpress.XtraGrid.Columns.GridColumn colAmountCube;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView2;
        private DevExpress.XtraEditors.Repository.RepositoryItemGridLookUpEdit repositoryItemGridLookUpEdit1;
        private DevExpress.XtraGrid.Views.Grid.GridView repositoryItemGridLookUpEdit1View;
        private DevExpress.XtraEditors.Repository.RepositoryItemComboBox repositoryItemComboBox1;
        private System.Windows.Forms.BindingSource matherialBindingSource;
        private System.Windows.Forms.BindingSource componentsBindingSource;
        private DevExpress.XtraGrid.Columns.GridColumn colMatherial1;
        private DevExpress.XtraGrid.Columns.GridColumn colAmountTonn1;
        private DevExpress.XtraGrid.Columns.GridColumn colAmountCube1;
    }
}