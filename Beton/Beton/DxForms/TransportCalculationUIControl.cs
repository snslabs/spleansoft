using System.ComponentModel;
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
            RecalculateTrnasportExpenses();
        }

        private void RecalculateTrnasportExpenses()
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
    }
}
