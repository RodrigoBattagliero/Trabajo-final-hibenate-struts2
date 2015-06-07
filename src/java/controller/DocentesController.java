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
        entity.setDepartamentosAcademicos(deptoAcademico());
        // Validar
//        sesion.setAttribute("SolicitudesControllerForm",sesion.getAttribute("SolicitudesControllerForm") );
        sesion.setAttribute("DocentesForm", this.entity);
        return SUCCESS;
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

}
