using System;

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

        public decimal TransportExpenses
        {
            get; set;
        }

        public decimal TransportedAmount
        {
            get; set;
        }
    }
}
