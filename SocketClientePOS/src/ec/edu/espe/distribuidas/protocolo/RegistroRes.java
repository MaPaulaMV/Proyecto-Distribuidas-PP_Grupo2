
package ec.edu.espe.distribuidas.protocolo;

import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class RegistroRes {
    private static final String SEPARATOR=Pattern.quote("|");
    private String mensaje;
    private String idTrans="";
    public RegistroRes(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
    }    
    
    public boolean unmarshal() throws ProtocolException 
    {
       String parts [] =this.mensaje.split(SEPARATOR);
       if(parts.length!=3){
            throw new ProtocolException("Mensaje Incorrecto");
        }
       if(parts[2].equals("OK"))
       {
           this.idTrans=parts[0];
           return true;
       }
        else
       {
           return false;
       }
        
    }
}
