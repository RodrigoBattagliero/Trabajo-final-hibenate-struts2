/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.entities.Sedes;

/**
 *
 * @author rodrigo
 */
public class SedesDAO extends DAO {

    public SedesDAO() {
        tableName = "Sedes";
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
        Sedes a = null;
        try{
//            iniciaOperacion();
            a = (Sedes) sesion.get(Sedes.class, (int) key);
        }finally{
//            sesion.close();
        }
        return a;
    }
    
}
