/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.proyecto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paula
 */
public class WorkerThread extends Thread{
    private static final Logger LOG = Logger.getLogger(WorkerThread.class.getName());
    private Socket socketPOS=new Socket();
    private String mensaje;
    private static final String IPBanco="";
    private static final int PuertoBanco=666;

    public WorkerThread(Socket socket) {
        this.socketPOS=socket;
    }

    @Override
    public void run() {
        BufferedReader readPOS=null;
        try {
            readPOS=new BufferedReader(new InputStreamReader(this.socketPOS.getInputStream()));
            PrintWriter sendPOS=new PrintWriter(this.socketPOS.getOutputStream());
            mensaje=null;
            while(true){
                mensaje=readPOS.readLine();
                System.out.println(mensaje);
                //////UNMARSHALL//////
                
                /////MARSAHL/////////
                try(Socket socketBanco=new Socket(IPBanco,PuertoBanco)){
                    //Enviar a banco info de POS
                    PrintWriter sendBanco=new PrintWriter(socketBanco.getOutputStream());
                    sendBanco.write(mensaje+"\n");
                    sendBanco.flush();
                    
                    //Obtener Respuesta del Banco
                    BufferedReader readBanco=new BufferedReader(new InputStreamReader(socketBanco.getInputStream()));
                    String mensajeBanco=readBanco.readLine();
                    
                    //Enviar respuesta al POS
                    sendPOS.write(mensajeBanco+"\n");
                    sendPOS.flush();
                    
                    //Cerrar
                    readPOS.close();
                    readBanco.close();
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
