/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.ComprobantesDAO;
import model.entities.Comprobantes;
import model.entities.ComprobantesComidaAlojamientos;
import model.entities.ComprobantesTraslados;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class ComprobantesController extends Controller<Comprobantes> implements ServletRequestAware {
    
    private int tipoComprobante;
    private ComprobantesTraslados traslado;
    private ComprobantesComidaAlojamientos comida;
    private HttpServletRequest request;
    private HttpSession sesion;
    
    public ComprobantesController() {
        dao = (ComprobantesDAO) new ComprobantesDAO();
    }
    
    @Override
    public ComprobantesDAO getDao() {
        return (ComprobantesDAO) dao;
    }

    public void setDao(ComprobantesDAO dao) {
        this.dao = dao;
    }

    public void setTipoComprobante(int tipoCompronbante) {
        this.tipoComprobante = tipoCompronbante;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        this.sesion = this.request.getSession();
    }
    
    @Override
    public String update(){        
        String res = ERROR;
        boolean b = false;
        try{
            this.dao.iniciaOperacion();
            b = dao.update(entity);
            dao.cerrarSession();
        }catch(NullPointerException e){
            System.out.println(e);
        }
        if(b)
            res = SUCCESS;
        return res; 
    }
}
