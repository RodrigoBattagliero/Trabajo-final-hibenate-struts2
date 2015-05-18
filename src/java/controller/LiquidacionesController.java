/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.AreasDAO;
import model.dao.LiquidacionesDAO;
import model.entities.Liquidaciones;

/**
 *
 * @author rodrigo
 */
public class LiquidacionesController extends Controller<Liquidaciones> {

    public LiquidacionesController() {
        dao = (LiquidacionesDAO) new LiquidacionesDAO();
    }
    
    @Override
    public LiquidacionesDAO getDao() {
        return (LiquidacionesDAO) dao;
    }

    public void setDao(LiquidacionesDAO dao) {
        this.dao = dao;
    }
    
}
