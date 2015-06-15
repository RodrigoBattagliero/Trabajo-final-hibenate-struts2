/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakan.controller;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import kakan.dao.DAO;

/**
 *
 * @author rodrigo
 */
public class Controller<Object> extends ActionSupport {
    
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
    
    public Object getModel(){
        return entity;
    }
}
