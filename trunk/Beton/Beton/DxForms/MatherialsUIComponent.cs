using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Text;
using System.Windows.Forms;
using Beton.Model;
using DevExpress.XtraEditors;

namespace Beton.DxForms
{
    public partial class MatherialsUIComponent : DevExpress.XtraEditors.XtraUserControl
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
                matherials.AddRange(value);
            }
            
        }


        public MatherialsUIComponent()
        {
            InitializeComponent();
            matherials = new List<Matherial>();
            matherialBindingSource.DataSource = matherials;
        }

        private void MatherialsUIComponent_Load(object sender, EventArgs e)
        {

        }
    }
}
