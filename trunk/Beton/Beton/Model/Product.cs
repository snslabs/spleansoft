using System;
using System.Collections.Generic;
using System.ComponentModel;

namespace Beton.Model
{
    [Serializable]
    public class Product
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public BindingList<ProductComponent> Components { get; set;}

        public Product()
        {
            Components = new BindingList<ProductComponent>();
        }

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

        public Product(int id, string name, IList<ProductComponent> components)
        {
            Id = id;
            Name = name;
            Components = new BindingList<ProductComponent>(components);
        }

    }
}
