/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventaBean;

/**
 *
 * @author HP
 */
public class UmedidaBean {

    private int proceso;
    private Integer idumedida;
    private String descripcion;
    private int tipo;

    public UmedidaBean() {
    }
    public int getProceso() {
        return proceso;
    }

    public void setProceso(int proceso) {
        this.proceso = proceso;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getIdumedida() {
        return idumedida;
    }

    public void setIdumedida(Integer idumedida) {
        this.idumedida = idumedida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
