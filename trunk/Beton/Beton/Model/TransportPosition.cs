using System;

namespace Beton.Model
{
    [Serializable]
    public class TransportPosition
    {
        public int Id { get; set; }
        public Position Position { get; set; }
        public decimal Volume { get; set; }
        public TransportType TransportType { get; set; }
        public decimal RatePerKm { get; set; }
        public decimal RatePerTrip { get; set; }
        public decimal Distance { get; set; }
    }
}
