using System.Collections.Generic;

namespace Beton.Model
{
    /// <summary>
    /// содержит справочники заранее определенных материалов, продуктов и типов транспортировки
    /// </summary>
    public class Directories
    {
        public static readonly List<Matherial> MATHERIALS = new List<Matherial>();
        public static readonly List<TransportType> TRANSPORT_TYPES = new List<TransportType>();
        public static readonly List<Product> PRODUCTS = new List<Product>();
        static Directories(){
            MATHERIALS.Add(new Matherial(1, "Цемент", 1, "Цемент обыкновенный =)", 500, 500));
            MATHERIALS.Add(new Matherial(2, "Песок речной", 1, "песок речной", 500, 500));
            MATHERIALS.Add(new Matherial(3, "Щебень 5-10см", 1, "Щебень мелкий", 500, 500));
            MATHERIALS.Add(new Matherial(4, "Щебень 10-15см", 1, "Щебень средний", 500, 500));
            MATHERIALS.Add(new Matherial(5, "Щебень 15-25см", 1, "Щебень крупный", 500, 500));
            MATHERIALS.Add(new Matherial(6, "Вода", 1, "Вода", 10, 10));
            MATHERIALS.Add(new Matherial(7, "Работа РБУ", 1, "Работа РБУ", 20, 20));

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
                ), 500));

            PRODUCTS.Add(new Product(2, "M200", 
                new List<ProductComponent>(
                new ProductComponent[]{
                        new ProductComponent(0, Directories.getMatherialById(1), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent(0, Directories.getMatherialById(2), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent(0, Directories.getMatherialById(4), new decimal(0.3), new decimal(0.3)), 
                        new ProductComponent(0, Directories.getMatherialById(6), new decimal(0.1), new decimal(0.1)), 
                }
                ), 500));

        }
        
        static public Matherial getMatherialById(int id)
        {
            foreach(var m in MATHERIALS)
            {
                if (m.Id == id) return m;
            }
            return null;
        }

        public static void UpdateMatherials(List<Matherial> matherials)
        {
            MATHERIALS.Clear();
            MATHERIALS.AddRange(matherials);
        }
    }
}
