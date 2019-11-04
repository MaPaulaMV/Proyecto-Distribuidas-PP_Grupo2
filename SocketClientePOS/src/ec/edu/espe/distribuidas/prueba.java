
package ec.edu.espe.distribuidas;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class prueba extends Thread {

    private Socket socketCliente;
    public static int index =1;

    public prueba(Socket sc) {

        this.socketCliente = sc;
    }

    @Override
    public void run() {
        InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        try {
            inp = socketCliente.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        brinp = new BufferedReader(new InputStreamReader(inp));
        try {
            out = new DataOutputStream(socketCliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.writeBytes("hola" + index + "\n");
        } catch (IOException ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        String respose = null;
        try {
            respose = brinp.readLine();
        } catch (IOException ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(respose +"->"+index);
        index++;
    }

}
