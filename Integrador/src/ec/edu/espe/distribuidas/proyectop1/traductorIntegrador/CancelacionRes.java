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
public class CancelacionRes {

    private static final String SEPARATOR = Pattern.quote("|");
    private String transaccion="";
    private String voucher="";
    private Double monTotal=null;
    private String fecha="";
    private String verificador="";
    private String mensaje="";

    public CancelacionRes(String transaccion, String voucher, Double monTotal, String fecha, String verificador) {
        this.transaccion = transaccion;
        this.voucher = voucher;
        this.monTotal = monTotal;
        this.fecha = fecha;
        this.verificador = verificador;
    }

    public String getMensaje() {
        return mensaje;
    }

    public CancelacionRes(String Respuesta) {
        try{
        String parts[] = this.mensaje.split(SEPARATOR);
        this.transaccion=parts[0];
        this.voucher=parts[1];
        this.monTotal=Double.parseDouble(parts[2]);
        this.fecha=parts[3];
        this.verificador=parts[4];}catch(NullPointerException e){}
    }
    public void marshalCancelacion(){
        this.mensaje=this.transaccion.concat(this.voucher).concat("|").concat(this.monTotal+"").concat("|").concat(this.fecha).concat("|").concat(this.verificador);
    }
    
}
