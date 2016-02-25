package puntoventaBean;

/**
 *
 * @author Joaquin
 */
public class FamiliaBean {

    private Integer idfamilia;
    private String descripcion;

    public FamiliaBean() {

    }
    public Integer getIdfamilia() {
        return idfamilia;
    }

    public void setIdfamilia(Integer idfamilia) {
        this.idfamilia = idfamilia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Metodos para llenar el combobox
    public FamiliaBean(Integer idfamilia, String descripcion) {
        this.idfamilia = idfamilia;
        this.descripcion = descripcion;
    }

    public int getID() {
        return idfamilia;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
