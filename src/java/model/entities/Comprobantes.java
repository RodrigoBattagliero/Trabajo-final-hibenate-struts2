package model.entities;
// Generated 12-Jun-2015 00:00:28 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Comprobantes generated by hbm2java
 */
public class Comprobantes  implements java.io.Serializable {


     private int id;
     private Solicitudes solicitudes;
     private float importe;
     private String numeroComprobante;
     private String proveedor;
     private String observaciones;
     private Set comprobantesTrasladoses = new HashSet(0);
     private Set comprobantesComidaAlojamientoses = new HashSet(0);

    public Comprobantes() {
    }

	
    public Comprobantes(Solicitudes solicitudes, float importe) {
        this.solicitudes = solicitudes;
        this.importe = importe;
    }
    public Comprobantes(Solicitudes solicitudes, float importe, String numeroComprobante, String proveedor, String observaciones, Set comprobantesTrasladoses, Set comprobantesComidaAlojamientoses) {
       this.solicitudes = solicitudes;
       this.importe = importe;
       this.numeroComprobante = numeroComprobante;
       this.proveedor = proveedor;
       this.observaciones = observaciones;
       this.comprobantesTrasladoses = comprobantesTrasladoses;
       this.comprobantesComidaAlojamientoses = comprobantesComidaAlojamientoses;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Solicitudes getSolicitudes() {
        return this.solicitudes;
    }
    
    public void setSolicitudes(Solicitudes solicitudes) {
        this.solicitudes = solicitudes;
    }
    public float getImporte() {
        return this.importe;
    }
    
    public void setImporte(float importe) {
        this.importe = importe;
    }
    public String getNumeroComprobante() {
        return this.numeroComprobante;
    }
    
    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }
    public String getProveedor() {
        return this.proveedor;
    }
    
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public Set getComprobantesTrasladoses() {
        return this.comprobantesTrasladoses;
    }
    
    public void setComprobantesTrasladoses(Set comprobantesTrasladoses) {
        this.comprobantesTrasladoses = comprobantesTrasladoses;
    }
    public Set getComprobantesComidaAlojamientoses() {
        return this.comprobantesComidaAlojamientoses;
    }
    
    public void setComprobantesComidaAlojamientoses(Set comprobantesComidaAlojamientoses) {
        this.comprobantesComidaAlojamientoses = comprobantesComidaAlojamientoses;
    }




}


