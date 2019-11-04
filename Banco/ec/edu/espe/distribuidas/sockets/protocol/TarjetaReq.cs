using BancoSocket.ec.edu.espe.distribuidas.sockets.protocol;
using System;

namespace BancoSocket
{
    class TarjetaReq
    {
        private static String SEPARATOR="|";
        private String numTarjeta;
        private String cvv;
        private String fecha;
        private String transaccion;
        private String monto;
        private String referencia;
        private String mensaje;
        private String codigo;

        public TarjetaReq(String mensaje)
        {
            this.Mensaje=mensaje;
        }

        public string NumTarjeta { 
            get => numTarjeta; 
            set => numTarjeta = value; 
        }
        public string Cvv { get => cvv; set => cvv = value; }
        public string Fecha { get => fecha; set => fecha = value; }
        public string Transaccion { get => transaccion; set => transaccion = value; }
        public string Monto { get => monto; set => monto = value; }
        public string Referencia { get => referencia; set => referencia = value; }
        public string Mensaje { get => mensaje; set => mensaje = value; }
        public string Codigo { get => codigo; set => codigo = value; }

        public void unmarshall()
        {
            string[] partes = Mensaje.Split(SEPARATOR);
            if (partes.Length !=7)
            {
                throw new ProtocolException("Mensaje Incorrecto");
            }
            if (partes[0].Length == 3)
            {
                this.Codigo = partes[0];
            }
            else
            {
                throw new ProtocolException("Operacion Invalida");
            }

            try
            {
                this.Transaccion = partes[1];
            }
            catch (Exception e)
            {
                throw new ProtocolException("Numero de transaccion invalido");
            }
            try
            {
                this.NumTarjeta = partes[2];
            }
            catch (Exception e)
            {
                throw new ProtocolException("Numero de tarjeta invalido");
            }

            try
            {
                this.Cvv = partes[3];
            }
            catch (Exception e)
            {
                throw new ProtocolException("Numero de seguridad invalido");
            }
            try
            {
                this.Fecha = partes[4];
            }
            catch (Exception e)
            {
                throw new ProtocolException("Fecha de caducidad invalida");
            }
            try
            {
                this.Monto = partes[5];
            }
            catch (Exception e)
            {
                throw new ProtocolException("Monto invalido");
            }
            try
            {
                this.Referencia = partes[6];
            }
            catch (Exception e)
            {
                throw new ProtocolException("Numero de referencia invalido");
            }
        }

    }
}
