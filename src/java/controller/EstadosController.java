/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.EstadosDAO;
import model.entities.Estados;

/**
 *
 * @author rodrigo
 */
public class EstadosController extends Controller<Estados> {

    public EstadosController() {
        dao = (EstadosDAO) new EstadosDAO();
    }
    
    @Override
    public EstadosDAO getDao() {
        return (EstadosDAO) dao;
    }

    public void setDao(EstadosDAO dao) {
        this.dao = dao;
    }
    
}
