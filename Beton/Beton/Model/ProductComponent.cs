using System;

namespace Beton.Model
{
    /// <summary>
    /// Составляющая производства продукта.
    /// Должно выполняться соотношение:
    /// AmountCube * Matherial.Density = AmountTonn
    /// </summary>
    [Serializable]
    public class ProductComponent
    {
        public Matherial Matherial { get; set; }
        public Decimal AmountTonn { get; set; }
        public Decimal AmountCube { get; set; }

        public ProductComponent()
        {
        }

        public ProductComponent(Matherial matherial, decimal amountTonn, decimal amountCube)
        {
            Matherial = matherial;
            AmountTonn = amountTonn;
            AmountCube = amountCube;

        }
    }
}
