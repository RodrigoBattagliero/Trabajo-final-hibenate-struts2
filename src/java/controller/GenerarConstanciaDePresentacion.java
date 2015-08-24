/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.entities.Docentes;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class GenerarConstanciaDePresentacion extends Controller<GenerarConstanciaDePresentacion> implements ServletRequestAware  {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    private String nombreArchivo;
    private long tamanoArchivo;
    private InputStream inputStream;

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public long getTamanoArchivo() {
        return tamanoArchivo;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
    
    
    @Override
    public String execute(){
        
        String idSolStr = String.valueOf(this.request.getParameter("idSolicitudSelected"));
        int idSol = Integer.parseInt(idSolStr);
        
        SolicitudesController solCon = new SolicitudesController();
        solCon.selectOne(idSol);
        
        DocentesController doc = new DocentesController();
        doc.selectRelated(idSol);
        doc.setEntity(doc.getEntities().get(0));
        
        ComprobantesController comprobantes = new ComprobantesController();
        comprobantes.selectRelated(idSol);
        int cantidadComprobantes = comprobantes.getEntities().size();
                
        reports.ConstanciaDePresentacion constancia = new reports.ConstanciaDePresentacion();
        constancia.setApellido(doc.getEntity().getApellido());
        constancia.setCantComprobantes(cantidadComprobantes);
        constancia.setFechaAlta(solCon.getEntity().getFechaAlta());
        constancia.setFechaFin(doc.getEntity().getFechaFinalizacion());
        constancia.setFechaInicio(doc.getEntity().getFechaInicio());
        constancia.setNombre(doc.getEntity().getNombre());
        constancia.setNumSolicitud(solCon.getEntity().getNumeroSolicitud());
        nombreArchivo = doc.getEntity().getApellido()+"_"+doc.getEntity().getNombre() +"_" + String.valueOf(solCon.getEntity().getNumeroSolicitud()) +"_" + solCon.getEntity().getFechaAlta() + ".pdf";
        try{
            String ruta = ServletActionContext.getServletContext().getRealPath("/Reportes/ConstanciasDePresentacion/constancia_de_presentacion.jasper");
            String ruta2 = ServletActionContext.getServletContext().getRealPath("/");
            ruta2 += "/Reportes/ConstanciasDePresentacion/"+nombreArchivo;
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,null,constancia);
            JasperExportManager.exportReportToPdfFile(jasperPrint,ruta2);
            
            File archivo = new File(ruta2);
            tamanoArchivo = archivo.length();
            inputStream = new FileInputStream(archivo);
        }catch(Exception e){
            
        
        }
        this.sesion.removeAttribute("Constancia");
        return SUCCESS;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = this.request.getSession();
    }
    
    
}
