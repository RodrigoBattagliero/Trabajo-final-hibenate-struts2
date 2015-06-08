/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.ComprobantesComidaAlojamientos;

/**
 *
 * @author rodrigo
 */
public class ComprobantesComidaAlojamientoDAO extends DAO{

    public ComprobantesComidaAlojamientoDAO() {
        tableName = "ComprobantesComidaAlojamientos";
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
        Object a;
        try{
            String sql = "FROM " + tableName + " AS t INNER JOIN FETCH t.comprobantes WHERE t = " + (int) key;
            List<ComprobantesComidaAlojamientos> l = sesion.createQuery(sql).list();
            a = l.get(0);
        }catch(Exception e){
            a = null;
        }
        return a;
    }
    
    @Override
    public List<Object> selectRelated(Object key) {
        List<Object> list = null;
        try{
//            iniciaOperacion();
            String sql = "FROM " + tableName + " AS t INNER JOIN FETCH t.comprobantes WHERE t.comprobantes = " + (int) key;
            list = sesion.createQuery(sql).list();
        }finally{
//            sesion.close();
        }
        return list;
    }
}
