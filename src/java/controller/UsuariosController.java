/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.UsuariosDAO;
import model.entities.Usuarios;

/**
 *
 * @author rodrigo
 */
public class UsuariosController extends Controller<Usuarios> {

    public UsuariosController() {
        this.dao = (UsuariosDAO) new UsuariosDAO();
    }
    
    @Override
    public UsuariosDAO getDao() {
        return (UsuariosDAO) dao;
    }

    public void setDao(UsuariosDAO dao) {
        this.dao = dao;
    }
    
    public boolean login(String user, String pass){
        boolean b = false;
        this.dao = (UsuariosDAO) new UsuariosDAO();
        this.entity = new Usuarios();
        this.dao.iniciaOperacion();
        this.entities = dao.login(user,pass);
        this.dao.cerrarSession();
        if(this.entities.size() == 1){
            b = true;
            //this.entity = this.entities.get(0);
            this.entity.setAreas(this.entities.get(0).getAreas());
            this.entity.setId(this.entities.get(0).getId());
            this.entity.setNombre(this.entities.get(0).getNombre());
            this.entity.setPassword(this.entities.get(0).getPassword());
            this.entity.setUser(this.entities.get(0).getUser());
            this.entity.setSedes(this.entities.get(0).getSedes());
        }
        
        return b;
    }
    
}