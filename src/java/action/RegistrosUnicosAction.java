/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import controller.ReSolConController;
import controller.ReSolConDetallesController;
import controller.RegistrosUnicosController;
import controller.SolicitudesController;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.entities.ReSolCon;
import model.entities.ReSolConDetalles;
import model.entities.RegistrosUnicos;
import model.entities.Usuarios;
import org.apache.struts2.interceptor.ServletRequestAware;
import reports.ConfirmarSolicitudesReporte;
import resources.DateManager;
import resources.SesionRemove;

/**
 *
 * @author rodrigo
 */
public class RegistrosUnicosAction extends ActionSupport implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    
    @Override
    public String execute() throws Exception{
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
            try{
                registrosProx.get(i).setSolicitudes(regController.getEntity().getSolicitudes());
                regController.setEntity(registrosProx.get(i));
            
                regController.getDao().iniciaOperacion();
                regController.getDao().create(registrosProx.get(i));
                regController.getDao().cerrarSession();
            }catch(IndexOutOfBoundsException e){
                
            }
        }
        
        // Reportes
        ReSolCon reporte = new ReSolCon();
        reporte.setFecha(new Date());
        reporte.setUsuarios((Usuarios) sesion.getAttribute("user"));
        ReSolConController reportesController = new ReSolConController();
        int idReporte;
        reportesController.getDao().iniciaOperacion();
        idReporte = reportesController.getDao().create(reporte);
        reportesController.getDao().cerrarSession();
        reportesController.selectOne(idReporte);
        // Reportes detalles
        if(idReporte > 0){
            for(RegistrosUnicos re : registros){
                ReSolConDetalles repo = new ReSolConDetalles();
                repo.setReSolCon((ReSolCon) reportesController.getEntity());
                repo.setRegistrosUnicos(re);
                ReSolConDetallesController repoController = new ReSolConDetallesController();
                repoController.getDao().iniciaOperacion();
                repoController.getDao().create(repo);
                repoController.getDao().cerrarSession();
            }
        }
            
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        
        return res;
    }
    
    public String guardarRegistroUnico(){
        String res = ERROR;
        SolicitudesController solCon = new SolicitudesController();
        String idSolStr = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
        solCon.selectOne(Integer.parseInt(idSolStr));
        
        RegistrosUnicosController regController = new RegistrosUnicosController();
        RegistrosUnicos registros = (RegistrosUnicos) sesion.getAttribute("RegistroUnicoForm");
        registros.setSolicitudes(solCon.getEntity());
        
        regController.getDao().iniciaOperacion();
        if(regController.getDao().create(registros) > 0)
            res = SUCCESS;
        regController.getDao().cerrarSession();
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        
        return res;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = this.request.getSession();
    }
    
}
