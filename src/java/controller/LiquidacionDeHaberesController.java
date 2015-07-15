/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.ExpedientesDAO;
import model.entities.Docentes;
import model.entities.Expedientes;
import model.entities.ExpedientesSolicitudes;
import model.entities.Liquidaciones;
import model.entities.RegistrosUnicos;
import model.entities.Solicitudes;
import model.entities.Usuarios;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class LiquidacionDeHaberesController extends ActionSupport implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    private ExpedientesDAO expDAO;
    private List<ExpedientesSolicitudes> solicitudes;
    private RegistrosUnicosController regUniCon;
    private AreasController areaCon;
    private EstadosController estadoCon;
    private Usuarios user;
    private ExpedientesSolicitudesController expSolCon;
    private List<Expedientes> expedientes;
    private Expedientes expediente;
    private List<Object> detalle;


    public List<Expedientes> getExpedientes() {
        return expedientes;
    }

    public List<Object> getDetalle() {
        return detalle;
    }

    public void setExpedientes(List<Expedientes> expedientes) {
        this.expedientes = (List<Expedientes>) expedientes;
    }

    public Expedientes getExpediente() {
        return expediente;
    }

    public void setExpediente(Expedientes expediente) {
        this.expediente = expediente;
    }

    public List<ExpedientesSolicitudes> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<ExpedientesSolicitudes> solicitudes) {
        this.solicitudes = solicitudes;
    }
    
    
    public String setExpedientes(){
        String res = SUCCESS;
        this.expedientes = new ArrayList();
        Usuarios user = (Usuarios) this.sesion.getAttribute("user");
        EstadosController estado = new EstadosController();
        estado.selectOne(1);
        expDAO = new ExpedientesDAO();
        expDAO.iniciaOperacion();
        this.expedientes = (List<Expedientes>) expDAO.selectRelated(user.getAreas(),estado.getEntity());
        expDAO.cerrarSession();
        return res;
    }
    
    public String setExpedientesPendientes(){
        String res = SUCCESS;
        this.expedientes = new ArrayList();
        Usuarios user = (Usuarios) this.sesion.getAttribute("user");
        EstadosController estado = new EstadosController();
        estado.selectOne(6);
        expDAO = new ExpedientesDAO();
        expDAO.iniciaOperacion();
        this.expedientes = (List<Expedientes>) expDAO.selectRelated(user.getAreas(),estado.getEntity());
        expDAO.cerrarSession();
        return res;
    }
    
    public String setExpediente(){
        String res = SUCCESS;
        this.detalle = new ArrayList();
        int idExpediente;
        try{
            idExpediente = Integer.parseInt(String.valueOf(this.request.getParameter("idExpedienteSelected")));
            this.sesion.setAttribute("idExpedienteSelected", idExpediente);
        }catch(Exception e){
            idExpediente = Integer.parseInt(String.valueOf(this.sesion.getAttribute("idExpedienteSelected")));
        }
        this.expSolCon = new ExpedientesSolicitudesController();
        this.expSolCon.getDao().iniciaOperacion();
        this.solicitudes =   this.expSolCon.getDao().selectFromExpediente(idExpediente);
        this.expSolCon.getDao().cerrarSession();
        for(ExpedientesSolicitudes ru : this.solicitudes){
            Object a[][] = new Object[1][3];
            a[0][0] = ru.getSolicitudes();
            Iterator it = ru.getSolicitudes().getDocenteses().iterator();
            Iterator it2 = ru.getSolicitudes().getLiquidacioneses().iterator();
            Docentes d = new Docentes();
            while(it.hasNext()){
                d = (Docentes) it.next();
            }
            Liquidaciones liq = new Liquidaciones();
            while(it2.hasNext()){
                liq = (Liquidaciones) it2.next();
            }
            a[0][1] = d;
            a[0][2] = liq;
            this.detalle.add(a[0]);
        }
        return res;
    }
    
    public String confirmarTodos(){
        String res = ERROR;
        user = (Usuarios) this.sesion.getAttribute("user");
        this.setExpediente();
            
        if(this.expedienteUpdate()){
            if(this.registroUnicoUpdate())
                res = SUCCESS;
        }
        
       return res;
    }
    
    public String confirmarTodosPendientes(){
        String res = ERROR;
        user = (Usuarios) this.sesion.getAttribute("user");
        this.setExpediente();
            
        if(this.expedienteUpdatePendientes()){
            if(this.registroUnicoUpdatePendientes())
                res = SUCCESS;
        }
        
       return res;
    }
    
    
    public boolean expedienteUpdate(){
        boolean b;
        int idExpediente = Integer.parseInt(String.valueOf(this.sesion.getAttribute("idExpedienteSelected")));
        expDAO = new ExpedientesDAO();
        expDAO.iniciaOperacion();
        this.expediente = (Expedientes) expDAO.selectOne(idExpediente);
        expDAO.cerrarSession();
        
//        this.areaCon = new AreasController();
//        this.areaCon.getDao().iniciaOperacion();
//        this.expediente.setAreas(this.areaCon.getDao().proxima(user.getAreas()));
//        this.areaCon.getDao().cerrarSession();
        
        this.estadoCon = new EstadosController();
        this.estadoCon.selectOne(6);
        this.expediente.setEstados(this.estadoCon.getEntity());
        
        this.expDAO.iniciaOperacion();
        b = this.expDAO.update(this.expediente);
        this.expDAO.cerrarSession();
        
        return b;
    }
    
    public boolean expedienteUpdatePendientes(){
        boolean b;
        int idExpediente = Integer.parseInt(String.valueOf(this.sesion.getAttribute("idExpedienteSelected")));
        expDAO = new ExpedientesDAO();
        expDAO.iniciaOperacion();
        this.expediente = (Expedientes) expDAO.selectOne(idExpediente);
        expDAO.cerrarSession();
        
//        this.areaCon = new AreasController();
//        this.areaCon.getDao().iniciaOperacion();
//        this.expediente.setAreas(this.areaCon.getDao().proxima(user.getAreas()));
//        this.areaCon.getDao().cerrarSession();
        
        this.estadoCon = new EstadosController();
        this.estadoCon.selectOne(7);
        this.expediente.setEstados(this.estadoCon.getEntity());
        
        this.expDAO.iniciaOperacion();
        b = this.expDAO.update(this.expediente);
        this.expDAO.cerrarSession();
        
        return b;
    }
    
    public boolean registroUnicoUpdate(){
        boolean b = true;
        int idExpediente = Integer.parseInt(String.valueOf(this.sesion.getAttribute("idExpedienteSelected")));
        List<RegistrosUnicos> listRegistrosUpdate = new ArrayList();
        List<RegistrosUnicos> listRegistrosNuevos = new ArrayList();

        EstadosController estado2Con = new EstadosController();
        estado2Con.selectOne(6);
        
        this.estadoCon = new EstadosController();
        this.estadoCon.selectOne(1);
        
        this.areaCon = new AreasController();
        this.areaCon.getDao().iniciaOperacion();
        this.areaCon.setEntity(this.areaCon.getDao().proxima(user.getAreas()));
        this.areaCon.getDao().cerrarSession();
        
        this.expSolCon = new ExpedientesSolicitudesController();
        this.expSolCon.getDao().iniciaOperacion();
        this.solicitudes =   this.expSolCon.getDao().selectFromExpediente(idExpediente);
        this.expSolCon.getDao().cerrarSession();
        for(ExpedientesSolicitudes ru : this.solicitudes){
            Solicitudes sol = ru.getSolicitudes();
            this.regUniCon = new RegistrosUnicosController();
            this.regUniCon.selectFromParametros(sol, this.user.getAreas(), this.estadoCon.getEntity());
            for(RegistrosUnicos regUni : this.regUniCon.getEntities()){
                regUni.setFechaSalida(new Date());
                regUni.setConfirmado(true);
                regUni.setEstados(estado2Con.getEntity());
                regUni.setAreas(user.getAreas());

                listRegistrosUpdate.add(regUni);
            }
        }
        this.sesion.setAttribute("RegistrosConfirmarForm", listRegistrosUpdate);
        this.sesion.setAttribute("RegistrosNuevosForm", listRegistrosNuevos);
        return b;
    }
    
    public boolean registroUnicoUpdatePendientes(){
        boolean b = true;
        int idExpediente = Integer.parseInt(String.valueOf(this.sesion.getAttribute("idExpedienteSelected")));
        List<RegistrosUnicos> listRegistrosUpdate = new ArrayList();
        List<RegistrosUnicos> listRegistrosNuevos = new ArrayList();

        EstadosController estado2Con = new EstadosController();
        estado2Con.selectOne(7);
        
        this.estadoCon = new EstadosController();
        this.estadoCon.selectOne(6);
        
        this.areaCon = new AreasController();
        this.areaCon.getDao().iniciaOperacion();
        this.areaCon.setEntity(this.areaCon.getDao().proxima(user.getAreas()));
        this.areaCon.getDao().cerrarSession();
        
        this.expSolCon = new ExpedientesSolicitudesController();
        this.expSolCon.getDao().iniciaOperacion();
        this.solicitudes =   this.expSolCon.getDao().selectFromExpediente(idExpediente);
        this.expSolCon.getDao().cerrarSession();
        
        for(ExpedientesSolicitudes ru : this.solicitudes){
            Solicitudes sol = ru.getSolicitudes();
            this.regUniCon = new RegistrosUnicosController();
            this.regUniCon.selectFromParametros(sol, this.user.getAreas(), this.estadoCon.getEntity());
            this.regUniCon = new RegistrosUnicosController();
            this.regUniCon.selectFromParametros(this.user.getAreas(), this.estadoCon.getEntity());
            for(RegistrosUnicos regUni : this.regUniCon.getEntities()){
                regUni.setFechaSalida(new Date());
                regUni.setConfirmado(true);
                regUni.setEstados(estado2Con.getEntity());
                regUni.setAreas(user.getAreas());

                listRegistrosUpdate.add(regUni);
            }
        }
        this.sesion.setAttribute("RegistrosConfirmarForm", listRegistrosUpdate);
        this.sesion.setAttribute("RegistrosNuevosForm", listRegistrosNuevos);
        return b;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        this.sesion = request.getSession();
    }
}
