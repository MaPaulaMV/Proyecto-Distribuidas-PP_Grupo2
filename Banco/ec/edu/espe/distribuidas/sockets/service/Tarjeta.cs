using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Text;

namespace BancoSocket
{
    class Tarjeta
    {
        
        public Tarjeta()
        {

        }
        
        public String ValidarTarjeta(String tarjeta,String cvv, String fecha, Conexion conexion)
        {

            String result = null;
            String data=conexion.ConsultaTarjeta(tarjeta);
            if (data==null)
            {
                result = null;
            }
            else
            {
                string[] words = data.Split('|');
                // TARJETA,CUENTA,SALDO,CVV,FECHA,EST
                String tar = words[0];
                String cuenta = words[1];
                String saldo = words[2];
                String ccv = words[3];
                String expi = words[4];
                String est = words[5];
                if (tarjeta == tar && ccv == cvv && expi == fecha && est == "ACT")
                {
                    result = "OK|" + data;
                }
                else
                {
                    result = null;
                }
            }
           

            return result;
        }

        public String RealizarConsumo(String cuenta,Double monto, Conexion conexion)
        {
            String result = null;
            String mensaje = conexion.ConsultaTarjeta(cuenta);
            String[] data=mensaje.Split("|");
            Double saldoDisponible=Convert.ToDouble(data[1]);
            if (monto>saldoDisponible)
            {
                result = null;
            }
            else
            {
                result = mensaje;
            }
            

            return result;
        }
    }
}
