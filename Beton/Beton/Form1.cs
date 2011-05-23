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

            productsForm = new ProductsForm();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            matherialsForm.Matherials = Directories.MATHERIALS;
            if (matherialsForm.ShowDialog(this) == DialogResult.OK)
            {
                Directories.UpdateMatherials(matherialsForm.Matherials);
            }
        }

        private void btnProducts_Click(object sender, EventArgs e)
        {
            productsForm.Products = Directories.PRODUCTS;
            productsForm.ShowDialog(this);
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            Directories.SaveToFile("beton.dat");
        }

        private void btnLoad_Click(object sender, EventArgs e)
        {
            Directories.LoadFromFile("beton.dat");
        }
    }
}
