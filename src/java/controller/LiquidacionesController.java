/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.LiquidacionesDAO;
import model.entities.Comprobantes;
import model.entities.ComprobantesComidaAlojamientos;
import model.entities.ComprobantesTraslados;
import model.entities.Liquidaciones;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class LiquidacionesController extends Controller<Liquidaciones> implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    
    public LiquidacionesController() {
        dao = (LiquidacionesDAO) new LiquidacionesDAO();
    }
    
    @Override
    public LiquidacionesDAO getDao() {
        return (LiquidacionesDAO) dao;
    }

    public void setDao(LiquidacionesDAO dao) {
        this.dao = dao;
    }
    
    public String prepared(){
        String res = SUCCESS;
        if(this.validar())
            sesion.setAttribute("LiquidacionesForm", this.entity);
        else
            res = INPUT;
        return res;
    }
    
    public String setSolicitud(){
        String res = SUCCESS;
        setIdSolicitudSelected();
        int idSol = Integer.parseInt(String.valueOf(sesion.getAttribute("idSolicitudSelected")));
        float gastoAloja = 0,gastoComida=0,gastoCombustible = 0, gastoTraslado = 0, importeTotal = 0;
        this.entity = new Liquidaciones();
        
        ComprobantesController comCon = new ComprobantesController();
        comCon.selectRelated(idSol);
        List<Comprobantes> comprobantes = comCon.getEntities();
        
        ComprobantesComidaAlojamientoController alojaController = new ComprobantesComidaAlojamientoController();
        ComprobantesComidaAlojamientos aloja;
        
        ComprobantesTrasladosController trasladoController = new ComprobantesTrasladosController();
        ComprobantesTraslados traslado;
        for(Comprobantes com : comprobantes){
            // Comprobantes de comida/alojameinto/Combustible
            try{
                alojaController.selectRelated(com.getId());
                aloja = (ComprobantesComidaAlojamientos) alojaController.getEntities().get(0);
                if(aloja.getTipo() == 1){ // Comida
                    gastoComida += com.getImporte();
                }else if(aloja.getTipo() == 2){ // Alojamiento
                    gastoAloja += com.getImporte();
                }else if(aloja.getTipo() == 3){ // Combustible
                    gastoCombustible += com.getImporte();
                }
                
            }catch(IndexOutOfBoundsException e){
                aloja = null;
            }
            // Pasajes
            try{
                trasladoController.selectRelated(com.getId());
                traslado =  (ComprobantesTraslados) trasladoController.getEntities().get(0);
                gastoTraslado += com.getImporte();
            }catch(IndexOutOfBoundsException e){
                traslado = null;
            }
            // Importe declarado.
            importeTotal += com.getImporte();
        }
        entity.setImporteDeclarado(importeTotal);
        entity.setReconocimientoGastoAlojamiento(gastoAloja);
        entity.setReconocimientoGastoCombustible(gastoCombustible);
        entity.setReconocimientoGastoComida(gastoComida);
        entity.setReconocimientoGastoPasajes(gastoTraslado);
        entity.setReconocimientoImporteTotal(importeTotal);
        
        try{
            DocentesController docCon = new DocentesController();
            docCon.selectRelated(idSol);
            this.sesion.setAttribute("DocentesForm", docCon.getEntities().get(0));
        }catch(Exception e){
            
        }
        return res;
    }
    
    public void setIdSolicitudSelected(){
        this.sesion.setAttribute("idSolicitudSelected", this.request.getParameter("idSolicitudSelected"));
    }
    
     public String updatePrepared(){
        String idSolStr = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
        this.selectRelated(Integer.parseInt(idSolStr));
        try{
            this.entity = this.entities.get(0);
        }catch(NullPointerException | IndexOutOfBoundsException e){
            this.entity = new Liquidaciones();
        }
        return SUCCESS;
    }
    
    @Override
    public String update(){
        String idSolStr = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
        SolicitudesController solCont = new SolicitudesController();
        solCont.selectOne(Integer.parseInt(idSolStr));
        this.entity.setSolicitudes(solCont.getEntity());
        this.dao.iniciaOperacion();
        String res = super.update();
        this.dao.cerrarSession();
        return res;
    }
    
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        this.sesion = this.request.getSession();
    }
    
    public boolean validar(){
        boolean b = true;
        if(this.entity.getReconocimientoImporteTotal() <= 0.0){
            addFieldError("reconocimientoImporteTotal", "ERROR: El monto a reconocer debe der mayor a cero");
            b = false;
        }
        if( !b && this.entity.getObservaciones().equals("")){
            addFieldError("observaciones", "ERROR: Debe agregar una observación si deja vacío un campo.");
        }else if(!b && !this.entity.getObservaciones().equals("")){
            b = true;
        }
        return b;
    }
}
