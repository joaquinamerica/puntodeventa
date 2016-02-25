package puntoventaDao;

import ConexionSQL.ConexionDB;
import daoInterfaces.familiaDaoInter;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import puntoventaBean.FamiliaBean;

/**
 *
 * @author Joaquin
 */
public class FamiliaDao implements familiaDaoInter {

    Connection miConex = null;
    String result = null;
    String proc = null;
    ResultSet resultado = null;

    public ResultSet catFamiliaD() {
        miConex = ConexionDB.GetConnection();
        try {
            proc = "{call catfamilia()}";
            CallableStatement cs = miConex.prepareCall(proc);
            resultado = cs.executeQuery();
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
        }
        return resultado;
    }
    @Override
    public String insert(FamiliaBean FamBean) {
        miConex = ConexionDB.GetConnection();
        try {
            proc = "{call FamiliaABC(?,?,?)}";
            try (CallableStatement cs = miConex.prepareCall(proc)) {
                cs.setString("idfamilia", "1");
                cs.setString("descripcion", FamBean.getDescripcion());
                cs.setString("proceso", "1");
                cs.executeQuery();
            }
            miConex.close();
        } catch (Exception e) {
            result = e.getMessage();
            if (result.equals("La instrucción no devolvió un conjunto de resultados.")) {
                result = null;
            }
        }
        return result;
    }

    @Override
    public String modificar(FamiliaBean FamBean) {
        miConex = ConexionDB.GetConnection();
        try {
            proc = "{call FamiliaABC(?,?,?)}";
            try (CallableStatement cs = miConex.prepareCall(proc)) {
                cs.setString("idfamilia", FamBean.getIdfamilia().toString());
                cs.setString("descripcion", FamBean.getDescripcion());
                cs.setString("proceso", "3");
                cs.executeQuery();
            }
            miConex.close();
        } catch (Exception e) {
            result = e.getMessage();
            if (result.equals("La instrucción no devolvió un conjunto de resultados.")) {
                result = null;
            }
        }
        return result;
    }

    @Override
    public String eliminar(FamiliaBean FamBean) {
        miConex = ConexionDB.GetConnection();
        try {
            proc = "{call FamiliaABC(?,?,?)}";
            try (CallableStatement cs = miConex.prepareCall(proc)) {
                cs.setString("idfamilia", FamBean.getIdfamilia().toString());
                cs.setString("descripcion", "3");
                cs.setString("proceso", "2");
                cs.executeQuery();
            }
        } catch (Exception e) {
            result = e.getMessage();
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
            proc = "{call maxidFamilia()}";
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
