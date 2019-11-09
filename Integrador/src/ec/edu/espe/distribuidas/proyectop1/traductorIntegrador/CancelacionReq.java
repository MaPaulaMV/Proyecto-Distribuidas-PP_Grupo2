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
public class CancelacionReq {
    private static final String SEPARATOR = Pattern.quote("|");
    private String idTransacion;
    private String pin;
    private String numTarjeta;
    private String cvv;
    private String fechaExp;
    private String voucher;
    private String codPos;
    private String mensaje;
    public CancelacionReq(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIdTransacion() {
        return idTransacion;
    }

    public String getPin() {
        return pin;
    }

    public String getCodPos() {
        return codPos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }
    
    public void unmarshallCancelacion(){
       String parts[] = this.mensaje.split(SEPARATOR);
       this.idTransacion=parts[1];
       this.pin=parts[2];
       this.numTarjeta=parts[3];
       this.cvv=parts[4];
       this.fechaExp=parts[5];
       this.voucher=parts[6];
       this.codPos=parts[7];
    }

}
