/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.entities.DepartamentosAcademicos;

/**
 *
 * @author rodrigo
 */
public class DepartamentosAcademicosDAO extends DAO {

    public DepartamentosAcademicosDAO() {
        tableName = "DepartamentosAcademicos";
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
        DepartamentosAcademicos a = null;
        try{
//            iniciaOperacion();
            a = (DepartamentosAcademicos) sesion.get(DepartamentosAcademicos.class, (int) key);
        }finally{
//            sesion.close();
        }
        return a;
    }
}
