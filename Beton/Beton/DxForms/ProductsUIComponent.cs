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
    public partial class ProductsUIComponent : DevExpress.XtraEditors.XtraUserControl, Persistable
    {
        private readonly List<Product> products;
        private readonly List<Matherial> matherials;
        private List<Matherial> Matherials
        {
            get { return matherials; }
            set
            {
                matherials.Clear();
                matherials.AddRange(value);
            }
        }
        public List<Product> Products
        {
            get
            {
                return products;
            }
            set
            {
                products.Clear();
                products.AddRange(value);
            }
        }

        public ProductsUIComponent()
        {
            InitializeComponent();
            products = new List<Product>();
            matherials = new List<Matherial>();
            productBindingSource.DataSource = products;
            matherialBindingSource.DataSource = matherials;
        }

        public bool SaveData()
        {
            return true;
        }

        public void LoadData()
        {
            Products = Directories.PRODUCTS;
            Matherials = Directories.MATHERIALS;
        }

        private void productBindingSource_AddingNew(object sender, AddingNewEventArgs e)
        {
            var product = (Product)(e.NewObject = new Product());
            int max = -1;
            foreach (var m in products)
            {
                max = m.Id > max ? m.Id : max;
            }
            product.Id = max + 1;

        }
    }
}
