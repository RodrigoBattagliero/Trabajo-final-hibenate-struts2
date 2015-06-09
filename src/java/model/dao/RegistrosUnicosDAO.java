/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.entities.Areas;
import model.entities.RegistrosUnicos;
import model.entities.Solicitudes;
import org.hibernate.Query;

/**
 *
 * @author rodrigo
 */
public class RegistrosUnicosDAO extends DAO {

    public RegistrosUnicosDAO() {
        tableName = "RegistrosUnicos";
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
        RegistrosUnicos a = null;
        try{
//            iniciaOperacion();
            a = (RegistrosUnicos) sesion.get(RegistrosUnicos.class, (int) key);
        }finally{
//            sesion.close();
        }
        return a;
    }
    
    public List<RegistrosUnicos> select(Object key) {
        List<RegistrosUnicos> list = null;
        try{
//            iniciaOperacion();
            String sql = "FROM " + tableName + " AS ru "
                    + "inner join fetch ru.areas "
                    + " inner join fetch ru.estados "
                    + "WHERE ru.solicitudes = " + (int) key;
            list = sesion.createQuery(sql).list();
        }finally{
//            sesion.close();
        }
        return list;
    }
    
    public Object selectOneWithSolicitud(Object key) {
        RegistrosUnicos a;
        List<RegistrosUnicos> list;
        try{
//            iniciaOperacion();
            list =  sesion.createQuery(
                    "FROM " + tableName + " AS ru INNER JOIN FETCH ru.solicitudes WHERE ru = " + (int) key
                ).list();
            a = list.get(0);
        }finally{
//            sesion.close();
        }
        return a;
    }
    
    public List<Solicitudes> selectACompletar(Areas area) {
        List<Solicitudes> list = null;
        try{
//            iniciaOperacion();
            
            list = sesion.createQuery(
                    "SELECT ru.solicitudes,ru.solicitudes.docenteses FROM " + tableName + " AS ru WHERE ru.areas = " + area.getId() +" AND ru.estados = 1"
                ).list();
        }finally{
//            sesion.close();
        }
        return list;
    }
    
    public List<Solicitudes> selectIniciadaSedeInterior() {
        List<Solicitudes> list = null;
        try{
//            iniciaOperacion();
            
            list = sesion.createQuery(
                    "SELECT ru.solicitudes,ru.solicitudes.docenteses FROM " + tableName + " AS ru WHERE ru.areas = 3 AND ru.estados = 2 AND ru.confirmado = true"
                ).list();
        }finally{
//            sesion.close();
        }
        return list;
    }
    
    public List<Solicitudes> selectActividadACompletar() {
        List<Solicitudes> list = null;
        try{
//            iniciaOperacion();
            
            list = sesion.createQuery(
                    "SELECT ru.solicitudes,ru.solicitudes.docenteses FROM " + tableName + " AS ru WHERE ru.areas = 4 AND ru.estados = 1"
                ).list();
        }finally{
//            sesion.close();
        }
        return list;
    }
    
    public RegistrosUnicos selectRegistroUnicoAdministrar(Areas area, int idSolicitud){
        RegistrosUnicos reg = null;
        List<RegistrosUnicos> list = null;
        try{
            String sql = "FROM " + tableName + " AS ru INNER JOIN FETCH ru.solicitudes WHERE ru.areas = " + area.getId() +" AND ru.estados = 1 AND ru.confirmado = FALSE AND ru.solicitudes = " + idSolicitud;
            Query q = sesion.createQuery(sql);
            list = q.list();
            reg = (RegistrosUnicos) list.get(0);
        }catch(NullPointerException e){
            reg = null;
        }
        return reg;
    }
    
    public List<RegistrosUnicos> selectAConfirmar(Areas area){
        RegistrosUnicos reg = null;
        List<RegistrosUnicos> list = null;
        try{
            String sql = "FROM "+tableName+" AS ru INNER JOIN ru.estados es INNER JOIN ru.solicitudes s INNER JOIN s.docenteses d WHERE ru.areas = "+area.getId()+" AND es <> 1 AND es <> 4 AND ru.confirmado = FALSE AND s = d.solicitudes";
            Query q = sesion.createQuery(sql);
            list = q.list();
        }catch(NullPointerException e){
            reg = null;
        }
        return list;
    }
    
    public List<RegistrosUnicos> selectExpedientes(Areas area){
        RegistrosUnicos reg = null;
        List<RegistrosUnicos> list = null;
        try{
            String sql = "FROM "+tableName+" AS ru INNER JOIN ru.estados es INNER JOIN ru.solicitudes s INNER JOIN s.docenteses d WHERE ru.areas = 6 AND es = 2 AND ru.confirmado = TRUE AND s = d.solicitudes AND (select COUNT(*) FROM ExpedientesSolicitudes AS es WHERE es.solicitudes = s) = 0";
            Query q = sesion.createQuery(sql);
            list = q.list();
        }catch(NullPointerException e){
            reg = null;
        }
        return list;
    }
    
    public List<RegistrosUnicos> selectHistorial(Areas area) {
        List<RegistrosUnicos> list;
        try{
            String sql = 
                    " FROM " + tableName + " AS ru "
                    + " INNER JOIN FETCH ru.solicitudes AS sol"
                    + " INNER JOIN sol.docenteses"
                    + " WHERE "
                        + " ru.areas = " + area.getId();
            list =  sesion.createQuery(sql).list();
        }catch(Exception e){
            list = null;
        }
        return list;
    }

    public List<RegistrosUnicos> consultarRegistro(String nombreDocente, String apellidoDocente, Date fechaDePresentacion) {
        List<RegistrosUnicos> list;
        try{
            String sql = 
                    " FROM " + tableName + " AS ru "
                    + " INNER JOIN FETCH ru.solicitudes AS sol"
                    + " INNER JOIN sol.docenteses AS doc"
                    + " WHERE ";
            if(nombreDocente != null && !nombreDocente.equals(""))
                sql += " doc.nombre LIKE '%"+nombreDocente+"%'";
            if(apellidoDocente != null && !apellidoDocente.equals(""))
                sql += " doc.apellido LIKE '%"+apellidoDocente+"%'";
            if(fechaDePresentacion != null)
                sql += " sol.fechaAlta = "+fechaDePresentacion;
            list = sesion.createQuery(sql).list();
        }catch(Exception e){
            list = null;
        }
        return list;
    }

    public List<RegistrosUnicos> selectDevueltas() {
        List<RegistrosUnicos> list1;
        List<RegistrosUnicos> list2;
        List<RegistrosUnicos> list3  = new ArrayList();
        try{
            String sql = "FROM "+tableName+" AS ru "
                    + "INNER JOIN FETCH ru.estados es "
                    + "INNER JOIN FETCH ru.solicitudes s "
                    + "INNER JOIN FETCH s.docenteses d "
                    + "WHERE es = 4 AND ru.confirmado = TRUE AND s = d.solicitudes";
            Query q = sesion.createQuery(sql);
            list1 = q.list();
            for(RegistrosUnicos re : list1){
                String sql2 = "FROM " +tableName+" AS ru "
                    + " WHERE ru.fechaEntrada >= '"+re.getFechaSalida()+"' AND ru.solicitudes = "+re.getSolicitudes().getId()+" AND ru > "+re.getId();
                list2 = sesion.createQuery(sql2).list();
                if(list2.isEmpty())
                    list3.add(re);
                
            }
        }catch(NullPointerException e){
            list3 = null;
        }
        return list3;
    }
    
    public List<RegistrosUnicos> selectDevueltasAreas(Areas area) {
        List<RegistrosUnicos> list1;
        try{
            String sql = "FROM " + tableName + " AS ru "
                    + "INNER JOIN FETCH ru.estados es "
                    + "INNER JOIN FETCH ru.solicitudes s "
                    + "INNER JOIN FETCH s.docenteses d "
                    + "WHERE ru.areas = "+area.getId()+" AND ru.estados = 4";
            Query q = sesion.createQuery(sql);
            list1 = q.list();
        }catch(NullPointerException e){
            list1 = null;
        }
        return list1;
    }
    
    public RegistrosUnicos selectRegistroDevueltasAreas(Areas area,int idSol) {
        List<RegistrosUnicos> list1;
        RegistrosUnicos a = new RegistrosUnicos();
        try{
            String sql = "FROM " + tableName + " AS ru "
                    + "INNER JOIN FETCH ru.estados es "
                    + "INNER JOIN FETCH ru.solicitudes s "
                    + "INNER JOIN FETCH s.docenteses d "
                    + "WHERE ru.areas = "+area.getId()+" AND ru.estados = 4 AND s = " + idSol;
            Query q = sesion.createQuery(sql);
            a = (RegistrosUnicos) q.list().get(0);
        }catch(Exception e){
            a = null;
        }
        return a;
    }
}
