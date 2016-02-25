package puntoventaDao;

import ConexionSQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import puntoventaBean.SalidaBean;

/**
 *
 * @author HP
 */
public class VentaDao {

    Connection miconex = null;
    String proc = null;
    ResultSet rs = null;
    String Rps=null;
    
    public String findmaxidVenta(){
    miconex=ConexionDB.GetConnection();
    proc="{call maxidventa()}";
        try {
            CallableStatement cs= miconex.prepareCall(proc);
            rs=cs.executeQuery();
            while (rs.next()) {
                Rps = rs.getString("idmax");
            }
        } catch (Exception e) {
        }
    return Rps;
    }
    /**
     *
     * @param idproducto
     * @return
     */
    public ResultSet buscarproducto(String idproducto) {
        miconex = ConexionDB.GetConnection();
        proc = "{call buscarproducto(?)}";
        try {
            try (CallableStatement cs = miconex.prepareCall(proc)) {
                cs.setString("idproducto", idproducto);
                rs = cs.executeQuery();
            }
            miconex.close();
        } catch (SQLException ex) {
        }
        return rs;
    }
    
    public void terminarventa(SalidaBean VtaBean){
    miconex=ConexionDB.GetConnection();
    proc="{call insertarVenta(?,?,?,?,?)}";
        try {
        try (CallableStatement cs = miconex.prepareCall(proc)) {
            cs.setString("idventa", "");
            cs.setString("idcliente", "");
            cs.setString("fecha", Rps);
            cs.executeQuery();
        }
            miconex.close();
        } catch (Exception e) {
            //rps
        }
    
    }

}
