using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Text;

namespace BancoSocket
{
    class Tarjeta
    {
        private String fecha;
        public Tarjeta()
        {

        }
        
        public String ValidarTarjeta(String tarjeta,String cvv, String fecha,String monto, Conexion conexion)
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
                    result = "OK|" + data+"|"+monto;
                }
                else
                {
                    result = null;
                }
            }
           

            return result;
        }

        public String ConsultaCuenta(String validacion, Conexion conexion)
        {
            String result = null;
            
            String[] datosTarjeta = validacion.Split("|");
            // CUENTA,SALDO_DISPONIBLE,SALDO_REAL,SALDO_BLOQUEADO
            String cuenta = datosTarjeta[2];
            Decimal monto = Convert.ToDecimal(datosTarjeta[7]);
            String data = conexion.ConsultaCuenta(cuenta);
            String[] datosCuenta = data.Split("|");
            Decimal saldoDisponible = Convert.ToDecimal(datosCuenta[1]);
            Double saldoReal = Convert.ToDouble(datosCuenta[2]);
            Decimal saldoBloqueado = Convert.ToDecimal(datosCuenta[3]);
            //Disponible = saldoDisponible;
            //Bloqueado = saldoBloqueado;
            //Cuenta = cuenta;
            //String[] data=mensaje.Split("|");

            if (monto>saldoDisponible)
            {
                result = null;
            }
            else
            {
                result = "OK|"+cuenta+"|"+monto +"|" + saldoBloqueado;
            }
            

            return result;
        }

        public String RealizarConsumos(String validacion, Conexion conexion)
        {
            String result = null;
            String[] datosConsumo = validacion.Split("|");
            //"OK|"+cuenta+"|"+monto +"|" + saldoBloqueado;
            String cuenta = datosConsumo[1];
            Decimal monto = Convert.ToDecimal(datosConsumo[2]);
            Decimal saldoBloqueado = Convert.ToDecimal(datosConsumo[3]);
            String data = conexion.RealizarConsumo(monto,cuenta,saldoBloqueado);
            result = data;
            
            //Disponible = saldoDisponible;
            //Bloqueado = saldoBloqueado;
            //Cuenta = cuenta;
            //String[] data=mensaje.Split("|");

            return result;
        }

        public String RegistroCuenta(String validacion, Conexion conexion)
        {

            String result = null;
            //"REF   |TRAN|ES|TARJETA.........|CUENTA..............|DISPONI|CVV|FECHA|EST|MON|ES|CUENTA................|MONTO|BLOQUEADO|MES
            //"ASDE34|1234|OK|4010238084736772|05790803399822514614|5000,00|707|09/20|ACT|125|OK| 05790803399822514614 | 125 | 418,76" |5
            String[] datosTarjeta = validacion.Split("|");
            String transaccion = datosTarjeta[1];
            String tarjeta = datosTarjeta[3];
            String tipo = null;
            String referencia= datosTarjeta[0];
            String monto = datosTarjeta[9];
            String mes= datosTarjeta[14];
            

            switch (tarjeta.Substring(0, 1))
            {
                case "4":
                    tipo = "VIS";
                    break;
                case "5":
                    tipo = "MAS";
                    break;
            }
            this.fecha = GetTimestamp(DateTime.Now);
            
            //String transaccion, String tarjeta, String tipo, String monto, String meses, String fecha,String referencia )
            String data = conexion.RegistrarConsumo(transaccion,tarjeta,tipo,monto,mes,fecha,referencia);
            return result;
        }

        public static String GetTimestamp(DateTime value)
        {
            return value.ToString("yyyyMMddHHmmss");
        }
    }
}
