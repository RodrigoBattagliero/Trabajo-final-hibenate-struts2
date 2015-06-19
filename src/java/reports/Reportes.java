/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class Reportes extends ActionSupport implements ServletRequestAware {
    
    private InputStream inputStream;
    private long tamanoArchivo;
    private String nombreArchivo;
    
    private HttpServletRequest request;
    private HttpSession sesion;

    public InputStream getInputStream() {
        return inputStream;
    }

    public long getTamanoArchivo() {
        return tamanoArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }
    
    @Override
    public String execute() throws Exception{
        String test = String.valueOf(sesion.getAttribute("test"));
        if(!test.equals("back")){
            String ruta = ServletActionContext.getServletContext().getRealPath("/pdf.pdf");
            File archivo = new File(ruta);
            tamanoArchivo = archivo.length();
            nombreArchivo = "reporte.pdf";
            inputStream = new FileInputStream(archivo);
            sesion.setAttribute("test", "back");
            return SUCCESS;
        }else{
            return test;
        }
        
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        this.sesion = request.getSession();
    }
}
