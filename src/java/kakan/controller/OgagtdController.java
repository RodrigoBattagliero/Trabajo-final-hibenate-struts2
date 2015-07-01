/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakan.controller;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;
import kakan.dao.OgagtdDAO;
import kakan.entities.VOgagtd;

/**
 *
 * @author rodrigo
 */
public class OgagtdController extends ActionSupport {

    private List<VOgagtd> entities;
    private VOgagtd entity;
    private OgagtdDAO model;
    private String dni;
    private Date fecha;
    private int comision;
    private int idDesignacion;
    
    public OgagtdController() {
        OgagtdDAO model = new OgagtdDAO();
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    public int getIdDesignacion() {
        return idDesignacion;
    }

    public void setIdDesignacion(int idDesignacion) {
        this.idDesignacion = idDesignacion;
    }
    
    
    @Override
    public String execute(){
        OgagtdDAO model = new OgagtdDAO();
        this.entities = model.selectParameter(this.dni, this.fecha);
        return SUCCESS;
    }
    
    public String selectFromComision(){
        OgagtdDAO model = new OgagtdDAO();
        this.entities = model.selectParameter(this.dni, this.fecha,this.comision);
        return SUCCESS;
    }
    
    public void selectFromDesignacion(){
        OgagtdDAO model = new OgagtdDAO();
        this.entities = model.selectFromDesignacion(idDesignacion);
    }
    
    public Object getModel(){
        return entity;
    }

    public List<VOgagtd> getEntities() {
        return entities;
    }

}
