
using System;
using System.Data;

namespace Beton.Model
{
    /// <summary>
    /// Составляющая производства продукта.
    /// Должно выполняться соотношение:
    /// AmountCube * Matherial.Density = AmountTonn
    /// </summary>
    [Serializable]
    public class ProductComponent
    {
        public Matherial Matherial { get; set; }
        public Decimal AmountTonn { get; set; }
        public Decimal AmountCube { get; set; }

        public ProductComponent()
        {
        }

        public ProductComponent(object[] data)
        {
            this.UpdateFromObjectArray(data);
        }

        public ProductComponent(Matherial matherial, decimal amountTonn, decimal amountCube)
        {
            Matherial = matherial;
            AmountTonn = amountTonn;
            AmountCube = amountCube;

        }

        public object[] ToObjectArray()
        {
            return new object[] { Matherial.Id, AmountTonn, AmountCube };
        }

        public void UpdateFromObjectArray(object[] data)
        {
            Matherial = Directories.getMatherialById((int)data[1]);
            AmountTonn = (Decimal)data[2];
            AmountCube = (Decimal)data[3];
        }

        public static void PopulateDataTableSchema(DataTable dataTable)
        {
            dataTable.Columns.Add(new DataColumn("Matherial", typeof(int)));
            dataTable.Columns.Add(new DataColumn("AmountTonn", typeof(Decimal)));
            dataTable.Columns.Add(new DataColumn("AmountCube", typeof(Decimal)));
        }
    }

}
