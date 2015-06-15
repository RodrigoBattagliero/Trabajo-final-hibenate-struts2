/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakan.controller;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import kakan.dao.SpgDocentesDAO;
import kakan.entities.SpgDocentes;

/**
 *
 * @author rodrigo
 */
public class SpgDocentesController extends ActionSupport{
    
    private List<SpgDocentes> entities;
    private SpgDocentes entity;
    private SpgDocentesDAO model;
    private String dni;
    
    public SpgDocentesController(){ 
        model = new SpgDocentesDAO();
        entities = null;
        entity = new SpgDocentes();
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    @Override
    public String execute(){
        model = new SpgDocentesDAO();
        this.entities = model.selectDocentes(this.dni);
        return SUCCESS;
    }
    
    public Object getModel(){
        return entity;
    }

    public List<SpgDocentes> getEntities() {
        return entities;
    }
    
    
}
