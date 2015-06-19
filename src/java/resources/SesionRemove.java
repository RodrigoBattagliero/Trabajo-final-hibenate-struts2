/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class SesionRemove {
    
//    private HttpServletRequest request;
//    private HttpSession sesion;

    public SesionRemove() {
    }
    
    public void removeAllSession(HttpSession sesion){
        // Administrativo
        sesion.removeAttribute("SolicitudesForm");
        sesion.removeAttribute("DocentesForm");
        sesion.removeAttribute("ComprobanteTraslado");
        sesion.removeAttribute("ComprobanteAlojamiento");
        sesion.removeAttribute("RegistroUnicoForm");
        sesion.removeAttribute("RegistroUnicoProximo");
        sesion.removeAttribute("idSolicitudSelected");
        sesion.removeAttribute("DesignacionesForm");
        sesion.removeAttribute("RegistrosAConfirmar");
        sesion.removeAttribute("RegistrosConfirmarForm");
        sesion.removeAttribute("RegistrosNuevosForm");
        sesion.removeAttribute("RegistrosConfirmarPrepared");
        sesion.removeAttribute("Solicitudes");
        sesion.removeAttribute("Docentes");
        sesion.removeAttribute("Traslados");
        sesion.removeAttribute("Alojamientos");
        sesion.removeAttribute("Sedes");
        sesion.removeAttribute("Designaciones");
        sesion.removeAttribute("Liquidaciones");
        sesion.removeAttribute("RegistroUnico");
        sesion.removeAttribute("idDocente");
        sesion.removeAttribute("idActividadDocente");
        sesion.removeAttribute("ExpedienteForm");
        sesion.removeAttribute("ExpedientesSolicitudesForm");
        sesion.removeAttribute("RegistrosUnicosForm");
        sesion.removeAttribute("idDesignacionSelected");
        sesion.removeAttribute("ActividadDocenteForm");
        // Bedel
        sesion.removeAttribute("idRegistroUnico");
        
        // Rendicion de cuentas
        sesion.removeAttribute("LiquidacionesForm");
        
        // SAF
        
    }

//    @Override
//    public void setServletRequest(HttpServletRequest hsr) {
//        this.request = hsr;
//        this.sesion = this.request.getSession();
//    }
    
}
