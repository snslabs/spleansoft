using System;
using System.Data;
using System.Windows.Forms;
using Beton.Model;

namespace Beton.Forms
{
    public partial class ContractForm : Form
    {
        public ContractForm()
        {
            InitializeComponent();
        }

        private void ContractForm_Load(object sender, EventArgs e)
        {
            var dataTable = new DataTable("Products");
            Product.PopulateDataTableSchema(dataTable);
            foreach (var product in Directories.PRODUCTS)
            {
                dataTable.Rows.Add(product.ToObjectArray());
            }
            productBindingSource.DataSource = dataTable;
        }
    }
}
