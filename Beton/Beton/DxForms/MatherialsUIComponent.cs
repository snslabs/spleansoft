using System.Collections.Generic;
using System.ComponentModel;
using System.Windows.Forms;
using Beton.Behavior;
using Beton.Model;
using DevExpress.XtraEditors;

namespace Beton.DxForms
{
    public partial class MatherialsUIComponent : XtraUserControl, IPersistable
    {
        private readonly List<Matherial> matherials;
        public List<Matherial> Matherials
        {
            get
            {
                return matherials;
            }
            set
            {
                matherials.Clear();
                foreach(var m in value)
                {
                    matherials.Add(new Matherial(m));
                }
            }
        }


        public MatherialsUIComponent()
        {
            InitializeComponent();
            matherials = new List<Matherial>();
            matherialBindingSource.DataSource = matherials;
        }


        private void matherialBindingSource_AddingNew(object sender, AddingNewEventArgs e)
        {
            var newMatherial = (Matherial)(e.NewObject = new Matherial());
            int max = -1;
            foreach(var m in matherials)
            {
                max = m.Id > max ? m.Id : max;
            }
            newMatherial.Id = max + 1;
        }

        public bool SaveData()
        {
            DialogResult dialogResult = MessageBox.Show("Сохранить изменения?", "Изменения", MessageBoxButtons.YesNoCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button1);

            switch(dialogResult)
            {
                case DialogResult.Yes:
                    Directories.UpdateMatherials(matherials);
                    return true;
                case DialogResult.No:
                    LoadData();
                    return true;
                case DialogResult.Cancel:
                    return false;
                default:
                    return false;
            }
        }

        public void LoadData()
        {
            Matherials = Directories.MATHERIALS;
        }
    }
}
