using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Text;

namespace BancoSocket
{
    
    class WorkerThread
    {
        private Socket socket;
        int j = 0;
        public WorkerThread(Socket socket, int j)
        {
            this.socket = socket;
            this.j = j;
        }

        public void run()
        {
            byte[] b = new byte[100];
            int k = socket.Receive(b);
            String mensaje = "";
            String validez = "";
            //Console.WriteLine(k);
            Console.WriteLine("Recieved...");
            for (int i = 0; i < k; i++)
            {
                Console.Write(Convert.ToChar(b[i]));
                ASCIIEncoding asen = new ASCIIEncoding();
                _ = Convert.ToChar(b[i]);
                mensaje = mensaje+Convert.ToChar(b[i]);
                Console.Write(mensaje);
                //Operaciones opera = new Operaciones();
                //validez = opera.ValidarTarjeta("4929186357873", "74", "19-03-19");
                //Console.WriteLine(validez);
            }
            j++;
            byte[] byData = System.Text.Encoding.ASCII.GetBytes("que mas mijin.... Att:ElBanco"+ j+ "\n");
            //byte[] byData = System.Text.Encoding.ASCII.GetBytes(validez + "\n");
            socket.Send(byData);
            //socket.Close();
        }

        public void start() { 
            
        }

        
        

    }
}
