/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.entities.Estados;

/**
 *
 * @author rodrigo
 */
public class EstadosDAO extends DAO {

    public EstadosDAO() {
        tableName = "Estados";
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
        Estados a = null;
        try{
//            iniciaOperacion();
            a = (Estados) sesion.get(Estados.class, (int) key);
        }finally{
//            sesion.close();
        }
        return a;
    }
    
}
