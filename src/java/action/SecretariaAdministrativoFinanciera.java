/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import controller.RegistrosUnicosController;
import controller.SolicitudesController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.DAO;
import model.entities.RegistrosUnicos;
import model.entities.Solicitudes;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.HibernateException;
import resources.SesionRemove;

/**
 *
 * @author rodrigo
 */
public class SecretariaAdministrativoFinanciera extends ActionSupport implements ServletRequestAware {
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

//            RegistrosUnicosController reg1 = new RegistrosUnicosController();
            RegistrosUnicos registroUnico1 = new RegistrosUnicos();
            registroUnico1 = (RegistrosUnicos)sesion.getAttribute("RegistroUnicoForm");
            registroUnico1.setSolicitudes(solicitud);
            dao.getSesion().update(registroUnico1);
//            reg1.setEntity(registroUnico1);
//            reg1.getDao().iniciaOperacion();
//            if(!reg1.getDao().update(registroUnico1))
//                res = ERROR;
//            reg1.getDao().cerrarSession();
            dao.commit();
        }catch(HibernateException he){
            dao.abordar();
            addActionError("Error al iniciar solicitud:");
            addActionError("Detalle: "+he);
            res = ERROR;
        }catch(Exception e){
            addActionError("Error:q ");
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
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = this.request.getSession();
    }
}
