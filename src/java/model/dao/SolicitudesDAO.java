/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

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
        try{
//            iniciaOperacion();
            a = (Solicitudes) sesion.get(Solicitudes.class, (int) key);
        }finally{
//            sesion.close();
        }
        return a;
    }
    
}
