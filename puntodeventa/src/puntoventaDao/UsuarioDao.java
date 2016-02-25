package puntoventaDao;

import ConexionSQL.ConexionDB;
import daoInterfaces.usuarioDaoInter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import puntoventaBean.UsuarioBean;

/**
 *
 * @author HP
 */
public class UsuarioDao implements usuarioDaoInter {

    Connection miconex = null;
    String proc = null;
    ResultSet rs = null;
    int resultado = 0;
    String result;

    public int Validar(UsuarioBean UserBean) {
        miconex = ConexionDB.GetConnection();
        proc = "{call validarUser(?,?)}";
        try {
            CallableStatement cs = miconex.prepareCall(proc);
            cs.setString("idusuario", UserBean.getUsuario());
            cs.setString("contrasena", UserBean.getContrasena());
            rs = cs.executeQuery();
            while(rs.next()){
              System.out.println(rs.getString("nombre"));
              resultado=1;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;
    }

    public ResultSet catUsuarios() {
        miconex = ConexionDB.GetConnection();
        proc = "{call catUsuario()}";
        try {
            CallableStatement cs = miconex.prepareCall(proc);
            rs = cs.executeQuery();
            miconex.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    //ABC de los Usuarios
    @Override
    public String ABCUsuario(UsuarioBean UserBean) {
        miconex = ConexionDB.GetConnection();
        proc = "{call UsuarioABC(?,?,?,?,?,?)}";
        try {
            CallableStatement cs = miconex.prepareCall(proc);
            cs.setInt("proceso", UserBean.getProceso());
            cs.setString("usuario", UserBean.getUsuario());
            cs.setString("contrasena", UserBean.getContrasena());
            cs.setString("nombre", UserBean.getNombre());
            cs.setString("apellido", UserBean.getApellidos());
            cs.setInt("nivel", UserBean.getNivel());
        } catch (Exception e) {
            result = e.getMessage();
            if (result.equals("La instrucción no devolvió un conjunto de resultados.")) {
                result = null;
            }
        }
        return result;
    }

}
