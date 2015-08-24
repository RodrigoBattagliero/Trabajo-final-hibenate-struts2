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
import model.entities.Docentes;
import model.entities.Estados;
import model.entities.RegistrosUnicos;
import model.entities.Sedes;
import model.entities.Solicitudes;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

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
                    + "WHERE ru.solicitudes = " + (int) key
                    + " ORDER BY ru.fechaEntrada ASC,ru.id ASC ";
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
    
    public List<Solicitudes> selectACompletar(Areas area,Sedes sede) {
        List<Solicitudes> list = null;
        try{
//            iniciaOperacion();
            String sql;
            if(sede.getId() == 2 || sede.getId() == 3 || sede.getId() == 4)
                sql = "SELECT ru.solicitudes AS s,ru.solicitudes.docenteses FROM " + tableName + " AS ru WHERE ru.areas = " + area.getId() +" AND ru.estados = 1 AND ru.solicitudes.sedes = "+ sede.getId() + " ORDER BY ru.solicitudes.fechaAlta ASC, ru.solicitudes.numeroSolicitud ASC";
            else
                sql = "SELECT ru.solicitudes AS s,ru.solicitudes.docenteses FROM " + tableName + " AS ru WHERE ru.areas = " + area.getId() +" AND ru.estados = 1 ORDER BY ru.solicitudes.fechaAlta ASC, ru.solicitudes.numeroSolicitud ASC";
            list = sesion.createQuery(sql).list();
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
                    "SELECT ru.solicitudes,ru.solicitudes.docenteses FROM " + tableName + " AS ru WHERE ru.areas = 4 AND ru.estados = 1 AND ru.solicitudes.tipo = 1"
                ).list();
        }finally{
//            sesion.close();
        }
        return list;
    }
    
     public List<Solicitudes> selectActividadACompletarInterior(Sedes sede) {
        List<Solicitudes> list = null;
        try{
//            iniciaOperacion();
            String sql = "SELECT ru.solicitudes,ru.solicitudes.docenteses FROM " + tableName + " AS ru WHERE ru.areas = 4 AND ru.estados = 1 AND ru.solicitudes.tipo = 2 AND ru.solicitudes.sedes = " + sede.getId();
            list = sesion.createQuery(sql).list();
        }finally{
//            sesion.close();
        }
        return list;
    }
    
    public RegistrosUnicos selectRegistroUnicoAdministrar(Areas area, int idSolicitud){
        RegistrosUnicos reg = null;
        List<RegistrosUnicos> list = null;
        try{
            //String sql = "FROM " + tableName + " AS ru INNER JOIN FETCH ru.solicitudes WHERE ru.areas = " + area.getId() +" AND ((ru.estados = 1 AND ru.confirmado = FALSE) or (ru.estados = 8 AND ru.confirmado = TRUE))  AND ru.solicitudes = " + idSolicitud;
            String sql = "FROM " + tableName + " AS ru INNER JOIN FETCH ru.solicitudes WHERE ru.areas = " + area.getId() +" AND ru.estados = 1 AND ru.confirmado = FALSE AND ru.solicitudes = " + idSolicitud;
            Query q = sesion.createQuery(sql);
            list = q.list();
            reg = (RegistrosUnicos) list.get(0);
        }catch(NullPointerException e){
            reg = null;
        }
        return reg;
    }
    
    public List<RegistrosUnicos> selectAConfirmar(Areas area,Sedes sede){
        RegistrosUnicos reg = null;
        List<RegistrosUnicos> list = null;
        try{
            String sql;
            if(area.getId() == 1){
                sql = "FROM "+tableName+" AS ru INNER JOIN ru.estados es INNER JOIN ru.solicitudes s INNER JOIN s.docenteses d WHERE"
                    + " ( "
                        + "(ru.areas = "+area.getId()+" AND es <> 1 AND es <> 8 AND ru.confirmado = FALSE AND s = d.solicitudes AND ru.solicitudes.sedes = "+ sede.getId()+") "
                    + "     and ( (ru.areas <> 1 or ru.areas <> 3) and es <> 4 )"
                    + ")"
                    + " OR "
                    + "("
                    + "     es = 9"
                    + ")";
            }else if(area.getId() == 1 || area.getId() == 2 || area.getId() == 3 || area.getId() == 4){
                sql = "FROM "+tableName+" AS ru INNER JOIN ru.estados es INNER JOIN ru.solicitudes s INNER JOIN s.docenteses d WHERE"
                    + " ( "
                        + "(ru.areas = "+area.getId()+" AND es <> 1 AND es <> 8 AND ru.confirmado = FALSE AND s = d.solicitudes AND ru.solicitudes.sedes = "+ sede.getId()+") "
                    + ")";
            }else{
                sql = "FROM "+tableName+" AS ru INNER JOIN ru.estados es INNER JOIN ru.solicitudes s INNER JOIN s.docenteses d WHERE"
                    + " ( "
                        + "(ru.areas = "+area.getId()+" AND es <> 1 AND es <> 8 AND ru.confirmado = FALSE AND s = d.solicitudes) "
                    + ")";    
            }
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
    
    public List<RegistrosUnicos> selectHistorial(Areas area,Sedes sede) {
        List<RegistrosUnicos> list = new ArrayList();
        try{
            String sql = " SELECT DISTINCT ru"
                    + " FROM " + tableName + " AS ru "
                    + " INNER JOIN FETCH ru.solicitudes AS sol"
                    + " INNER JOIN sol.docenteses"
                    + " WHERE "
                        + " ru.areas = " + area.getId()
                        + " AND ru.solicitudes.sedes = "+ sede.getId()
                    + " ORDER BY sol.fechaAlta DESC,sol.id DESC";
                  list = sesion.createQuery(sql).list();
            
        }catch(Exception e){
            list = null;
        }
        return list;
    }

    public List<RegistrosUnicos> consultarRegistro(String nombreDocente, String apellidoDocente, Date fechaDePresentacion,String dni,int numeroSol) {
        List<RegistrosUnicos> list = new ArrayList();
        try{
            String sql = 
                    " FROM " + tableName + " AS ru "
                    + " INNER JOIN FETCH ru.solicitudes AS sol"
                    + " INNER JOIN sol.docenteses AS doc "
                    + " WHERE 1 = 1 ";
            if(nombreDocente != null && !nombreDocente.equals(""))
                sql += " AND doc.nombre LIKE '%"+nombreDocente.toUpperCase()+"%'";
            if(apellidoDocente != null && !apellidoDocente.equals(""))
                sql += " AND doc.apellido LIKE '%"+apellidoDocente.toUpperCase()+"%'";
            if(fechaDePresentacion != null)
                sql += " AND sol.fechaAlta = '"+fechaDePresentacion+"' ";
            if(dni != null && !dni.equals(""))
                sql += " AND doc.dni = '"+dni+"' ";
            if(numeroSol != 0)
                sql += " AND sol.numeroSolicitud = '"+numeroSol+"' ";
            //list =  sesion.createQuery(sql).list();
            List<Object[]> aux =  sesion.createQuery(sql).list();
            int cant1 = aux.size();
            for (int i = 0; i < cant1; i++) {
                Object[] a = aux.get(i);
                RegistrosUnicos as = (RegistrosUnicos) a[0];
                int idSol = as.getSolicitudes().getId();
                boolean b = true;
                int cant2 = list.size();
                for (int j = 0; j < cant2; j++) {
                    if( (idSol == list.get(j).getSolicitudes().getId()))
                        b = false;
                }
                if(b)
                    list.add(as);
            }
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
                    + "WHERE es = 4 AND ru.confirmado = FALSE AND s = d.solicitudes AND ru.areas = 1";
            Query q = sesion.createQuery(sql);
            list1 = q.list();
            for(RegistrosUnicos re : list1){
                if(re.getFechaSalida() == null){
                    list3.add(re);
                }else{
                    String sql2 = "FROM " +tableName+" AS ru "
                    + " WHERE ru.fechaEntrada >= '"+re.getFechaSalida()+"' AND ru.solicitudes = "+re.getSolicitudes().getId()+" AND ru > "+re.getId();
                    list2 = sesion.createQuery(sql2).list();
                    if(list2.isEmpty())
                        list3.add(re);
                }
                
            }
        }catch(NullPointerException e){
            list3 = null;
        }
        return list3;
    }
    public RegistrosUnicos selectDevueltas(Solicitudes solicitud) {
        List<RegistrosUnicos> list1;
        List<RegistrosUnicos> list2;
        List<RegistrosUnicos> list3  = new ArrayList();
        RegistrosUnicos ru = new RegistrosUnicos();
        try{
            String sql = "FROM "+tableName+" AS ru "
                    + "INNER JOIN FETCH ru.estados es "
                    + "INNER JOIN FETCH ru.solicitudes s "
                    + "INNER JOIN FETCH s.docenteses d "
                    + "WHERE es = 4 AND ru.confirmado = FALSE AND s = d.solicitudes AND s = " + solicitud.getId();
            Query q = sesion.createQuery(sql);
            list1 = q.list();
            for(RegistrosUnicos re : list1){
                if(re.getFechaSalida() == null){
                    list3.add(re);
                }else{
                    String sql2 = "FROM " +tableName+" AS ru "
                    + " WHERE ru.fechaEntrada >= '"+re.getFechaSalida()+"' AND ru.solicitudes = "+re.getSolicitudes().getId()+" AND ru > "+re.getId();
                    list2 = sesion.createQuery(sql2).list();
                    if(list2.isEmpty())
                        list3.add(re);
                }
                
            }
            ru = list3.get(0);
        }catch(NullPointerException e){
            list3 = null;
        }
        return ru;
    }
    public List<RegistrosUnicos> selectDevueltasAreas(Areas area) {
        List<RegistrosUnicos> list1;
        try{
            String sql = "FROM " + tableName + " AS ru "
                    + "INNER JOIN FETCH ru.estados es "
                    + "INNER JOIN FETCH ru.solicitudes s "
                    + "INNER JOIN FETCH s.docenteses d "
                    + "WHERE ru.areas = "+area.getId()+" AND ru.estados = 8 AND confirmado = TRUE AND ru.fechaSalida = NULL";
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
                    + "WHERE ru.areas = "+area.getId()+" AND ru.fechaSalida = NULL AND ru.estados = 8 AND s = " + idSol;
            Query q = sesion.createQuery(sql);
            a = (RegistrosUnicos) q.list().get(0);
        }catch(Exception e){
            a = null;
        }
        return a;
    }
    
    public List<RegistrosUnicos> selectFromParametros(Areas area,Estados estado) {
        List<RegistrosUnicos> list1;
        try{
            String sql = "FROM " + tableName + " AS ru "
                    + "INNER JOIN FETCH ru.estados es "
                    + "INNER JOIN FETCH ru.solicitudes s "
                    + "WHERE ru.areas = "+area.getId()+" AND ru.estados = " + estado.getId();
            Query q = sesion.createQuery(sql);
            list1 = q.list();
        }catch(NullPointerException e){
            list1 = null;
        }
        return list1;
    }
    
    public List<RegistrosUnicos> selectFromParametros(Estados estado) {
        List<RegistrosUnicos> list1;
        try{
            String sql = "FROM " + tableName + " AS ru "
                    + "INNER JOIN FETCH ru.estados es "
                    + "INNER JOIN FETCH ru.solicitudes s "
                    + "WHERE ru.estados = " + estado.getId();
            Query q = sesion.createQuery(sql);
            list1 = q.list();
        }catch(NullPointerException e){
            list1 = null;
        }
        return list1;
    }
    
    public List<RegistrosUnicos> selectFromParametros(Solicitudes sol, Areas area,Estados estado) {
        List<RegistrosUnicos> list1;
        try{
            String sql = "FROM " + tableName + " AS ru "
                    + "INNER JOIN FETCH ru.estados es "
                    + "INNER JOIN FETCH ru.solicitudes s "
                    + "WHERE ru.areas = "+area.getId()+" AND ru.estados = " + estado.getId() + " AND ru.solicitudes = " + sol.getId();
            Query q = sesion.createQuery(sql);
            list1 = q.list();
        }catch(NullPointerException e){
            list1 = null;
        }
        return list1;
    }
    public List<RegistrosUnicos> selectFromParametros(Solicitudes sol, Areas area,Estados estado,int i) {
        List<RegistrosUnicos> list1;
        try{
            String sql = "FROM " + tableName + " AS ru "
                    + "INNER JOIN FETCH ru.estados es "
                    + "INNER JOIN FETCH ru.solicitudes s "
                    + "WHERE ru.areas = "+area.getId()+" AND ru.estados = " + estado.getId() + " AND ru.solicitudes = " + sol.getId()+" AND ru.fechaSalida = NULL "
                    + " ORDER BY ru DESC";
            Query q = sesion.createQuery(sql);
            list1 = q.list();
        }catch(NullPointerException e){
            list1 = null;
        }
        return list1;
    }
}
