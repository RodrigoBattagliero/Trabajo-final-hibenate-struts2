/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.SedesDAO;
import model.entities.Sedes;

/**
 *
 * @author rodrigo
 */
public class SedesController extends Controller<Sedes> {

    public SedesController() {
        dao = (SedesDAO) new SedesDAO();
        entity = new Sedes();
    }
    
    @Override
    public SedesDAO getDao() {
        return (SedesDAO) dao;
    }

    public void setDao(SedesDAO dao) {
        this.dao = dao;
    }
    
    public String AltaSedes(){
        return SUCCESS;
    }
    
}
