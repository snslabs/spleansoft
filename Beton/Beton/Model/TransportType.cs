using System;

namespace Beton.Model
{
    /// <summary>
    /// Тип транспортировки
    /// </summary>
    [Serializable]
    public class TransportType
    {
        public TransportType()
        {
        }

        public TransportType(int id, string name, decimal pricePerCube, decimal pricePerTonn, decimal pricePerTrip, decimal maxVolume, decimal maxWeight)
        {
            Id = id;
            Name = name;
            PricePerCube = pricePerCube;
            PricePerTonn = pricePerTonn;
            PricePerTrip = pricePerTrip;
            MaxVolume = maxVolume;
            MaxWeight = maxWeight;
        }

        public int Id { get; set; }
        public string Name { get; set; }
        public decimal PricePerCube { get; set; }
        public decimal PricePerTonn { get; set; }
        public decimal PricePerTrip { get; set; }
        public decimal MaxVolume { get; set; }
        public decimal MaxWeight { get; set; }
    }
}
