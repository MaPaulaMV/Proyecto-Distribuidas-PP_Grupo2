
package ec.edu.espe.distribuidas.protocolo;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LecturaTxt {
    
    private static final String SEPARATOR=Pattern.quote("|");
    String rutaAp = "ArchivosTarjetas/Tarjetas.txt";
    
       
    
    
    String newTarjeta() throws IOException {

        String tarjetaPipes = Files.readAllLines(Paths.get(rutaAp)).get((int) (Math.random() * 29));
        String parts [] =tarjetaPipes.split(SEPARATOR);
        
        
        return "";
    }
}
