import ConexionSQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HP
 */
public class configuraciones {
    ResultSet rs = null;
    Connection miconex = null;
    String Rps = null;
    String proc = null;

    public String obtenerIntentos() {
        miconex = ConexionDB.GetConnection();
        proc = "{call obtenerintentos()}";
        try {
            CallableStatement cs= miconex.prepareCall(proc);
            rs=cs.executeQuery();
            while(rs.next())
                Rps=rs.getString("intentos");
        } catch (Exception e) {
        }
    return Rps;
    }

    
    
    
}
