using System;
using System.Collections.Generic;
using System.Data;

namespace Beton.Model
{
    [Serializable]
    public class Product
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public List<ProductComponent> Components { get; set; }
        public Decimal PricePerTonn
        {
            get
            {
                // вычислить цену за кубометр
                Decimal sum = 0;
                foreach (ProductComponent component in Components)
                {
                    sum += component.Matherial.OrderPricePerTonn * component.AmountTonn;
                }
                return sum;
            }
        }
        public Decimal PricePerCube
        {
            get
            {
                // вычислить цену за кубометр
                Decimal sum = 0;
                foreach (ProductComponent component in Components)
                {
                    sum += component.Matherial.OrderPricePerCube * component.AmountCube;
                }
                return sum;
            }
        }

        public Product(object[] data)
        {
            this.UpdateFromObjectArray(data);
            Components = new List<ProductComponent>();
        }

        public Product(int id, string name, List<ProductComponent> components)
        {
            Id = id;
            Name = name;
            Components = components;
        }

        public object[] ToObjectArray()
        {
            return new object[] { Id, Name, PricePerTonn, PricePerCube };
        }

        public void UpdateFromObjectArray(object[] data)
        {
            Id = (int)data[0];
            Name = ((string)data[1]);
            // PricePerCube = (Decimal)data[2];
            // PricePerTonn = (Decimal)data[3];
            // WorkPricePerCube = (Decimal)data[4];
        }

        public static void PopulateDataTableSchema(DataTable dataTable)
        {
            dataTable.Columns.Add(new DataColumn("Id", typeof(int)));
            dataTable.Columns.Add(new DataColumn("Name", typeof(string)));
            dataTable.Columns.Add(new DataColumn("PricePerTonn", typeof(Decimal)));
            dataTable.Columns.Add(new DataColumn("PricePerCube", typeof(Decimal)));
            dataTable.Columns.Add(new DataColumn("WorkPricePerCube", typeof(Decimal)));
        }
    }
}
