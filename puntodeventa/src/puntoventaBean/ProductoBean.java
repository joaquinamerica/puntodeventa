/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventaBean;

/**
 *
 * @author HP catproduct
 */
public class ProductoBean {

    private String proceso;
    private String idproducto;
    private String descripcion;
    private int idfamilia;
    private float pcompra;
    private float pventa;
    private float existencia;
    private float cantidadminima;
    private String umedida;
    private String imagen;
    private int idproveedor;
    private String observaciones;

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdfamilia() {
        return idfamilia;
    }

    public void setIdfamilia(int idfamilia) {
        this.idfamilia = idfamilia;
    }

    public float getPcompra() {
        return pcompra;
    }

    public void setPcompra(float pcompra) {
        this.pcompra = pcompra;
    }

    public float getPventa() {
        return pventa;
    }

    public void setPventa(float pventa) {
        this.pventa = pventa;
    }

    public float getExistencia() {
        return existencia;
    }

    public void setExistencia(float existencia) {
        this.existencia = existencia;
    }

    public float getCantidadminima() {
        return cantidadminima;
    }

    public void setCantidadminima(float cantidadminima) {
        this.cantidadminima = cantidadminima;
    }

    public String getUmedida() {
        return umedida;
    }

    public void setUmedida(String umedida) {
        this.umedida = umedida;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
