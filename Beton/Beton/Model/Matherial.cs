﻿using System;
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
        public Matherial()
        {
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

        private Product defaultProduct;
        public Product DefaultProduct
        {
            get {
                if(defaultProduct == null)
                {
                    defaultProduct = new Product(-1, "", new List<ProductComponent>(new []{ new ProductComponent(this, 1, 1),  }));
                    
                }
                defaultProduct.Id = 9000 + Id;
                defaultProduct.Name = Name;
                return defaultProduct;
            }
        }

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
}