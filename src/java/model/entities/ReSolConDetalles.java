/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

/**
 *
 * @author rodrigo
 */
public class ReSolConDetalles implements java.io.Serializable{
    
    private int id;
    private ReSolCon reSolCon;
    private RegistrosUnicos registrosUnicos;

    public ReSolConDetalles() {
    }

    public ReSolConDetalles(int id, ReSolCon reSolcones, RegistrosUnicos registrosUnicoses) {
        this.id = id;
        this.reSolCon = reSolcones;
        this.registrosUnicos = registrosUnicoses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReSolCon getReSolCon() {
        return reSolCon;
    }

    public void setReSolCon(ReSolCon reSolcones) {
        this.reSolCon = reSolcones;
    }

    public RegistrosUnicos getRegistrosUnicos() {
        return registrosUnicos;
    }

    public void setRegistrosUnicos(RegistrosUnicos registrosUnicoses) {
        this.registrosUnicos = registrosUnicoses;
    }
    
    
    
}
