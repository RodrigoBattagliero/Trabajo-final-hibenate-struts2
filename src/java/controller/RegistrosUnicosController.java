/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.RegistrosUnicosDAO;
import model.entities.Areas;
import model.entities.Estados;
import model.entities.RegistrosUnicos;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class RegistrosUnicosController extends Controller<RegistrosUnicos> implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    private int idAreaSelected;
    private int idEstadoSelected;
    private List<Estados> listEstados;
    
    public RegistrosUnicosController() {
        dao = (RegistrosUnicosDAO) new RegistrosUnicosDAO();
        this.entity = new RegistrosUnicos();
        selectEstados();
    }
    
    @Override
    public RegistrosUnicosDAO getDao() {
        return (RegistrosUnicosDAO) dao;
    }
    
    public List<Estados> getListEstados(){
        return this.listEstados;
    }
    
    public void setDao(RegistrosUnicosDAO dao) {
        this.dao = dao;
    }
    
    public void setIdAreaSelected(int id){
        this.idAreaSelected = id;
    }
    
    public void setIdEstadoSelected(int id){
        this.idEstadoSelected = id;
    }
    
    public String crear(){
        
//        sesion.setAttribute("SolicitudesControllerForm", sesion.getAttribute("SolicitudesControllerForm"));
//        sesion.setAttribute("DocentesControllerForm", sesion.getAttribute("DocentesControllerForm"));
//        sesion.setAttribute("ComprobantesControllerForm", sesion.getAttribute("ComprobantesControllerForm"));
        this.entity.setEstados(getEstado());
        this.entity.setAreas(getArea());
        this.entity.setConfirmado(true);
        sesion.setAttribute("RegistroUnicoForm", this.entity);
//        this.entity = null;
//        this.entity = new RegistrosUnicos();
        this.idAreaSelected = 1;
        RegistrosUnicos re = new RegistrosUnicos();
        re.setAreas(getArea());
        re.setFechaEntrada(new Date());
        this.idEstadoSelected = 1;
        re.setEstados(getEstado());
        re.setConfirmado(Boolean.FALSE);
        re.setObservaciones("");
        sesion.setAttribute("RegistroUnicoProximo",re );
        return SUCCESS;
    }
    
    private Estados getEstado(){
        EstadosController est = new EstadosController();
        est.selectOne(idEstadoSelected);
        return est.getEntity();
    }
    
    private Areas getArea(){
        AreasController ar = new AreasController();
        ar.selectOne(idAreaSelected);
        return ar.getEntity();
    }
    
    private void selectEstados(){
        EstadosController es = new EstadosController();
        es.select();
        listEstados = es.getEntities();
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        this.sesion = this.request.getSession();
    }
    
    
}
