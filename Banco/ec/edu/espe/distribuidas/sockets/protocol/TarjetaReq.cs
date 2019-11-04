using System;

namespace BancoSocket
{
    class TarjetaReq
    {
        private String numTarjeta;
        private String cvv;
        private String fecha;

        public TarjetaReq(String tarjeta, String cvv, String fecha)
        {
            this.NumTarjeta = tarjeta;
            this.Cvv = cvv;
            this.Fecha = fecha;
        }

        public string NumTarjeta { get => numTarjeta; set => numTarjeta = value; }
        public string Cvv { get => cvv; set => cvv = value; }
        public string Fecha { get => fecha; set => fecha = value; }
    }
}
