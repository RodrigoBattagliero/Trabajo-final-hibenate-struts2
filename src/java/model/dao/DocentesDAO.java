/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Docentes;

/**
 *
 * @author rodrigo
 */
public class DocentesDAO extends DAO {

    public DocentesDAO() {
        tableName = "Docentes";
    }

    @Override
    public Object selectOne(Object key) {
        Docentes a = null;
        try{
//            iniciaOperacion();
            a = (Docentes) sesion.get(Docentes.class, (int) key);
        }finally{
//            sesion.close();
        }
        return a;
    }
    
    public List<Docentes> selectRelatedWithDepto(Object key) {
        List<Docentes> list;
        try{
            String sql = "FROM " + tableName + " t INNER JOIN FETCH t.departamentosAcademicos WHERE t.solicitudes = " + (int) key;
            list = sesion.createQuery(sql).list();
        }catch(Exception e){
            list = null;
        }
        return list;
    }
    
    
}
