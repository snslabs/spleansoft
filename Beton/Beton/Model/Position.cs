using System;

namespace Beton.Model
{
    [Serializable]
    public class Position
    {
        public int Id { get; set; }
        public Product Product { get; set; }
        public decimal Volume { get; set; }
        public decimal AddedPrice { get; set; }
        public decimal TotalPrice { get; set; }

        public Position(int id, Product product, decimal volume, decimal addedPrice, decimal totalPrice)
        {
            Id = id;
            Product = product;
            Volume = volume;
            AddedPrice = addedPrice;
            TotalPrice = totalPrice;
        }
    }
}
