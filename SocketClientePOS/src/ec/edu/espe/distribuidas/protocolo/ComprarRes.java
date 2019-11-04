
package ec.edu.espe.distribuidas.protocolo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.net.ProtocolException;
import java.util.regex.Pattern;

public class ComprarRes {
    private static final String SEPARATOR=Pattern.quote("|");
    private String idTransaccion;
    private String codVoucher;
    private String mensaje;
    private String confirmacion;
    private String respuesta;

    public ComprarRes(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getCodVoucher() {
        return codVoucher;
    }

    public void setCodVoucher(String codVoucher) {
        this.codVoucher = codVoucher;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    
    
    public void unmarshall() throws ProtocolException
    {
        String parts [] =this.mensaje.split(SEPARATOR);
        if(parts.length!=3){
            throw new ProtocolException("Mensaje Incorrecto");
        }else
        {
            this.idTransaccion=parts[0];
            this.codVoucher=parts[1];
            this.confirmacion=parts[2];
        }
        
        if(this.confirmacion.equals("OK"))
        {
            this.respuesta="Transacción "+this.idTransaccion+" Voucher #: "+this.codVoucher+" aceptada y procesada";
        }else
        {
            this.respuesta="Transaccaión rechazada";
        }
       
    }
    
    
}
