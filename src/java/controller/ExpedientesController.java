/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.ExpedientesDAO;
import model.entities.Expedientes;

/**
 *
 * @author rodrigo
 */
public class ExpedientesController extends Controller<Expedientes> {

    public ExpedientesController() {
        dao = (ExpedientesDAO) new ExpedientesDAO();
    }
    
    @Override
    public ExpedientesDAO getDao() {
        return (ExpedientesDAO) dao;
    }

    public void setDao(ExpedientesDAO dao) {
        this.dao = dao;
    }
    
}
