using System;
using System.Collections.Generic;
using System.Globalization;
using System.Text;
using MySql.Data.MySqlClient;

namespace BancoSocket
{
    class Conexion
    {
        //static string connectionString = "datasource=127.0.0.1;port=3306;username=root;password=facilita;database=proyecto;";
        //MySqlConnection databaseConnection;
        private MySqlConnection connection;
        private string server;
        private string database;
        private string user;
        private string password;
        private string port;
        static string connectionString;


        private string sslM;

        public Conexion()
        {
            server = "localhost";
            database = "proyecto";
            user = "root";
            password = "";
            port = "3306";
            sslM = "none";

            connectionString = String.Format("server={0};port={1};user id={2}; password={3}; database={4}; SslMode={5}", server, port, user, password, database, sslM);

            connection = new MySqlConnection(connectionString);
            //databaseConnection = new MySqlConnection(connectionString);
            //Console.WriteLine("Conexion con la base");
        }

        public void conectar()
        {
            try
            {
                connection.Open();
                Console.WriteLine("Base conectada");
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
        }

        public void desconectar()
        {
            try
            {
                connection.Close();
            }
            catch(Exception e)
            {
                Console.WriteLine(e.Message);
            }
        }

        public String ConsultaTarjeta(String tarjeta)//, String cvv, String fecha)
        {
            String result=null;
            conectar();
            MySqlCommand cmd = connection.CreateCommand();
            cmd.CommandText = "SELECT COD_CUENTA,SALDO_DISPONIBLE,CVV,FECHA_EXP,ESTADO FROM tarjeta where NUM_TARJETA like " + tarjeta + " ;";
            MySqlDataReader reader = cmd.ExecuteReader();
            String res=null;
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    // CUENTA,SALDO,CVV,FECHA,EST
                    // Hacer algo con cada fila obtenida
                    string[] row = { reader.GetString(0), reader.GetString(1), reader.GetString(2), reader.GetString(3), reader.GetString(4) };
                    res = row[0] + "|" + row[1] + "|" + row[2] + "|" + row[3] + "|" + row[4];
                    //Console.WriteLine(tarjeta+"|"+result);
                    
                }
                result = tarjeta + "|" + res;
                reader.Close();
                desconectar();
            }
            else
            {
                Console.WriteLine("No se encontraron datos.");
                result = null;
            }
            //reader.Close();
            return result;
        }

        public String ConsultaCuenta(String cuenta)//, String cvv, String fecha)
        {
            String result = null;
            conectar();
            MySqlCommand cmd = connection.CreateCommand();
            cmd.CommandText = "SELECT SALDO_DISPONIBLE,SALDO_REAL,SALDO_BLOQUEADO FROM saldo where COD_CUETA like "+"'"+ cuenta + "'"+";";
            MySqlDataReader reader = cmd.ExecuteReader();
            String res = null;
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    // SALDO_DISPONIBLE,SALDO_REAL,SALDO_BLOQUEADO
                    string[] row = { reader.GetString(0), reader.GetString(1), reader.GetString(2) };
                    res = row[0] + "|" + row[1] + "|" + row[2];

                }
                result = cuenta + "|" + res;
                reader.Close();
                desconectar();
            }
            else
            {
                Console.WriteLine("No se encontraron datos.");
                result = null;
            }
            //reader.Close();
            return result;
        }

        public String RealizarConsumo(Decimal consumo, String cuenta, Decimal bloqueado)
        {
            String result = null;
            bloqueado += consumo;
            conectar();
            MySqlCommand cmd = connection.CreateCommand();
            cmd.CommandText = "UPDATE SALDO SET SALDO_BLOQUEADO="+ bloqueado.ToString(CultureInfo.CreateSpecificCulture("-SE")) + " WHERE COD_CUETA like " + "'" + cuenta + "'" + ";";
            /**/
            if (cmd.ExecuteNonQuery()==1)
            {
                
                result = "OK";
            }
            else
            {
                result = null;
            }
            desconectar();

            return result;
        }

        public String RegistrarConsumo(String transaccion, String tarjeta, String tipo, String monto, String meses, String fecha,String referencia )
        {
            String result = null;
            Double impuesto = Convert.ToDouble(monto)*0.12;
            Double total = Convert.ToDouble(monto) + impuesto;
            conectar();
            MySqlCommand cmd = connection.CreateCommand();
            //COD_TRANSACCION,NUM_TARJETA,TIPO.VALOR_COMPRA,IMPUESTO,MONTO,MESES,FECHA,ESTADO,REFERENCIA_VOUCHER
            
            cmd.CommandText = "INSERT INTO TRANSACCION VALUES('"+transaccion+"','"+tarjeta+"','"+tipo+"','" + monto + "','" + impuesto + "','" + total + "','" + meses + "','" + fecha + "','PEN','" + referencia +"');";
            if (cmd.ExecuteNonQuery() == 1)
            {

                result = "OK";
            }
            else
            {
                result = null;
            }
            desconectar();
            return result;
        }
        public String RealizarCancelacion(String transaccion, String tarjeta, String tipo, String monto, String meses, String fecha, String referencia)
        {
            String result = null;
            Double impuesto = Convert.ToDouble(monto) * 0.12;
            Double total = Convert.ToDouble(monto) + impuesto;
            conectar();
            MySqlCommand cmd = connection.CreateCommand();
            //COD_TRANSACCION,NUM_TARJETA,TIPO.VALOR_COMPRA,IMPUESTO,MONTO,MESES,FECHA,ESTADO,REFERENCIA_VOUCHER

            cmd.CommandText = "INSERT INTO TRANSACCION VALUES('" + transaccion + "','" + tarjeta + "','CAN','" + monto + "','" + impuesto + "','" + total + "','" + meses + "','" + fecha + "','PEN','" + referencia + "');";
            if (cmd.ExecuteNonQuery() == 1)
            {

                result = "OK";
            }
            else
            {
                result = null;
            }
            desconectar();
            return result;
        }


    }
}
