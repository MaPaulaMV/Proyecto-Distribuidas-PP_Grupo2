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
public class CompraReq {
    private static final String SEPARATOR = Pattern.quote("|");
    private int idTransaccion;
    private String numTarjeta;
    private String cvv;
    private String fechaExp;
    private Double monto;
    private Double iva;
    private int meses;
    private Double montoTotal;
    private int voucher;
    private String codLocal;
    private String mensaje;

    public CompraReq(String mensaje) {
        this.mensaje = mensaje;
    }

    public static String getSEPARATOR() {
        return SEPARATOR;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public String getFechaExp() {
        return fechaExp;
    }

    public Double getMonto() {
        return monto;
    }

    public Double getIva() {
        return iva;
    }

    public int getMeses() {
        return meses;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public int getVoucher() {
        return voucher;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getCodLocal() {
        return codLocal;
    }
    
    public void unmarshalCompra(){
        try{
        String parts[] = this.mensaje.split(SEPARATOR);
        this.idTransaccion=Integer.parseInt(parts[1]);
        this.numTarjeta=parts[2];
        this.cvv=parts[3];
        this.fechaExp=parts[4];
        this.monto=Double.parseDouble(parts[5]);
        this.iva=Double.parseDouble(parts[6]);
        this.meses=Integer.parseInt(parts[8]);
        this.montoTotal=Double.parseDouble(parts[7]);
        this.voucher=Integer.parseInt(parts[9]);
        this.codLocal=parts[10];}catch(Exception e){}
    }
}
