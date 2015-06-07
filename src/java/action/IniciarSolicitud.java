/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import controller.ComprobantesComidaAlojamientoController;
import controller.ComprobantesController;
import controller.ComprobantesTrasladosController;
import controller.DocentesController;
import controller.RegistrosUnicosController;
import controller.SolicitudesController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.entities.Comprobantes;
import model.entities.ComprobantesComidaAlojamientos;
import model.entities.ComprobantesTraslados;
import model.entities.Docentes;
import model.entities.RegistrosUnicos;
import model.entities.Solicitudes;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class IniciarSolicitud extends ActionSupport implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    
    public IniciarSolicitud() {
    }
    
    @Override
    public String execute(){
        
        String res = SUCCESS;
        
        SolicitudesController sol = new SolicitudesController();
        Solicitudes solicitud = new Solicitudes();
        solicitud = (Solicitudes)sesion.getAttribute("SolicitudesForm");
        sol.setEntity(solicitud);
        if(sol.save().equals("error"))
            res = ERROR;
        
        sol.selectOne(sol.getId());
        solicitud = sol.getEntity();
        
        DocentesController doc = new DocentesController();
        Docentes docente = new Docentes();
        docente = (Docentes)sesion.getAttribute("DocentesForm");
        docente.setSolicitudes(solicitud);
        doc.setEntity(docente);
        if(doc.save().equals("error"))
            res = ERROR;
        
        
        ComprobantesTrasladosController tras = new ComprobantesTrasladosController();
        List<ComprobantesTraslados> listTraslado = (List) sesion.getAttribute("ComprobanteTraslado");
        if(listTraslado != null){
            for(ComprobantesTraslados traslado : listTraslado){
                ComprobantesController com = new ComprobantesController();
                Comprobantes comprobantes = new Comprobantes();
                comprobantes = (Comprobantes)traslado.getComprobantes();
                comprobantes.setSolicitudes(solicitud);
                com.setEntity(comprobantes);
                if(com.save().equals("error"))
                    res = ERROR;
                
                traslado.setComprobantes(comprobantes);
                tras.setEntity(traslado);
                if(tras.save().equals("error"))
                    res = ERROR;  
            }
             
        }
        
        ComprobantesComidaAlojamientoController aloj = new ComprobantesComidaAlojamientoController();
        List<ComprobantesComidaAlojamientos> listAlojamiento = (List) sesion.getAttribute("ComprobanteAlojamiento");
        if(listAlojamiento != null){
            for(ComprobantesComidaAlojamientos alojamiento : listAlojamiento){
                
                ComprobantesController com = new ComprobantesController();
                Comprobantes comprobantes = new Comprobantes();
                comprobantes = (Comprobantes)alojamiento.getComprobantes();
                comprobantes.setSolicitudes(solicitud);
                com.setEntity(comprobantes);
                if(com.save().equals("error"))
                    res = ERROR;
                
                alojamiento.setComprobantes(comprobantes);
                aloj.setEntity(alojamiento);
                if(aloj.save().equals("error"))
                    res = ERROR;    
            }
        }
        
        
        RegistrosUnicosController reg1 = new RegistrosUnicosController();
        RegistrosUnicos registroUnico1 = new RegistrosUnicos();
        registroUnico1 = (RegistrosUnicos)sesion.getAttribute("RegistroUnicoForm");
        registroUnico1.setSolicitudes(solicitud);
        reg1.setEntity(registroUnico1);
        reg1.getDao().iniciaOperacion();
        if(reg1.getDao().create(registroUnico1) > 0)
            res = ERROR;
        reg1.getDao().cerrarSession();
        
        RegistrosUnicosController reg2 = new RegistrosUnicosController();
        RegistrosUnicos registroUnico2 = new RegistrosUnicos();
        registroUnico2 = (RegistrosUnicos)sesion.getAttribute("RegistroUnicoProximo");
        registroUnico2.setSolicitudes(solicitud);
        reg2.setEntity(registroUnico2);
        reg2.getDao().iniciaOperacion();
        if(reg2.getDao().create(registroUnico2) > 0)
            res = ERROR;
        reg2.getDao().cerrarSession();
        
        return res;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = this.request.getSession();
    }
    
    
}
