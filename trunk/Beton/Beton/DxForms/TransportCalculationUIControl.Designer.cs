namespace Beton.DxForms
{
    partial class TransportCalculationUIControl
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
            this.transportPositionBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colPosition = new DevExpress.XtraGrid.Columns.GridColumn();
            this.repositoryItemGridLookUpEdit1 = new DevExpress.XtraEditors.Repository.RepositoryItemGridLookUpEdit();
            this.positionBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.repositoryItemGridLookUpEdit1View = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colId1 = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colPositionDisplayName = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colVolume = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colTransportType = new DevExpress.XtraGrid.Columns.GridColumn();
            this.repositoryItemGridLookUpEdit2 = new DevExpress.XtraEditors.Repository.RepositoryItemGridLookUpEdit();
            this.transportTypeBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.repositoryItemGridLookUpEdit2View = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colDistance = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colRatePerKm = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colRatePerTrip = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colTripCount = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colPositionPrice = new DevExpress.XtraGrid.Columns.GridColumn();
            ((System.ComponentModel.ISupportInitialize)(this.grid)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.transportPositionBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.positionBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit1View)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.transportTypeBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit2View)).BeginInit();
            this.SuspendLayout();
            // 
            // grid
            // 
            this.grid.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.grid.DataSource = this.transportPositionBindingSource;
            this.grid.Location = new System.Drawing.Point(0, 0);
            this.grid.MainView = this.gridView1;
            this.grid.Name = "grid";
            this.grid.RepositoryItems.AddRange(new DevExpress.XtraEditors.Repository.RepositoryItem[] {
            this.repositoryItemGridLookUpEdit1,
            this.repositoryItemGridLookUpEdit2});
            this.grid.Size = new System.Drawing.Size(834, 236);
            this.grid.TabIndex = 0;
            this.grid.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            this.grid.Click += new System.EventHandler(this.grid_Click);
            // 
            // transportPositionBindingSource
            // 
            this.transportPositionBindingSource.DataSource = typeof(Beton.Model.TransportPosition);
            this.transportPositionBindingSource.AddingNew += new System.ComponentModel.AddingNewEventHandler(this.transportPositionBindingSource_AddingNew);
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colId,
            this.colPosition,
            this.colVolume,
            this.colTransportType,
            this.colDistance,
            this.colRatePerKm,
            this.colRatePerTrip,
            this.colTripCount,
            this.colPositionPrice});
            this.gridView1.GridControl = this.grid;
            this.gridView1.Name = "gridView1";
            this.gridView1.NewItemRowText = "Введите новую транспортную позицию";
            this.gridView1.OptionsView.NewItemRowPosition = DevExpress.XtraGrid.Views.Grid.NewItemRowPosition.Bottom;
            this.gridView1.OptionsView.ShowFooter = true;
            this.gridView1.CellValueChanged += new DevExpress.XtraGrid.Views.Base.CellValueChangedEventHandler(this.gridView1_CellValueChanged);
            this.gridView1.KeyDown += new System.Windows.Forms.KeyEventHandler(this.gridView1_KeyDown);
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
            // colPosition
            // 
            this.colPosition.Caption = "Позиция контракта";
            this.colPosition.ColumnEdit = this.repositoryItemGridLookUpEdit1;
            this.colPosition.FieldName = "Position";
            this.colPosition.Name = "colPosition";
            this.colPosition.Visible = true;
            this.colPosition.VisibleIndex = 1;
            // 
            // repositoryItemGridLookUpEdit1
            // 
            this.repositoryItemGridLookUpEdit1.AutoHeight = false;
            this.repositoryItemGridLookUpEdit1.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.repositoryItemGridLookUpEdit1.DataSource = this.positionBindingSource;
            this.repositoryItemGridLookUpEdit1.DisplayMember = "PositionDisplayName";
            this.repositoryItemGridLookUpEdit1.Name = "repositoryItemGridLookUpEdit1";
            this.repositoryItemGridLookUpEdit1.NullText = "<Выберите позицию контракта>";
            this.repositoryItemGridLookUpEdit1.View = this.repositoryItemGridLookUpEdit1View;
            // 
            // positionBindingSource
            // 
            this.positionBindingSource.AllowNew = false;
            this.positionBindingSource.DataSource = typeof(Beton.Model.Position);
            // 
            // repositoryItemGridLookUpEdit1View
            // 
            this.repositoryItemGridLookUpEdit1View.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colId1,
            this.colPositionDisplayName});
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
            this.colId1.Width = 40;
            // 
            // colPositionDisplayName
            // 
            this.colPositionDisplayName.Caption = "Позиция";
            this.colPositionDisplayName.FieldName = "PositionDisplayName";
            this.colPositionDisplayName.Name = "colPositionDisplayName";
            this.colPositionDisplayName.Visible = true;
            this.colPositionDisplayName.VisibleIndex = 1;
            this.colPositionDisplayName.Width = 300;
            // 
            // colVolume
            // 
            this.colVolume.Caption = "Объём (куб)";
            this.colVolume.DisplayFormat.FormatString = "N2";
            this.colVolume.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colVolume.FieldName = "Volume";
            this.colVolume.Name = "colVolume";
            this.colVolume.Visible = true;
            this.colVolume.VisibleIndex = 2;
            // 
            // colTransportType
            // 
            this.colTransportType.Caption = "Тип транспортировки";
            this.colTransportType.ColumnEdit = this.repositoryItemGridLookUpEdit2;
            this.colTransportType.FieldName = "TransportType";
            this.colTransportType.Name = "colTransportType";
            this.colTransportType.Visible = true;
            this.colTransportType.VisibleIndex = 3;
            // 
            // repositoryItemGridLookUpEdit2
            // 
            this.repositoryItemGridLookUpEdit2.AutoHeight = false;
            this.repositoryItemGridLookUpEdit2.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.repositoryItemGridLookUpEdit2.DataSource = this.transportTypeBindingSource;
            this.repositoryItemGridLookUpEdit2.DisplayMember = "Name";
            this.repositoryItemGridLookUpEdit2.Name = "repositoryItemGridLookUpEdit2";
            this.repositoryItemGridLookUpEdit2.NullText = "<Выберите тип транспортировки>";
            this.repositoryItemGridLookUpEdit2.View = this.repositoryItemGridLookUpEdit2View;
            // 
            // transportTypeBindingSource
            // 
            this.transportTypeBindingSource.DataSource = typeof(Beton.Model.TransportType);
            // 
            // repositoryItemGridLookUpEdit2View
            // 
            this.repositoryItemGridLookUpEdit2View.FocusRectStyle = DevExpress.XtraGrid.Views.Grid.DrawFocusRectStyle.RowFocus;
            this.repositoryItemGridLookUpEdit2View.Name = "repositoryItemGridLookUpEdit2View";
            this.repositoryItemGridLookUpEdit2View.OptionsSelection.EnableAppearanceFocusedCell = false;
            this.repositoryItemGridLookUpEdit2View.OptionsView.ShowGroupPanel = false;
            // 
            // colDistance
            // 
            this.colDistance.Caption = "Расстояние (км)";
            this.colDistance.DisplayFormat.FormatString = "N2";
            this.colDistance.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colDistance.FieldName = "Distance";
            this.colDistance.Name = "colDistance";
            this.colDistance.Visible = true;
            this.colDistance.VisibleIndex = 7;
            // 
            // colRatePerKm
            // 
            this.colRatePerKm.Caption = "Цена (руб / куб*км)";
            this.colRatePerKm.DisplayFormat.FormatString = "N2";
            this.colRatePerKm.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colRatePerKm.FieldName = "RatePerKm";
            this.colRatePerKm.Name = "colRatePerKm";
            this.colRatePerKm.OptionsColumn.AllowEdit = false;
            this.colRatePerKm.OptionsColumn.ReadOnly = true;
            this.colRatePerKm.Visible = true;
            this.colRatePerKm.VisibleIndex = 4;
            // 
            // colRatePerTrip
            // 
            this.colRatePerTrip.Caption = "Цена за рейс";
            this.colRatePerTrip.DisplayFormat.FormatString = "N2";
            this.colRatePerTrip.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colRatePerTrip.FieldName = "RatePerTrip";
            this.colRatePerTrip.Name = "colRatePerTrip";
            this.colRatePerTrip.OptionsColumn.AllowEdit = false;
            this.colRatePerTrip.OptionsColumn.ReadOnly = true;
            this.colRatePerTrip.Visible = true;
            this.colRatePerTrip.VisibleIndex = 5;
            // 
            // colTripCount
            // 
            this.colTripCount.Caption = "Рейсов";
            this.colTripCount.DisplayFormat.FormatString = "N0";
            this.colTripCount.FieldName = "TripCount";
            this.colTripCount.Name = "colTripCount";
            this.colTripCount.OptionsColumn.AllowEdit = false;
            this.colTripCount.OptionsColumn.ReadOnly = true;
            this.colTripCount.Visible = true;
            this.colTripCount.VisibleIndex = 6;
            // 
            // colPositionPrice
            // 
            this.colPositionPrice.Caption = "Стоимость";
            this.colPositionPrice.DisplayFormat.FormatString = "N2";
            this.colPositionPrice.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.colPositionPrice.FieldName = "PositionPrice";
            this.colPositionPrice.Name = "colPositionPrice";
            this.colPositionPrice.SummaryItem.SummaryType = DevExpress.Data.SummaryItemType.Sum;
            this.colPositionPrice.Visible = true;
            this.colPositionPrice.VisibleIndex = 8;
            // 
            // TransportCalculationUIControl
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.grid);
            this.Name = "TransportCalculationUIControl";
            this.Size = new System.Drawing.Size(834, 269);
            ((System.ComponentModel.ISupportInitialize)(this.grid)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.transportPositionBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.positionBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit1View)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.transportTypeBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.repositoryItemGridLookUpEdit2View)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private DevExpress.XtraGrid.GridControl grid;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private System.Windows.Forms.BindingSource transportPositionBindingSource;
        private DevExpress.XtraGrid.Columns.GridColumn colId;
        private DevExpress.XtraGrid.Columns.GridColumn colPosition;
        private DevExpress.XtraGrid.Columns.GridColumn colVolume;
        private DevExpress.XtraGrid.Columns.GridColumn colTransportType;
        private DevExpress.XtraGrid.Columns.GridColumn colRatePerKm;
        private DevExpress.XtraGrid.Columns.GridColumn colRatePerTrip;
        private DevExpress.XtraGrid.Columns.GridColumn colDistance;
        private DevExpress.XtraEditors.Repository.RepositoryItemGridLookUpEdit repositoryItemGridLookUpEdit1;
        private System.Windows.Forms.BindingSource positionBindingSource;
        private DevExpress.XtraGrid.Views.Grid.GridView repositoryItemGridLookUpEdit1View;
        private DevExpress.XtraEditors.Repository.RepositoryItemGridLookUpEdit repositoryItemGridLookUpEdit2;
        private System.Windows.Forms.BindingSource transportTypeBindingSource;
        private DevExpress.XtraGrid.Views.Grid.GridView repositoryItemGridLookUpEdit2View;
        private DevExpress.XtraGrid.Columns.GridColumn colId1;
        private DevExpress.XtraGrid.Columns.GridColumn colPositionDisplayName;
        private DevExpress.XtraGrid.Columns.GridColumn colTripCount;
        private DevExpress.XtraGrid.Columns.GridColumn colPositionPrice;
    }
}
