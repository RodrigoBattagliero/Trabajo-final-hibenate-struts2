/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Designaciones;

/**
 *
 * @author rodrigo
 */
public class DesignacionesDAO extends DAO {

    public DesignacionesDAO() {
        tableName = "Designaciones";
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
    public Object selectOne(Object key) {
        Designaciones a = null;
        try{
//            iniciaOperacion();
            a = (Designaciones) sesion.get(Designaciones.class, (int) key);
        }finally{
//            sesion.close();
        }
        return a;
    }
    
    
    public List<Designaciones> selectRelatedAll(Object key) {
        List<Designaciones> list;
        try{
            String sql = "FROM " + tableName + " AS de INNER JOIN FETCH de.actividadDocenteses AS act WHERE de.solicitudes = " + (int) key;
            list = sesion.createQuery(sql).list();
        }catch(Exception e){
            list = null;
        }
        return list;
    }
}
