/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conexion.HibernateUtil;
import interfaces.IQueries;
import java.util.List;
import model.entities.Areas;
import model.entities.Usuarios;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rodrigo
 * @param <TipoObject>
 */
public class DAO implements IQueries<Object>{
    protected Session sesion;
    protected Transaction tx;
    protected String tableName = "";
    protected Object entitie;
    
    
    public void iniciaOperacion() throws HibernateException{
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        
    }
    public Session getSesion(){
        return this.sesion;
    }
    public void iniciarTransaccion(){
        tx = sesion.beginTransaction();
    }
    public void commit(){
        tx.commit();
    }
    public void abordar(){
        tx.rollback();
    }
    public void manejaExcepcion(HibernateException he) throws HibernateException{
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
    
    public void cerrarSession(){
        sesion.close();
    }

    @Override
    public int create(Object c) {
        int id = 0;
        try{
//            iniciaOperacion();
            id =Integer.parseInt(String.valueOf(sesion.save(c)));
            tx.commit();
        }catch(HibernateException he){
            manejaExcepcion(he);
            throw he;
        }catch(Exception e){
            System.out.println(e);
        }finally{
//            sesion.close();
        }
        return id;
    }

    @Override
    public boolean delete(Object c) {
        boolean b = false;
        try{
//            iniciaOperacion();
            sesion.delete(c);
            tx.commit();
            b = true;
        }catch(HibernateException he){
            manejaExcepcion(he);
            throw he;
        }finally{
//            sesion.close();
        }
        return b;
    }

    @Override
    public boolean update(Object c) {
        boolean b = false;
        try{
//            iniciaOperacion();
            sesion.update(c);
            tx.commit();
            b = true;
        }catch(HibernateException he){
            manejaExcepcion(he);
            throw he;
        }finally{
//            sesion.close();
        }
        return b;
    }
    
    @Override
    public Object selectOne(Object key) {
        Object a = entitie;
        try{
//            iniciaOperacion();
            a = sesion.get(entitie.getClass(), (int) key);
        }finally{
//            sesion.close();
        }
        return a;
    }
    
    @Override
    public List<Object> selectAll() {
        List<Object> list = null;
        try{
//            iniciaOperacion();
            list = sesion.createQuery("FROM " + tableName).list();
        }finally{
//            sesion.close();
        }
        return list;
    }
    
    @Override
    public List<Object> selectRelated(Object key) {
        List<Object> list = null;
        String idStr = String.valueOf(key);
        try{
//            iniciaOperacion();
            String sql = "FROM " + tableName + " t WHERE t.solicitudes = " + (int) key;
            list = sesion.createQuery(sql).list();
        }finally{
//            sesion.close();
        }
        return list;
    }

    public Areas proxima(Areas area) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Usuarios> login(String user, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
