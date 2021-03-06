/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Areas;
import model.entities.Estados;
import model.entities.Expedientes;

/**
 *
 * @author rodrigo
 */
public class ExpedientesDAO extends DAO {

    public ExpedientesDAO() {
        tableName = "Expedientes";
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
        Expedientes a = null;
        try{
//            iniciaOperacion();
            a = (Expedientes) sesion.get(Expedientes.class, (int) key);
        }finally{
//            sesion.close();
        }
        return a;
    }
    
    public List<Expedientes> selectExpedientes() {
        List<Expedientes> list = null;
        try{
//            iniciaOperacion();
            list = sesion.createQuery("FROM " + tableName).list();
        }finally{
//            sesion.close();
        }
        return list;
    }
    
    public List<Expedientes> selectRelated(Areas area, Estados estado){
        List<Expedientes> list = null;
        try{
            String sql = "FROM " +tableName + " AS ex "
                        + " INNER JOIN FETCH ex.areas "
                        + " INNER JOIN FETCH ex.estados "
                        + " WHERE ex.areas = " + area.getId() + " AND ex.estados = " + estado.getId();
            list = sesion.createQuery(sql).list();
        }finally{
//            sesion.close();
        }
        return list;
    }
}
