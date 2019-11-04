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
            string[] words = data.Split('|');
            String tar = words[0];
            String ccv = words[1];
            String expi = words[2];
            if (tarjeta ==tar && ccv==cvv && expi==fecha)
            {
                
                result = "OK";
            }
            else
            {
                result = null;
            }

            return result;
        }
    }
}
