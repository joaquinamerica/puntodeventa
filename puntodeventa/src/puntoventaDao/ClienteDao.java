package puntoventaDao;

import ConexionSQL.ConexionDB;
import daoInterfaces.clienteDaoInter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import puntoventaBean.ClienteBean;

/**
 *
 * @author HP
 */
public class ClienteDao implements clienteDaoInter {

    ResultSet rs = null;
    Connection miconex = null;
    String proc = null;
    String Rps = null;

    public ResultSet catcliente() {
        miconex = ConexionDB.GetConnection();
        proc = "{call catcliente()}";
        try {
            CallableStatement cs = miconex.prepareCall(proc);
            rs = cs.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public ResultSet buscarcliente(int idcliente) {
        miconex = ConexionDB.GetConnection();
        proc = "{ call buscarcliente(?)}";
        try {
            CallableStatement cs = miconex.prepareCall(proc);
            cs.setInt("idcliente", idcliente);
            rs = cs.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public String maximoId() {
        miconex = ConexionDB.GetConnection();
        String id = "";
        try {
            proc = "{call maxidcliente()}";
            CallableStatement cs = miconex.prepareCall(proc);
            rs = cs.executeQuery();
            //resultado.last();
            while (rs.next()) {
                id = rs.getString("id");
            }
        } catch (Exception e) {

        }

        return id;
    }

    @Override
    public String ABCCliente(ClienteBean CteBean) {
        miconex = ConexionDB.GetConnection();
        proc = "{call ClienteABC(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = miconex.prepareCall(proc);
            cs.setInt("proceso", CteBean.getProceso());//Cambiar el tipo de dato en el SP
            cs.setInt("idcliente", CteBean.getIdcliente());
            cs.setString("razonsocial", CteBean.getRazonsocial());
            cs.setString("telefono", CteBean.getTelefono());
            cs.setString("direccion", CteBean.getDireccion());
            cs.setString("referencia", CteBean.getReferencia());
            cs.setString("ciudad", CteBean.getCiudad());
            cs.setString("estado", CteBean.getEstado());
            cs.setString("cp", CteBean.getCp());
            cs.setString("curp", CteBean.getCurp());
            cs.setString("rfc", CteBean.getRfc());
            cs.setString("observaciones", CteBean.getObservaciones());
            cs.executeQuery();
        } catch (Exception e) {
            Rps = e.getMessage();
            if (Rps.equals("La instrucción no devolvió un conjunto de resultados.")) {
                Rps = null;
            }
            System.out.println(e);
        }
        return Rps;
    }

    public ResultSet filtrarCliente(String palabra) {
        miconex = ConexionDB.GetConnection();
        proc = "{call filtrocliente(?)}";
        try {
            CallableStatement cs = miconex.prepareCall(proc);
            cs.setString("nombre", palabra);
            rs = cs.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
}
