using System;
using System.Collections.Generic;
using System.Data;
using System.Windows.Forms;
using Beton.Model;

namespace Beton.Forms
{
    public partial class ProductsForm : Form
    {
        private ComponentsForm componentsForm;
        private readonly DataTable dataTable;
        private List<Product> products;
        public List<Product> Products { get { return products; }
            set{
                products = new List<Product>(value);
            } 
        }

        public ProductsForm()
        {
            InitializeComponent();
            componentsForm = new ComponentsForm();
            dataTable = new DataTable("Products");
            Product.PopulateDataTableSchema(dataTable);
            productsGridView.DataSource = dataTable;
        }

        private void ProductsForm_Load(object sender, EventArgs e)
        {

            reloadCollection();

        }

        private void reloadCollection()
        {
            dataTable.Clear();
            foreach (Product m in Products)
                dataTable.Rows.Add(m.ToObjectArray());
            productsGridView.Refresh();
        }

        private void productsGridView_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            DataGridViewRow row = productsGridView.Rows[e.RowIndex];
            //row.
            int componentsColumnIndex = 4;
            if(e.ColumnIndex == componentsColumnIndex)
            {
                Product selectedProduct = products[e.RowIndex];
                /*
                componentsForm.Text = "Состав продукта: " + selectedProduct.Name;
                componentsForm.ProductComponents = selectedProduct.Components;
                if(componentsForm.ShowDialog(this) == DialogResult.OK)
                {
                    // save changes to product
                    selectedProduct.Components.Clear();
                    selectedProduct.Components = componentsForm.ProductComponents;
                    reloadCollection();
                }
                 * */
                
            }
        }

        private void btnOK_Click(object sender, EventArgs e)
        {
            updateCollection();
        }

        private void updateCollection()
        {
            // products.Clear();
            foreach (DataRow row in dataTable.Rows)
            {
                foreach(Product p in products)
                {
                    if (p.Id == (int)row.ItemArray[0])
                    {
                        p.Name = row.ItemArray[1] as string;
                    }
                }
            }
            
        }

        private int GetNextMaterialId()
        {
            int max = 0;
            foreach (DataGridViewRow row in productsGridView.Rows)
            {
                var id = row.Cells[0].Value as int?;
                if (id != null && id > max) max = id ?? 0;
            }
            return max + 1;
        }

        private void productsGridView_RowEnter(object sender, DataGridViewCellEventArgs e)
        {
            DataGridViewCell cell = productsGridView.Rows[e.RowIndex].Cells[0];
            if (cell.Value == null)
            {
                cell.Value = GetNextMaterialId();
            }
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            products.Add(new Product(GetNextMaterialId(), "", new List<ProductComponent>()));
            reloadCollection();
        }

        private void productsGridView_RowValidated(object sender, DataGridViewCellEventArgs e)
        {
            updateCollection();
        }
    }
}
