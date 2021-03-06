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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.ComprobantesTrasladosDAO;
import model.dao.DAO;
import model.entities.Comprobantes;
import model.entities.ComprobantesComidaAlojamientos;
import model.entities.ComprobantesTraslados;
import model.entities.Docentes;
import model.entities.RegistrosUnicos;
import model.entities.Solicitudes;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.HibernateException;
import reports.ConfirmarSolicitudesReporte;
import reports.ConstanciaDePresentacion;
import resources.SesionRemove;

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
        DAO dao = new DAO();
        try{
            dao.iniciaOperacion();
            int cantidadComprobantes = 0;
            int idSol = 0;
//            SolicitudesController sol = new SolicitudesController();
            Solicitudes solicitud = new Solicitudes();
            solicitud = (Solicitudes)sesion.getAttribute("SolicitudesForm");
//            sol.setEntity(solicitud);
            idSol = Integer.parseInt(String.valueOf(dao.getSesion().save(solicitud)));

    //        solicitud.setId(idSol);
            
//            DocentesController doc = new DocentesController();
            Docentes docente = new Docentes();
            docente = (Docentes)sesion.getAttribute("DocentesForm");
            docente.setSolicitudes(solicitud);
            docente.setNombre(docente.getNombre().toUpperCase());
            docente.setApellido(docente.getApellido().toUpperCase());
//            doc.setEntity(docente);
            dao.getSesion().save(docente);


//            ComprobantesTrasladosController tras = new ComprobantesTrasladosController();
            List<ComprobantesTraslados> listTraslado = (List) sesion.getAttribute("ComprobanteTraslado");
            if(listTraslado != null){
                for(ComprobantesTraslados traslado : listTraslado){

                    cantidadComprobantes++;

//                    ComprobantesController com = new ComprobantesController();
                    Comprobantes comprobantes = new Comprobantes();
                    comprobantes = (Comprobantes)traslado.getComprobantes();
                    comprobantes.setSolicitudes(solicitud);
//                    com.setEntity(comprobantes);
                    dao.getSesion().save(comprobantes);

                    traslado.setComprobantes(comprobantes);
                    dao.getSesion().save(traslado);
                }

            }

//            ComprobantesComidaAlojamientoController aloj = new ComprobantesComidaAlojamientoController();
            List<ComprobantesComidaAlojamientos> listAlojamiento = (List) sesion.getAttribute("ComprobanteAlojamiento");
            if(listAlojamiento != null){
                for(ComprobantesComidaAlojamientos alojamiento : listAlojamiento){

                    cantidadComprobantes++;

//                    ComprobantesController com = new ComprobantesController();
                    Comprobantes comprobantes = new Comprobantes();
                    comprobantes = (Comprobantes)alojamiento.getComprobantes();
                    comprobantes.setSolicitudes(solicitud);
//                    com.setEntity(comprobantes);
                    dao.getSesion().save(comprobantes);

                    alojamiento.setComprobantes(comprobantes);
                    dao.getSesion().save(alojamiento);
                }
            }


//            RegistrosUnicosController reg1 = new RegistrosUnicosController();
            RegistrosUnicos registroUnico1 = new RegistrosUnicos();
            registroUnico1 = (RegistrosUnicos)sesion.getAttribute("RegistroUnicoForm");
            registroUnico1.setSolicitudes(solicitud);
//            reg1.setEntity(registroUnico1);
            dao.getSesion().save(registroUnico1);

//            RegistrosUnicosController reg2 = new RegistrosUnicosController();
            RegistrosUnicos registroUnico2 = new RegistrosUnicos();
            registroUnico2 = (RegistrosUnicos)sesion.getAttribute("RegistroUnicoProximo");
            registroUnico2.setSolicitudes(solicitud);
//            reg2.setEntity(registroUnico2);
            dao.getSesion().save(registroUnico2);
        
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
        int cantidadComprobantes = 0;
        
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
        docente.setNombre(docente.getNombre().toLowerCase());
        docente.setApellido(docente.getApellido().toLowerCase());
        doc.setEntity(docente);
        if(doc.save().equals("error"))
            res = ERROR;
        
        
        ComprobantesTrasladosController tras = new ComprobantesTrasladosController();
        List<ComprobantesTraslados> listTraslado = (List) sesion.getAttribute("ComprobanteTraslado");
        if(listTraslado != null){
            for(ComprobantesTraslados traslado : listTraslado){
                
                cantidadComprobantes++;
                
                ComprobantesController com = new ComprobantesController();
                Comprobantes comprobantes = new Comprobantes();
                comprobantes = (Comprobantes)traslado.getComprobantes();
                comprobantes.setSolicitudes(solicitud);
                com.setEntity(comprobantes);
                if(com.save().equals("error"))
                    res = ERROR;
                
                traslado.setComprobantes(comprobantes);
                tras.setEntity(traslado);
                tras.setDao(new ComprobantesTrasladosDAO());
                tras.getDao().iniciaOperacion();
                if(tras.getDao().create(traslado) == 0)
                    res = ERROR;  
                tras.getDao().cerrarSession();
            }
             
        }
        
        ComprobantesComidaAlojamientoController aloj = new ComprobantesComidaAlojamientoController();
        List<ComprobantesComidaAlojamientos> listAlojamiento = (List) sesion.getAttribute("ComprobanteAlojamiento");
        if(listAlojamiento != null){
            for(ComprobantesComidaAlojamientos alojamiento : listAlojamiento){
                
                cantidadComprobantes++;
                
                ComprobantesController com = new ComprobantesController();
                Comprobantes comprobantes = new Comprobantes();
                comprobantes = (Comprobantes)alojamiento.getComprobantes();
                comprobantes.setSolicitudes(solicitud);
                com.setEntity(comprobantes);
                if(com.save().equals("error"))
                    res = ERROR;
                
                alojamiento.setComprobantes(comprobantes);
                aloj.setEntity(alojamiento);
                aloj.getDao().iniciaOperacion();
                if(aloj.getDao().create(alojamiento) == 0)
                    res = ERROR;    
                aloj.getDao().cerrarSession();
            }
        }
        
        
        RegistrosUnicosController reg1 = new RegistrosUnicosController();
        RegistrosUnicos registroUnico1 = new RegistrosUnicos();
        registroUnico1 = (RegistrosUnicos)sesion.getAttribute("RegistroUnicoForm");
        registroUnico1.setSolicitudes(solicitud);
        reg1.setEntity(registroUnico1);
        reg1.getDao().iniciaOperacion();
        if(reg1.getDao().create(registroUnico1) == 0)
            res = ERROR;
        reg1.getDao().cerrarSession();
        
        try{
            RegistrosUnicosController reg2 = new RegistrosUnicosController();
            RegistrosUnicos registroUnico2 = new RegistrosUnicos();
            registroUnico2 = (RegistrosUnicos)sesion.getAttribute("RegistroUnicoProximo");
            registroUnico2.setSolicitudes(solicitud);
            reg2.setEntity(registroUnico2);
            reg2.getDao().iniciaOperacion();
            if(reg2.getDao().create(registroUnico2) == 0)
                res = ERROR;
            reg2.getDao().cerrarSession();
        }catch(Exception e){
            
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
