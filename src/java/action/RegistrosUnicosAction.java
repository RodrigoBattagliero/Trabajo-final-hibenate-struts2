/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import controller.RegistrosUnicosController;
import controller.SolicitudesController;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import static javassist.bytecode.InstructionPrinter.print;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.entities.RegistrosUnicos;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import reports.ConfirmarSolicitudesReporte;
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
        
            
            ConfirmarSolicitudesReporte reporteDatos = new ConfirmarSolicitudesReporte();
            reporteDatos.setListRegistros(registros);
            
                String ruta = ServletActionContext.getServletContext().getRealPath("/solicitudes_confirmadas.jasper");
                String ruta2 = ServletActionContext.getServletContext().getRealPath("/");
                JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,null,reporteDatos);
                JasperExportManager.exportReportToPdfFile(jasperPrint,ruta2+"/solicitud_confirmadas3.pdf");
//                File pdf = File.createTempFile("output.", ".pdf");
//                JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf));
            
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
