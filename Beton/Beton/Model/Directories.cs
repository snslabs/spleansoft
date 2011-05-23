using System;
using System.Collections.Generic;
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
        private List<Product> products = new List<Product>();
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

            TRANSPORT_TYPES.Add(new TransportType("Тягач"));
            TRANSPORT_TYPES.Add(new TransportType("Ж/Д"));
            TRANSPORT_TYPES.Add(new TransportType("АвтоМиксер"));

            PRODUCTS.Add(new Product(1, "M50",
                new List<ProductComponent>(
                new ProductComponent[]{
                        new ProductComponent(0, Directories.getMatherialById(1), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent(0, Directories.getMatherialById(2), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent(0, Directories.getMatherialById(3), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent(0, Directories.getMatherialById(6), new decimal(0.1), new decimal(0.1)), 
                }
                )));

            PRODUCTS.Add(new Product(2, "M200",
                new List<ProductComponent>(
                new ProductComponent[]{
                        new ProductComponent(0, Directories.getMatherialById(1), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent(0, Directories.getMatherialById(2), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent(0, Directories.getMatherialById(4), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent(0, Directories.getMatherialById(6), new decimal(0.1), new decimal(0.1)), 
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
        public static void UpdateMatherials(List<Matherial> matherials)
        {
            MATHERIALS.Clear();
            MATHERIALS.AddRange(matherials);
        }
        public static void UpdateProducts(List<Product> products)
        {
            PRODUCTS.Clear();
            PRODUCTS.AddRange(products);
        }
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

    }
}
