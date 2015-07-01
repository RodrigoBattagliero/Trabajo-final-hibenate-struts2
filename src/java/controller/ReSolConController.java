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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import reports.ConfirmarSolicitudesReporte;

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
        
        ConfirmarSolicitudesReporte reporteDatos = new ConfirmarSolicitudesReporte();
        reporteDatos.setListRegistros(registros);
        this.nombreArchivo = "nombre.pdf";
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
