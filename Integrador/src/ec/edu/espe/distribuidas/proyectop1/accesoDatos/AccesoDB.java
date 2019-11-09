/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.proyectop1.accesoDatos;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import ec.edu.espe.distribuidas.proyectop1.traductorIntegrador.CancelacionReq;
import ec.edu.espe.distribuidas.proyectop1.traductorIntegrador.CompraReq;
import ec.edu.espe.distribuidas.proyectop1.traductorIntegrador.RegistroReq;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anthony
 */
public class AccesoDB {

    public int registroPos(RegistroReq req) {
        Connection cnn = Conexion.getConexionDB();
        CallableStatement sp;
        int resultado = 0;
        try {
            sp = cnn.prepareCall(" CALL registroPost(?,?,?,?)");
            sp.setString(1, req.getRuc());
            sp.setInt(2, req.getCodPos());
            sp.setString(3, req.getPin());
            sp.registerOutParameter(4, java.sql.Types.INTEGER);
            sp.execute();
            resultado = sp.getInt(4);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDB.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException e){
            
        }

        return resultado;
    }

    public int verificarTransaccion(int idTransaccion) {
        Connection cnn = Conexion.getConexionDB();
        CallableStatement sp;
        int resultado = 0;
        try {
            sp = cnn.prepareCall("CALL verificarTransaccion(?,?)");
            sp.setInt(1, idTransaccion);
            sp.registerOutParameter(2, java.sql.Types.INTEGER);
            sp.execute();
            resultado = sp.getInt(2);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public int verificarTransaccionCancelar(CancelacionReq req) {
        Connection cnn = Conexion.getConexionDB();
        CallableStatement sp;
        int resultado = 0;
        try {
            sp = cnn.prepareCall("CALL verificarTransaccionCancelacion(?,?,?,?)");
            sp.setInt(1, Integer.parseInt(req.getIdTransacion()));
            sp.setInt(2, Integer.parseInt(req.getCodPos()));
            sp.setString(3, req.getPin());
            sp.registerOutParameter(4, java.sql.Types.INTEGER);
            sp.execute();
            resultado = sp.getInt(4);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public void insertarTransaccion(String codLocal, String tipo, String numTarjeta, Double valor_compra, Double impuesto, Double monto_final, Date fecha, String estado) {
        Connection cnn = Conexion.getConexionDB();
        String query = "INSERT INTO transaccioni(COD_LOCAL,TIPO,NUM_TARJETA,VALOR_COMPRA,IMPUESTO,MONTO_FINAL,FECHA,ESTADO) VALUES (?,?,?,?,?,?,?,?)";
        java.util.Date d = new java.util.Date();
        java.sql.Date date2 = new java.sql.Date(d.getTime());
        try {
            PreparedStatement preparedStmt = cnn.prepareStatement(query);
            preparedStmt.setString(1, codLocal);
            preparedStmt.setString(2, tipo);
            preparedStmt.setString(3, numTarjeta);
            preparedStmt.setDouble(4, valor_compra);
            preparedStmt.setDouble(5, impuesto);
            preparedStmt.setDouble(6, monto_final);
            preparedStmt.setDate(7, date2);
            preparedStmt.setString(8, estado);
            preparedStmt.execute();
        } catch (SQLException ex) {
            //Logger.getLogger(AccesoDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error DB");
        }catch(NullPointerException a){
            
        }

    }

}
