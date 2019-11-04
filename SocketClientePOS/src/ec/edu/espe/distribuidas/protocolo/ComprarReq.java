
package ec.edu.espe.distribuidas.protocolo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;

public class ComprarReq {
    
    private static final String codCompra="CMP";
    private String idTrans;
    private String numTarjet="4731565033672452";
    private String cvv="745";
    private String fechaExp="01/22";
    private BigDecimal valorCompra;
    private BigDecimal valorIva;
    private BigDecimal montoFinal;
    private int numMeses; 
    private int codigoVoucher;
    private String mensaje; 

    
    
    
    public ComprarReq(String idTrans, int codigoVoucher) {
        this.idTrans = idTrans;
        this.codigoVoucher = codigoVoucher;
    }
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    


    public String getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
    }

    public String getNumTarjet() {
        return numTarjet;
    }

    public void setNumTarjet(String numTarjet) {
        this.numTarjet = numTarjet;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(String fechaExp) {
        this.fechaExp = fechaExp;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public BigDecimal getValorIva() {
        return valorIva;
    }

    public void setValorIva(BigDecimal valorIva) {
        this.valorIva = valorIva;
    }

    public BigDecimal getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(BigDecimal montoFinal) {
        this.montoFinal = montoFinal;
    }

    public int getNumMeses() {
        return numMeses;
    }

    public void setNumMeses(int numMeses) {
        this.numMeses = numMeses;
    }

    public int getCodigoVoucher() {
        return codigoVoucher;
    }

    public void setCodigoVoucher(int codigoVoucher) {
        this.codigoVoucher = codigoVoucher;
    }

    
    public void compraMonto()
    {
        double valorMin=5.00;
        double valorMax=99.00;
        Random rand = new Random();
        //Randomico de compras del 5.00-99.0
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("#.##",simbolos);
        this.valorCompra= new BigDecimal(valorMin + ( valorMax - valorMin ) * rand.nextDouble());
        this.valorIva = this.valorCompra.multiply(new BigDecimal(0.12));
        this.montoFinal= this.valorCompra.add(this.valorIva);
       
       
    }
    
    
    public void genMese()
    {
        Random rand = new Random();
       this.numMeses=rand.nextInt((12-1)+1)+1;
    }
    
    
    public void marshall()
    {
        this.mensaje=codCompra+"|"+this.idTrans+"|"+this.numTarjet+"|"+this.cvv+"|"+this.fechaExp+"|"
        +this.valorCompra.setScale(2, this.valorCompra.ROUND_CEILING).toPlainString()+"|"
        +this.valorIva.setScale(2, this.valorIva.ROUND_CEILING).toPlainString()+"|"
        +this.montoFinal.setScale(2, this.montoFinal.ROUND_CEILING).toPlainString()+"|"
        +this.numMeses+"|"+this.codigoVoucher;
    }
   
    
}
