
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
        String opcion;
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
            Console.WriteLine("Recieved...");
            for (int i = 0; i < k; i++)
            {
                mensaje = mensaje+Convert.ToChar(b[i]);
            }
            Console.WriteLine("Mensaje: " + mensaje);
            try
            {
                opcion = mensaje.Substring(0, 3);
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
            
            Console.WriteLine("Opcion de transaccion: "+opcion);
            switch (opcion)
            {
                case "CMP":
                    TarjetaReq reqT = new TarjetaReq(mensaje);
                    reqT.unmarshall();
                    Tarjeta tarjetaOp = new Tarjeta();
                    String valid = tarjetaOp.ValidarTarjeta(reqT.NumTarjeta, reqT.Cvv, reqT.Fecha,reqT.Monto, conexion);
                    Console.WriteLine("Valid: "+valid);
                    String cuentaValidacion = tarjetaOp.ConsultaCuenta(valid,conexion);
                    Console.WriteLine("Valid: " + cuentaValidacion);
                    String cobroMonto = tarjetaOp.RealizarConsumos(cuentaValidacion,conexion);
                    String registroCuentra = reqT.Referencia+"|"+reqT.Transaccion+ "|" + valid +"|"+ cuentaValidacion + "|" + reqT.Mes;

                    String resRegistro=tarjetaOp.RegistroCuenta(registroCuentra,conexion); 
                    TarjetaRes resT = new TarjetaRes(reqT.Transaccion, reqT.Referencia, valid);
                    resT.marshall();
                    response = resT.Mensaje;
                    Console.WriteLine(response);
                    break;
                case "CNP":
                    TarjetaReqC req = new TarjetaReqC(mensaje);
                    req.unmarshall();
                    Tarjeta tarjeta = new Tarjeta();
                    String validar = tarjeta.ValidarTarjetaCancelacion(req.NumTarjeta, req.Cvv, req.Fecha, conexion);

                    Console.WriteLine("Valid: " + validar);
                    String cuentaValida = tarjeta.ConsultaCuenta(validar, conexion);
                    Console.WriteLine("Valid: " + cuentaValida);

                    //String cancelacion = tarjetaOp.RealizarConsumos(cuentaValidacion, conexion);
                    String registroCuentaC = req.Referencia + "|" + req.Transaccion + "|" + validar + "|" + cuentaValida + "|" + "0";

                    String resRegistroC = tarjeta.RegistroCuenta(registroCuentaC, conexion);


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
