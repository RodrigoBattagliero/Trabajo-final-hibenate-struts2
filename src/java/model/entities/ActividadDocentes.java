package model.entities;
// Generated 12-Jun-2015 00:00:28 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * ActividadDocentes generated by hbm2java
 */
public class ActividadDocentes  implements java.io.Serializable {


     private int id;
     private Designaciones designaciones;
     private Date fecha;
     private String observaciones;
     private Boolean visadoBedelia;
     private int comision;
     private String unidadAcademica;
     private String nombreUnidadAcademica;
     private String carrera;
     private String nombreCarrera;
     private String plan;
     private String materia;
     private String nombreMateria;

    public ActividadDocentes() {
    }

    public ActividadDocentes(int id, Designaciones designaciones, Date fecha, String observaciones, Boolean visadoBedelia, int Comision, String UnidadAcademica, String nombreUnidadAcademica, String carrera, String nombreCarrera, String plan, String materia, String nombreMateria) {
        this.id = id;
        this.designaciones = designaciones;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.visadoBedelia = visadoBedelia;
        this.comision = Comision;
        this.unidadAcademica = UnidadAcademica;
        this.nombreUnidadAcademica = nombreUnidadAcademica;
        this.carrera = carrera;
        this.nombreCarrera = nombreCarrera;
        this.plan = plan;
        this.materia = materia;
        this.nombreMateria = nombreMateria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Designaciones getDesignaciones() {
        return designaciones;
    }

    public void setDesignaciones(Designaciones designaciones) {
        this.designaciones = designaciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getVisadoBedelia() {
        return visadoBedelia;
    }

    public void setVisadoBedelia(Boolean visadoBedelia) {
        this.visadoBedelia = visadoBedelia;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int Comision) {
        this.comision = Comision;
    }

    public String getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(String UnidadAcademica) {
        this.unidadAcademica = UnidadAcademica;
    }

    public String getNombreUnidadAcademica() {
        return nombreUnidadAcademica;
    }

    public void setNombreUnidadAcademica(String nombreUnidadAcademica) {
        this.nombreUnidadAcademica = nombreUnidadAcademica;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }
     
    


}


