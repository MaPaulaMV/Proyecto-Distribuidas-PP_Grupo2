
package ec.edu.espe.distribuidas;

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
        while(true){
            new prueba(new Socket("localhost", 1234)).start();
            Thread.sleep(300);
        }

    }

    public static String getParametros(String mensaje) throws IOException {
        LOG.info(mensaje + " :");
        BufferedReader lec = new BufferedReader(new InputStreamReader(System.in));
        return lec.readLine();
    }
    private static final Logger LOG = Logger.getLogger(SocketCliente.class.getName());

}
