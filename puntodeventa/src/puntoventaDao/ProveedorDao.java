package puntoventaDao;

import ConexionSQL.ConexionDB;
import daoInterfaces.proveedorDaoInter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import puntoventaBean.ProveedorBean;

public class ProveedorDao implements proveedorDaoInter {

    ResultSet rs = null;
    Connection miconex = null;
    String proc = null;
    String Rps = null;
    String result;

    public ResultSet catproveedor() {
        miconex = ConexionDB.GetConnection();
        proc = "{call catproveedor()}";
        try {
            CallableStatement cs = miconex.prepareCall(proc);
                rs = cs.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    @Override
    public String ABCProveedor(ProveedorBean ProveBean) {
        miconex = ConexionDB.GetConnection();
        proc = "{call ABCProveedor(?,?,?,?,?,?,?,?,)}";
        try {
            CallableStatement cs = miconex.prepareCall(proc);
            cs.executeQuery();
        } catch (Exception e) {
            result = e.getMessage();
            if (result.equals("La instrucción no devolvió un conjunto de resultados.")) {
                result = null;
            }

        }
        return Rps;
    }
}
