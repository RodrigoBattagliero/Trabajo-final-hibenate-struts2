/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.ComprobantesComidaAlojamientoDAO;
import model.entities.Comprobantes;
import model.entities.ComprobantesComidaAlojamientos;
import model.entities.ComprobantesTraslados;
import org.apache.struts2.interceptor.ServletRequestAware;
import resources.DateManager;

/**
 *
 * @author rodrigo
 */
public class ComprobantesComidaAlojamientoController extends Controller<ComprobantesComidaAlojamientos> implements ServletRequestAware {
    
    private HttpServletRequest request;
    List<ComprobantesComidaAlojamientos> listAlomamiento;
    ArrayList<String> listTipo;
    private String[] alojamientoComprobantesImporte;
    private String[] alojamientoComprobantesNumeroComprobante;
    private String[] alojamientoComprobantesNroveedor;
    private short[] alojamientoTipo;
    private String[] alojamientoDescripcion;
    private String[] alojamientoComprobantesObservaciones;
    
    

    public void setAlojamientoComprobantesImporte(String[] alojamientoComprobantesImporte) {
        this.alojamientoComprobantesImporte = alojamientoComprobantesImporte;
    }

    public void setAlojamientoComprobantesNumeroComprobante(String[] alojamientoComprobantesNumeroComprobante) {
        this.alojamientoComprobantesNumeroComprobante = alojamientoComprobantesNumeroComprobante;
    }

    public void setAlojamientoComprobantesNroveedor(String[] alojamientoComprobantesNroveedor) {
        this.alojamientoComprobantesNroveedor = alojamientoComprobantesNroveedor;
    }

    public void setAlojamientoTipo(short[] alojamientoTipo) {
        this.alojamientoTipo = alojamientoTipo;
    }

    public void setAlojamientoDescripcion(String[] alojamientoDescripcion) {
        this.alojamientoDescripcion = alojamientoDescripcion;
    }

    public void setAlojamientoComprobantesObservaciones(String[] alojamientoComprobantesObservaciones) {
        this.alojamientoComprobantesObservaciones = alojamientoComprobantesObservaciones;
    }
    
    public List<String> getListTipo(){
        return this.listTipo;
    }
    
    public String setTipo(){
        listTipo = new ArrayList();
        listTipo.add("Comida");
        listTipo.add("Alojamiento");
        return SUCCESS;
    }
    
    public ComprobantesComidaAlojamientoController() {
        dao = (ComprobantesComidaAlojamientoDAO) new ComprobantesComidaAlojamientoDAO();
        this.entity = new ComprobantesComidaAlojamientos();
    }
    
    @Override
    public ComprobantesComidaAlojamientoDAO getDao() {
        return (ComprobantesComidaAlojamientoDAO) dao;
    }

    public void setDao(ComprobantesComidaAlojamientoDAO dao) {
        this.dao = dao;
    }
    
    public String prepared(){
        setListAlojamiento();
        HttpSession sesion = this.request.getSession();
        sesion.setAttribute("ComprobanteAlojamiento", this.listAlomamiento);
        return SUCCESS;
    }
    
    private void setListAlojamiento(){
        ComprobantesComidaAlojamientos alojamiento = new ComprobantesComidaAlojamientos();
        Comprobantes comprobante = new Comprobantes();
        listAlomamiento = new ArrayList();
        int cant = alojamientoComprobantesImporte.length;
        DateManager dateManager = new DateManager("00-00-0000 00:00:00");
        
        if(cant  < alojamientoComprobantesNumeroComprobante.length)
            cant = alojamientoComprobantesNumeroComprobante.length;
        
        if(cant  < alojamientoComprobantesNroveedor.length)
            cant = alojamientoComprobantesNroveedor.length;
        
        if(cant  < alojamientoTipo.length)
            cant = alojamientoTipo.length;
        
        if(cant  < alojamientoDescripcion.length)
            cant = alojamientoDescripcion.length;
        
        if(cant  < alojamientoComprobantesObservaciones.length)
            cant = alojamientoComprobantesObservaciones.length;
        
        for (int i = 0; i < cant; i++) {
            alojamiento = new ComprobantesComidaAlojamientos();
            comprobante = new Comprobantes();
            comprobante.setImporte((float) Double.parseDouble(alojamientoComprobantesImporte[i]));
            comprobante.setNumeroComprobante(alojamientoComprobantesNumeroComprobante[i]);
            comprobante.setObservaciones(alojamientoComprobantesObservaciones[i]);
            comprobante.setProveedor(alojamientoComprobantesNroveedor[i]);
            alojamiento.setDescripcion(alojamientoDescripcion[i]);
            alojamiento.setTipo(alojamientoTipo[i]);
            alojamiento.setComprobantes(comprobante);
            System.out.println("as");
            listAlomamiento.add(alojamiento);
        }
        
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
}
