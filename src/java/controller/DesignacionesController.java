/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.AreasDAO;
import model.dao.DesignacionesDAO;
import model.entities.Designaciones;

/**
 *
 * @author rodrigo
 */
public class DesignacionesController extends Controller<Designaciones> {

    public DesignacionesController() {
        dao = (DesignacionesDAO) new DesignacionesDAO();
    }
    
    @Override
    public DesignacionesDAO getDao() {
        return (DesignacionesDAO) dao;
    }

    public void setDao(DesignacionesDAO dao) {
        this.dao = dao;
    }
    
}
