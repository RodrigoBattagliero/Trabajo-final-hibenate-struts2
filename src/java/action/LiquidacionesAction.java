/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import controller.LiquidacionesController;
import controller.RegistrosUnicosController;
import controller.SolicitudesController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.entities.Liquidaciones;
import model.entities.RegistrosUnicos;
import model.entities.Solicitudes;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class LiquidacionesAction extends ActionSupport implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    
    @Override
    public String execute(){
        String res = SUCCESS;
        
        int idSol = Integer.parseInt(String.valueOf(sesion.getAttribute("idSolicitudSelected")));
        SolicitudesController solContr = new SolicitudesController();
        solContr.selectOne(idSol);
        Solicitudes solicitud = solContr.getEntity();
        
        LiquidacionesController controller = new LiquidacionesController();
        Liquidaciones liquidacion = new Liquidaciones();
        liquidacion = (Liquidaciones) sesion.getAttribute("LiquidacionesForm");
        liquidacion.setSolicitudes(solicitud);
        controller.setEntity(liquidacion);
        if(controller.save().equals("error"))
            res = ERROR;
        
        RegistrosUnicosController reg1 = new RegistrosUnicosController();
        RegistrosUnicos registroUnico1 = new RegistrosUnicos();
        registroUnico1 = (RegistrosUnicos)sesion.getAttribute("RegistroUnicoForm");
        registroUnico1.setSolicitudes(solicitud);
        reg1.setEntity(registroUnico1);
        reg1.getDao().iniciaOperacion();
        if(!reg1.getDao().update(registroUnico1))
            res = ERROR;
        reg1.getDao().cerrarSession();
        
        return res;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = this.request.getSession();
    }
    
}
