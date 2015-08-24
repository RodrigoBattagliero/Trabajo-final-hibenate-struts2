/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakan.dao;

import conexion.ConexionKakan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kakan.entities.SpgDocentes;

/**
 *
 * @author rodrigo
 */
public class SpgDocentesDAO {
    
    private final String SELECT_DOCENTE="SELECT apellido,nombre,documento,kakan.get_residencia(id) as residencia,mail,telefono_fijo,telefono_celular,kakan.formatear_cuil(cuil) as cuil "
                    + "FROM kakan.sgp_docentes "
                    + "WHERE documento = ?";
//    private final String SELECT_DOCENTE="SELECT apellido,nombre,documento,kakan.get_residencia(id) as residencia "
//                    + "FROM kakan.sgp_docentes "
//                    + "WHERE documento = ?";
    private static final ConexionKakan conex = ConexionKakan.estado();
    
    public SpgDocentesDAO() {   
    }
    
    public List<SpgDocentes> selectDocentes(String dni){
        PreparedStatement ps;
        ResultSet res;
        ArrayList spgDocentesList = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SELECT_DOCENTE);
            ps.setString(1, dni);
            res = ps.executeQuery();
            while(res.next()){
                //spgDocentesList.add(new SpgDocentes(res.getString(1),res.getString(2),res.getString(3),res.getString(4),"","",""));
                spgDocentesList.add(new SpgDocentes(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8)));
            }
            return spgDocentesList;
        } catch (SQLException ex) {
            Logger.getLogger(SpgDocentes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return spgDocentesList;
    }
    
}
