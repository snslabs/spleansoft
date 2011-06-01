using System.Collections.Generic;
using System.ComponentModel;
using Beton.Behavior;
using Beton.Model;
using DevExpress.XtraEditors;

namespace Beton.DxForms
{
    public partial class MatherialsUIComponent : XtraUserControl, IBetonComponent
    {

        public MatherialsUIComponent()
        {
            InitializeComponent();
        }


        private void matherialBindingSource_AddingNew(object sender, AddingNewEventArgs e)
        {
            var newMatherial = (Matherial)(e.NewObject = new Matherial());
            int max = -1;
            foreach (var m in Directories.MATHERIALS)
            {
                max = m.Id > max ? m.Id : max;
            }
            newMatherial.Id = max + 1;
        }

        public bool SaveData()
        {
            // Directories.UpdateMatherials(matherials);
            return true;
        }

        public void LoadData()
        {
            matherialBindingSource.DataSource = Directories.MATHERIALS;
        }

        public string FormCaption
        {
            get { return "Материалы"; }
        }
    }
}
