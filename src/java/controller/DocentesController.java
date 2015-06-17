/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kakan.controller.SpgDocentesController;
import kakan.entities.SpgDocentes;
import model.dao.DocentesDAO;
import model.entities.DepartamentosAcademicos;
import model.entities.Docentes;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class DocentesController extends Controller<Docentes> implements ServletRequestAware {
    
    private List<String> listMotivoComision;
    private List<DepartamentosAcademicos> listDeptosAcademicos;
    private int idDeptoAcademico;
    private ServletContext application;
    private HttpServletRequest request;
    private HttpSession sesion;
    
    public DocentesController() {
        this.dao = (DocentesDAO) new DocentesDAO();
        entity = new Docentes();
        setMotivoComision();
        setDeptosAcademicos();
    }
    
    @Override
    public DocentesDAO getDao() {
        return (DocentesDAO) dao;
    }

    public void setDao(DocentesDAO dao) {
        this.dao = dao;
    }
    
    public List<String> getListMotivoComision(){
        return this.listMotivoComision;
    }
    
    public List<DepartamentosAcademicos> getListDeptosAcademicos() {
        return listDeptosAcademicos;
    }
    
    public void setIdDeptoAcademico(int id){
        this.idDeptoAcademico = id;
    }
    
    private void setMotivoComision(){
        listMotivoComision = new ArrayList();
        listMotivoComision.add("Dictado de clases");
        listMotivoComision.add("Exámen final");
        listMotivoComision.add("Clases de apoyo");
        listMotivoComision.add("Otra");
    }
    
    private void setDeptosAcademicos(){
        DepartamentosAcademicosController deptoController = new DepartamentosAcademicosController();
        deptoController.select();
        listDeptosAcademicos = (List) deptoController.getEntities();
    }
    
    private DepartamentosAcademicos deptoAcademico(){
        DepartamentosAcademicosController deptoController = new DepartamentosAcademicosController();
        deptoController.selectOne(idDeptoAcademico);
        return deptoController.getEntity();
    }
    
    public String prepared(){
        String res = SUCCESS;
        entity.setDepartamentosAcademicos(deptoAcademico());
        if(this.validar())
            sesion.setAttribute("DocentesForm", this.entity);
        else
            res = INPUT;
        return res;
    }
    
    public String updatePrepared(){
        String idSolStr = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
        this.selectRelated(Integer.parseInt(idSolStr));
        try{
            this.entity = this.entities.get(0);
            this.sesion.setAttribute("idDocente", this.entity.getId());
        }catch(NullPointerException | IndexOutOfBoundsException e){
            this.entity = new Docentes();
        }
        return SUCCESS;
    }
    
    @Override
    public String update(){
        String idDocStr = String.valueOf(this.sesion.getAttribute("idDocente"));
        String idSolStr = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
        SolicitudesController solCont = new SolicitudesController();
        solCont.selectOne(Integer.parseInt(idSolStr));
        this.entity.setSolicitudes(solCont.getEntity());
        this.entity.setId(Integer.parseInt(idDocStr));
        this.entity.setDepartamentosAcademicos(deptoAcademico());
        this.dao.iniciaOperacion();
        String res = super.update();
        this.dao.cerrarSession();
        return res;
    }
    
    public void selectRelatedWithDepto(int idSol){
        DocentesDAO dao = new DocentesDAO();
        dao.iniciaOperacion();
        entities = dao.selectRelatedWithDepto(idSol);
        dao.cerrarSession();
    }


    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = request.getSession();
    }
    
    public String BuscarDocenteKakan(){
        SpgDocentesController docKakan = new SpgDocentesController();
        try{
            docKakan.setDni(this.entity.getDni());
            docKakan.execute();
            if(docKakan.getEntities().size() > 1){
                addActionError("Error: se han encontrado más de un docente con el mismo DNI. Por favor, ingresar datos manualmente.");
            }else if(docKakan.getEntities().size() == 0){
                addActionError("Error: No se han encontrado datos.");
            }else{
                SpgDocentes spgDoc = docKakan.getEntities().get(0);
                this.entity.setApellido(spgDoc.getApellido());
                this.entity.setNombre(spgDoc.getNombre());
                this.entity.setDni(spgDoc.getDocumento());
                this.entity.setLugarResidencia(spgDoc.getResidencia());
                this.entity.setEmail(spgDoc.getMail());
            }
        }catch(Exception e){
            this.entity = null;
        }
        return SUCCESS;
    }
    
    public boolean validar(){
        boolean b = true;
        if(this.entity.getApellido().equals("")){
            addFieldError("apellido", "Debe completar el apellido.");
            b = false;
        }
        if(this.entity.getDepartamentosAcademicos() == null){
            addFieldError("departamentosAcademicos", "Debe seleccionar el departamento acádemico.");
            b = false;
        }
        if(this.entity.getDni().equals("")){
            addFieldError("dni", "Debe completar el DNI.");
            b = false;
        }
        if(this.entity.getEmail().equals("")){
            addFieldError("email", "Debe completar el email.");
            b = false;
        }
        if(this.entity.getFechaFinalizacion() == null){
            addFieldError("fechaFinalizacion", "Debe completar la fecha de finalización.");
            b = false;
        }
        if(this.entity.getFechaInicio() == null){
            addFieldError("apellido", "Debe completar la fecha de inicio.");
            b = false;
        }
        if(this.entity.getLugarResidencia().equals("")){
            addFieldError("lugarResidencia", "Debe completar el lugar de residencia.");
            b = false;
        }
        if(this.entity.getMotivoComision().equals("")){
            addFieldError("motivoComision", "Debe completar el motivo de comisión.");
            b = false;
        }
        if(this.entity.getNombre().equals("")){
            addFieldError("nombre", "Debe completar el nombre.");
            b = false;
        }
        if(this.entity.getTelefono().equals("")){
            addFieldError("telefono", "Debe completar el telefono.");
            b = false;
        }
        return b;
    }
}
