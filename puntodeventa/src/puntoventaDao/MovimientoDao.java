package puntoventaDao;

import ConexionSQL.ConexionDB;
import daoInterfaces.movimientoDaoInter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import puntoventaBean.MovimientoBean;

/**
 *
 * @author HP
 */
public class MovimientoDao implements movimientoDaoInter {

    Connection miconex = null;
    ResultSet rs = null;
    String rsp = null;
    String proc = null;

    public ResultSet catmovimiento() {
        miconex = ConexionDB.GetConnection();
        proc = "{call catMovimiento()}";
        try {
            CallableStatement cs = miconex.prepareCall(proc);
            rs = cs.executeQuery();
            miconex.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    @Override
    public String ABCMoviento(MovimientoBean MoviBean) {
        miconex = ConexionDB.GetConnection();
        proc = "{call movimientoABC()}";
        try {
            CallableStatement cs = miconex.prepareCall(proc);
            cs.setInt("proceso", MoviBean.getTipo());
            cs.setInt("idmovimiento", MoviBean.getIdmovimiento());
            cs.setString("descripcion", MoviBean.getDescripcion());
            cs.setInt("tipo", MoviBean.getTipo());
            cs.executeQuery();
        } catch (Exception e) {
            rsp = e.getLocalizedMessage();
            if (rsp.equals("La instrucción no devolvió un conjunto de resultados.")) {
                rsp = null;
            }
        }
        return rsp;
    }
}
