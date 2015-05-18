/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.AreasDAO;
import model.dao.ExpedientesSolicitudesDAO;
import model.entities.ExpedientesSolicitudes;

/**
 *
 * @author rodrigo
 */
public class ExpedientesSolicitudesController extends Controller<ExpedientesSolicitudes> {

    public ExpedientesSolicitudesController() {
        dao = (ExpedientesSolicitudesDAO) new ExpedientesSolicitudesDAO();
    }
    
    @Override
    public ExpedientesSolicitudesDAO getDao() {
        return (ExpedientesSolicitudesDAO) dao;
    }

    public void setDao(ExpedientesSolicitudesDAO dao) {
        this.dao = dao;
    }
    
}
