/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.Date;
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
            String sql = "SELECT max(s.numeroSolicitud) FROM "+tableName+ " AS s WHERE year(s.fechaAlta) = year(now()) AND s.sedes = " + id;
            cant = (int) sesion.createQuery(sql).list().get(0);
        }catch(Exception e){
            cant = 0;
        }
        return cant;
    }
    
    public boolean validarNumeroSolicitud(int numeroSolicitud,int id){
        boolean b = false;
        try{
            String sql = "FROM "+tableName+" t WHERE year(t.fechaAlta) = year(now()) AND t.numeroSolicitud  = "+numeroSolicitud+"  AND t.sedes = " + id;
            List<Solicitudes> sol = sesion.createQuery(sql).list();
            if(sol.size() > 0)
                b = true;
        }catch(Exception e){
            b = false;
        }
        return b;
    }
    
}
