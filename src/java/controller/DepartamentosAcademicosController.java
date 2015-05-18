/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.DepartamentosAcademicosDAO;
import model.entities.DepartamentosAcademicos;

/**
 *
 * @author rodrigo
 */
public class DepartamentosAcademicosController extends Controller<DepartamentosAcademicos> {

    public DepartamentosAcademicosController() {
        dao = (DepartamentosAcademicosDAO) new DepartamentosAcademicosDAO();
        entity = new DepartamentosAcademicos();
    }
    
    @Override
    public DepartamentosAcademicosDAO getDao() {
        return (DepartamentosAcademicosDAO) dao;
    }

    public void setDao(DepartamentosAcademicosDAO dao) {
        this.dao = dao;
    }
    
}
