using System;
using System.Windows.Forms;
using Beton.Forms;
using Beton.Model;

namespace Beton
{
    public partial class MainForm : Form
    {
        private MatherialsForm matherialsForm;
        private ProductsForm productsForm;
        public MainForm()
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
            if(productsForm.ShowDialog(this) == DialogResult.OK)
            {
                Directories.UpdateProducts(productsForm.Products);
            }
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            Directories.SaveToFile("beton.dat");
        }

        private void btnLoad_Click(object sender, EventArgs e)
        {
            Directories.LoadFromFile("beton.dat");
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            Directories.LoadFromFile("beton.dat");
        }

        private void MainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            Directories.SaveToFile("beton.dat");
        }
    }
}
