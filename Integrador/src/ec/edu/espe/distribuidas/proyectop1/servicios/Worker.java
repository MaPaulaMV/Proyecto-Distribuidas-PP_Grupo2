/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.proyectop1.servicios;

import ec.edu.espe.distribuidas.proyectop1.accesoDatos.AccesoDB;
import ec.edu.espe.distribuidas.proyectop1.redirecionamientoBancos.RedBanco;
import ec.edu.espe.distribuidas.proyectop1.traductorIntegrador.CancelacionReq;
import ec.edu.espe.distribuidas.proyectop1.traductorIntegrador.CancelacionRes;
import ec.edu.espe.distribuidas.proyectop1.traductorIntegrador.CompraReq;
import ec.edu.espe.distribuidas.proyectop1.traductorIntegrador.CompraRes;
import ec.edu.espe.distribuidas.proyectop1.traductorIntegrador.RegistroReq;
import ec.edu.espe.distribuidas.proyectop1.traductorIntegrador.RegistroRes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Anthony
 */
public class Worker extends Thread {
    public static final String IP_BANCOA = "25.77.86.95";
    public static final int PORT_BANCOA = 666;
    public static final String IP_BANCOB = "192.168.1.104";
    public static final int PORT_BANCOB = 666;
    private static final String SEPARATOR = Pattern.quote("|");
    private Socket socketClientePos;
    InputStream inp = null;
    BufferedReader brinp = null;
    PrintWriter out = null;
    public Worker(Socket socketCliente) {
        this.socketClientePos = socketCliente;
    }
    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            AccesoDB db = new AccesoDB();
            int resultadoT = 0;
            inp = socketClientePos.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new PrintWriter(socketClientePos.getOutputStream());
            String line = brinp.readLine();
            String parts[] = line.split(SEPARATOR);
            System.out.println(line);
            switch (parts[0]) {
                case "RGP":
                    RegistroReq req = new RegistroReq(line);
                    req.unmarshallRegistroPos();
                    RegistroRes res;
                    resultadoT = db.registroPos(req);
                    if (resultadoT <= 0) {
                        res = new RegistroRes("-1", "NK");
                    } else {
                        res = new RegistroRes(resultadoT + "", "OK");
                    }
                    res.marshalRegistroPos();
                    out.write(res.getMensaje());
                    out.flush();
                    break;
                case "CMP":
                    CompraReq reqC = new CompraReq(line);
                    reqC.unmarshalCompra();
                    resultadoT = db.verificarTransaccion(reqC.getIdTransaccion());
                    CompraRes resC = null;
                    if (resultadoT > 0) {
                        RedBanco red = new RedBanco(reqC.getMensaje());
                        db.insertarTransaccion(reqC.getCodLocal(), "CMP", reqC.getNumTarjeta(), reqC.getMonto(), reqC.getIva(), reqC.getMontoTotal(), calendar.getTime(), "CMP");
                        String respuestBanco = "";
                        switch (reqC.getNumTarjeta().charAt(0)) {
                            case '4':
                                respuestBanco = red.redirecionarBancos(IP_BANCOA, PORT_BANCOA);
                                break;
                            case '5':
                                respuestBanco = red.redirecionarBancos(IP_BANCOB, PORT_BANCOB);
                                break;
                        }
                        resC = new CompraRes(respuestBanco);
                    } else {
                        resC = new CompraRes("-1", "-1", "NK");
                    }
                    resC.marshallCompras();
                    out.write(resC.getMensaje());
                    out.flush();
                    break;
                case "CNP":
                    CancelacionReq reqCa = new CancelacionReq(line);
                    reqCa.unmarshallCancelacion();
                    CancelacionRes resCa;
                    resultadoT = db.verificarTransaccionCancelar(reqCa);
                    if (resultadoT > 0) {
                        db.insertarTransaccion(reqCa.getCodPos(), "CNP", reqCa.getNumTarjeta(),0.0,0.0,0.0, calendar.getTime(), "CNP");
                        RedBanco red = new RedBanco(reqCa.getMensaje());
                        String respuestBanco = "";
                        switch (reqCa.getNumTarjeta().charAt(0)) {
                            case '4':
                                respuestBanco = red.redirecionarBancos(IP_BANCOA, PORT_BANCOA);
                                break;
                            case '5':
                                respuestBanco = red.redirecionarBancos(IP_BANCOB, PORT_BANCOB);
                                break;
                        }
                        resCa = new CancelacionRes(respuestBanco);
                    } else {
                        resCa = new CancelacionRes("-1", "-1", 0.0, "N/A", "NK");
                    }
                    resCa.marshalCancelacion();
                    out.write(resCa.getMensaje());
                    out.flush();
                    break;
            }
            socketClientePos.close();
        } catch (Exception ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

    }

}
