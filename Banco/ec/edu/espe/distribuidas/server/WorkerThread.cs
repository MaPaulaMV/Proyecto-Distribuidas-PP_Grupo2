
using BancoSocket.ec.edu.espe.distribuidas.sockets.protocol;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Text;

namespace BancoSocket
{
    
    class WorkerThread
    {
        private Socket socket;
        Conexion conexion;
        int j = 0;
        public WorkerThread(Socket socket, int j,Conexion conexion)
        {
            this.conexion = conexion;
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
                
                
            }
            Console.Write("Mensaje: " + mensaje);
            TarjetaReq reqT = new TarjetaReq(mensaje);
            reqT.unmarshall();
            Tarjeta tarjetaOp= new Tarjeta();
            String valid = tarjetaOp.ValidarTarjeta(reqT.NumTarjeta, reqT.Cvv, reqT.Fecha, conexion);
            TarjetaRes resT = new TarjetaRes(reqT.Transaccion,reqT.Referencia,valid);
            resT.marshall();
            Console.WriteLine("Mensaje de salida" + resT.Mensaje);
            j++;
            //byte[] byData = System.Text.Encoding.ASCII.GetBytes("que mas mijin.... Att:ElBanco"+ j+ "\n");
            byte[] byData = System.Text.Encoding.ASCII.GetBytes(resT.Mensaje);
            String datas=resT.Mensaje;
            
            socket.Send(byData);
            socket.Close();
        }

        public void start() { 
            
        }

        
        

    }
}
