using Beton.Behavior;
using DevExpress.XtraEditors;

namespace Beton.DxForms
{
    public partial class CompleteCalculationUIComponent : XtraUserControl, IPersistable
    {
        public CompleteCalculationUIComponent()
        {
            InitializeComponent();
            transportCalculationUIControl1.DataRefreshedEventHandler = contractCalculationUIComponent1.RefreshData;
        }

        public bool SaveData()
        {
            contractCalculationUIComponent1.SaveData();
            transportCalculationUIControl1.SaveData();
            return true;
        }

        public void LoadData()
        {
            contractCalculationUIComponent1.LoadData();
            transportCalculationUIControl1.LoadData();
        }
    }
}
