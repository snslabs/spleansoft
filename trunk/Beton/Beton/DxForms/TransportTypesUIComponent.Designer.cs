namespace Beton.DxForms
{
    partial class TransportTypesUIComponent
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
            this.panelControl1 = new DevExpress.XtraEditors.PanelControl();
            this.grid = new DevExpress.XtraGrid.GridControl();
            this.transportTypeBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.gridView = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colName = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colPricePerCube = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colPricePerTonn = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colPricePerTrip = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colMaxVolume = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colMaxWeight = new DevExpress.XtraGrid.Columns.GridColumn();
            this.labelControl1 = new DevExpress.XtraEditors.LabelControl();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl1)).BeginInit();
            this.panelControl1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.grid)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.transportTypeBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView)).BeginInit();
            this.SuspendLayout();
            // 
            // panelControl1
            // 
            this.panelControl1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.panelControl1.Controls.Add(this.grid);
            this.panelControl1.Location = new System.Drawing.Point(0, 30);
            this.panelControl1.Name = "panelControl1";
            this.panelControl1.Size = new System.Drawing.Size(792, 224);
            this.panelControl1.TabIndex = 0;
            // 
            // grid
            // 
            this.grid.DataSource = this.transportTypeBindingSource;
            this.grid.Dock = System.Windows.Forms.DockStyle.Fill;
            this.grid.Location = new System.Drawing.Point(2, 2);
            this.grid.MainView = this.gridView;
            this.grid.Name = "grid";
            this.grid.Size = new System.Drawing.Size(788, 220);
            this.grid.TabIndex = 0;
            this.grid.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView});
            // 
            // transportTypeBindingSource
            // 
            this.transportTypeBindingSource.DataSource = typeof(Beton.Model.TransportType);
            this.transportTypeBindingSource.AddingNew += new System.ComponentModel.AddingNewEventHandler(this.transportTypeBindingSource_AddingNew);
            // 
            // gridView
            // 
            this.gridView.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colId,
            this.colName,
            this.colPricePerCube,
            this.colPricePerTonn,
            this.colPricePerTrip,
            this.colMaxVolume,
            this.colMaxWeight});
            this.gridView.GridControl = this.grid;
            this.gridView.Name = "gridView";
            this.gridView.NewItemRowText = "Добавить новый тип транспортировки";
            this.gridView.OptionsView.NewItemRowPosition = DevExpress.XtraGrid.Views.Grid.NewItemRowPosition.Bottom;
            this.gridView.OptionsView.ShowGroupPanel = false;
            this.gridView.KeyDown += new System.Windows.Forms.KeyEventHandler(this.gridView_KeyDown);
            // 
            // colId
            // 
            this.colId.AppearanceCell.BackColor = System.Drawing.Color.Gainsboro;
            this.colId.AppearanceCell.Options.UseBackColor = true;
            this.colId.Caption = "#";
            this.colId.FieldName = "Id";
            this.colId.Name = "colId";
            this.colId.OptionsColumn.AllowEdit = false;
            this.colId.OptionsColumn.ReadOnly = true;
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
            // colPricePerCube
            // 
            this.colPricePerCube.Caption = "Цена за куб*км";
            this.colPricePerCube.DisplayFormat.FormatString = "N2";
            this.colPricePerCube.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colPricePerCube.FieldName = "PricePerCube";
            this.colPricePerCube.Name = "colPricePerCube";
            this.colPricePerCube.Visible = true;
            this.colPricePerCube.VisibleIndex = 2;
            // 
            // colPricePerTonn
            // 
            this.colPricePerTonn.Caption = "Цена за тонну*км";
            this.colPricePerTonn.DisplayFormat.FormatString = "N2";
            this.colPricePerTonn.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colPricePerTonn.FieldName = "PricePerTonn";
            this.colPricePerTonn.Name = "colPricePerTonn";
            this.colPricePerTonn.Visible = true;
            this.colPricePerTonn.VisibleIndex = 3;
            // 
            // colPricePerTrip
            // 
            this.colPricePerTrip.Caption = "Цена за рейс";
            this.colPricePerTrip.DisplayFormat.FormatString = "N2";
            this.colPricePerTrip.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colPricePerTrip.FieldName = "PricePerTrip";
            this.colPricePerTrip.Name = "colPricePerTrip";
            this.colPricePerTrip.Visible = true;
            this.colPricePerTrip.VisibleIndex = 4;
            // 
            // colMaxVolume
            // 
            this.colMaxVolume.Caption = "Максимальный объём (куб)";
            this.colMaxVolume.DisplayFormat.FormatString = "N2";
            this.colMaxVolume.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colMaxVolume.FieldName = "MaxVolume";
            this.colMaxVolume.Name = "colMaxVolume";
            this.colMaxVolume.Visible = true;
            this.colMaxVolume.VisibleIndex = 5;
            // 
            // colMaxWeight
            // 
            this.colMaxWeight.Caption = "Максимальный вес (тонн)";
            this.colMaxWeight.DisplayFormat.FormatString = "N2";
            this.colMaxWeight.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colMaxWeight.FieldName = "MaxWeight";
            this.colMaxWeight.Name = "colMaxWeight";
            this.colMaxWeight.Visible = true;
            this.colMaxWeight.VisibleIndex = 6;
            // 
            // labelControl1
            // 
            this.labelControl1.Appearance.Font = new System.Drawing.Font("Tahoma", 14F);
            this.labelControl1.Location = new System.Drawing.Point(3, 3);
            this.labelControl1.Name = "labelControl1";
            this.labelControl1.Size = new System.Drawing.Size(205, 23);
            this.labelControl1.TabIndex = 1;
            this.labelControl1.Text = "Типы транспортировки";
            // 
            // TransportTypesUIComponent
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.labelControl1);
            this.Controls.Add(this.panelControl1);
            this.Name = "TransportTypesUIComponent";
            this.Size = new System.Drawing.Size(792, 255);
            ((System.ComponentModel.ISupportInitialize)(this.panelControl1)).EndInit();
            this.panelControl1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.grid)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.transportTypeBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DevExpress.XtraEditors.PanelControl panelControl1;
        private DevExpress.XtraGrid.GridControl grid;
        private System.Windows.Forms.BindingSource transportTypeBindingSource;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView;
        private DevExpress.XtraGrid.Columns.GridColumn colId;
        private DevExpress.XtraGrid.Columns.GridColumn colName;
        private DevExpress.XtraGrid.Columns.GridColumn colPricePerCube;
        private DevExpress.XtraGrid.Columns.GridColumn colPricePerTonn;
        private DevExpress.XtraGrid.Columns.GridColumn colPricePerTrip;
        private DevExpress.XtraGrid.Columns.GridColumn colMaxVolume;
        private DevExpress.XtraGrid.Columns.GridColumn colMaxWeight;
        private DevExpress.XtraEditors.LabelControl labelControl1;
    }
}
