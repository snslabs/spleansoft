using System;
using System.Data;

namespace Beton.Model
{
    [Serializable]
    public class Position
    {
        public int Id { get; set; }
        public Product Product { get; set; }
        public decimal Volume { get; set; }
        public decimal SelfPricePerCube { 
            get
            {
                return Product == null ? 0 : Product.PricePerCube;
            } 
            set { }
        }
        public decimal AddedPrice { get; set; }
        public decimal FinalPrice { 
            get
            {
                return SelfPricePerCube + AddedPrice;
            } 
            set
            {
                
            } 
        }
        public decimal TotalPrice { 
            get
            {
                return FinalPrice*Volume;
            }
            set { } 
        }
        public string PositionDisplayName { 
            get
            {
                return Product == null ? "" : string.Format("#{0} {1}, {2} куб.м.", Id, Product.Name, Volume.ToString("N2"));
            }
            set { }
        }

        public Position()
        {
        }

        public Position(int id, Product product, decimal volume, decimal addedPrice, decimal totalPrice)
        {
            Update(id, product, volume, addedPrice, totalPrice);
        }

        public void Update(int id, Product product, decimal volume, decimal addedPrice, decimal totalPrice)
        {
            Id = id;
            Product = product;
            Volume = volume;
            AddedPrice = addedPrice;
            TotalPrice = totalPrice;
        }

        public static void PopulateDataTableSchema(DataTable dataTable)
        {
            dataTable.Columns.Add(new DataColumn("Id", typeof(int)));
            dataTable.Columns.Add(new DataColumn("Product", typeof(int)));
            dataTable.Columns.Add(new DataColumn("Volume", typeof(decimal)));
            dataTable.Columns.Add(new DataColumn("SelfPricePerCube", typeof(decimal)));
            dataTable.Columns.Add(new DataColumn("AddedPrice", typeof(decimal)));
            dataTable.Columns.Add(new DataColumn("FinalPrice", typeof(decimal)));
            dataTable.Columns.Add(new DataColumn("TotalPrice", typeof(decimal)));
            dataTable.Columns.Add(new DataColumn("PositionDisplayName", typeof(string)));
            
        }

        public object[] ToObjectArray()
        {
            return new object[]{Id, Product.Id, Volume, SelfPricePerCube, AddedPrice, FinalPrice, TotalPrice, PositionDisplayName };
        }
    }
}
