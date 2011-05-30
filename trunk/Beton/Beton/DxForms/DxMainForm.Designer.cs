namespace Beton.DxForms
{
    partial class DxMainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DxMainForm));
            this.lookAndFeel = new DevExpress.LookAndFeel.DefaultLookAndFeel(this.components);
            this.splitContainerControl1 = new DevExpress.XtraEditors.SplitContainerControl();
            this.navBarControl1 = new DevExpress.XtraNavBar.NavBarControl();
            this.calculations = new DevExpress.XtraNavBar.NavBarGroup();
            this.contractCalculation = new DevExpress.XtraNavBar.NavBarItem();
            this.directories = new DevExpress.XtraNavBar.NavBarGroup();
            this.transportTypes = new DevExpress.XtraNavBar.NavBarItem();
            this.products = new DevExpress.XtraNavBar.NavBarItem();
            this.matherials = new DevExpress.XtraNavBar.NavBarItem();
            this.menuTreeImageList = new System.Windows.Forms.ImageList(this.components);
            this.mainPanel = new DevExpress.XtraEditors.PanelControl();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainerControl1)).BeginInit();
            this.splitContainerControl1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.navBarControl1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.mainPanel)).BeginInit();
            this.SuspendLayout();
            // 
            // splitContainerControl1
            // 
            this.splitContainerControl1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.splitContainerControl1.BorderStyle = DevExpress.XtraEditors.Controls.BorderStyles.Style3D;
            this.splitContainerControl1.Location = new System.Drawing.Point(12, 12);
            this.splitContainerControl1.Name = "splitContainerControl1";
            this.splitContainerControl1.Panel1.Controls.Add(this.navBarControl1);
            this.splitContainerControl1.Panel1.Text = "Panel1";
            this.splitContainerControl1.Panel2.Controls.Add(this.mainPanel);
            this.splitContainerControl1.Panel2.Text = "Panel2";
            this.splitContainerControl1.Size = new System.Drawing.Size(1131, 520);
            this.splitContainerControl1.SplitterPosition = 195;
            this.splitContainerControl1.TabIndex = 0;
            this.splitContainerControl1.Text = "splitContainerControl1";
            // 
            // navBarControl1
            // 
            this.navBarControl1.ActiveGroup = this.calculations;
            this.navBarControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.navBarControl1.Groups.AddRange(new DevExpress.XtraNavBar.NavBarGroup[] {
            this.directories,
            this.calculations});
            this.navBarControl1.Items.AddRange(new DevExpress.XtraNavBar.NavBarItem[] {
            this.matherials,
            this.products,
            this.transportTypes,
            this.contractCalculation});
            this.navBarControl1.LargeImages = this.menuTreeImageList;
            this.navBarControl1.Location = new System.Drawing.Point(0, 0);
            this.navBarControl1.Name = "navBarControl1";
            this.navBarControl1.OptionsNavPane.ExpandedWidth = 155;
            this.navBarControl1.Size = new System.Drawing.Size(195, 516);
            this.navBarControl1.SmallImages = this.menuTreeImageList;
            this.navBarControl1.TabIndex = 0;
            this.navBarControl1.Text = "Главное меню";
            this.navBarControl1.Click += new System.EventHandler(this.navBarControl1_Click);
            // 
            // calculations
            // 
            this.calculations.Caption = "Расчеты";
            this.calculations.Expanded = true;
            this.calculations.ItemLinks.AddRange(new DevExpress.XtraNavBar.NavBarItemLink[] {
            new DevExpress.XtraNavBar.NavBarItemLink(this.contractCalculation)});
            this.calculations.LargeImageIndex = 3;
            this.calculations.Name = "calculations";
            // 
            // contractCalculation
            // 
            this.contractCalculation.Caption = "Расчёт контракта";
            this.contractCalculation.Name = "contractCalculation";
            this.contractCalculation.LinkClicked += new DevExpress.XtraNavBar.NavBarLinkEventHandler(this.contractCalculation_LinkClicked);
            // 
            // directories
            // 
            this.directories.Caption = "Справочники";
            this.directories.Expanded = true;
            this.directories.ItemLinks.AddRange(new DevExpress.XtraNavBar.NavBarItemLink[] {
            new DevExpress.XtraNavBar.NavBarItemLink(this.transportTypes),
            new DevExpress.XtraNavBar.NavBarItemLink(this.products),
            new DevExpress.XtraNavBar.NavBarItemLink(this.matherials)});
            this.directories.LargeImageIndex = 2;
            this.directories.Name = "directories";
            // 
            // transportTypes
            // 
            this.transportTypes.Caption = "Способы транспортировки";
            this.transportTypes.Name = "transportTypes";
            this.transportTypes.LinkClicked += new DevExpress.XtraNavBar.NavBarLinkEventHandler(this.transportTypes_LinkClicked);
            // 
            // products
            // 
            this.products.Caption = "Продукты";
            this.products.Name = "products";
            this.products.LinkClicked += new DevExpress.XtraNavBar.NavBarLinkEventHandler(this.products_LinkClicked);
            // 
            // matherials
            // 
            this.matherials.Caption = "Материалы";
            this.matherials.Name = "matherials";
            this.matherials.LinkClicked += new DevExpress.XtraNavBar.NavBarLinkEventHandler(this.matherials_LinkClicked);
            // 
            // menuTreeImageList
            // 
            this.menuTreeImageList.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("menuTreeImageList.ImageStream")));
            this.menuTreeImageList.TransparentColor = System.Drawing.Color.Transparent;
            this.menuTreeImageList.Images.SetKeyName(0, "banknote.ico");
            this.menuTreeImageList.Images.SetKeyName(1, "bar_chart.ico");
            this.menuTreeImageList.Images.SetKeyName(2, "calculator.ico");
            this.menuTreeImageList.Images.SetKeyName(3, "cash_register.ico");
            this.menuTreeImageList.Images.SetKeyName(4, "coins.ico");
            this.menuTreeImageList.Images.SetKeyName(5, "credit_card.ico");
            this.menuTreeImageList.Images.SetKeyName(6, "gold_bullion.ico");
            this.menuTreeImageList.Images.SetKeyName(7, "invoice.ico");
            this.menuTreeImageList.Images.SetKeyName(8, "line_chart.ico");
            this.menuTreeImageList.Images.SetKeyName(9, "pie_chart.ico");
            this.menuTreeImageList.Images.SetKeyName(10, "safe.ico");
            this.menuTreeImageList.Images.SetKeyName(11, "shopping cart.ico");
            // 
            // mainPanel
            // 
            this.mainPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.mainPanel.Location = new System.Drawing.Point(0, 0);
            this.mainPanel.Name = "mainPanel";
            this.mainPanel.Size = new System.Drawing.Size(927, 516);
            this.mainPanel.TabIndex = 0;
            // 
            // DxMainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1155, 544);
            this.Controls.Add(this.splitContainerControl1);
            this.Name = "DxMainForm";
            this.Text = "DxMainForm";
            this.Load += new System.EventHandler(this.DxMainForm_Load);
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.DxMainForm_FormClosing);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainerControl1)).EndInit();
            this.splitContainerControl1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.navBarControl1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.mainPanel)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private DevExpress.LookAndFeel.DefaultLookAndFeel lookAndFeel;
        private DevExpress.XtraEditors.SplitContainerControl splitContainerControl1;
        private System.Windows.Forms.ImageList menuTreeImageList;
        private DevExpress.XtraNavBar.NavBarControl navBarControl1;
        private DevExpress.XtraNavBar.NavBarGroup directories;
        private DevExpress.XtraNavBar.NavBarGroup calculations;
        private DevExpress.XtraNavBar.NavBarItem transportTypes;
        private DevExpress.XtraNavBar.NavBarItem products;
        private DevExpress.XtraNavBar.NavBarItem matherials;
        private DevExpress.XtraNavBar.NavBarItem contractCalculation;
        private DevExpress.XtraEditors.PanelControl mainPanel;
    }
}