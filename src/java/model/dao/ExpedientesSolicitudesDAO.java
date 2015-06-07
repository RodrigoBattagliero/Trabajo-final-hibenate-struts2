/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.ExpedientesSolicitudes;

/**
 *
 * @author rodrigo
 */
public class ExpedientesSolicitudesDAO extends DAO {

    public ExpedientesSolicitudesDAO() {
        tableName = "ExpedientesSolicitudes";
    }
    
    @Override
    public void iniciaOperacion(){
        super.iniciaOperacion();
    }
    
    @Override
    public void cerrarSession(){
        super.cerrarSession();
    }
    
    public List<ExpedientesSolicitudes> selectDetalle(int idExpediente) {
        List<ExpedientesSolicitudes> list = null;
        try{
//            iniciaOperacion();
            String sql = "FROM " + tableName + " AS es,Docentes AS d INNER JOIN FETCH es.solicitudes AS ess WHERE es.expedientes = " + (int) idExpediente + " AND d.solicitudes = ess";
            list = sesion.createQuery(sql).list();
        }finally{
//            sesion.close();
        }
        return list;
    }
}
