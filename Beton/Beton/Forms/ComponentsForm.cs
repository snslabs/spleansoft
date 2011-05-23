using System;
using System.Collections.Generic;
using System.Data;
using System.Windows.Forms;
using Beton.Model;

namespace Beton.Forms
{
    public partial class ComponentsForm : Form
    {

        private DataTable dataTable;
        private List<ProductComponent> productComponents;

        public List<ProductComponent> ProductComponents
        {
            get { return productComponents; }
            set { productComponents = new List<ProductComponent>(value); }
        }

        public ComponentsForm()
        {
            InitializeComponent();
            dataTable = new DataTable("Components");
            ProductComponent.PopulateDataTableSchema(dataTable);
            productComponentBindingSource.DataSource = dataTable;
        }

        private void ComponentsForm_Load(object sender, EventArgs e)
        {
            var matherialsDataTable = new DataTable();
            Matherial.PopulateDataTableSchema(matherialsDataTable);
            foreach (Matherial m in Directories.MATHERIALS)
            {
                matherialsDataTable.Rows.Add(m.ToObjectArray());
            }
            matherialBindingSource.DataSource = matherialsDataTable;

            
            dataTable.Clear();
            foreach (var pc in productComponents)
                dataTable.Rows.Add(pc.ToObjectArray());
            componentsDataGridView.Refresh();
            foreach(DataGridViewRow row in componentsDataGridView.Rows)
            {
                this.calculatePrice(row);
            }

        }

        private void btnOK_Click(object sender, EventArgs e)
        {
            updateCollection();
        }

        private void updateCollection()
        {
            productComponents.Clear();
            foreach (DataRow row in dataTable.Rows)
            {
                productComponents.Add(new ProductComponent(row.ItemArray));
            }
        }

        private void componentsDataGridView_CellValidated(object sender, DataGridViewCellEventArgs e)
        {
            calculatePrice(componentsDataGridView.Rows[e.RowIndex]);
        }

        private void componentsDataGridView_RowValidated(object sender, DataGridViewCellEventArgs e)
        {
            calculatePrice(componentsDataGridView.Rows[e.RowIndex]);
        }

        private void calculatePrice(DataGridViewRow row)
        {
            if (row.Cells[1].Value != null && row.Cells[1].Value != DBNull.Value)
            {
                Matherial matherial = Directories.getMatherialById((int) row.Cells[1].Value);
                row.Cells[4].Value = matherial.OrderPricePerTonn*
                                     ((decimal) (row.Cells[2].Value == DBNull.Value ? Decimal.Zero : row.Cells[2].Value));
            }
            else
            {
                row.Cells[4].Value = -1;
            }
        }

    }
}
