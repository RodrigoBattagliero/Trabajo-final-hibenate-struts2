/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.DAO;
import model.dao.SolicitudesDAO;
import model.entities.Sedes;
import model.entities.Solicitudes;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class SolicitudesController extends Controller<Solicitudes> implements ServletRequestAware {
    
    private List<Sedes> sedesList;
    private int idSelectedSede;
    private Sedes sede = null;
    private HttpServletRequest request;
    
    public SolicitudesController() {
        dao = (SolicitudesDAO) new SolicitudesDAO();
        entity = new Solicitudes();
        selectSedes();
    }
    
    @Override
    public SolicitudesDAO getDao() {
        return (SolicitudesDAO) dao;
    }

    public void setDao(SolicitudesDAO dao) {
        this.dao = dao;
    }
    
    public String AltaSolicitud(){
        DAO sedesDAO = new SedesController().dao;
        sedesDAO.iniciaOperacion();
        sedesList = (List) sedesDAO.selectAll();
        sedesDAO.cerrarSession();
        return SUCCESS;
    }
    
    public List<Sedes> getSedesList() {
        return sedesList;
    }

    public void setSedesList(List<Sedes> sedesList) {
        this.sedesList = sedesList;
    }
    
    public void setIdSelectedSede(int idSede){
        this.idSelectedSede = idSede;
        
    }  
    
    public void setSede(Sedes sede){
        this.sede = sede;
    }
    
    public String selectSedes(){
        
        SedesController sedesController = new SedesController();
        sedesController.select();
        sedesList = sedesController.getEntities();
        return SUCCESS;
    }
    
    public Sedes selectSedes(int key){
        SedesController sedesController = new SedesController();
        sedesController.selectOne(key);
        return sedesController.getEntity();
    }
    
    public String prepared(){
        this.entity.setSedes(selectSedes(idSelectedSede));
        HttpSession sesion = request.getSession();
        // validar
        sesion.setAttribute("SolicitudesForm", this.entity);
        return SUCCESS;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
