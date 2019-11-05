/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.proyectop1.traductorIntegrador;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Anthony
 */
public class RegistroRes {
    private String idTransaccion;
    private String fechaActual;
    private String confirmacion;
    private String mensaje;

    public RegistroRes(String idTransaccion, String mensaje) {
        this.idTransaccion = idTransaccion;
        this.confirmacion = mensaje;
    }
    public void marshalRegistroPos(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.fechaActual= formatter.format(calendar.getTime());
        this.mensaje=this.idTransaccion.concat("|").concat(this.fechaActual).concat("|").concat(this.confirmacion);
    }

    public String getMensaje() {
        return mensaje;
    }

    
}
