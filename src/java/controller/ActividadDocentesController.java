/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.ActividadDocentesDAO;
import model.entities.ActividadDocentes;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class ActividadDocentesController extends Controller<ActividadDocentes> implements ServletRequestAware {

    private HttpServletRequest request;
    private HttpSession sesion;
    
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
    
    public String prepared(){
        this.sesion.setAttribute("ActividadDocenteForm", this.entity);
        return SUCCESS;
    }
    
    public String setIdDesignacion(){
        this.sesion.setAttribute("idDesignacionSelected", this.request.getParameter("idDesignacionSelected"));
        return SUCCESS;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        this.sesion = this.request.getSession();
    }
}
