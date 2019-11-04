/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.proyecto;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

/**
 *
 * @author Paula
 */
public class StarterServer {
    private static final Logger LOG = Logger.getLogger(StarterServer.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        LOG.info("Iniciando server por puerto 666....");
        ServerSocket server = new ServerSocket(666);
        LOG.info("Esperando conexiones......");
        int clientes = 0;
        while (true) {
            new WorkerThread(server.accept()).start();
            clientes++;
            LOG.info("Cliente: " + clientes);

        }
    }
    
}
