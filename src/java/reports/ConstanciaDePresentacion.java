/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import java.util.Date;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author rodrigo
 */
public class ConstanciaDePresentacion  implements JRDataSource {
    
    private String nombre;
    private String apellido;
    private int numSolicitud;
    private Date fechaAlta;
    private Date fechaInicio;
    private Date fechaFin;
    private int cantComprobantes;
    private int userIndex = -1;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNumSolicitud(int numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setCantComprobantes(int cantComprobantes) {
        this.cantComprobantes = cantComprobantes;
    }
    
    
    @Override
    public boolean next() throws JRException {
        return ++userIndex < 1;
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        
        if("docente".equals(jrf.getName()))
            valor = this.apellido + ", " + this.nombre;
        if("numeroSolicitud".equals(jrf.getName()))
            valor = String.valueOf(this.numSolicitud);
        if("fechaAlta".equals(jrf.getName()))
            valor = this.fechaAlta;
        if("fechaInicio".equals(jrf.getName()))
            valor = this.fechaInicio;
        if("fechaFin".equals(jrf.getName()))
            valor = this.fechaFin;
        if("comprobantes".equals(jrf.getName()))
            valor = this.cantComprobantes;
        
        return valor;
    }
    
}
