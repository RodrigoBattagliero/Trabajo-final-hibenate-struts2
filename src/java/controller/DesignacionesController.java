/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kakan.controller.OgagtdController;
import kakan.entities.VOgagtd;
import model.dao.DesignacionesDAO;
import model.entities.ActividadDocentes;
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
    private String[] seleccionado;
    private int[] idComision;
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

    public void setSeleccionado(String[] seleccionado) {
        this.seleccionado = seleccionado;
    }

    public int[] getIdComision() {
        return idComision;
    }

    public void setIdComision(int[] idComision) {
        this.idComision = idComision;
    }
    
    public void setEntities(List<Designaciones> de){
        this.entities = de;
    }
    
    public String prepared(){
        String res = SUCCESS;
        if(setDesignaciones())
            sesion.setAttribute("DesignacionesForm", this.entities);
        else
            res = INPUT;
        return res;
    }
    private boolean setDesignaciones(){
        this.entities = new ArrayList();
        boolean b = true;
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
            if(seleccionado[i].equals("si")){
                if( !numeroResolucion[i].equals("") || !categoria[i].equals("") || desde[i] != null || hasta[i] != null || !dedicacion[i].equals("") || !observaciones[i].equals("") || idComision[i] != 0 ){
                    this.entity = new Designaciones();
                    this.entity.setCategoria(categoria[i]);
                    this.entity.setDedicacion(dedicacion[i]);
                    this.entity.setDesde(desde[i]);
                    this.entity.setHasta(hasta[i]);
                    this.entity.setNumeroResolucion(numeroResolucion[i]);
                    this.entity.setIdComision(idComision[i]);
                    this.entity.setObservaciones(observaciones[i]);
                    this.entity.setSolicitudes(selectSolicitud());
                    if(!this.validar())
                        b = false;
                    this.entities.add(this.entity);
                        
                }
            }
        }
        return b;
    }
    
    private Solicitudes selectSolicitud(){
        SolicitudesController sol = new SolicitudesController();
        sol.selectOne(idSolicitudSelected);
        return sol.getEntity();
    }
    
    public String setDesignacionesToSolicitud(){
        String idSol = null;
        try{
            idSol = String.valueOf(this.request.getParameter("idSolicitudSelected"));
        }catch(Exception e){
            //idSol = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
        }
        if(idSol.equals("null")){
            idSol = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
        }
        this.sesion.setAttribute("idSolicitudSelected",idSol );
        this.dao.iniciaOperacion();
        this.entities = new ArrayList();
        this.entities = (List) this.dao.selectRelated(Integer.parseInt(idSol));
        this.dao.cerrarSession();
        
        List<ActividadDocentes> listAct = (List<ActividadDocentes>) this.sesion.getAttribute("ActividadDocenteForm");
        List<Integer> listToRemove = new ArrayList();
        if(listAct != null){
            for(ActividadDocentes actDoc : listAct){
                int cantEntities =this.entities.size();
                for (int i = 0; i < cantEntities; i++) {
                    if(actDoc.getDesignaciones().getId() == this.entities.get(i).getId()){
                        listToRemove.add(i);
                    }
                }
            }
            
            Collections.sort(listToRemove);
            int cantToRemove = listToRemove.size();
            for(int i = (--cantToRemove); i >= 0; i--){
                int idRemove = listToRemove.get(i);
                this.entities.remove(idRemove);
            }
        }
        
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
    
    public String setUpdate(){
        String idSolStr = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
        DesignacionesDAO dao = new DesignacionesDAO();
        dao.iniciaOperacion();
        this.entities = dao.selectRelatedAll(Integer.parseInt(idSolStr));
        dao.cerrarSession();
        return SUCCESS;
    }
    
    public String setDesignacion(){
        setUpdate();
        String idDesStr = this.request.getParameter("idDesignacionSelected");
        DesignacionesDAO dao = new DesignacionesDAO();
        dao.iniciaOperacion();
        this.entity = (Designaciones) dao.selectOne(Integer.parseInt(idDesStr));
        dao.cerrarSession();
        return SUCCESS;
    }
    
    @Override
    public String update(){
        String res = ERROR;
        DesignacionesDAO dao = new DesignacionesDAO();
        boolean b = false;
        try{
            String idSolStr = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
            SolicitudesController solCon = new SolicitudesController();
            solCon.selectOne(Integer.parseInt(idSolStr));
            this.entity.setSolicitudes(solCon.getEntity());
            dao.iniciaOperacion();
            b = dao.update(entity);
            dao.cerrarSession();
        }catch(NullPointerException e){
            System.out.println(e);
        }
        if(b)
            res = SUCCESS;
        return res; 
    }
    
    public String CompletarDatosCapital(){
        this.sesion.setAttribute("idSolicitudSelected", this.request.getParameter("idSolicitudSelected"));
        int idSol = Integer.parseInt(String.valueOf(sesion.getAttribute("idSolicitudSelected")));
        DocentesController docCon = new DocentesController();
        SolicitudesController solCon = new SolicitudesController();
        OgagtdController oCon = new OgagtdController();
        this.entities = new ArrayList();
        Designaciones desig;
        
        solCon.selectOne(idSol);
        docCon.selectRelated(idSol);
        
        try{
            oCon.setDni(docCon.getEntities().get(0).getDni());
            oCon.setFecha(solCon.getEntity().getFechaAlta());
            oCon.execute();
        }catch(Exception e){
            
        }
        for(VOgagtd vO : oCon.getEntities()){
            desig = new Designaciones();
            desig.setCategoria(vO.getNombreCategoria());
            desig.setDedicacion(vO.getDedicacion());
            desig.setDesde(vO.getFechaInicio());
            desig.setHasta(vO.getFechaFin());
            desig.setNumeroResolucion(vO.getNroResolucion());
            desig.setIdComision(vO.getComision());
            desig.setSolicitudes(solCon.getEntity());
            this.entities.add(desig);
        }
        
        return SUCCESS;
    }
    
    public boolean validar(){
        boolean b = true;
        if(this.entity.getCategoria().equals("")){
            addFieldError("categoria", "ERROR: Debe ingresar la categoría");
            b = false;
        }
        if(this.entity.getDedicacion().equals("")){
            addFieldError("dedicacion", "ERROR: Debe ingresar la dedicación");
            b = false;
        }
        if(this.entity.getDesde() == null){
            addFieldError("desde", "ERROR: Debe ingresar la fecha de inicio");
            b = false;
        }
        if(this.entity.getHasta() == null ){
            addFieldError("hasta", "ERROR: Debe ingresar la fecha de fin");
            b = false;
        }
        if(this.entity.getIdComision() == 0){
            addFieldError("idComision", "ERROR: Debe ingresar la comisión");
            b = false;
        }
        if(this.entity.getNumeroResolucion().equals("")){
            addFieldError("numeroResolucion", "ERROR: Debe ingresar el número de resolución");
            b = false;
        }
        return b;
    }
}
