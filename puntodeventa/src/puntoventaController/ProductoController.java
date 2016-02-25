package puntoventaController;

import daoInterfaces.productoDaoInter;
import puntoventaBean.ProductoBean;
import puntoventaDao.ProductoDao;

/**
 *
 * @author JGamboa
 */
public class ProductoController {

    productoDaoInter productoInter;

    public ProductoController() {
        productoInter = new ProductoDao();
    }

    public String CUDPruducto(ProductoBean ProdBean) {
        return productoInter.ABCProducto(ProdBean);
    }

}
