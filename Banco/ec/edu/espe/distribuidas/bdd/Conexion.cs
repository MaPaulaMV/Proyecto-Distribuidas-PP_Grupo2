using System;
using System.Collections.Generic;
using System.Text;
using MySql.Data.MySqlClient;

namespace BancoSocket
{
    class Conexion
    {
        static string connectionString = "datasource=127.0.0.1;port=3306;username=root;password=facilita;database=proyecto;";
        MySqlConnection databaseConnection;
        
        public Conexion()
        {
            databaseConnection = new MySqlConnection(connectionString);
            Console.WriteLine("Conexion con la base");
        }

        

        public void conectar()
        {
            try
            {
                // Abre la base de datos
                databaseConnection.Open();
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
                databaseConnection.Close();
            }
            catch(Exception e)
            {
                Console.WriteLine(e.Message);
            }
        }

        public String ConsultaTarjeta(String tarjeta)//, String cvv, String fecha)
        {
            String result=null;
            MySqlCommand cmd = databaseConnection.CreateCommand();
            cmd.CommandText = "SELECT CVV,FECHA_EXP FROM tarjeta where NUM_TARJETA like " + tarjeta + " ;";
            conectar();
            MySqlDataReader reader = cmd.ExecuteReader();

            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    // En nuestra base de datos, el array contiene:  ID 0, FIRST_NAME 1
                    // Hacer algo con cada fila obtenida
                    string[] row = { reader.GetString(0), reader.GetString(1) };
                    result = row[0] + "|" + row[1];
                    Console.Write(tarjeta+"|"+result);
                }
            }
            else
            {
                Console.WriteLine("No se encontraron datos.");
                result = "1|Error";
            }
            desconectar();
            return tarjeta + "|" + result;
        }       


    }
}
