/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.entities.Docentes;
import model.entities.RegistrosUnicos;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author rodrigo
 */
public class ConfirmarSolicitudesReporte implements JRDataSource {
    
    private List<RegistrosUnicos> listRegistros = new ArrayList();
    private int userIndex = -1;

    public void setListRegistros(List<RegistrosUnicos> listUser) {
        this.listRegistros = listUser;
    }
    
    
    @Override
    public boolean next() throws JRException {
        return ++userIndex < listRegistros.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        
        if("numeroSolicitud".equals(jrf.getName()))
            valor = listRegistros.get(userIndex).getSolicitudes().getNumeroSolicitud();
        
        if("docente".equals(jrf.getName())){
            Iterator i = listRegistros.get(userIndex).getSolicitudes().getDocenteses().iterator();
            Docentes doc = new Docentes();
            while(i.hasNext()){
                doc = (Docentes) i.next();
            }
            valor = doc.getApellido() + ", " + doc.getNombre();
        }
        
        if("fechaAlta".equals(jrf.getName()))
            valor = listRegistros.get(userIndex).getSolicitudes().getFechaAlta();
        
        if("area".equals(jrf.getName()))
            valor = listRegistros.get(userIndex).getAreas().getNombre();
        
        if("estado".equals(jrf.getName()))
            valor = listRegistros.get(userIndex).getEstados().getNombre();
        
        return valor;
    }
    
}
