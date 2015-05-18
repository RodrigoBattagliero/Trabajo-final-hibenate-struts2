/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.ActividadDocentesDAO;
import model.entities.ActividadDocentes;

/**
 *
 * @author rodrigo
 */
public class ActividadDocentesController extends Controller<ActividadDocentes> {

    public ActividadDocentesController() {
        dao = (ActividadDocentesDAO) new ActividadDocentesDAO();
    }
    
    @Override
    public ActividadDocentesDAO getDao() {
        return (ActividadDocentesDAO) dao;
    }

    public void setDao(ActividadDocentesDAO dao) {
        this.dao = dao;
    }
    
    
}
