/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

/**
 *
 * @author rodrigo
 */
public class ComprobantesComidaAlojamientoDAO extends DAO{

    public ComprobantesComidaAlojamientoDAO() {
        tableName = "Comprobantes_comida_alojamientos";
    }
    
    @Override
    public void iniciaOperacion(){
        super.iniciaOperacion();
    }
    
    @Override
    public void cerrarSession(){
        super.cerrarSession();
    }
}
