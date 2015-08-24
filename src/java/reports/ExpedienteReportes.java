/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import java.util.ArrayList;
import java.util.List;
import model.entities.Docentes;
import model.entities.Liquidaciones;
import model.entities.Solicitudes;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author rodrigo
 */
public class ExpedienteReportes implements JRDataSource{
    
    private List<Object> listRegistros = new ArrayList();
    private int userIndex = -1;

    public void setListRegistros(List<Object> listRegistros) {
        this.listRegistros = listRegistros;
    }
    
    @Override
    public boolean next() throws JRException {
        return ++userIndex < listRegistros.size();
    }
    
    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        Object[] data = (Object[]) listRegistros.get(userIndex);
        Solicitudes solicitud = (Solicitudes) data[0];
        Docentes docente = (Docentes) data[1];
        Liquidaciones liquidacion = (Liquidaciones) data[2];
        if("numeroSolicitud".equals(jrf.getName()))
            valor = solicitud.getNumeroSolicitud();
        if("docente".equals(jrf.getName()))
            valor = docente.getApellido() + ", " + docente.getNombre();
        if("cuil".equals(jrf.getName()))
            valor = docente.getCuil();
        if("fechaAlta".equals(jrf.getName()))
            valor = solicitud.getFechaAlta();
        if("importeDeclarado".equals(jrf.getName()))
            valor = liquidacion.getImporteDeclarado();
        if("importeReconocido".equals(jrf.getName()))
            valor = liquidacion.getReconocimientoImporteTotal();
        return valor;
    }
}
