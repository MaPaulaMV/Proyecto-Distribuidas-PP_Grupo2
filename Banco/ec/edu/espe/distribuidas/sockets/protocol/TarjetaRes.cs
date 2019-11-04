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
        private int validez;
        private const String SEPARATOR = "|";
        private String fecha;

        public TarjetaRes(String transaccion, String referencia,int validez)
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
                mensaje = transaccion +TarjetaRes.SEPARATOR+
                          referencia+TarjetaRes.SEPARATOR+
                          fecha+TarjetaRes.SEPARATOR+
                          "OK";
            }
            else
            {
                mensaje = transaccion + TarjetaRes.SEPARATOR +
                          referencia + TarjetaRes.SEPARATOR +
                          fecha + TarjetaRes.SEPARATOR +
                          "NK";
            }
        }

        public static String GetTimestamp(DateTime value)
        {
            return value.ToString("yyyyMMddHHmmssffff");
        }
        
    }
}
