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
    public partial class ContractCalculationUIComponent : DevExpress.XtraEditors.XtraUserControl, IPersistable
    {
        private BindingList<Position> positions;
        public BindingList<Position> Positions { 
            get
            {
                return positions;
            } 
            set
            {
                positions.Clear();
                foreach (Position pos in value)
                {
                    positions.Add(pos);
                }
            } 
        }
        public ContractCalculationUIComponent()
        {
            positions = new BindingList<Position>();
            InitializeComponent();
        }

        public bool SaveData()
        {
            Directories.UpdatePositions(positions);
            return true;
        }

        public void LoadData()
        {
            // 
            productBindingSource.DataSource = Directories.ALL_PRODUCTS;
            positions.Clear();
            foreach (Position pos in Directories.POSITIONS)
            {
                positions.Add(pos);
            }
            positionBindingSource.DataSource = positions;

        }


        private void grid_Validated(object sender, EventArgs e)
        {
            // MessageBox.Show("Fuck!");
        }

        private void gridView1_CellValueChanged(object sender, DevExpress.XtraGrid.Views.Base.CellValueChangedEventArgs e)
        {
            CalculateTotals();
        }

        private void CalculateTotals()
        {
            decimal totalVolume = 0;
            decimal totalSelfSum = 0;
            decimal totalFinalSum = 0;

            foreach (Position pos in positions)
            {
                totalVolume += pos.Volume;
                totalSelfSum += pos.SelfPricePerCube*pos.Volume;
                totalFinalSum += pos.TotalPrice;
            }

            teTotalVolume.Text = totalVolume.ToString("N2");
            teSelfSum.Text = totalSelfSum.ToString("N2");
            teTotalSum.Text = totalFinalSum.ToString("N2");

        }
    }
}
