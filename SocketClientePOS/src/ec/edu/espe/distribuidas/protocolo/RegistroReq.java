/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.protocolo;

/**
 *
 * @author Paula
 */
public class RegistroReq {
    private String RUC="1723551069001";
    private int codPOS=4;
    private String PIN="1234";
    private String COD="RGP";
    private String mensaje;

    public RegistroReq() {
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public int getCodPOS() {
        return codPOS;
    }

    public void setCodPOS(int codPOS) {
        this.codPOS = codPOS;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
    public void marshall(){
        this.mensaje=this.COD+"|"+this.RUC+"|"+this.codPOS+"|"+this.PIN;
    }
    
}
