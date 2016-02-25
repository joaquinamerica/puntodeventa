/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventaDao;

import ConexionSQL.ConexionDB;
import daoInterfaces.umedidaDaoInter;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import puntoventaBean.UmedidaBean;

/**
 *
 * @author JGamboa
 */
public class UmedidaDao implements umedidaDaoInter {

    Connection miConex = null;
    String result = null;
    String proc = null;
    ResultSet resultado = null;

    public ResultSet catUmedida() {
        miConex = ConexionDB.GetConnection();
        try {
            proc = "{call catumedida()}";
            CallableStatement cs = miConex.prepareCall(proc);
            resultado = cs.executeQuery();
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
        }
        return resultado;
    }

    /**
     *
     * @param UmedBean
     * @return
     */
    @Override
    public String ABCUMedida(UmedidaBean UmedBean) {
        miConex = ConexionDB.GetConnection();
        try {
            proc = "{call UmedidaABC(?,?,?)}";
            CallableStatement cs = miConex.prepareCall(proc);
            cs.setInt("proceso", UmedBean.getProceso());
            cs.setInt("idumedida", UmedBean.getIdumedida());
            cs.setString("descripcion", UmedBean.getDescripcion());
            cs.executeQuery();
        } catch (Exception e) {
            result = e.getMessage();
            System.out.println(e);
            if (result.equals("La instrucción no devolvió un conjunto de resultados.")) {
                result = null;
            }
        }
        return result;
    }

    public String maximoId() {
        miConex = ConexionDB.GetConnection();
        String id = "";
        try {
            proc = "{call maxidumedida()}";
            CallableStatement cs = miConex.prepareCall(proc);
            resultado = cs.executeQuery();
            //resultado.last();
            while (resultado.next()) {
                id = resultado.getString("id");
            }
        } catch (Exception e) {

        }

        return id;
    }
}
  
  