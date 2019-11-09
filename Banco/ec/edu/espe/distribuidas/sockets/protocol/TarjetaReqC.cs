using BancoSocket.ec.edu.espe.distribuidas.sockets.protocol;
using System;

namespace BancoSocket
{
    class TarjetaReqC
    {
        private static String SEPARATOR="|";
        private String numTarjeta;
        private String cvv;
        private String fecha;
        private String transaccion;
        private String referencia;
        private String mensaje;
        private String codigo;

        public TarjetaReqC(String mensaje)
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
        public string Mensaje { get => mensaje; set => mensaje = value; }
        public string Codigo { get => codigo; set => codigo = value; }
        public string Referencia { get => referencia; set => referencia = value; }

        public void unmarshall()
        {
            //CNP|1233|5100123412341234|186|09/2020|000120
            string[] partes = Mensaje.Split(SEPARATOR);
            if (partes.Length !=6)
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
                this.referencia = partes[5];
            }
            catch (Exception e)
            {
                throw new ProtocolException("Referencia Invalido");
            }
        }

    }
}
