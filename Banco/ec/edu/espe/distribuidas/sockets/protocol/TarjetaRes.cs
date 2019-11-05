using System;
using System.Collections.Generic;
using System.Text;

namespace BancoSocket.ec.edu.espe.distribuidas.sockets.protocol
{
    class TarjetaRes
    {
        private String transaccion;
        private String referencia;
        private String mensaje;
        private String validez;
        private const String SEPARATOR = "|";
        private String fecha;

        public string Mensaje { get => mensaje; set => mensaje = value; }

        public TarjetaRes(String transaccion, String referencia,String validez)
        {
            this.transaccion = transaccion;
            this.referencia = referencia;
            this.validez = validez;
            this.fecha= GetTimestamp(DateTime.Now);
        }

        /**
         * Identificador de transacción: Numeric(4)
         * Número de referencia de voucher: Varchar(6) 
         * Fecha-hora: TimeStamp: yyyyMMdd - HH:mm:ss:mili 
         * OK/NOK: Varchar(2) → OK, NK
         * 
         */
        public void marshall()
        {
            if(this.validez!=null)
            {
                Mensaje = transaccion+SEPARATOR+referencia+SEPARATOR+fecha+SEPARATOR+"OK";
                Console.Beep();
            }
            else
            {
                Mensaje = transaccion+SEPARATOR+referencia+SEPARATOR+fecha+SEPARATOR+"NK";
                Console.Beep();
            }
        }

        public static String GetTimestamp(DateTime value)
        {
            return value.ToString("yyyyMMddHHmmss");
        }
        
    }
}
