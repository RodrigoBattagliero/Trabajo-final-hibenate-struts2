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
    private int id;
    
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

    public void setId(int id) {
        this.id = id;
    }
    
    public String prepared(){
        this.sesion.setAttribute("ActividadDocenteForm", this.entity);
        return SUCCESS;
    }
    
    public String setIdDesignacion(){
        this.sesion.setAttribute("idDesignacionSelected", this.request.getParameter("idDesignacionSelected"));
        return SUCCESS;
    }
    
    public String updatePrepared(){
        String idDesStr = String.valueOf(this.request.getParameter("idDesignacionSelected"));
        this.sesion.setAttribute("idDesignacionSelected", idDesStr);
        this.selectRelated(Integer.parseInt(idDesStr));
        try{
            this.entity = this.entities.get(0);
            this.sesion.setAttribute("idActividadDocente", this.entity.getId());
        }catch(NullPointerException | IndexOutOfBoundsException e){
            this.entity = new ActividadDocentes();
        }
        return SUCCESS;
    }
    
    @Override
    public String update(){
        String idSolStr = String.valueOf(this.sesion.getAttribute("idDesignacionSelected"));
        String idActStr = String.valueOf(this.sesion.getAttribute("idActividadDocente"));
        DesignacionesController desCont = new DesignacionesController();
        desCont.selectOne(Integer.parseInt(idSolStr));
        this.entity.setDesignaciones(desCont.getEntity());
        this.entity.setId(Integer.parseInt(idActStr));
        this.dao.iniciaOperacion();
        String res = super.update();
        this.dao.cerrarSession();
        return res;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        this.sesion = this.request.getSession();
    }
}
