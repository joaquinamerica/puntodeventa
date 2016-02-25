/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventaController;

import daoInterfaces.umedidaDaoInter;
import puntoventaBean.UmedidaBean;
import puntoventaDao.UmedidaDao;

/**
 *
 * @author HP
 */
public class UmedidaController {

    umedidaDaoInter UmedidaInter;

    public UmedidaController() {
        UmedidaInter = new UmedidaDao();
    }

    public String CUDUmedida(UmedidaBean UmedBean) {
        if (UmedBean.getProceso() != 3) {
        } else if (UmedBean.getDescripcion().isEmpty()) {
            return "Campos descripcion vacio, no se guardo en la base de datos";
        }
        return UmedidaInter.ABCUMedida(UmedBean);
    }
}
    /*
    public String insertarUmedida(UmedidaBean UmedBean) {
        return UmedidaInter.insert(UmedBean);
    }

    public String modificarUmedida(UmedidaBean UmedBean) {
        if (UmedBean.getDescripcion().isEmpty()) {
            return "Campos vacios. No se guardo cambios";
        }
        return UmedidaInter.modificar(UmedBean);
    }

    public String eliminarUmedida(UmedidaBean UmedBean) {
        return UmedidaInter.eliminar(UmedBean);
    }*/

