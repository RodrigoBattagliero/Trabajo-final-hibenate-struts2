/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Areas;

/**
 *
 * @author rodrigo
 */
public class AreasDAO extends DAO{
    
    public AreasDAO(){
        tableName = "Areas";
        entitie = new Areas();
    }

    @Override
    public List<Object> selectRelated(Object key) {
        return null;
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
        Areas a = null;
        try{
//            iniciaOperacion();
            a = (Areas) sesion.get(Areas.class, (int) key);
        }finally{
//            sesion.close();
        }
        return a;
    }
    
    public Areas proxima(Areas area){
        Areas a = null;
        int orden = area.getOrden();
        orden++;
        try{
            a = (Areas) sesion.createQuery("FROM Areas WHERE orden = " + orden);
        }catch(Exception e){
            a = null;
        }
        return a;
    }
}
