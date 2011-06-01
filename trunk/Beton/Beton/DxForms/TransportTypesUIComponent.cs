using System;
using System.Linq;
using System.Windows.Forms;
using Beton.Behavior;
using Beton.Model;

namespace Beton.DxForms
{
    public partial class TransportTypesUIComponent : DevExpress.XtraEditors.XtraUserControl, IBetonComponent
    {
        public TransportTypesUIComponent()
        {
            InitializeComponent();
        }

        public bool SaveData()
        {
            //
            return true;
        }

        public void LoadData()
        {
            transportTypeBindingSource.DataSource = Directories.TRANSPORT_TYPES;

        }

        public string FormCaption
        {
            get { return "Типы транспортировки"; }
        }

        private void transportTypeBindingSource_AddingNew(object sender, System.ComponentModel.AddingNewEventArgs e)
        {
            var newTransportType = (TransportType)(e.NewObject = new TransportType());
            int max = -1;
            foreach (var m in Directories.TRANSPORT_TYPES)
            {
                max = m.Id > max ? m.Id : max;
            }
            newTransportType.Id = max + 1;

        }

        private void gridView_KeyDown(object sender, System.Windows.Forms.KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Delete)
            {
                var transportType = gridView.GetRow(gridView.FocusedRowHandle);
                string message = "Вы уверены что хотите удалить строку?";
                int transportPositionsCount =
                    Directories.TRANSPORT_POSITIONS.Count(
                        transportPosition => transportPosition.TransportType == transportType);
                if(transportPositionsCount > 0)
                {
                    message += "\nБудет удалено " + transportPositionsCount + " позиций в транспортных расходах!";
                }
                if (MessageBox.Show(message, "Удаление строки", MessageBoxButtons.YesNo, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2) == DialogResult.Yes)
                {
                    
                    Directories.TRANSPORT_POSITIONS.RemoveAll(transportPosition => transportPosition.TransportType == transportType);
                    gridView.DeleteRow(gridView.FocusedRowHandle);
                }
            }

        }
    }
}
