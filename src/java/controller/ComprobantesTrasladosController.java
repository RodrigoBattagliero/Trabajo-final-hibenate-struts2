/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.ComprobantesTrasladosDAO;
import model.entities.Comprobantes;
import model.entities.ComprobantesTraslados;
import model.entities.Docentes;
import org.apache.struts2.interceptor.ServletRequestAware;
import resources.DateManager;

/**
 *
 * @author rodrigo
 */
public class ComprobantesTrasladosController extends Controller<ComprobantesTraslados> implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    private ArrayList<ComprobantesTraslados> listComprobantes;
    private String[] trasladoComprobantesImporte;
    private String[] trasadoComprobantesNumeroComprobante;
    private String[] trasladoComprobantesProveedor;
    private String[] trasladoDesde;
    private String[] trasladoHasta;
    private Date[] trasladoFechaHoraSalida;
    private String[] trasladoComprobantesObservaciones;
    private int[] idComprobantes;
    private int[] idTraslados;
    private List<ComprobantesTraslados> comprobantesUpdate;
    private ComprobantesTraslados[] traslado;

    public void setTraslado(ComprobantesTraslados[] traslado) {
        this.traslado = traslado;
    }

    
    
    
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

    public void setTrasladoComprobantesObservaciones(String[] trasladoComprobantestrasladoComprobantesObservaciones) {
        this.trasladoComprobantesObservaciones = trasladoComprobantestrasladoComprobantesObservaciones;
    }

    public void setIdComprobantes(int[] idComprobantes) {
        this.idComprobantes = idComprobantes;
    }

    public void setIdTraslados(int[] idTraslados) {
        this.idTraslados = idTraslados;
    }

    public List<ComprobantesTraslados> getComprobantesUpdate() {
        return comprobantesUpdate;
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
        String res = SUCCESS;
        setListComprobantes();
        sesion.setAttribute("ComprobanteTraslado", this.listComprobantes);
        if(!this.validar())
            res = INPUT;
        return res;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = this.request.getSession();
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
        
        if(cant  < trasladoComprobantesObservaciones.length)
            cant = trasladoComprobantesObservaciones.length;
        
        for (int i = 0; i < cant; i++) {
            traslado = new ComprobantesTraslados();
            comprobante = new Comprobantes();
            // Eliminar en caso de error para crear 
            //comprobante.setId(idComprobantes[i]);
            try{
                comprobante.setImporte((float) Double.parseDouble(trasladoComprobantesImporte[i]));   
            }catch(Exception e){
                comprobante.setImporte((float)0.0);
            }
            comprobante.setNumeroComprobante(trasadoComprobantesNumeroComprobante[i]);
            comprobante.setObservaciones(trasladoComprobantesObservaciones[i]);
            comprobante.setProveedor(trasladoComprobantesProveedor[i]);
            traslado.setComprobantes(comprobante);
            // Eliminar en caso de error para crear 
            //traslado.setId(idTraslados[i]);
            traslado.setDesde(trasladoDesde[i]);
            traslado.setHasta(trasladoHasta[i]);
            traslado.setFechaHoraSalida(trasladoFechaHoraSalida[i]);
            
            listComprobantes.add(traslado);
        }
    }
    
    public String setComprobante(){
        setUpdate();
        String idComStr = this.request.getParameter("idComprobanteSelected");
        ComprobantesTrasladosDAO dao = new ComprobantesTrasladosDAO();
        dao.iniciaOperacion();
        this.entity = (ComprobantesTraslados) dao.selectOne(Integer.parseInt(idComStr));
        dao.cerrarSession();
        return SUCCESS;
    }
    
    public String setUpdate(){
        String res = SUCCESS;
        this.entities = new ArrayList();
        List<Object> s;
        ComprobantesTrasladosDAO dao = new ComprobantesTrasladosDAO();
        String idSolStr = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
        ComprobantesController comCon = new ComprobantesController();
        comCon.selectRelated(Integer.parseInt(idSolStr));
        for(Comprobantes com : comCon.getEntities()){
            s = new ArrayList();
            dao.iniciaOperacion();
            s = dao.selectRelated(com.getId());
            dao.cerrarSession();
            try{
                this.entity = (ComprobantesTraslados) s.get(0);
            }catch(Exception e){
                this.entity = null;
            }
            if(this.entity != null)
                this.entities.add(this.entity);
        }
        return res;
    }
    
    @Override
    public String update(){
        String res = ERROR;
        ComprobantesTrasladosDAO dao = new ComprobantesTrasladosDAO();
        boolean b = false;
        try{
            String idSolStr = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
            SolicitudesController solCon = new SolicitudesController();
            solCon.selectOne(Integer.parseInt(idSolStr));
            this.entity.getComprobantes().setSolicitudes(solCon.getEntity());
            ComprobantesController comCon = new ComprobantesController();
            comCon.setEntity(this.entity.getComprobantes());
            comCon.update();
            dao.iniciaOperacion();
            b = dao.update(entity);
            dao.cerrarSession();
        }catch(NullPointerException e){
            System.out.println(e);
        }
        if(b)
            res = SUCCESS;
        return res; 
    }
    
    public boolean validar(){
        boolean b = true;
        for(ComprobantesTraslados com : this.listComprobantes){
            if(com.getComprobantes().getImporte() == 0){
                addFieldError("importe", "Debe completar el importe");
                b = false;
            }
            if(com.getComprobantes().getNumeroComprobante().equals("")){
                addFieldError("numeroComprobante", "Debe completar el número de comprobante");
                b = false;
            }
            if(com.getComprobantes().getProveedor().equals("")){
                addFieldError("proveedor", "Debe completar el proveedor");
                b = false;
            }
            if(com.getDesde().equals("")){
                addFieldError("desde", "Debe completar el lugar de salida");
                b = false;
            }
            if(com.getFechaHoraSalida()== null){
                addFieldError("fechaHoraSalida", "Debe completar la fecha y hora de salida");
                b = false;
            }
            if(com.getHasta().equals("")){
                addFieldError("hasta", "Debe completar el lugar de llegada");
                b = false;
            }
//            if(com.getFechaHoraRegreso().before(com.getFechaHoraSalida())){
//                addFieldError("fechaHoraSalida", "La fecha de inicio debe ser posterior o igual a la fecha de finalización.");
//                b = false;
//            }
            try{
                Docentes doc = (Docentes) sesion.getAttribute("DocentesForm");
                if(com.getFechaHoraSalida().before(doc.getFechaInicio())){
                    addFieldError("fechaHoraSalida", "La fecha de salida y regreso debe estar entre fecha de inicio y finalización ingresadas en datos de docente.");
                    b = false;
                }
//                if(doc.getFechaFinalizacion().before(com.getFechaHoraRegreso())){
//                    addFieldError("fechaHoraSalida", "La fecha de salida y regreso debe estar entre fecha de inicio y finalización ingresadas en datos de docente.");
//                    b = false;
//                }
            }catch(Exception e){
                
            }
        }
        return b;
    }
}
