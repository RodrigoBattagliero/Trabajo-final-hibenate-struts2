/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author rodrigo
 */
public class ReSolCon implements java.io.Serializable{
    
    private int id;
    private Date fecha;
    private Usuarios usuarios;
    private Set reSolConDetalleses = new HashSet(0);

    public ReSolCon() {
    }

    public ReSolCon(int id, Date fecha, Usuarios usuarios) {
        this.id = id;
        this.fecha = fecha;
        this.usuarios = usuarios;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Set getReSolConDetalleses() {
        return reSolConDetalleses;
    }

    public void setReSolConDetalleses(Set reSolConDetalleses) {
        this.reSolConDetalleses = reSolConDetalleses;
    }
    
}
