namespace Beton.DxForms
{
    partial class CompleteCalculationUIComponent
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.splitContainerControl1 = new DevExpress.XtraEditors.SplitContainerControl();
            this.contractCalculationUIComponent1 = new Beton.DxForms.ContractCalculationUIComponent();
            this.transportCalculationUIControl1 = new Beton.DxForms.TransportCalculationUIControl();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainerControl1)).BeginInit();
            this.splitContainerControl1.SuspendLayout();
            this.SuspendLayout();
            // 
            // splitContainerControl1
            // 
            this.splitContainerControl1.BorderStyle = DevExpress.XtraEditors.Controls.BorderStyles.Office2003;
            this.splitContainerControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainerControl1.Horizontal = false;
            this.splitContainerControl1.Location = new System.Drawing.Point(0, 0);
            this.splitContainerControl1.Name = "splitContainerControl1";
            this.splitContainerControl1.Panel1.Controls.Add(this.contractCalculationUIComponent1);
            this.splitContainerControl1.Panel1.Text = "Panel1";
            this.splitContainerControl1.Panel2.Controls.Add(this.transportCalculationUIControl1);
            this.splitContainerControl1.Panel2.Text = "Panel2";
            this.splitContainerControl1.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.splitContainerControl1.Size = new System.Drawing.Size(890, 596);
            this.splitContainerControl1.SplitterPosition = 297;
            this.splitContainerControl1.TabIndex = 0;
            this.splitContainerControl1.Text = "splitContainerControl1";
            // 
            // contractCalculationUIComponent1
            // 
            this.contractCalculationUIComponent1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.contractCalculationUIComponent1.Location = new System.Drawing.Point(0, 0);
            this.contractCalculationUIComponent1.Name = "contractCalculationUIComponent1";
            this.contractCalculationUIComponent1.Size = new System.Drawing.Size(886, 297);
            this.contractCalculationUIComponent1.TabIndex = 0;
            // 
            // transportCalculationUIControl1
            // 
            this.transportCalculationUIControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.transportCalculationUIControl1.Location = new System.Drawing.Point(0, 0);
            this.transportCalculationUIControl1.Name = "transportCalculationUIControl1";
            this.transportCalculationUIControl1.Size = new System.Drawing.Size(886, 290);
            this.transportCalculationUIControl1.TabIndex = 0;
            // 
            // CompleteCalculationUIComponent
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.splitContainerControl1);
            this.Name = "CompleteCalculationUIComponent";
            this.Size = new System.Drawing.Size(890, 596);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainerControl1)).EndInit();
            this.splitContainerControl1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private DevExpress.XtraEditors.SplitContainerControl splitContainerControl1;
        private ContractCalculationUIComponent contractCalculationUIComponent1;
        private TransportCalculationUIControl transportCalculationUIControl1;
    }
}
