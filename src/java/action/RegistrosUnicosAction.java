/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionSupport;
import controller.RegistrosUnicosController;
import controller.SolicitudesController;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.DAO;
import model.entities.ActividadDocentes;
import model.entities.Designaciones;
import model.entities.Liquidaciones;
import model.entities.ReSolCon;
import model.entities.ReSolConDetalles;
import model.entities.RegistrosUnicos;
import model.entities.Solicitudes;
import model.entities.Usuarios;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.HibernateException;
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
        DAO dao = new DAO();
        try{
            dao.iniciaOperacion();
            
            List<RegistrosUnicos> registros = (List<RegistrosUnicos>) sesion.getAttribute("RegistrosConfirmarForm");
            List<RegistrosUnicos> registrosProx = (List<RegistrosUnicos>) sesion.getAttribute("RegistrosNuevosForm");
            int cant = registros.size();
            for(int i = 0; i < cant; i++){
                dao.getSesion().update(registros.get(i));
            }
            cant = registrosProx.size();
            for(int i = 0; i < cant; i++){
                registrosProx.get(i).setSolicitudes(registros.get(i).getSolicitudes());
                dao.getSesion().save(registrosProx.get(i));
            }

            // Reportes
            ReSolCon reporte = new ReSolCon();
            reporte.setFecha(new Date());
            reporte.setUsuarios((Usuarios) sesion.getAttribute("user"));
            int idReporte;
            idReporte = Integer.parseInt(String.valueOf(dao.getSesion().save(reporte)));
            // Reportes detalles
            if(idReporte > 0){
                for(RegistrosUnicos re : registros){
                    ReSolConDetalles repo = new ReSolConDetalles();
                    repo.setReSolCon(reporte);
                    repo.setRegistrosUnicos(re);
                    dao.getSesion().save(repo);
                }
            }
            dao.commit();
        }catch(HibernateException he){
            dao.abordar();
            addActionError("Error al iniciar solicitud:");
            addActionError("Detalle: "+he);
            res = ERROR;
        }catch(Exception e){
            dao.abordar();
            addActionError("Error1: ");
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
    
    public String update(){
        String res = SUCCESS;
        Usuarios user = (Usuarios) this.sesion.getAttribute("user");
        DAO dao = new DAO();
        try{
            dao.iniciaOperacion();
            switch(user.getAreas().getId()){
                case 1: // OGAGTD
                    designacionesDelete(dao);
                    designacionesSave(dao);
                    liquidacionesDelete(dao);
                    break;
                case 2: // Docente
                    break;
                case 3: // Sede interior
                    designacionesDelete(dao);
                    designacionesSave(dao);
                    liquidacionesDelete(dao);
                    break;
                case 4: // Bedelia
                    actividadDocenteDelete(dao);
                    actividadDocenteSave(dao);
                    liquidacionesDelete(dao);
                    break;
                case 5: // Rendicion
                    liquidacionesDelete(dao);
                    liquidacionesSave(dao);
                    break;
                case 6: // SAF
                    break;
                default:
                    break;
            }
            RegistrosUnicos re = (RegistrosUnicos) sesion.getAttribute("registroUnico");
            dao.getSesion().update(re);
            dao.commit();
        }catch(HibernateException he){
            dao.abordar();
            addActionError("Error al iniciar solicitud:");
            addActionError("Detalle: "+he);
            res = ERROR;
        }finally{
            dao.cerrarSession();
        }
        return res;
    }
    
    private void designacionesDelete(DAO dao) throws HibernateException{
        try{
            int idSol = Integer.parseInt(String.valueOf(sesion.getAttribute("idSolicitudSelected")));
            String sql = "FROM Designaciones d WHERE d.solicitudes = "+idSol;
            List<Designaciones> designaciones = dao.getSesion().createQuery(sql).list();
            int cant = designaciones.size();
            for (int i = 0; i < cant; i++) {
                dao.getSesion().delete(designaciones.get(i));
            }
        }catch(NullPointerException | IndexOutOfBoundsException e ){
            
        }

    }
    
    private void designacionesSave(DAO dao){
        try{
            int idSol = Integer.parseInt(String.valueOf(sesion.getAttribute("idSolicitudSelected")));
            Solicitudes sol = (Solicitudes) dao.getSesion().get(Solicitudes.class, idSol);
            List<Designaciones> desig = (List) sesion.getAttribute("DesignacionesForm");
            int cant = desig.size();
            for (int i = 0; i < cant; i++) {
                desig.get(i).setSolicitudes(sol);
                dao.getSesion().save(desig.get(i));
            }
        }catch(Exception e){
            
        }
    }
    
    private void actividadDocenteDelete(DAO dao) throws HibernateException{
        try{
            int idSol = Integer.parseInt(String.valueOf(sesion.getAttribute("idSolicitudSelected")));
            String sql = "FROM Designaciones d WHERE d.solicitudes = "+idSol;
            List<Designaciones> designaciones = dao.getSesion().createQuery(sql).list();
            for(Designaciones de : designaciones ){
                sql = "FROM ActividadDocentes t WHERE t.designaciones = "+de.getId();
                List<ActividadDocentes> aDoc = dao.getSesion().createQuery(sql).list();
                int cant = aDoc.size();
                for (int i = 0; i < cant; i++) {
                    dao.getSesion().delete(aDoc.get(i));
                }
            }
        }catch(NullPointerException | IndexOutOfBoundsException e ){
            
        }
    }
    
    private void actividadDocenteSave(DAO dao) throws HibernateException{
        List<ActividadDocentes> act = (List) sesion.getAttribute("ActividadDocenteForm");
        int cant = act.size();
        for(int i = 0; i < cant; i++){
            dao.getSesion().save(act.get(i));
        }
            
    }
    
    private void liquidacionesDelete(DAO dao) throws HibernateException{
        try{
            int idSol = Integer.parseInt(String.valueOf(sesion.getAttribute("idSolicitudSelected")));
            String sql = "FROM Liquidaciones d WHERE d.solicitudes = "+idSol;
            List<Liquidaciones> liqui = dao.getSesion().createQuery(sql).list();
            int cant = liqui.size();
            for (int i = 0; i < cant; i++) {
                dao.getSesion().delete(liqui.get(i));
            }
        }catch(NullPointerException | IndexOutOfBoundsException e ){
            
        }
    }
    
    private void liquidacionesSave(DAO dao) throws HibernateException{
        Liquidaciones li = (Liquidaciones) sesion.getAttribute("LiquidacionesForm");
        dao.getSesion().save(li);
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = this.request.getSession();
    }
    
}
