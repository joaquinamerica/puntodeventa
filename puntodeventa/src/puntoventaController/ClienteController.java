/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventaController;

import daoInterfaces.clienteDaoInter;
import puntoventaBean.ClienteBean;
import puntoventaDao.ClienteDao;

/**
 *
 * @author JGamboa
 */
public class ClienteController {

    clienteDaoInter clienteInter;

    public ClienteController() {
        clienteInter = new ClienteDao();
    }

    public String CUDCliente(ClienteBean CteBean) {
        if (CteBean.getProceso() != 3) {
        } else if (CteBean.getRazonsocial().isEmpty()) {
            return "Campos razon social vacio, no se guardo en la base de datos";
        }
        return clienteInter.ABCCliente(CteBean);
    }
/*
    public String insertar(ClienteBean CteBean) {
        return clienteInter.insert(CteBean);
    }

    public String eliminar(ClienteBean CteBean) {
        return clienteInter.eliminar(CteBean);
    }

    public String modificar(ClienteBean CteBean) {
        return clienteInter.modificar(CteBean);
    }
*/
}
