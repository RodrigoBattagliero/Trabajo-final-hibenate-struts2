/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakan.dao;

import conexion.ConexionKakan;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kakan.entities.VOgagtd;
import resources.DateManager;

/**
 *
 * @author rodrigo
 */
public class OgagtdDAO {
    
    private final String SELECT_DESIGNACIONES = 
                        "SELECT * FROM kakan.v_ogagtd "
                        + "WHERE "
                            + " dni = ? AND ? BETWEEN fecha_inicio  AND fecha_fin";
    private final String SELECT_FROM_COMISION = 
                        "SELECT * FROM kakan.v_ogagtd "
                        + "WHERE "
                            + " dni = ? AND comision = ? AND ? BETWEEN fecha_inicio  AND fecha_fin";
    private static final ConexionKakan conex = ConexionKakan.estado();
    
    public OgagtdDAO() {
    }
    
   public List<VOgagtd> selectParameter(String dni, java.util.Date fecha){
       PreparedStatement ps;
        ResultSet res;
        ArrayList vOgagtdList = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SELECT_DESIGNACIONES);
            ps.setString(1, dni);
            DateManager d = new DateManager(fecha);
            ps.setDate(2, new Date(d.getFechaLong()));
            res = ps.executeQuery();
            while(res.next()){
                
                vOgagtdList.add(new VOgagtd(
                        res.getString("id_categoria"),
                        res.getString("estado"),
                        res.getString("categoria_investigacion"),
                        res.getString("id_caracter_liquidacion"),
                        res.getInt("id_periodo"),
                        res.getInt("nro"),
                        res.getInt("anio_academico"),
                        res.getString("periodo"),
                        res.getInt("comision"),
                        res.getInt("id"),
                        res.getLong("id_docente"),
                        res.getShort("id_departamento"),
                        res.getString("nombre_dpto"),
                        res.getShort("id_unidad_academica"),
                        res.getString("nombre_sede"),
                        res.getString("apellido"),
                        res.getString("nombre"),
                        res.getString("dni"),
                        res.getString("tipo_documento"),
                        res.getString("documento"),
                        res.getString("nombre_categoria"),
                        res.getString("cargo"),
                        res.getString("dedicacion"),
                        res.getString("res_comp"),
                        res.getString("nombre_caracter"),
                        res.getString("unidad_academica"),
                        res.getString("nombre_unidad_academica"),
                        res.getString("carrera"),
                        res.getString("nombre_carrera"),
                        res.getString("plan"),
                        res.getString("nro_resolucion"),
                        res.getString("materia"),
                        res.getString("nombre_materia"),
                        res.getInt("anio"),
                        res.getString("periodo_lectivo"),
                        res.getString("reg"),
                        res.getString("extension"),
                        res.getString("emisor_norma"),
                        res.getString("norma"),
                        res.getLong("nro_norma"),
                        res.getDate("fec_norma"),
                        res.getString("act_extension"),
                        res.getString("proy_investigacion"),
                        res.getString("act_gestion"),
                        res.getString("form_rrhh"),
                        res.getString("observaciones"),
                        res.getString("descripcion_proyecto_investigacion"),
                        res.getString("descripcion_proyecto_extencion"),
                        res.getString("descripcion_actividad_gestion"),
                        res.getString("descripcion_rrhh"),
                        res.getDate("fecha_inicio"),
                        res.getDate("fecha_fin"),
                        res.getInt("aumento_dedicacion"),
                        res.getString("tipo_dedicacion"),
                        res.getString("info"),
                        res.getString("estado_actual"),
                        res.getString("instituto"),
                        res.getString("leyenda"),
                        res.getString("ambito_laboral"),
                        res.getString("centro_investigacion"),
                        res.getString("dpto_materia")
                ));
            }
            return vOgagtdList;
        } catch (SQLException ex) {
            Logger.getLogger(VOgagtd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return vOgagtdList;
   }
   
   public List<VOgagtd> selectParameter(String dni, java.util.Date fecha,int comision){
       PreparedStatement ps;
        ResultSet res;
        ArrayList vOgagtdList = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SELECT_FROM_COMISION);
            ps.setString(1, dni);
            ps.setInt(2, comision);
            DateManager d = new DateManager(fecha);
            ps.setDate(3, new Date(d.getFechaLong()));
            res = ps.executeQuery();
            while(res.next()){
                
                vOgagtdList.add(new VOgagtd(
                        res.getString("id_categoria"),
                        res.getString("estado"),
                        res.getString("categoria_investigacion"),
                        res.getString("id_caracter_liquidacion"),
                        res.getInt("id_periodo"),
                        res.getInt("nro"),
                        res.getInt("anio_academico"),
                        res.getString("periodo"),
                        res.getInt("comision"),
                        res.getInt("id"),
                        res.getLong("id_docente"),
                        res.getShort("id_departamento"),
                        res.getString("nombre_dpto"),
                        res.getShort("id_unidad_academica"),
                        res.getString("nombre_sede"),
                        res.getString("apellido"),
                        res.getString("nombre"),
                        res.getString("dni"),
                        res.getString("tipo_documento"),
                        res.getString("documento"),
                        res.getString("nombre_categoria"),
                        res.getString("cargo"),
                        res.getString("dedicacion"),
                        res.getString("res_comp"),
                        res.getString("nombre_caracter"),
                        res.getString("unidad_academica"),
                        res.getString("nombre_unidad_academica"),
                        res.getString("carrera"),
                        res.getString("nombre_carrera"),
                        res.getString("plan"),
                        res.getString("nro_resolucion"),
                        res.getString("materia"),
                        res.getString("nombre_materia"),
                        res.getInt("anio"),
                        res.getString("periodo_lectivo"),
                        res.getString("reg"),
                        res.getString("extension"),
                        res.getString("emisor_norma"),
                        res.getString("norma"),
                        res.getLong("nro_norma"),
                        res.getDate("fec_norma"),
                        res.getString("act_extension"),
                        res.getString("proy_investigacion"),
                        res.getString("act_gestion"),
                        res.getString("form_rrhh"),
                        res.getString("observaciones"),
                        res.getString("descripcion_proyecto_investigacion"),
                        res.getString("descripcion_proyecto_extencion"),
                        res.getString("descripcion_actividad_gestion"),
                        res.getString("descripcion_rrhh"),
                        res.getDate("fecha_inicio"),
                        res.getDate("fecha_fin"),
                        res.getInt("aumento_dedicacion"),
                        res.getString("tipo_dedicacion"),
                        res.getString("info"),
                        res.getString("estado_actual"),
                        res.getString("instituto"),
                        res.getString("leyenda"),
                        res.getString("ambito_laboral"),
                        res.getString("centro_investigacion"),
                        res.getString("dpto_materia")
                ));
            }
            return vOgagtdList;
        } catch (SQLException ex) {
            Logger.getLogger(VOgagtd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return vOgagtdList;
   }
}
