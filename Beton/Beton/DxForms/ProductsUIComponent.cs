using System;
using System.ComponentModel;
using Beton.Behavior;
using Beton.Model;

namespace Beton.DxForms
{
    public partial class ProductsUIComponent : DevExpress.XtraEditors.XtraUserControl, IBetonComponent
    {
        public ProductsUIComponent()
        {
            InitializeComponent();
        }

        public bool SaveData()
        {
            return true;
        }

        public void LoadData()
        {
            productBindingSource.DataSource = Directories.PRODUCTS;
            matherialBindingSource.DataSource = Directories.MATHERIALS;
        }

        public string FormCaption
        {
            get { return "Продукты"; }
        }

        private void productBindingSource_AddingNew(object sender, AddingNewEventArgs e)
        {
            var product = (Product)(e.NewObject = new Product());
            int max = -1;
            foreach (var m in Directories.PRODUCTS)
            {
                max = m.Id > max ? m.Id : max;
            }
            product.Id = max + 1;

        }

        private void gridView2_InitNewRow(object sender, DevExpress.XtraGrid.Views.Grid.InitNewRowEventArgs e)
        {
            gridView2.SetRowCellValue(e.RowHandle, gridView2.Columns[0], 1);
            
        }

        private void gridControl1_Click(object sender, EventArgs e)
        {

        }
    }
}
