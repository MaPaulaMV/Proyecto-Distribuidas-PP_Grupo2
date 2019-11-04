using System;
using System.Collections.Generic;
using System.Text;

namespace BancoSocket
{
    class Tarjeta
    {
        Conexion conexion = new Conexion();
        public String ValidarTarjeta(String tarjeta,String cvv, String fecha)
        {
            String result = null;
            String data=conexion.ConsultaTarjeta(tarjeta);
            string[] words = data.Split('|');
            String tar = words[0];
            String ccv = words[1];
            String expi = words[2];
            if (tarjeta ==tar && ccv==cvv && expi==fecha)
            {
                
                result = "Valores correctos";
            }
            else
            {
                result = "No existe tarjeta";
            }

            return result;
        }
    }
}
