/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.ExpedientesDAO;
import model.entities.Expedientes;
import model.entities.ExpedientesSolicitudes;
import model.entities.RegistrosUnicos;
import model.entities.Usuarios;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class ExpedientesController extends Controller<Expedientes> implements ServletRequestAware, ParameterAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    private Map<String, String[]> parametros;
    private Date fecha;
    private String numeroExpediente;
    private ExpedientesDAO dao;
    
    public ExpedientesController() {
        this.dao = (ExpedientesDAO) new ExpedientesDAO();
    }
    
    @Override
    public ExpedientesDAO getDao() {
        return (ExpedientesDAO) dao;
    }

    public void setDao(ExpedientesDAO dao) {
        this.dao = dao;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }
    
    public String prepared(){
        String res = SUCCESS;
        // List de ExpedientesSolicitudes
        List<ExpedientesSolicitudes> listExpSol = new ArrayList();
        // Expediente
        // Rgistro unico para cada solicitud        
        List<RegistrosUnicos> listRegistros = new ArrayList();
        RegistrosUnicos reg;
        // Solicitud
        SolicitudesController solController;
        RegistrosUnicosController regContoller = new RegistrosUnicosController();
        // Areas
        AreasController areaController = new AreasController();
        areaController.selectOne(7);
        // Estados
        EstadosController estController = new EstadosController();
        estController.selectOne(1);
        // Expediente
        Expedientes expe = null;
        int cant = parametros.size();
        if(cant > 0){
            expe = new Expedientes(0, numeroExpediente, fecha, null);
        }
        for (String key : parametros.keySet()) {
            String value[] = parametros.get(key);
            if(value[0].equals("true")){
                solController = new SolicitudesController();
                solController.selectOne(Integer.parseInt(key));
                listExpSol.add(new ExpedientesSolicitudes(0, null, solController.getEntity()));
                reg = new RegistrosUnicos(0, areaController.getEntity(), estController.getEntity(), solController.getEntity(), this.fecha, null , false,"");
                listRegistros.add(reg);
            }
        }
        if(this.validar()){
            this.sesion.setAttribute("ExpedienteForm", expe);
            this.sesion.setAttribute("ExpedientesSolicitudesForm", listExpSol);
            this.sesion.setAttribute("RegistrosUnicosForm", listRegistros);
        }else{
            res = INPUT;
        }    
        return res;
    }
    
    public String listarExpedientes(){
       String res = SUCCESS;
       this.dao.iniciaOperacion();
       this.entities = this.dao.selectExpedientes();
       this.dao.cerrarSession();
       return res;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        this.sesion = request.getSession();
    }
    
    @Override
    public void setParameters(Map<String, String[]> maps) {
        this.parametros = maps;
    }
    
    public boolean validar(){
        boolean b = false;
        if(this.entity.getFecha() == null){
            addFieldError("fecha", "ERROR: Debe ingresar la fecha de expediente");
            b = false;
        }
        if(this.entity.getNumeroExpediente().equals("")){
            addFieldError("numeroExpediente", "ERROR: Debe ingresar el número de expediente");
            b = false;
        }
        return b;
    }
}
