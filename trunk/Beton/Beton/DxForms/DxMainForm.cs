using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using Beton.Model;
using DevExpress.XtraEditors;

namespace Beton.DxForms
{
    public partial class DxMainForm : DevExpress.XtraEditors.XtraForm
    {
        private MatherialsUIComponent matherialsComponent;
        public DxMainForm()
        {
            InitializeComponent();
            matherialsComponent = new MatherialsUIComponent();
        }

        private void navBarControl1_Click(object sender, EventArgs e)
        {

        }

        private void DxMainForm_Load(object sender, EventArgs e)
        {

        }

        private void transportTypes_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            mainPanel.Controls.Clear(); // removing all controls from the form

            matherialsComponent.Matherials = Directories.MATHERIALS;

            mainPanel.Controls.Add(matherialsComponent);
            matherialsComponent.Dock = DockStyle.Fill;
            
        }

        private void mainPanel_Paint(object sender, PaintEventArgs e)
        {

        }
    }
}