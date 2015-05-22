/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import model.dao.DAO;

/**
 *
 * @author rodrigo
 * @param <Object>
 * 
 */
public abstract class Controller<Object> extends ActionSupport {
    
    protected DAO dao;
    protected Object entity;
    protected List<Object> entities;
    protected int id;

    public Controller() {
    }
    
    public String select(){     
        String res = SUCCESS;
        dao.iniciaOperacion();
        entities = (List) dao.selectAll();
        dao.cerrarSession();
        return res;
    }
    
    public String selectOne(int id){
        String res = SUCCESS;
        dao.iniciaOperacion();
        entity = (Object) dao.selectOne(id);
        dao.cerrarSession();
        return res;
    }
    
    public String selectRelated(int id){
        String res = SUCCESS;
        dao.iniciaOperacion();
        entities = (List) dao.selectRelated(id);
        dao.cerrarSession();
        return res;
    }
    
    public String save(){   
        String res = ERROR;
        dao.iniciaOperacion();
        id = dao.create(entity);
        dao.cerrarSession();
        if(id > 0)
            res = SUCCESS;
        return res; 
    }
    
    public String update(){
        String res = ERROR;
        boolean b = false;
        try{
//            this.dao.iniciaOperacion();
            b = dao.update(entity);
//            dao.cerrarSession();
        }catch(NullPointerException e){
            System.out.println(e);
        }
        if(b)
            res = SUCCESS;
        return res; 
    }
    
    public Object getModel(){
        return entity;
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }
    
    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public List<Object> getEntities() {
        return entities;
    }
    
    public int getId(){
        return this.id;
    }

//    public void setEntities(List<Object> entities) {
//        this.entities = entities;
//    }
    
    
    
}
