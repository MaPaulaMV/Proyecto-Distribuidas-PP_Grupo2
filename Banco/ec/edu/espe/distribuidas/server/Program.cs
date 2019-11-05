using System;
using System.Net.Sockets;
using System.Net;
using System.Threading;
using System.Text;

namespace BancoSocket
{
    class Program
        
    {
        static Conexion conexion;
        static void Main(string[] args)
        {
            conexion = new Conexion();
            
            Conectar();  //Llamar a función Conectar, es ajeno al tema
            Thread.CurrentThread.CurrentCulture = new System.Globalization.CultureInfo("en-EN");
        }
        
        public static void Conectar()
        {
           
            Socket miPrimerSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            // paso 2 - creamos el socket
            IPEndPoint miDireccion = new IPEndPoint(IPAddress.Any, 666);
            //paso 3 -IPAddress.Any significa que va a escuchar al cliente en toda la red
            try
            {
                // paso 4
                miPrimerSocket.Bind(miDireccion); // Asociamos el socket a miDireccion
                miPrimerSocket.Listen(1); // Lo ponemos a escucha
                Console.WriteLine("Escuchando...");
                Random rnd = new Random();
                
                int clientes = 0;
                while (true)
                {
                    //int wait = rnd.Next(500);
//                    Console.WriteLine("Espera "+wait);
                    clientes++;
                    new WorkerThread(miPrimerSocket.Accept(), clientes, conexion).run();                                      
                    Console.WriteLine(clientes+"  ");
                    Thread.Sleep(1000);
                }
                
            }
            catch (Exception error)
            {
                Console.WriteLine("Error: {0}", error.ToString());
            }
            Console.WriteLine("Presione cualquier tecla para terminar");
            Console.ReadLine();
        }

    }
}
