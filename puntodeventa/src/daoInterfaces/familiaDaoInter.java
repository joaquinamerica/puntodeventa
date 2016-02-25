/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoInterfaces;

import puntoventaBean.FamiliaBean;

/**
 *
 * @author Joaquin
 */
public interface familiaDaoInter {

    public String insert(FamiliaBean FamBean);

    public String modificar(FamiliaBean FamBean);

    public String eliminar(FamiliaBean FamBean);

}
