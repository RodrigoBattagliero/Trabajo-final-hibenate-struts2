/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionSupport;
import controller.DesignacionesController;
import controller.RegistrosUnicosController;
import controller.SolicitudesController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.DAO;
import model.entities.Designaciones;
import model.entities.RegistrosUnicos;
import model.entities.Solicitudes;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.HibernateException;
import resources.SesionRemove;

/**
 *
 * @author rodrigo
 */
public class Designacion extends ActionSupport implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    
    @Override
    public String execute(){
        String res = SUCCESS;
        DAO dao = new DAO();
        try{
            dao.iniciaOperacion();
            int idSol = Integer.parseInt(String.valueOf(sesion.getAttribute("idSolicitudSelected")));
            SolicitudesController solContr = new SolicitudesController();
            solContr.selectOne(idSol);
            Solicitudes solicitud = solContr.getEntity();

            List<Designaciones> designaciones = (List<Designaciones>) sesion.getAttribute("DesignacionesForm");
            DesignacionesController degContr;
            for(Designaciones desig : designaciones){
                desig.setSolicitudes(solicitud);
                dao.getSesion().save(desig);
            }
            RegistrosUnicos registroUnico1 = new RegistrosUnicos();
            registroUnico1 = (RegistrosUnicos)sesion.getAttribute("RegistroUnicoForm");
            registroUnico1.setSolicitudes(solicitud);
            dao.getSesion().update(registroUnico1);
            
            dao.commit();
            
        }catch(HibernateException he){
            dao.abordar();
            addActionError("Error al iniciar solicitud:");
            addActionError("Detalle: "+he);
            res = ERROR;
        }catch(Exception e){
            addActionError("Error: ");
            addActionError("Detalle: "+e);
            res = ERROR;
        }finally{
            dao.cerrarSession();
        }
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        
        return res;
    }
    
    public String executeAux(){
        String res = SUCCESS;
        
        int idSol = Integer.parseInt(String.valueOf(sesion.getAttribute("idSolicitudSelected")));
        SolicitudesController solContr = new SolicitudesController();
        solContr.selectOne(idSol);
        Solicitudes solicitud = solContr.getEntity();
        
        List<Designaciones> designaciones = (List<Designaciones>) sesion.getAttribute("DesignacionesForm");
        DesignacionesController degContr;
        for(Designaciones desig : designaciones){
            degContr = new DesignacionesController();
            desig.setSolicitudes(solicitud);
            degContr.setEntity(desig);
            if(degContr.save().equals("error"))
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
        sesion = this.request.getSession();
    }
    
}
