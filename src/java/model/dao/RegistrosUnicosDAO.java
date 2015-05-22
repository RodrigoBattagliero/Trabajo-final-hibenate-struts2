/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

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
            String sql = "FROM "+tableName+" AS ru INNER JOIN ru.estados es INNER JOIN ru.solicitudes s INNER JOIN s.docenteses d WHERE ru.areas = "+area.getId()+" AND es <> 1 AND ru.confirmado = FALSE AND s = d.solicitudes";
            Query q = sesion.createQuery(sql);
            list = q.list();
        }catch(NullPointerException e){
            reg = null;
        }
        return list;
    }
    
}
