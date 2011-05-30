using System;

namespace Beton.Model
{
    [Serializable]
    public class TransportPosition
    {
        public int Id { get; set; }
        public Position Position { get; set; }
        public decimal Volume
        {
            get; set;
        }
        public TransportType TransportType { get; set; }
        public decimal RatePerKm {
            get { return TransportType == null ? 0 : TransportType.PricePerCube; } 
            set { }
        }
        public decimal RatePerTrip
        {
            get { return TransportType == null ? 0 : TransportType.PricePerTrip; }
            set { }

        }
        public decimal Distance { get; set; }

        public int TripCount
        {
            get
            {
                if (TransportType != null)
                {
                    return (int)decimal.Round(Volume/TransportType.MaxVolume, 0, MidpointRounding.AwayFromZero);
                }
                return 0;
            }
            set { }
        }

        public decimal PositionPrice
        {
            get
            {
                return TripCount*RatePerTrip + Volume*Distance*RatePerKm;
            }
            set { }
        }
    }
}
