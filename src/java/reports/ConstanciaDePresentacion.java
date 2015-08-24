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
import resources.DateManager;

/**
 *
 * @author rodrigo
 */
public class ConstanciaDePresentacion  implements JRDataSource {
    
    private String nombre;
    private String apellido;
    private int numSolicitud;
    private DateManager fechaAlta;
    private DateManager fechaInicio;
    private DateManager fechaFin;
    private int cantComprobantes;
    private String texto;
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
        this.fechaAlta = new DateManager(fechaAlta);
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = new DateManager(fechaInicio);
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = new DateManager(fechaFin);
    }

    public void setCantComprobantes(int cantComprobantes) {
        this.cantComprobantes = cantComprobantes;
    }
    
    public void setTexto(){
        this.texto = "Recibí la cantidad de "+this.cantComprobantes+" comprobantes de pago correspondientes a lo declarado por el Docente Sr./a "+this.apellido+", "+this.nombre+" en concepto de reconocimiento de gastos, realizado entre los días "+this.fechaInicio.getFechaString()+" y "+this.fechaFin.getFechaString()+" de acuerdo a lo detallado en la solicitud N° "+this.numSolicitud+" de Reconocimiento de Gasto de Traslado.";
    }
    
    @Override
    public boolean next() throws JRException {
        return ++userIndex < 1;
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        setTexto();
        if("texto".equals(jrf.getName()))
            valor = this.texto;
        
//        if("docente".equals(jrf.getName()))
//            valor = this.apellido + ", " + this.nombre;
//        if("numeroSolicitud".equals(jrf.getName()))
//            valor = String.valueOf(this.numSolicitud);
        if("fechaAlta".equals(jrf.getName()))
            valor = this.fechaAlta.getFechaString();
//        if("fechaInicio".equals(jrf.getName()))
//            valor = this.fechaInicio;
//        if("fechaFin".equals(jrf.getName()))
//            valor = this.fechaFin;
//        if("comprobantes".equals(jrf.getName()))
//            valor = this.cantComprobantes;
        
        return valor;
    }
    
}
