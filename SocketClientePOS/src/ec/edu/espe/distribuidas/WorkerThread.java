
package ec.edu.espe.distribuidas;

import ec.edu.espe.distribuidas.protocolo.ComprarReq;
import ec.edu.espe.distribuidas.protocolo.ComprarRes;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkerThread extends Thread {

    private Socket socketCliente;
    private Boolean ban;
    public static int index =1;
    private static String id;
    private int cont;

    public WorkerThread(Socket sc, String id, int cont) {

        this.socketCliente = sc;
        this.id=id;
        this.cont=cont;
    }

    @Override
    public void run() {
        InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        
        ComprarReq compra= new ComprarReq(this.id, this.cont);
        try {
            inp = socketCliente.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socketCliente.getOutputStream());
            compra.newTarjeta();
            compra.compraMonto();
            compra.genMese();
            compra.marshall();
            out.writeBytes(compra.getMensaje()+"\n");
            System.out.println(compra.getMensaje());
            out.flush();
            String respuestaInt=brinp.readLine();
            //System.out.println(respuestaInt);
            ComprarRes res=new ComprarRes(respuestaInt);
            res.unmarshall();
            System.out.println(res.getRespuesta());
            
        } catch (IOException ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
