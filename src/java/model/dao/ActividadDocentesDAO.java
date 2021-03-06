/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodrigo
 */
public class ActividadDocentesDAO extends DAO {

    public ActividadDocentesDAO() {
        tableName = "ActividadDocentes";
    }
    
    @Override
    public void iniciaOperacion(){
        super.iniciaOperacion();
    }
    
    @Override
    public void cerrarSession(){
        super.cerrarSession();
    }
    
    @Override
    public List<Object> selectRelated(Object key) {
        List<Object> list = new ArrayList();

        try{
//            iniciaOperacion();
            String sql = "FROM " + tableName + " t WHERE t.designaciones = " + (int) key;
            list = sesion.createQuery(sql).list();
        }catch(Exception e){
            list = new ArrayList();
        }
        return list;
    }
    
}
