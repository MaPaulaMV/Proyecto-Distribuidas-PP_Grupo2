/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.proyectop1.traductorIntegrador;

import java.util.regex.Pattern;

/**
 *
 * @author Anthony
 */
public class RegistroReq {
    private static final String SEPARATOR = Pattern.quote("|");
    private String mensaje;
    private String ruc;
    private String pin;
    private int codPos;
    public RegistroReq(String mensaje) {
        this.mensaje = mensaje;
    }

    public static String getSEPARATOR() {
        return SEPARATOR;
    }

    public String getRuc() {
        return ruc;
    }

    public String getPin() {
        return pin;
    }

    public int getCodPos() {
        return codPos;
    }

    public String getMensaje() {
        return mensaje;
    }
    public void unmarshallRegistroPos(){
        try{
        String parts[] = this.mensaje.split(SEPARATOR);
        this.ruc=parts[1];
        this.pin=parts[3];
        this.codPos=Integer.parseInt(parts[2]);
        }catch(Exception e){
            
        }
    }
}
