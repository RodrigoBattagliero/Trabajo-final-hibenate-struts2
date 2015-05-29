/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;

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
    public List<Object> selectRelated(Object key) {
        List<Object> list = null;
        String idStr = String.valueOf(key);
        try{
//            iniciaOperacion();
            String sql = "FROM " + tableName + " WHERE comprobantes = " + (int) key;
            list = sesion.createQuery(sql).list();
        }finally{
//            sesion.close();
        }
        return list;
    }
}
