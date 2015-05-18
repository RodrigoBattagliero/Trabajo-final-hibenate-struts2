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
        dao = (UsuariosDAO) new UsuariosDAO();
    }
    
    @Override
    public UsuariosDAO getDao() {
        return (UsuariosDAO) dao;
    }

    public void setDao(UsuariosDAO dao) {
        this.dao = dao;
    }
    
}
