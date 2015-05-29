/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import controller.RegistrosUnicosController;
import controller.SolicitudesController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.entities.RegistrosUnicos;
import model.entities.Solicitudes;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class RegistrosUnicosAction extends ActionSupport implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    
    @Override
    public String execute(){
        String res = SUCCESS;
        
        
        List<RegistrosUnicos> registros = (List<RegistrosUnicos>) sesion.getAttribute("RegistrosConfirmarForm");
        List<RegistrosUnicos> registrosProx = (List<RegistrosUnicos>) sesion.getAttribute("RegistrosNuevosForm");
        RegistrosUnicosController regController;
        int cant = registros.size();
        for(int i = 0; i < cant; i++){
            regController = new RegistrosUnicosController();
            regController.setEntity(registros.get(i));
            regController.getDao().iniciaOperacion();
            if(!regController.getDao().update(registros.get(i)))
                res = ERROR;
            regController.getDao().cerrarSession();
            registrosProx.get(i).setSolicitudes(regController.getEntity().getSolicitudes());
            regController.setEntity(registrosProx.get(i));
            regController.getDao().iniciaOperacion();
            regController.getDao().create(registrosProx.get(i));
            regController.getDao().cerrarSession();
        }
        return res;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = this.request.getSession();
    }
    
}
