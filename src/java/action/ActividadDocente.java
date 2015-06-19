/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import controller.ActividadDocentesController;
import controller.RegistrosUnicosController;
import controller.SolicitudesController;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.entities.ActividadDocentes;
import model.entities.RegistrosUnicos;
import model.entities.Solicitudes;
import org.apache.struts2.interceptor.ServletRequestAware;
import resources.SesionRemove;

/**
 *
 * @author rodrigo
 */
public class ActividadDocente extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest request;
    private HttpSession sesion; 
   
    @Override
    public String execute(){
        String res = SUCCESS;
        
        int idSol = Integer.parseInt(String.valueOf(sesion.getAttribute("idSolicitudSelected")));
        SolicitudesController solContr = new SolicitudesController();
        solContr.selectOne(idSol);
        Solicitudes solicitud = solContr.getEntity();
        
        List<ActividadDocentes> actList = (List<ActividadDocentes>) sesion.getAttribute("ActividadDocenteForm");
        if(actList == null)
            actList = new ArrayList();
        for(ActividadDocentes actDoc : actList){
            ActividadDocentesController actController = new ActividadDocentesController();
            actController.setEntity(actDoc);
            if(actController.save().equals("error"))
                res = ERROR;
        }
        
        RegistrosUnicosController reg1 = new RegistrosUnicosController();
        RegistrosUnicos registroUnico1 = new RegistrosUnicos();
        registroUnico1 = (RegistrosUnicos)sesion.getAttribute("RegistroUnicoForm");
        registroUnico1.setSolicitudes(solicitud);
        reg1.setEntity(registroUnico1);
        reg1.getDao().iniciaOperacion();
        if(!reg1.getDao().update(registroUnico1))
            res = ERROR;
        reg1.getDao().cerrarSession();
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        
        return res;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        this.sesion = this.request.getSession();
    }
    
}
