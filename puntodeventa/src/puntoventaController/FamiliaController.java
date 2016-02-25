package puntoventaController;

import daoInterfaces.familiaDaoInter;
import puntoventaBean.FamiliaBean;
import puntoventaDao.FamiliaDao;

/**
 *
 * @author Joaquin
 */
public class FamiliaController {

    familiaDaoInter familiaInter;

    public FamiliaController() {
        familiaInter = new FamiliaDao();
    }

    public String insertarFamilia(FamiliaBean FamBean) {
        return familiaInter.insert(FamBean);
    }

    public String modificarFamilia(FamiliaBean FamBean) {
        if (FamBean.getDescripcion().isEmpty()) {
            return "Campos vacios. No se guardo cambios";
        }
        return familiaInter.modificar(FamBean);
    }

    public String eliminarFamilia(FamiliaBean FamBean) {
        return familiaInter.eliminar(FamBean);
    }   
}
