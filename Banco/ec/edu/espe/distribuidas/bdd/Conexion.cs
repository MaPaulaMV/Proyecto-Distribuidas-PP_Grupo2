using System;
using System.Collections.Generic;
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
            password = "facilita";
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
                    ;
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
            reader.Close();
            return result;
        }

        public String RealizarConsumo(Double consumo)//, String cvv, String fecha)
        {
            String result = null;
            conectar();
            MySqlCommand cmd = connection.CreateCommand();
            cmd.CommandText = "Select SALDO_DISPONIBLE FROM TARJETA WHERE NUM_TARJETA LIKE " + consumo + " ;";
            MySqlDataReader reader = cmd.ExecuteReader();

            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    // En nuestra base de datos, el array contiene:  ID 0, FIRST_NAME 1
                    // Hacer algo con cada fila obtenida
                    string[] row = { reader.GetString(0) };
                    result = row[0];
                    reader.Close();
                }
                desconectar();
            }
            else
            {
                Console.WriteLine("No se encontraron datos.");
                result = "1|No existe la tarjeta en el sistema";
                reader.Close();
            }

            return result;
        }


    }
}
