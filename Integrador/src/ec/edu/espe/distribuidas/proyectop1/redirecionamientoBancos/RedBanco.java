/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.proyectop1.redirecionamientoBancos;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Pattern;

/**
 *
 * @author Anthony
 */
public class RedBanco {
    private String mensaje;
    private static final String SEPARATOR = Pattern.quote("|");
    public RedBanco(String mensaje) {
        this.mensaje = mensaje;
    }
    public void marshallIntegradoBanco(){
        String parts[] = this.mensaje.split(SEPARATOR);
        this.mensaje=parts[0].concat("|").concat(parts[1]).concat("|").concat(parts[2]).concat("|").concat(parts[3]).concat("|").concat(parts[4]).concat("|").concat(parts[7]).concat("|").concat(parts[8]).concat("|").concat(parts[9]);
    }
    public String redirecionarBancos(String ipBanco, int puertoBanco) {
        InputStream inp = null;
        String respuesta="";
        try (Socket socketBanco = new Socket(ipBanco, puertoBanco)) {
            PrintWriter printWriter = new PrintWriter(socketBanco.getOutputStream());
            this.marshallIntegradoBanco();
            printWriter.write(mensaje);
            printWriter.flush();
            respuesta = (new BufferedReader(new InputStreamReader(socketBanco.getInputStream()))).readLine();
            socketBanco.close();
        }catch(Exception e){
            
        }
        return respuesta;
    }
}
