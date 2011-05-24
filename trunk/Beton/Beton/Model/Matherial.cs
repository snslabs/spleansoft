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
    public class Matherial
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

        public decimal PricePerTonn
        {
            get { return OrderPricePerTonn; }
        }

        public decimal PricePerCube
        {
            get { return OrderPricePerCube; }
        }

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

    public class ITransportable
    {
        public virtual string Name { get; set; }
        public virtual decimal PricePerTonn { get; set; }
        public virtual decimal PricePerCube { get; set; }
    }

}
