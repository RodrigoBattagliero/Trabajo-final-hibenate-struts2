/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionSupport;
import controller.ExpedientesController;
import controller.ExpedientesSolicitudesController;
import controller.RegistrosUnicosController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.DAO;
import model.entities.Expedientes;
import model.entities.ExpedientesSolicitudes;
import model.entities.RegistrosUnicos;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.HibernateException;
import resources.SesionRemove;

/**
 *
 * @author rodrigo
 */
public class ExpedientesAction extends ActionSupport implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    
    @Override
    public String execute(){
        String res = SUCCESS;
        DAO dao = new DAO();
        try{
            dao.iniciaOperacion();
            int idExpediente = 0;
            // Expediente
            Expedientes expediente = (Expedientes)this.sesion.getAttribute("ExpedienteForm");
            idExpediente = Integer.parseInt(String.valueOf(dao.getSesion().save(expediente)));
//            ExpedientesController expController = new ExpedientesController();
//            expController.setEntity((Expedientes)this.sesion.getAttribute("ExpedienteForm"));
//            expController.getDao().iniciaOperacion();
//            if(expController.getDao().create(expController.getEntity()) == 0)
//                res = ERROR;
//            expController.getDao().cerrarSession();
//            expController.getDao().iniciaOperacion();
//            expController.getDao().selectOne(expController.getId());
//            expController.getDao().cerrarSession();

            // Expedientes - solicitudes
//            ExpedientesSolicitudesController expSolController;
            List<ExpedientesSolicitudes> listExpSol = (List) this.sesion.getAttribute("ExpedientesSolicitudesForm");
            for(ExpedientesSolicitudes expSol : listExpSol){
                expSol.setExpedientes(expediente);
                dao.getSesion().save(expSol);
//                expSolController = new ExpedientesSolicitudesController();
//                expSolController.setEntity(expSol);
//                expSolController.getDao().iniciaOperacion();
//                if(expSolController.getDao().create(expSol) == 0)
//                    res = ERROR;
            }

            // Registro Unico
            RegistrosUnicosController regUniController;
            List<RegistrosUnicos> listRegUni = (List) this.sesion.getAttribute("RegistrosUnicosForm");
            for(RegistrosUnicos reg : listRegUni){
                dao.getSesion().save(reg);
//                regUniController = new RegistrosUnicosController();
//                regUniController.setEntity(reg);
//                regUniController.getDao().iniciaOperacion();
//                if(regUniController.getDao().create(reg) == 0)
//                    res = ERROR;
//                regUniController.getDao().cerrarSession();
            }
            
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
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = this.request.getSession();
    }
}
