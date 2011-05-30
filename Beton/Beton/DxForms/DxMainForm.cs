using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using Beton.Behavior;
using Beton.Model;
using DevExpress.XtraEditors;

namespace Beton.DxForms
{
    public partial class DxMainForm : DevExpress.XtraEditors.XtraForm
    {
        private MatherialsUIComponent matherialsComponent;
        private ProductsUIComponent productsComponent;
        private ContractCalculationUIComponent contractCalculationComponent;
        public DxMainForm()
        {
            InitializeComponent();
            matherialsComponent = new MatherialsUIComponent();
            productsComponent = new ProductsUIComponent();
            contractCalculationComponent = new ContractCalculationUIComponent();
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
                if(control as IPersistable != null)
                {
                    if(!((IPersistable)control).SaveData())
                    {
                        return false;
                    }
                }
            }


            if (viewComponent != null)
            {
                mainPanel.Controls.Clear(); // removing all controls from the form

                // if new control can be reinitialized with data - do it
                if (viewComponent as IPersistable != null)
                    ((IPersistable)viewComponent).LoadData();

                mainPanel.Controls.Add(viewComponent);
                viewComponent.Dock = DockStyle.Fill;
            }
            return true;
        }

        private void contractCalculation_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            SwitchView(contractCalculationComponent);
        }

    }
}