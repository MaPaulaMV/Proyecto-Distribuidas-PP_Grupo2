
package ec.edu.espe.distribuidas;

import ec.edu.espe.distribuidas.protocolo.RegistroReq;
import ec.edu.espe.distribuidas.protocolo.RegistroRes;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketCliente {

    public static void main(String[] args) throws IOException, InterruptedException {

        int index=1;
        Socket socketRGP=new Socket("25.76.226.113", 1234);
        //peticion
        

        RegistroRes res = new RegistroRes(RegistroPOS(socketRGP));
        if(res.unmarshal())
        {
            while (true) {
                new WorkerThread(new Socket("25.76.226.113", 1234)).start();

                Thread.sleep(300);
            }
        }


    }

    public static String getParametros(String mensaje) throws IOException {
        LOG.info(mensaje + " :");
        BufferedReader lec = new BufferedReader(new InputStreamReader(System.in));
        return lec.readLine();
    }
    
    public static String RegistroPOS(Socket socketRGP) throws IOException
    {
        RegistroReq req = new RegistroReq();
        InputStream inp = socketRGP.getInputStream();
        BufferedReader brinp = new BufferedReader(new InputStreamReader(inp));
        DataOutputStream out = new DataOutputStream(socketRGP.getOutputStream());
        String message="";
        try {
            req.marshall();
            out.writeBytes(req.getMensaje() + "\n");
            out.flush();
            //Leer el mensaje
            message=brinp.readLine();
            socketRGP.close();
            System.out.println(message);
        } catch (Exception e) {
        }
        return message;
    }
    private static final Logger LOG = Logger.getLogger(SocketCliente.class.getName());

}
