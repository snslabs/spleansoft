using System;
using System.Collections.Generic;
using System.Data;
using System.Windows.Forms;
using Beton.Model;

namespace Beton.Forms
{
    public partial class MatherialsForm : Form
    {
        private List<Matherial> matherials;
        private DataTable dataTable = new DataTable("Matherials");

        public List<Matherial> Matherials
        {
            get { return matherials; }
            set { matherials = new List<Matherial>(value); }
        }

        public MatherialsForm()
        {
            InitializeComponent();
            dataTable = new DataTable("Matherials");
            Matherial.PopulateDataTableSchema(dataTable);
            this.matherialBindingSource.DataSource = dataTable;
            
        }

        private void updateCollection()
        {
            matherials.Clear();
            foreach(DataRow row in dataTable.Rows)
            {
                matherials.Add(new Matherial(row.ItemArray));
            }

        }

        private void MatherialsForm_Load(object sender, EventArgs e)
        {
            dataTable.Clear();
            foreach (Matherial m in matherials)
                dataTable.Rows.Add(m.ToObjectArray());
            matherialsGridView.Refresh();
        }

        private void btnClose_Click(object sender, EventArgs e)
        {
            // сохраняем изменения в коллекции
            updateCollection();
        }

        private void matherialsGridView_RowsAdded(object sender, DataGridViewRowsAddedEventArgs e)
        {
        }

        private void matherialsGridView_UserAddedRow(object sender, DataGridViewRowEventArgs e)
        {
            // e.Row.Cells[0].Value = GetNextMaterialId();
        }

        private int GetNextMaterialId()
        {
            int max = 0;
            foreach (DataGridViewRow row in matherialsGridView.Rows)
            {
                var id = row.Cells[0].Value as int?;
                if (id != null && id > max) max = id ?? 0;
            }
            return max + 1;
        }

        private void matherialsGridView_RowEnter(object sender, DataGridViewCellEventArgs e)
        {
            DataGridViewCell cell = matherialsGridView.Rows[e.RowIndex].Cells[0];
            if(cell.Value == null)
            {
                cell.Value = GetNextMaterialId();
            }
        }
    }
}
