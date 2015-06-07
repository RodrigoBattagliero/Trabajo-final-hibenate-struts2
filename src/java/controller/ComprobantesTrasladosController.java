/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.ComprobantesTrasladosDAO;
import model.entities.Comprobantes;
import model.entities.ComprobantesTraslados;
import org.apache.struts2.interceptor.ServletRequestAware;
import resources.DateManager;

/**
 *
 * @author rodrigo
 */
public class ComprobantesTrasladosController extends Controller<ComprobantesTraslados> implements ServletRequestAware {
    
    private HttpServletRequest request;
    private ArrayList<ComprobantesTraslados> listComprobantes;
    private String[] trasladoComprobantesImporte;
    private String[] trasadoComprobantesNumeroComprobante;
    private String[] trasladoComprobantesProveedor;
    private String[] trasladoDesde;
    private String[] trasladoHasta;
    private Date[] trasladoFechaHoraSalida;
    private Date[] trasladofechaHoraRegreso;
    private String[] trasladoComprobantesObservaciones;
    
    public ComprobantesTrasladosController() {
        ComprobantesTrasladosDAO dao = (ComprobantesTrasladosDAO) new ComprobantesTrasladosDAO();
        entity = new ComprobantesTraslados();
    }

    public void setTrasladoComprobantesImporte(String[] trasladoComprobantesImporte) {
        this.trasladoComprobantesImporte = trasladoComprobantesImporte;
    }

    public void setTrasadoComprobantesNumeroComprobante(String[] trasadoComprobantesNumeroComprobante) {
        this.trasadoComprobantesNumeroComprobante = trasadoComprobantesNumeroComprobante;
    }

    public void setTrasladoComprobantesProveedor(String[] trasladoComprobantesProveedor) {
        this.trasladoComprobantesProveedor = trasladoComprobantesProveedor;
    }

    public void setTrasladoDesde(String[] trasladoDesde) {
        this.trasladoDesde = trasladoDesde;
    }

    public void setTrasladoHasta(String[] trasladoHasta) {
        this.trasladoHasta = trasladoHasta;
    }

    public void setTrasladoFechaHoraSalida(Date[] trasladoFechaHoraSalida) {
        this.trasladoFechaHoraSalida = trasladoFechaHoraSalida;
    }

    public void setTrasladofechaHoraRegreso(Date[] trasladofechaHoraRegreso) {
        this.trasladofechaHoraRegreso = trasladofechaHoraRegreso;
    }

    public void setTrasladoComprobantesObservaciones(String[] trasladoComprobantestrasladoComprobantesObservaciones) {
        this.trasladoComprobantesObservaciones = trasladoComprobantestrasladoComprobantesObservaciones;
    }
    
    @Override
    public ComprobantesTrasladosDAO getDao() {
        return (ComprobantesTrasladosDAO) dao;
    }
    
    @Override
    public String selectRelated(int id){
        String res = SUCCESS;
        dao = new ComprobantesTrasladosDAO();
        dao.iniciaOperacion();
        entities = (List) dao.selectRelated(id);
        dao.cerrarSession();
        return res;
    }
    
    public void setDao(ComprobantesTrasladosDAO dao) {
        this.dao = dao;
    }
    
    public String prepared(){
        setListComprobantes();
        HttpSession sesion = this.request.getSession();
        sesion.setAttribute("ComprobanteTraslado", this.listComprobantes);
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    private void setListComprobantes(){
        
        ComprobantesTraslados traslado = new ComprobantesTraslados();
        Comprobantes comprobante = new Comprobantes();
        listComprobantes = new ArrayList();
        int cant = trasladoComprobantesImporte.length;
        DateManager dateManager = new DateManager("00-00-0000 00:00:00");
        
        if(cant  < trasadoComprobantesNumeroComprobante.length)
            cant = trasadoComprobantesNumeroComprobante.length;
        
        if(cant  < trasladoComprobantesProveedor.length)
            cant = trasladoComprobantesProveedor.length;
        
        if(cant  < trasladoDesde.length)
            cant = trasladoDesde.length;
        
        if(cant  < trasladoHasta.length)
            cant = trasladoHasta.length;
        
        if(cant  < trasladoFechaHoraSalida.length)
            cant = trasladoFechaHoraSalida.length;
        
        if(cant  < trasladofechaHoraRegreso.length)
            cant = trasladofechaHoraRegreso.length;
        
        if(cant  < trasladoComprobantesObservaciones.length)
            cant = trasladoComprobantesObservaciones.length;
        
        for (int i = 0; i < cant; i++) {
            traslado = new ComprobantesTraslados();
            comprobante = new Comprobantes();
            comprobante.setImporte((float) Double.parseDouble(trasladoComprobantesImporte[i]));
            comprobante.setNumeroComprobante(trasadoComprobantesNumeroComprobante[i]);
            comprobante.setObservaciones(trasladoComprobantesObservaciones[i]);
            comprobante.setProveedor(trasladoComprobantesProveedor[i]);
            traslado.setComprobantes(comprobante);
            traslado.setDesde(trasladoDesde[i]);
            traslado.setHasta(trasladoHasta[i]);
//            dateManager.setFechaString(trasladofechaHoraRegreso[i]);
            traslado.setFechaHoraRegreso(trasladofechaHoraRegreso[i]);
//            dateManager.setFechaString(trasladoFechaHoraSalida[i]);
            traslado.setFechaHoraSalida(trasladoFechaHoraSalida[i]);
            listComprobantes.add(traslado);
        }
        
    }
}
