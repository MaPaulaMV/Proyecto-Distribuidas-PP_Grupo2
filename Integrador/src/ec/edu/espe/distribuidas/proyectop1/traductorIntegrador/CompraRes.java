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
public class CompraRes {
    private static final String SEPARATOR = Pattern.quote("|");
    private String transaccion="";
    private String voucher="";
    private String hora="";
    private String verificador="";
    private String mensaje="";

    public CompraRes(String respuesta) {
        try{
        String parts[] = respuesta.split(SEPARATOR);
            System.out.println("STIVEN"+respuesta);
        this.transaccion=parts[0];
        this.voucher=parts[1];
        this.hora=parts[2];
        this.verificador=parts[3];}catch(NullPointerException e){}
    }

    public CompraRes(String transaccion, String voucher, String verificador) {
        this.transaccion = transaccion;
        this.voucher = voucher;
        this.verificador = verificador;
    }

    public String getMensaje() {
        return mensaje;
    }
    public void marshallCompras(){
        this.mensaje=this.transaccion.concat("|").concat(this.voucher).concat("|").concat(this.hora).concat("|").concat(this.verificador);
    }
}
