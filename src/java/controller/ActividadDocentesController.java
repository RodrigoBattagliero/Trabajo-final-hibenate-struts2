/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kakan.controller.OgagtdController;
import kakan.entities.VOgagtd;
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
        String res = SUCCESS;
        int idSol = Integer.parseInt(String.valueOf(sesion.getAttribute("idSolicitudSelected")));
        int idDesignacion = Integer.parseInt(String.valueOf(sesion.getAttribute("idDesignacionSelected")));
        int cantActual;
        
        DesignacionesController desCon = new DesignacionesController();
        desCon.getDao().iniciaOperacion();
        desCon.setEntities((List) desCon.getDao().selectRelated(idSol));
        desCon.getDao().cerrarSession();
        int cantDesignaciones = desCon.getEntities().size();
        
        DesignacionesController desigController = new DesignacionesController();
        desigController.selectOne(idDesignacion);
        this.entity.setDesignaciones(desigController.getEntity());
        this.entities = (List<ActividadDocentes>) this.sesion.getAttribute("ActividadDocenteForm");
        
        if(this.entities == null){
            this.entities = new ArrayList();
            cantActual = 0;
        }else{
            cantActual = this.entities.size();
        }
        if(this.validar()){
            this.entities.add(this.entity);
            this.sesion.removeAttribute("ActividadDocenteForm");
            this.sesion.setAttribute("ActividadDocenteForm", this.entities);
            cantActual++;
            if(cantDesignaciones > cantActual )
                res = "back";
        }else{
            res = INPUT;
        }
        return res;
    }
    
    public String setIdDesignacion(){
        this.sesion.setAttribute("idDesignacionSelected", this.request.getParameter("idDesignacionSelected"));
        this.actividadesDocentesKakan();
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
    
    private void actividadesDocentesKakan(){
        int idDes = Integer.parseInt(String.valueOf(this.sesion.getAttribute("idDesignacionSelected")));
        int idSol = Integer.parseInt(String.valueOf(this.sesion.getAttribute("idSolicitudSelected")));
        this.entity = new ActividadDocentes();
        
        SolicitudesController solCon = new SolicitudesController();
        solCon.selectOne(idSol);
        
        DocentesController docCon = new DocentesController();
        docCon.selectRelated(idSol);
        
        DesignacionesController desCon = new DesignacionesController();
        desCon.selectOne(idDes);
        
        OgagtdController vO = new OgagtdController();
        try{
            vO.setDni(docCon.getEntities().get(0).getDni());
            vO.setFecha(solCon.getEntity().getFechaAlta());
            vO.setComision(desCon.getEntity().getIdComision());
            vO.selectFromComision();
        }catch(Exception e){
            
        }
        if(vO.getEntities().size() == 1){
            VOgagtd o = vO.getEntities().get(0);
            this.entity.setAsignatura(o.getNombreMateria());
            this.entity.setCarrera(o.getCarrera());
            //this.entity.setDesignaciones();
            //this.entity.setFecha();
            //this.entity.setId();
            this.entity.setIdComision(o.getComision());
            this.entity.setIdMateria(o.getMateria());
            this.entity.setIdUnidadAcademica(o.getIdUnidadAcademica());
            //this.entity.setObservaciones();
            //this.entity.setVisadoBedelia();
        }else if(vO.getEntities().size() > 1){
            addActionError("Error: Se han encontrado más de una relación para la comsión seleccionada. Ingrese los datos manualmente.");
        }else{
            addActionError("Error: No se ha encontrado una relación para la comsión seleccionada. Ingrese los datos manualmente.");
        }
        
    }
    
    public boolean validar(){
        boolean b = true;
        if(this.entity.getAsignatura().equals("")){
            addFieldError("asignatura", "ERROR: Debe ingresar una asignatura");
            b = false;
        }
        if(this.entity.getCarrera().equals("")){
            addFieldError("carrera", "ERROR: Debe ingresar una carrera");
            b = false;
        }
        if(this.entity.getFecha() == null){
            addFieldError("fecha", "ERROR: Debe ingresar una fecha");
            b = false;
        }
        if(this.entity.getIdComision() == 0){
            addFieldError("idComision", "ERROR: Debe ingresar el ID de la comisión");
            b = false;
        }
        if(this.entity.getIdMateria().equals("")){
            addFieldError("idMateria", "ERROR: Debe ingresar el ID de la materia");
            b = false;
        }
        if(this.entity.getIdUnidadAcademica() == 0){
            addFieldError("idUnidadAcademica", "ERROR: Debe ingresar el ID de la unidad academica");
            b = false;
        }
        if( !b && this.entity.getObservaciones().equals("")){
            addFieldError("observaciones", "ERROR: Si deja algún campo obligatorio debe ingresar una observación.");
        }else if(!b && !this.entity.getObservaciones().equals("")){
            b = true;
        }
        return b;
    }
}
