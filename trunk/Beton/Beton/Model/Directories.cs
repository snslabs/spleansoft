using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Windows.Forms;

namespace Beton.Model
{
    /// <summary>
    /// содержит справочники заранее определенных материалов, продуктов и типов транспортировки
    /// </summary>
    [Serializable]
    public class Directories
    {
        private static int VERSION = 1;

        #region properties
        private List<Matherial> matherials = new List<Matherial>();
        private List<TransportType> transportTypes = new List<TransportType>();
        private List<TransportPosition> transportPositions = new List<TransportPosition>();
        private List<Product> products = new List<Product>();
        private List<Position> positions = new List<Position>();


        public static List<TransportPosition> TRANSPORT_POSITIONS
        {
            get
            {
                return instance.transportPositions;
            }
        }
        public static List<Position> POSITIONS
        {
            get
            {
                return instance.positions;
            }
        }

        public static List<Matherial> MATHERIALS
        {
            get
            {
                return instance.matherials;
            }
        } 
        public static List<TransportType> TRANSPORT_TYPES
        {
            get
            {
                return instance.transportTypes;
            }
        }
        public static List<Product> PRODUCTS
        {
            get
            {
                return instance.products;
            }
        }

        public static List<Product> ALL_PRODUCTS
        {
            get
            {
                var list = new List<Product>(instance.products);
                foreach(var m in instance.matherials)
                {
                    list.Add(m.DefaultProduct);
                }
                return list;
            }
        }

        private static Directories instance;
        #endregion

        #region constructors
        static Directories(){
            instance = new Directories();
            defaultData();
        }

        private static void defaultData()
        {
            MATHERIALS.Add(new Matherial(1, "Цемент", 1.2, "Цемент обыкновенный =)", 2000));
            MATHERIALS.Add(new Matherial(2, "Песок речной", 1.640, "песок речной", 230));
            MATHERIALS.Add(new Matherial(3, "Щебень 5-10см", 1.242, "Щебень мелкий", 250));
            MATHERIALS.Add(new Matherial(4, "Щебень 10-15см", 1.045, "Щебень средний", 300));
            MATHERIALS.Add(new Matherial(5, "Щебень 15-25см", 0.96, "Щебень крупный", 310));
            MATHERIALS.Add(new Matherial(6, "Вода", 1, "Вода", 10));
            MATHERIALS.Add(new Matherial(7, "Работа РБУ", 1, "Работа РБУ", 20));

            TRANSPORT_TYPES.Add(new TransportType(1,"Авто тягач, 20т", 10, 10, 8000, 20, 20));
            TRANSPORT_TYPES.Add(new TransportType(2, "Ж/Д сыпуч. 80т", 5, 5, 26000, 80, 80));
            TRANSPORT_TYPES.Add(new TransportType(3, "АвтоМиксер, 5куб", 20, 20, 6000, 5, 5));

            PRODUCTS.Add(new Product(1, "M50",
                new List<ProductComponent>(
                new[]{
                        new ProductComponent( getMatherialById(1), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent( getMatherialById(2), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent( getMatherialById(3), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent( getMatherialById(6), new decimal(0.1), new decimal(0.1)), 
                }
                )));

            PRODUCTS.Add(new Product(2, "M150", new List<ProductComponent>(
                new[] { new ProductComponent(getMatherialById(6), new decimal(0.1), new decimal(0.1)) }
                )));

            PRODUCTS.Add(new Product(3, "M200",
                new List<ProductComponent>(
                new[]{
                        new ProductComponent( getMatherialById(1), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent( getMatherialById(2), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent( getMatherialById(4), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent( getMatherialById(6), new decimal(0.1), new decimal(0.1)), 
                }
                )));
        }
        #endregion

        #region lookup methods
        static public Matherial getMatherialById(int id)
        {
            foreach(var m in MATHERIALS)
            {
                if (m.Id == id) return m;
            }
            return null;
        }
        #endregion

        #region update methods
        /*
        public static void UpdateMatherials(IEnumerable<Matherial> matherials)
        {
            // MATHERIALS.Clear();
            // MATHERIALS.AddRange(matherials);
        }
        public static void UpdateProducts(IEnumerable<Product> products)
        {
            // PRODUCTS.Clear();
            // PRODUCTS.AddRange(products);
        }
        public static void UpdatePositions(IEnumerable<Position> list)
        {
            // POSITIONS.Clear();
            // POSITIONS.AddRange(list);
        }
         * */
        #endregion

        #region serializations
        public static void SaveToFile(string fileName)
        {
            Stream stream = null;
            try {
                IFormatter formatter = new BinaryFormatter();
                stream = new FileStream(fileName, FileMode.Create, FileAccess.Write, FileShare.None);
                formatter.Serialize(stream, VERSION);
                formatter.Serialize(stream, instance);
            } catch {
                // do nothing, just ignore any possible errors
            } finally {
                if (null != stream)
                    stream.Close();
            }
            
            
        }

        public static void LoadFromFile(string fileName)
        {
            if(!File.Exists(fileName))
            {
                return;
            }
            Stream stream = null;
            Directories directories = null;
            try
            {
                IFormatter formatter = new BinaryFormatter();
                stream = new FileStream(fileName, FileMode.Open, FileAccess.Read, FileShare.None);
                int version = (int)formatter.Deserialize(stream);
                Debug.Assert(version == VERSION);
                directories = (Directories)formatter.Deserialize(stream);
            }
            catch(Exception e)
            {
                MessageBox.Show(e.Message, "Error loading data", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            finally
            {
                if (null != stream)
                    stream.Close();
            }
            instance = directories;

        }
        #endregion

        public static Product FindProductById(int id)
        {
            foreach (var product in ALL_PRODUCTS)
            {
                if(product.Id == id)
                {
                    return product;
                }
            }
            return null;
        }

    }
}
