/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.DAO;
import model.dao.SolicitudesDAO;
import model.entities.Sedes;
import model.entities.Solicitudes;
import model.entities.Usuarios;
import org.apache.struts2.interceptor.ServletRequestAware;
import resources.SesionRemove;

/**
 *
 * @author rodrigo
 */
public class SolicitudesController extends Controller<Solicitudes> implements ServletRequestAware {
    
    private List<Sedes> sedesList;
    private int idSelectedSede;
    private Sedes sede = null;
    private HttpServletRequest request;
    private HttpSession sesion;
    
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
    
    public String iniciarSolicitudPrepared(){
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        
        int id = 0;
        UsuariosController user = new UsuariosController();
        user.setEntity((Usuarios) this.sesion.getAttribute("user"));
        
        if(user.getEntity().getAreas().getId() == 1){
            this.entity.setTipo((short)1);
        }else{
            this.entity.setTipo((short)2);
        }
        SolicitudesDAO dao = new SolicitudesDAO();
        dao.iniciaOperacion();
        id = dao.selectMaxNumSol(user.getEntity().getSedes().getId());
        dao.cerrarSession();
        this.sedesList = new ArrayList();
        this.sedesList.add(user.getEntity().getSedes());
        this.entity.setNumeroSolicitud(++id);
        this.entity.setFechaAlta(new Date());
        return SUCCESS;
    }   
    
    public String preparedConFecha(){
        String res = SUCCESS;
        this.entity.setSedes(selectSedes(idSelectedSede));
        if(this.validar())
            if(this.validarCantidadDias())
                sesion.setAttribute("SolicitudesForm", this.entity);
            else
                res = "fecha";
        else
            res = INPUT;
        return res;
    }
    public String prepared(){
//        Locale locale = new Locale("fr");
//        ActionContext.getContext().setLocale(locale);
        String res = SUCCESS;
        this.entity.setSedes(selectSedes(idSelectedSede));
        if(this.validar()){
            sesion.setAttribute("SolicitudesForm", this.entity);
            if(!this.validarCantidadDias())
                res = "fecha";
        }else{
            res = INPUT;
        }
        return res;
    }
    public String updatePrepared(){
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        
        String idSolStr = String.valueOf(this.request.getParameter("idSolicitudSelected"));
        this.sesion.setAttribute("idSolicitudSelected",idSolStr);
        this.selectOne(Integer.parseInt(idSolStr));
        return SUCCESS;
    }
    
    @Override
    public String update(){
        String idSolStr = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
        this.entity.setId(Integer.parseInt(idSolStr));
        this.entity.setSedes(selectSedes(idSelectedSede));
        this.dao.iniciaOperacion();
        String res = super.update();
        this.dao.cerrarSession();
        return res;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        this.sesion = request.getSession();
    }
    
    public boolean validar(){
        boolean b = true;
        boolean validar;
        if(this.entity.getFechaAlta() == null){
            addFieldError("fechaAlta", "Debe ingresar la fecha de alta de la solicitud.");   
            b = false;
        }
        if(this.entity.getNumeroSolicitud() == 0){
            addFieldError("numeroSolicitud", "Debe ingresar el número de solicitud.");
            b = false;
        }else{
            UsuariosController user = new UsuariosController();
            user.setEntity((Usuarios) this.sesion.getAttribute("user"));
            SolicitudesDAO dao = new SolicitudesDAO();
            dao.iniciaOperacion();
            validar = dao.validarNumeroSolicitud(this.entity.getNumeroSolicitud(),user.getEntity().getSedes().getId());
            dao.cerrarSession();
            if(validar){
                addFieldError("numeroSolicitud", "El número de solicitud ya etá registrado.");   
            b = false;
            }
        }
        return b;
    }
    private boolean validarCantidadDias(){
        boolean b = true;
        long milSegundosPorDia = 24 * 60 * 60 * 1000;
        long diferecia = (new Date().getTime() - this.entity.getFechaAlta().getTime() ) / milSegundosPorDia;
        if(diferecia > 30)
            b = false;
        return b;
    }
    
}
