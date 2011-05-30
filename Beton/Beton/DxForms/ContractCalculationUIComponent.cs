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
        }
    }
}
