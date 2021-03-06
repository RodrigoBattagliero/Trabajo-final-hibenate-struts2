package model.entities;
// Generated 12-Jun-2015 00:00:28 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * DepartamentosAcademicos generated by hbm2java
 */
public class DepartamentosAcademicos  implements java.io.Serializable {


     private int id;
     private String nombre;
     private Set docenteses = new HashSet(0);

    public DepartamentosAcademicos() {
    }

	
    public DepartamentosAcademicos(String nombre) {
        this.nombre = nombre;
    }
    public DepartamentosAcademicos(String nombre, Set docenteses) {
       this.nombre = nombre;
       this.docenteses = docenteses;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getDocenteses() {
        return this.docenteses;
    }
    
    public void setDocenteses(Set docenteses) {
        this.docenteses = docenteses;
    }




}


