
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
            String response = null;
            //Console.WriteLine(k);
            Console.WriteLine("Recieved...");
            for (int i = 0; i < k; i++)
            {
                //Console.WriteLine(Convert.ToChar(b[i]));
                ASCIIEncoding asen = new ASCIIEncoding();
                _ = Convert.ToChar(b[i]);
                mensaje = mensaje+Convert.ToChar(b[i]);
                
                
            }
            Console.WriteLine("Mensaje: " + mensaje);
            String opcion = mensaje.Substring(0,3);
            Console.WriteLine("Opcion de transaccion: "+opcion);
            switch (opcion)
            {
                case "CMP":
                    TarjetaReq reqT = new TarjetaReq(mensaje);
                    reqT.unmarshall();
                    Tarjeta tarjetaOp = new Tarjeta();
                    String valid = tarjetaOp.ValidarTarjeta(reqT.NumTarjeta, reqT.Cvv, reqT.Fecha, conexion);
                    //tring cobro = tarjetaOp.RealizarConsumo(reqT.);
                    TarjetaRes resT = new TarjetaRes(reqT.Transaccion, reqT.Referencia, valid);
                    resT.marshall();
                    //Console.WriteLine("Mensaje de salida" + resT.Mensaje);
                    response = resT.Mensaje;
                    Console.WriteLine(response);
                    break;
                case "CNP":
                    TarjetaReqC req = new TarjetaReqC(mensaje);
                    req.unmarshall();
                    Tarjeta tarjeta = new Tarjeta();
                    String validar = tarjeta.ValidarTarjeta(req.NumTarjeta, req.Cvv, req.Fecha, conexion);
                    TarjetaResC resp = new TarjetaResC(req.Transaccion,req.Referencia,validar);
                    resp.marshall();
                    //Console.WriteLine("Mensaje de salida" + resp.Mensaje);
                    response = resp.Mensaje;
                    Console.WriteLine(response);
                    break;
            }
            
            j++;
            //byte[] byData = System.Text.Encoding.ASCII.GetBytes("que mas mijin.... Att:ElBanco"+ j+ "\n");
            byte[] byData = System.Text.Encoding.ASCII.GetBytes(response);
            socket.Send(byData);
            socket.Close();
        }

        public void start() { 
            
        }

        
        

    }
}
