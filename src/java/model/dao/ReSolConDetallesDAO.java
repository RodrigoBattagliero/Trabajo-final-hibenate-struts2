/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.entities.ReSolCon;
import model.entities.ReSolConDetalles;

/**
 *
 * @author rodrigo
 */
public class ReSolConDetallesDAO extends DAO {

    public ReSolConDetallesDAO() {
        this.tableName = "ReSolConDetalles";
        this.entitie = new ReSolConDetalles();
    }
    
    @Override
    public Object selectOne(Object key) {
        ReSolCon a = null;
        try{
//            iniciaOperacion();
            a = (ReSolCon) sesion.get(ReSolConDetalles.class, (int) key);
        }finally{
//            sesion.close();
        }
        return a;
    }
    
}
