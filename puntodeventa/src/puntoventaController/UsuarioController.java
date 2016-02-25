/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventaController;

import daoInterfaces.usuarioDaoInter;
import puntoventaBean.UsuarioBean;
import puntoventaDao.UsuarioDao;
/**
 *
 * @author HP
 */
public class UsuarioController {
    
    
    usuarioDaoInter UserInter;

    public UsuarioController() {
        UserInter = new UsuarioDao();
    }

    public String CUDCliente(UsuarioBean UserBean) {
        if (UserBean.getProceso() != 3) {
        } else if (UserBean.getUsuario().isEmpty() || UserBean.getContrasena().isEmpty()) {
            return "Campos razon social vacio, no se guardo en la base de datos";
        }
        return UserInter.ABCUsuario(UserBean);
    }
}
