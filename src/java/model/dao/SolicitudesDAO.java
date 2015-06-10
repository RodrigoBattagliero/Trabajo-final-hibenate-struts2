/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Solicitudes;

/**
 *
 * @author rodrigo
 */
public class SolicitudesDAO extends DAO {

    public SolicitudesDAO() {
        tableName = "Solicitudes";
    }
    
    
    
    @Override
    public Object selectOne(Object key) {
        Solicitudes a = null;
        List<Solicitudes> l;
        try{
            String sql = "FROM " + tableName + " AS t INNER JOIN FETCH t.sedes WHERE t = " + (int) key;
            l = sesion.createQuery(sql).list();
            a = l.get(0);
        }catch(Exception e){
            a = null;
        }
        return a;
    }

    public int selectMaxNumSol(int id) {
        int cant;
        try{
            String sql = "SELECT max(s.numeroSolicitud) FROM "+tableName+ " AS s WHERE s.sedes = " + id;
            cant = (int) sesion.createQuery(sql).list().get(0);
        }catch(Exception e){
            cant = 0;
        }
        return cant;
    }
    
}
