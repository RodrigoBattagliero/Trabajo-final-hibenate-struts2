/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.ExpedientesSolicitudesDAO;
import model.entities.ExpedientesSolicitudes;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class ExpedientesSolicitudesController extends Controller<ExpedientesSolicitudes> implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    private ExpedientesSolicitudesDAO dao;
    
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
    
    public String detalle(){
        String res = SUCCESS;
        String idExp = this.request.getParameter("idExpedienteSelected");
        this.dao.iniciaOperacion();
        this.entities = this.dao.selectDetalle(Integer.parseInt(idExp));
        this.dao.cerrarSession();
        return res;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        this.sesion = this.request.getSession();
    }
    
}
