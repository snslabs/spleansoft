using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Text;
using System.Windows.Forms;
using Beton.Behavior;
using Beton.Model;
using DevExpress.XtraEditors;

namespace Beton.DxForms
{
    public partial class ProductsUIComponent : DevExpress.XtraEditors.XtraUserControl, IPersistable
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
