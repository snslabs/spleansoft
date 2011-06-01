using System;
using System.Windows.Forms;
using Beton.Behavior;
using Beton.Model;

namespace Beton.DxForms
{
    public partial class DxMainForm : DevExpress.XtraEditors.XtraForm, IBetonComponent
    {
        private readonly MatherialsUIComponent matherialsComponent;
        private readonly ProductsUIComponent productsComponent;
        private readonly CompleteCalculationUIComponent completeCalculationComponent;
        public DxMainForm()
        {
            InitializeComponent();
            matherialsComponent = new MatherialsUIComponent();
            productsComponent = new ProductsUIComponent();
            completeCalculationComponent = new CompleteCalculationUIComponent();
        }

        private void navBarControl1_Click(object sender, EventArgs e)
        {

        }

        private void DxMainForm_Load(object sender, EventArgs e)
        {
            Directories.LoadFromFile("beton.dat");

        }

        private void transportTypes_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            
        }

        private void DxMainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            if(!SwitchView(null))
            {
                e.Cancel = true;
            }
            Directories.SaveToFile("beton.dat");
        }

        private void matherials_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            SwitchView(matherialsComponent);
        }

        private void products_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            SwitchView(productsComponent);

        }

        private bool SwitchView(Control viewComponent)
        {
            foreach (var control in mainPanel.Controls)
            {
                if(control as IBetonComponent != null)
                {
                    if(!((IBetonComponent)control).SaveData())
                    {
                        return false;
                    }
                }
            }


            if (viewComponent != null)
            {
                mainPanel.Controls.Clear(); // removing all controls from the form

                // if new control can be reinitialized with data - do it
                if (viewComponent as IBetonComponent != null)
                {
                    IBetonComponent component = (viewComponent as IBetonComponent);
                    component.LoadData();
                    Text = FormCaption + " :: " + component.FormCaption;
                }
                mainPanel.Controls.Add(viewComponent);
                viewComponent.Dock = DockStyle.Fill;
            }
            else
            {
                Text = FormCaption;                
            }


            return true;
        }

        private void contractCalculation_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            SwitchView(completeCalculationComponent);
        }

        public bool SaveData()
        {
            return true;
        }

        public void LoadData()
        {
            //
        }

        public string FormCaption
        {
            get { return "Бетономешалка версия 0.2 "; }
        }
    }
}