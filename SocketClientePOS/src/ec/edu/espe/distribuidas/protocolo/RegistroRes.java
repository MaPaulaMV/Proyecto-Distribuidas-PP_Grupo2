
package ec.edu.espe.distribuidas.protocolo;

import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class RegistroRes {
    private static final String SEPARATOR=Pattern.quote("|");
    private String mensaje;

    public RegistroRes(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
    public boolean unmarshal() throws ProtocolException 
    {
       String parts [] =this.mensaje.split(SEPARATOR);
       if(parts.length!=3){
            throw new ProtocolException("Mensaje Incorrecto");
        }
       if(parts[2].equals("OK"))
       {
           return true;
       }
        else
       {
           return false;
       }
        
    }
}
