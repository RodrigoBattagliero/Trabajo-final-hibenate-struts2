/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakan.dao;

import conexion.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rodrigo
 */
public class DAO {
    
    protected Session sesion;
    protected Transaction tx;
    protected String tableName = "";
    protected Object entitie;
    
    public void iniciaOperacion() throws HibernateException{
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }
    
    protected void manejaExcepcion(HibernateException he) throws HibernateException{
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
    
    public void cerrarSession(){
        sesion.close();
    }
    
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
    
    
}
