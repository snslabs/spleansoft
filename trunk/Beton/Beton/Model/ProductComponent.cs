
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
        public int Id { get; set; }
        public Matherial Matherial { get; set; }
        public Decimal AmountTonn { get; set; }
        public Decimal AmountCube { get; set; }

        public ProductComponent(object[] data)
        {
            this.UpdateFromObjectArray(data);
        }

        public ProductComponent(int id, Matherial matherial, decimal amountTonn, decimal amountCube)
        {
            Id = id;
            Matherial = matherial;
            AmountTonn = amountTonn;
            AmountCube = amountCube;

        }

        public object[] ToObjectArray()
        {
            return new object[] { Id, Matherial.Id, AmountTonn, AmountCube };
        }

        public void UpdateFromObjectArray(object[] data)
        {
            Id = (int)(data[0] == DBNull.Value ? 0 : data[0]);
            Matherial = Directories.getMatherialById((int)data[1]);
            AmountTonn = (Decimal)data[2];
            AmountCube = (Decimal)data[3];
        }

        public static void PopulateDataTableSchema(DataTable dataTable)
        {
            dataTable.Columns.Add(new DataColumn("Id", typeof(int)));
            dataTable.Columns.Add(new DataColumn("Matherial", typeof(int)));
            dataTable.Columns.Add(new DataColumn("AmountTonn", typeof(Decimal)));
            dataTable.Columns.Add(new DataColumn("AmountCube", typeof(Decimal)));
        }
    }

}
