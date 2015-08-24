/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.ReSolConDAO;
import model.entities.ReSolCon;
import model.entities.ReSolConDetalles;
import model.entities.RegistrosUnicos;
import model.entities.Usuarios;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import reports.ConfirmarSolicitudesReporte;
import reports.ExpedienteReportes;

/**
 *
 * @author rodrigo
 */
public class ReSolConController extends Controller<ReSolCon> implements ServletRequestAware {
    
    private String nombreArchivo;
    private long tamanoArchivo;
    private HttpServletRequest request;
    private HttpSession sesion;
    private InputStream inputStream;

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public long getTamanoArchivo() {
        return tamanoArchivo;
    }

    public void setTamanoArchivo(long tamanoArchivo) {
        this.tamanoArchivo = tamanoArchivo;
    }
    public InputStream getInputStream() {
        return inputStream;
    }
    public ReSolConController() {
        this.dao = new ReSolConDAO();
        this.entity = new ReSolCon();
    }
    
    @Override
    public String select(){
        String res = SUCCESS;
        ReSolConDAO dao = new ReSolConDAO();
        dao.iniciaOperacion();
        entities = (List) dao.selectAll((Usuarios) this.sesion.getAttribute("user"));
        dao.cerrarSession();
        return res;
    }
    
    public String descargar() throws Exception{
        String res = SUCCESS;
        String idStr = request.getParameter("idReporteSelected");
        int idReporte = Integer.parseInt(idStr);
        ReSolConDAO dao = new ReSolConDAO();
        dao.iniciaOperacion();
        this.entity = (ReSolCon) dao.selectReporte(idReporte);
        dao.cerrarSession();
        Set reporteDetalle = this.entity.getReSolConDetalleses();
        List<RegistrosUnicos> registros = new ArrayList();
        for (Iterator it = reporteDetalle.iterator(); it.hasNext();) {
            ReSolConDetalles re = (ReSolConDetalles) it.next();
            registros.add(re.getRegistrosUnicos());
        }
        
        Usuarios user = (Usuarios) sesion.getAttribute("user");
        
        ConfirmarSolicitudesReporte reporteDatos = new ConfirmarSolicitudesReporte();
        reporteDatos.setListRegistros(registros);
        reporteDatos.setFechaReporte(this.entity.getFecha());
        this.nombreArchivo = user.getAreas().getNombre() +"_solicitudes_procesadas.pdf";
        try {
            String ruta = ServletActionContext.getServletContext().getRealPath("/Reportes/SolicitudesProcesadas/solicitudes_confirmadas.jasper");
            String ruta2 = ServletActionContext.getServletContext().getRealPath("/") + "/Reportes/SolicitudesProcesadas/"+nombreArchivo;
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,null,reporteDatos);
            JasperExportManager.exportReportToPdfFile(jasperPrint,ruta2); 
            String ruta3 = ServletActionContext.getServletContext().getRealPath(ruta2);
            File archivo = new File(ruta2);
            tamanoArchivo = archivo.length();
            inputStream = new FileInputStream(archivo);
        } catch (JRException ex) {
            Logger.getLogger(ReSolConController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public String descargarExcel() throws Exception{
        String res = SUCCESS;
        String idStr = request.getParameter("idReporteSelected");
        int idReporte = Integer.parseInt(idStr);
        ReSolConDAO dao = new ReSolConDAO();
        dao.iniciaOperacion();
        this.entity = (ReSolCon) dao.selectReporte(idReporte);
        dao.cerrarSession();
        Set reporteDetalle = this.entity.getReSolConDetalleses();
        List<RegistrosUnicos> registros = new ArrayList();
        for (Iterator it = reporteDetalle.iterator(); it.hasNext();) {
            ReSolConDetalles re = (ReSolConDetalles) it.next();
            registros.add(re.getRegistrosUnicos());
        }
        
        Usuarios user = (Usuarios) sesion.getAttribute("user");
        
        ConfirmarSolicitudesReporte reporteDatos = new ConfirmarSolicitudesReporte();
        reporteDatos.setListRegistros(registros);
        this.nombreArchivo = user.getAreas().getNombre() +"_solicitudes_procesadas.xls";
        try {
            String ruta = ServletActionContext.getServletContext().getRealPath("/Reportes/SolicitudesProcesadas/solicitudes_confirmadas_excel.jasper");
            String ruta2 = ServletActionContext.getServletContext().getRealPath("/") + "/Reportes/SolicitudesProcesadas/"+nombreArchivo;
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,null,reporteDatos);
           
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(true);
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(true);
            configuration.setWhitePageBackground(false);
            
            JRXlsExporter exporterXLS = new JRXlsExporter();
            exporterXLS.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(ruta2));
            exporterXLS.setConfiguration(configuration);
            exporterXLS.exportReport();
           
            String ruta3 = ServletActionContext.getServletContext().getRealPath(ruta2);
            File archivo = new File(ruta2);
            tamanoArchivo = archivo.length();
            inputStream = new FileInputStream(archivo);
        } catch (JRException ex) {
            Logger.getLogger(ReSolConController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    public String descargarExpedienteExcel() throws Exception{
        String res = SUCCESS;
        DireccionEconomicoFinancieraController area = new DireccionEconomicoFinancieraController();
        List<Object> registros = area.setExpedienteToPrint(Integer.parseInt(String.valueOf(this.request.getParameter("idExpedienteSelected"))));
        this.sesion.setAttribute("idExpedienteSelected", Integer.parseInt(String.valueOf(this.request.getParameter("idExpedienteSelected"))));
        Usuarios user = (Usuarios) sesion.getAttribute("user");
        
        ExpedienteReportes reporteDatos = new ExpedienteReportes();
        reporteDatos.setListRegistros(registros);
        this.nombreArchivo = user.getAreas().getNombre() +"_solicitudes_expediente.xls";
        try {
            String ruta = ServletActionContext.getServletContext().getRealPath("/Reportes/Expedientes/expedientes_excel.jasper");
            String ruta2 = ServletActionContext.getServletContext().getRealPath("/") + "/Reportes/Expedientes/"+nombreArchivo;
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,null,reporteDatos);
           
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(true);
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(true);
            configuration.setWhitePageBackground(false);
            
            JRXlsExporter exporterXLS = new JRXlsExporter();
            exporterXLS.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(ruta2));
            exporterXLS.setConfiguration(configuration);
            exporterXLS.exportReport();
           
            String ruta3 = ServletActionContext.getServletContext().getRealPath(ruta2);
            File archivo = new File(ruta2);
            tamanoArchivo = archivo.length();
            inputStream = new FileInputStream(archivo);
        } catch (JRException ex) {
            Logger.getLogger(ReSolConController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    public String detalle(){
        String res = SUCCESS;
        String idStr = request.getParameter("idReporteSelected");
        int idReporte = Integer.parseInt(idStr);
        
        return res;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = this.request.getSession();
    }
}
