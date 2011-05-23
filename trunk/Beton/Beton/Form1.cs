using System;
using System.Windows.Forms;
using Beton.Forms;
using Beton.Model;

namespace Beton
{
    public partial class Form1 : Form
    {
        private MatherialsForm matherialsForm;
        private ProductsForm productsForm;
        public Form1()
        {
            InitializeComponent();
            
            matherialsForm = new MatherialsForm();
            matherialsForm.Matherials = Directories.MATHERIALS;

            productsForm = new ProductsForm();
            productsForm.Products = Directories.PRODUCTS;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if(matherialsForm.ShowDialog(this) == DialogResult.OK)
            {
                Directories.UpdateMatherials(matherialsForm.Matherials);
            }
        }

        private void btnProducts_Click(object sender, EventArgs e)
        {
            productsForm.ShowDialog(this);
        }
    }
}
