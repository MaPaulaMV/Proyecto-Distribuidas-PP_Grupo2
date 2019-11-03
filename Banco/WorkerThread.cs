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
            
            //Console.WriteLine(k);
            Console.WriteLine("Recieved...");
            for (int i = 0; i < k; i++)
            {
                Console.Write(Convert.ToChar(b[i]));
                ASCIIEncoding asen = new ASCIIEncoding();
                _ = Convert.ToChar(b[i]);
            }
            j++;
            byte[] byData = System.Text.Encoding.ASCII.GetBytes("que mas mijin.... Att:ElBanco"+ j+ "\n");
            socket.Send(byData);
            //socket.Close();
        }

        public void start() { 
            
        }
        

    }
}
