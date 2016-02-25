package puntoventaController;

import daoInterfaces.movimientoDaoInter;
import puntoventaBean.MovimientoBean;
import puntoventaDao.MovimientoDao;

/**
 * public FamiliaController() { familiaInter = new FamiliaDao(); }
 *
 * public String insertarFamilia(FamiliaBean FamBean) { return
 * familiaInter.insert(FamBean); }
 *
 * @author HP
 */
public class MovimientoController {

    movimientoDaoInter moviInter;

    public MovimientoController() {
        moviInter = new MovimientoDao();
    }

    public String CUDMoviento(MovimientoBean MoviBean) {
        return moviInter.ABCMoviento(MoviBean);
    }

}
