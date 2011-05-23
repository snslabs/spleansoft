using System;
using System.Collections.Generic;
using System.Data;

namespace Beton.Model
{
    /// <summary>
    /// Сырьё для производства
    /// Должно выполняться соотношение
    /// PricePerTonn * Density = PricePerCube
    /// </summary>
    [Serializable]
    public class Matherial : ITransportable, IGridDispalyable<Matherial>
    {
        public Matherial(object[] data)
        {
            UpdateFromObjectArray(data);
        }

        public Matherial(string name, double density, string description, decimal orderPricePerTonn, decimal orderPricePerCube)
        {
            Name = name;
            Density = density;
            Description = description;
            OrderPricePerTonn = orderPricePerTonn;
            OrderPricePerCube = orderPricePerCube;
        }

        public Matherial(int id, string name, double density, string description, decimal orderPricePerTonn, decimal orderPricePerCube)
        {
            Id = id;
            Name = name;
            Density = density;
            Description = description;
            OrderPricePerCube = orderPricePerCube;
        }

        public Matherial(int id, string name, double density, string description, decimal orderPricePerCube)
        {
            Id = id;
            Name = name;
            Density = density;
            Description = description;
            OrderPricePerCube = orderPricePerCube;
            OrderPricePerTonn = CalcPricePerTonn(density.ToString(), orderPricePerCube.ToString());
        }

        public int Id { set; get; }
        /// <summary>
        /// Наименование
        /// </summary>
        public string Name { set; get; }
        /// <summary>
        /// Плотность тонн/кубометр
        /// </summary>
        public double Density { set; get; }
        /// <summary>
        /// Описание
        /// </summary>
        public string Description { set; get; }
        /// <summary>
        /// Закупочная цена за тонну
        /// </summary>
        public Decimal OrderPricePerTonn { set; get;  }
        /// <summary>
        /// Закупочная цена за кубометр
        /// </summary>
        public Decimal OrderPricePerCube { set; get; }

        public static void PopulateDataTableSchema(DataTable dataTable)
        {
            dataTable.Columns.Add(new DataColumn("Id", typeof(int)));
            dataTable.Columns.Add(new DataColumn("Name", typeof(string)));
            dataTable.Columns.Add(new DataColumn("Density", typeof(double)));
            dataTable.Columns.Add(new DataColumn("Description", typeof(string)));
            dataTable.Columns.Add(new DataColumn("OrderPricePerTonn", typeof(Decimal)));
            dataTable.Columns.Add(new DataColumn("OrderPricePerCube", typeof(Decimal)));
            
        }

        public object[] ToObjectArray()
        {
            return new object[] {Id, Name, Density, Description, OrderPricePerTonn, OrderPricePerCube};
        }

        public void UpdateFromObjectArray(object[] data)
        {
            Id = (int) data[0];
            Name = (string) data[1];
            Density = (double) data[2];
            Description = (string) data[3];
            OrderPricePerTonn = (Decimal) data[4];
            OrderPricePerCube = (Decimal) data[5];
        }

        public static decimal CalcPricePerTonn(string strDensity, string strPricePerCube)
        {
            return decimal.Round(decimal.Divide(decimal.Parse(strPricePerCube), decimal.Parse(strDensity)), 2, MidpointRounding.AwayFromZero);
        }

        public static decimal CalcPricePerCube(string strDensity, string strPricePerCube)
        {
            return decimal.Round(decimal.Multiply(decimal.Parse(strPricePerCube), decimal.Parse(strDensity)), 2, MidpointRounding.AwayFromZero);
        }
    }

    /// <summary>
    /// Составляющая производства продукта.
    /// Должно выполняться соотношение:
    /// AmountCube * Matherial.Density = AmountTonn
    /// </summary>
    [Serializable]
    public class ProductComponent : IGridDispalyable<ProductComponent>
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
            return new object[]{Id, Matherial.Id, AmountTonn, AmountCube};
        }

        public void UpdateFromObjectArray(object[] data)
        {
            Id = (int) (data[0] == DBNull.Value ? 0 : data[0]);
            Matherial = Directories.getMatherialById((int) data[1]);
            AmountTonn = (Decimal) data[2];
            AmountCube = (Decimal) data[3];
        }

        public static void PopulateDataTableSchema(DataTable dataTable)
        {
            dataTable.Columns.Add(new DataColumn("Id", typeof(int)));
            dataTable.Columns.Add(new DataColumn("Matherial", typeof(int)));
            dataTable.Columns.Add(new DataColumn("AmountTonn", typeof(Decimal)));
            dataTable.Columns.Add(new DataColumn("AmountCube", typeof(Decimal)));
        }
    }
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
                    sum += component.Matherial.OrderPricePerCube*component.AmountCube;
                }
                return sum;
            }
        }
        /// <summary>
        /// Стоимость работы РБУ для выработки 1 кубометра бетона по заданному рецепту
        /// </summary>
        public Decimal WorkPricePerCube { get; set; }
        //public Decimal WorkPricePerTonn { get; set; }
        
        
        public Product(object[] data)
        {
            this.UpdateFromObjectArray(data);
            Components = new List<ProductComponent>();
        }

        public Product(int id, string name, List<ProductComponent> components, decimal workPricePerCube)
        {
            Id = id;
            Name = name;
            Components = components;
            WorkPricePerCube = workPricePerCube;
        }

        public object[] ToObjectArray()
        {
            return new object[] { Id, Name, PricePerTonn, PricePerCube, WorkPricePerCube};
        }

        public void UpdateFromObjectArray(object[] data)
        {
            Id = (int)data[0];
            Name = ((string)data[1]);
            // PricePerCube = (Decimal)data[2];
            // PricePerTonn = (Decimal)data[3];
            WorkPricePerCube = (Decimal)data[4];
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
    
    [Serializable]
    public class TransportPrice
    {
        public TransportType TransportType { get; set; }
        /// <summary>
        /// продукт
        /// </summary>
        public Product Product {get; set;}
        /// <summary>
        /// цена транспортировки за 1 кубометр на 1 километр
        /// </summary>
        public Decimal PricePerCubeKm { get; set; }
        
    }

    /// <summary>
    /// Тип транспортировки
    /// </summary>
    [Serializable]
    public class TransportType
    {
        public TransportType(string name)
        {
            Name = name;
        }

        public string Name { get; set; }
    }

    public interface ITransportable
    {
    }

    public interface IGridDispalyable<T>  where T : class
    {
        object[] ToObjectArray();
        void UpdateFromObjectArray(object[] data);

    }
}
