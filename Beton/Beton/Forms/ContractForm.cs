using System;
using System.Collections.Generic;
using System.Data;
using System.Windows.Forms;
using Beton.Model;

namespace Beton.Forms
{
    public partial class ContractForm : Form
    {
        private List<Position> positions = new List<Position>();
        private DataTable positionsDataTable;
        public ContractForm()
        {
            InitializeComponent();
            positionsDataTable = new DataTable("Positions");
            Position.PopulateDataTableSchema(positionsDataTable);
        }

        private void ContractForm_Load(object sender, EventArgs e)
        {
        }

        private void positionsDataGrid_CellValidated(object sender, DataGridViewCellEventArgs e)
        {
            DataGridViewRow row = ((DataGridView)sender).Rows[e.RowIndex];
            object productIdValue = row.Cells[1].Value;
            if (productIdValue != null && productIdValue != DBNull.Value)
            {
                int productId = (int)productIdValue;
                Product product = Directories.FindProductById(productId);
                decimal volume = decimal.Parse(row.Cells[2].Value == null ? "0" : row.Cells[2].Value.ToString());
                decimal addedPrice = decimal.Parse(row.Cells[4].Value == null ? "0" : row.Cells[4].Value.ToString());
                decimal finalPrice = addedPrice + product.PricePerCube;

                row.Cells[3].Value = product.PricePerCube;
                row.Cells[5].Value = addedPrice;
                row.Cells[5].Value = finalPrice;
                row.Cells[6].Value = finalPrice * volume;

                UpdateModels();

            }
            else
            {
                row.Cells[3].Value = null;
                row.Cells[4].Value = null;
                row.Cells[5].Value = null;
                row.Cells[6].Value = null;
            }
            CalculateTotals();

        }

        private void UpdateModels()
        {
            positions.Clear();
            foreach (DataGridViewRow row in positionsDataGrid.Rows)
            {
                if (row.Cells[1].Value != null)
                {
                    if (positions.Count <= row.Index)
                    {
                        Position position = new Position(
                            (int) (row.Cells[0].Value),
                            Directories.FindProductById((int) row.Cells[1].Value),
                            decimal.Parse((row.Cells[2].Value ?? "0").ToString()),
                            decimal.Parse((row.Cells[3].Value ?? "0").ToString()),
                            decimal.Parse((row.Cells[4].Value ?? "0").ToString())
                            );
                        positions.Add(position);
                        // positionsDataTable.Rows.Add(position.ToObjectArray());
                    }
                    else
                    {
                        positions[row.Index].Update(
                            (int) (row.Cells[0].Value),
                            Directories.FindProductById((int) row.Cells[1].Value),
                            decimal.Parse((row.Cells[2].Value ?? "0").ToString()),
                            decimal.Parse((row.Cells[3].Value ?? "0").ToString()),
                            decimal.Parse((row.Cells[4].Value ?? "0").ToString())
                            );
                    }
                }
            }
            while(positionsDataGrid.Rows.Count < positions.Count)
            {
                positions.RemoveAt(positions.Count - 1);
            }
        }

        private void CalculateTotals()
        {
            decimal totalVolume = 0;
            decimal totalSumm = 0;
            foreach (var position in positions)
            {
                totalVolume += position.Volume;
                totalSumm += position.TotalPrice;
            }
            tbTotalVolume.Text = totalVolume.ToString("N2");
            tbTotalSum.Text = totalSumm.ToString("N2");
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void positionsDataGrid_UserAddedRow(object sender, DataGridViewRowEventArgs e)
        {
            int i = 1;
            foreach (DataGridViewRow row in positionsDataGrid.Rows)
            {
                row.Cells[0].Value = i++;
            }
        }
    }
}
