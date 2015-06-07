/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.ActividadDocentesDAO;
import model.dao.DesignacionesDAO;
import model.entities.Designaciones;
import model.entities.Solicitudes;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class DesignacionesController extends Controller<Designaciones> implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    private String[] numeroResolucion;
    private String[] categoria;
    private Date[] desde;
    private Date[] hasta;
    private String[] dedicacion;
    private String[] observaciones;
    private int idSolicitudSelected;

    public DesignacionesController() {
        dao = (DesignacionesDAO) new DesignacionesDAO();
        entity = new Designaciones();
    }
    
    @Override
    public DesignacionesDAO getDao() {
        return (DesignacionesDAO) dao;
    }

    public void setDao(DesignacionesDAO dao) {
        this.dao = dao;
    }

    public void setIdSolicitudSelected(int idSolicitudSelected) {
        this.idSolicitudSelected = idSolicitudSelected;
    }

    public void setNumeroResolucion(String[] numeroResolucion) {
        this.numeroResolucion = numeroResolucion;
    }

    public void setCategoria(String[] categoria) {
        this.categoria = categoria;
    }

    public void setDesde(Date[] desde) {
        this.desde = desde;
    }

    public void setHasta(Date[] hasta) {
        this.hasta = hasta;
    }

    public void setDedicacion(String[] dedicacion) {
        this.dedicacion = dedicacion;
    }

    public void setObservaciones(String[] observaciones) {
        this.observaciones = observaciones;
    }
    
    public String prepared(){
        setDesignaciones();
        String res = SUCCESS;
        sesion.setAttribute("DesignacionesForm", this.entities);
        return res;
    }
    private void setDesignaciones(){
        this.entities = new ArrayList();
        int cant = numeroResolucion.length;
        if(cant < categoria.length)
            cant = categoria.length;
        if(cant < desde.length)
            cant = desde.length;
        if(cant < hasta.length)
            cant = hasta.length;
        if(cant < dedicacion.length)
            cant = dedicacion.length;
        if(cant < observaciones.length)
            cant = observaciones.length;
        
        for (int i = 0; i < cant; i++) {
            if( !numeroResolucion[i].equals("") || !categoria[i].equals("") || desde[i] != null || hasta[i] != null || !dedicacion[i].equals("") || !observaciones[i].equals("") ){
                this.entity = new Designaciones();
                this.entity.setCategoria(categoria[i]);
                this.entity.setDedicacion(dedicacion[i]);
                this.entity.setDesde(desde[i]);
                this.entity.setHasta(hasta[i]);
                this.entity.setNumeroResolucion(numeroResolucion[i]);
                this.entity.setObservaciones(observaciones[i]);
                this.entity.setSolicitudes(selectSolicitud());
                this.entities.add(this.entity);
            }
        }
    }
    
    private Solicitudes selectSolicitud(){
        SolicitudesController sol = new SolicitudesController();
        sol.selectOne(idSolicitudSelected);
        return sol.getEntity();
    }
    
    public String setDesignacionesToSolicitud(){
        String idSol = String.valueOf(this.request.getParameter("idSolicitudSelected"));
        this.sesion.setAttribute("idSolicitudSelected",idSol );
        this.dao.iniciaOperacion();
        this.entities = new ArrayList();
        this.entities = (List) this.dao.selectRelated(Integer.parseInt(idSol));
        this.dao.cerrarSession();
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        this.sesion = this.request.getSession();
    }

    public void selectRelatedAll(int idSol) {
        DesignacionesDAO dao = new DesignacionesDAO();
        dao.iniciaOperacion();
        this.entities = dao.selectRelatedAll(idSol);
        dao.cerrarSession();
    }
}
