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
import model.dao.ActividadDocentesDAO;
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
    private HttpSession sesion;
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
    
    @Override
    public String selectRelated(int id){
        String res = SUCCESS;
        dao = new ComprobantesComidaAlojamientoDAO();
        dao.iniciaOperacion();
        entities = (List) dao.selectRelated(id);
        dao.cerrarSession();
        return res;
    }

    public void setDao(ComprobantesComidaAlojamientoDAO dao) {
        this.dao = dao;
    }
    
    public String prepared(){
        String res = SUCCESS;
        setListAlojamiento();
        if(this.validar())
            sesion.setAttribute("ComprobanteAlojamiento", this.listAlomamiento);
        else
            res = INPUT;        
        return res;
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
            listAlomamiento.add(alojamiento);
        }
        
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = this.request.getSession();
    }
    
    public String setUpdate(){
        String res = SUCCESS;
        this.entities = new ArrayList();
        List<Object> s;
        ComprobantesComidaAlojamientoDAO dao = new ComprobantesComidaAlojamientoDAO();
        String idSolStr = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
        ComprobantesController comCon = new ComprobantesController();
        comCon.selectRelated(Integer.parseInt(idSolStr));
        for(Comprobantes com : comCon.getEntities()){
            s = new ArrayList();
            dao.iniciaOperacion();
            s = dao.selectRelated(com.getId());
            dao.cerrarSession();
            try{
                this.entity = (ComprobantesComidaAlojamientos) s.get(0);
            }catch(Exception e){
                this.entity = null;
            }
            if(this.entity != null)
                this.entities.add(this.entity);
        }
        return res;
    }
    
    public String setComprobante(){
        setUpdate();
        String idComStr = this.request.getParameter("idComprobanteSelected");
        ComprobantesComidaAlojamientoDAO dao = new ComprobantesComidaAlojamientoDAO();
        dao.iniciaOperacion();
        this.entity = (ComprobantesComidaAlojamientos) dao.selectOne(Integer.parseInt(idComStr));
        dao.cerrarSession();
        return SUCCESS;
    }
    
    @Override
    public String update(){
        String res = ERROR;
        ComprobantesComidaAlojamientoDAO dao = new ComprobantesComidaAlojamientoDAO();
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
        for(ComprobantesComidaAlojamientos a : listAlomamiento){
            if(a.getComprobantes().getImporte() == 0.0){
                addFieldError("importe", "ERROR: Debe ingresar el importe.");
                b = false;
            }
            if(a.getComprobantes().getNumeroComprobante().equals("")){
                addFieldError("numeroComprobante", "ERROR: Debe ingresar el número de comprobante.");
                b = false;
            }
            if(a.getComprobantes().getProveedor().equals("")){
                addFieldError("proveedor", "ERROR: Debe ingresar el proveedor.");
                b = false;
            }
            if(a.getTipo() == 0){
                addFieldError("tipo", "ERROR: Debe seleccionar el tipo.");
                b = false;
            }
            if(a.getDescripcion().equals("")){
                addFieldError("descripcion", "ERROR: Debe ingresar la descripción.");
                b = false;
            }
        }
        return b;
    }
}
