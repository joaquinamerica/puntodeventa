package puntoventaDao;

import daoInterfaces.productoDaoInter;
import java.sql.Connection;
import java.sql.ResultSet;
import ConexionSQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.SQLException;
import puntoventaBean.ProductoBean;

/**
 *
 * @author JGamboa
 */
public class ProductoDao implements productoDaoInter {

    String Result;
    ResultSet rs = null;
    String proc = null;
    Connection miconex = null;

    public ResultSet catproducto() {
        miconex = ConexionDB.GetConnection();
        try {
            proc = "{call catproducto()}";
            CallableStatement cs = miconex.prepareCall(proc);
           rs = cs.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
  public ResultSet buscarproducto(String idproducto) {
        miconex = ConexionDB.GetConnection();
        try {
            proc = "{call buscarproducto(?)}";
            CallableStatement cs = miconex.prepareCall(proc);
            cs.setString("idproducto", idproducto);
           rs = cs.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
    /**
     *
     * @param idproducto
     * @return
     * @throws java.sql.SQLException
     * si regresa true es que el id ya existe.
     */
    public boolean buscarid(String idproducto) throws SQLException {
        boolean rps;
        miconex = ConexionDB.GetConnection();
        try {
            proc = "{call buscarid(?)}";
           CallableStatement cs = miconex.prepareCall(proc);
                cs.setString("idproducto", idproducto);
                rs = cs.executeQuery();
        } catch (Exception e) {
        }
        if (!rs.next()) {
            rps = true;
        } else {
            rps = false;
        }
        return rps;
    }

    public String ABCProducto(ProductoBean ProdBean) {
        miconex = ConexionDB.GetConnection();
        try {
            proc = "{call ProductoABC(?,?,?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cs = miconex.prepareCall(proc);
            cs.setString("proceso", ProdBean.getProceso());
            cs.setString("idproducto", ProdBean.getIdproducto());
            cs.setString("descripcion", ProdBean.getDescripcion());
            cs.setInt("idfamilia", ProdBean.getIdfamilia());
            cs.setFloat("pcompra", ProdBean.getPcompra());
            cs.setFloat("pventa", ProdBean.getPventa());
            cs.setFloat("existencia", ProdBean.getExistencia());
            cs.setFloat("cantidadminima",ProdBean.getCantidadminima());
            cs.setString("umedida", ProdBean.getUmedida());
            cs.setString("imagen", ProdBean.getImagen());
            cs.setInt("idproveedor", ProdBean.getIdproveedor());
            cs.setString("observaciones", ProdBean.getObservaciones());
            cs.executeQuery();
        } catch (Exception e) {
        }

        return Result;
    }   
}
