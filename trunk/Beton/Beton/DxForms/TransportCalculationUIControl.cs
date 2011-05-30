using System.ComponentModel;
using Beton.Behavior;
using Beton.Model;

namespace Beton.DxForms
{
    public partial class TransportCalculationUIControl : DevExpress.XtraEditors.XtraUserControl, IPersistable
    {
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

        private void transportPositionBindingSource_AddingNew(object sender, AddingNewEventArgs e)
        {
            e.NewObject = new TransportPosition();
            
        }

        private void grid_Click(object sender, System.EventArgs e)
        {

        }
    }
}
