/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.ReSolCon;

/**
 *
 * @author rodrigo
 */
public class ReSolConDAO extends DAO {

    public ReSolConDAO() {
        this.tableName = "ReSolCon";
        this.entitie = new ReSolCon();
    }
    
    @Override
    public Object selectOne(Object key) {
        ReSolCon a = new ReSolCon();
        try{
            a = (ReSolCon) sesion.get(a.getClass(), (int) key);
        }catch(Exception e){
            a = null;
        }
        return a;
    }
    
    public Object selectReporte(Object key) {
        ReSolCon a;
        try{
            String sql = "FROM " + tableName +" AS a INNER JOIN FETCH a.reSolConDetalleses AS d INNER JOIN FETCH d.registrosUnicos WHERE a =" + (int) key ;
            a = (ReSolCon) sesion.createQuery(sql).list().get(0);
        }catch(Exception e){
            a = null;
        }
        return a;
    }
    
    @Override
    public List<Object> selectAll() {
        List<Object> list = null;
        try{
//            iniciaOperacion();
            list = sesion.createQuery("FROM " + tableName + " AS a ORDER BY a.fecha DESC").list();
        }finally{
//            sesion.close();
        }
        return list;
    }
    
}
