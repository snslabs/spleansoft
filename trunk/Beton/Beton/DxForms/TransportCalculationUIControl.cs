using System.ComponentModel;
using System.Windows.Forms;
using Beton.Behavior;
using Beton.Model;

namespace Beton.DxForms
{
    public partial class TransportCalculationUIControl : DevExpress.XtraEditors.XtraUserControl, IBetonComponent
    {
        private DataRefreshedEventHandler dataRefreshed;
        public DataRefreshedEventHandler DataRefreshedEventHandler
        {
            set
            {
                dataRefreshed += value;
            }
        }

        public TransportCalculationUIControl()
        {
            InitializeComponent();
        }

        public bool SaveData()
        {
            return true;
        }

        public void LoadData()
        {
            positionBindingSource.DataSource = Directories.POSITIONS;
            transportTypeBindingSource.DataSource = Directories.TRANSPORT_TYPES;
            transportPositionBindingSource.DataSource = Directories.TRANSPORT_POSITIONS;
        }

        public string FormCaption
        {
            get { return "Расчет транспортных расходов"; }
        }

        public void RefreshData()
        {
            transportPositionBindingSource.ResetBindings(false);
            positionBindingSource.ResetBindings(false);
            transportTypeBindingSource.ResetBindings(false);
            RecalculateTransportExpenses();
        }

        private void transportPositionBindingSource_AddingNew(object sender, AddingNewEventArgs e)
        {
            e.NewObject = new TransportPosition();
            
        }

        private void grid_Click(object sender, System.EventArgs e)
        {

        }

        private void gridView1_CellValueChanged(object sender, DevExpress.XtraGrid.Views.Base.CellValueChangedEventArgs e)
        {
            // recalculating remainings
            RecalculateTransportExpenses();
        }

        private void RecalculateTransportExpenses()
        {
            foreach (Position position in Directories.POSITIONS)
            {
                position.TransportExpenses = 0;
                position.TransportedAmount = 0;
                foreach (TransportPosition transPos in Directories.TRANSPORT_POSITIONS)
                {
                    if(transPos.Position == position)
                    {
                        position.TransportExpenses += transPos.PositionPrice;
                        position.TransportedAmount += transPos.Distance*transPos.Volume;

                    }

                }
            }
            dataRefreshed();
        }

        private void gridView1_KeyDown(object sender, System.Windows.Forms.KeyEventArgs e)
        {
            if(e.KeyCode == Keys.Delete)
            {
                if (MessageBox.Show("Вы уверены что хотите удалить строку?", "Удаление строки", MessageBoxButtons.YesNo, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2) == DialogResult.Yes)
                {
                    gridView1.DeleteRow(gridView1.FocusedRowHandle);
                    RecalculateTransportExpenses();
                }
            }
        }
    }
}
