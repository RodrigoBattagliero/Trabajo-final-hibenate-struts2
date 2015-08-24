/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.UsuariosDAO;
import model.entities.Usuarios;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class UsuariosController extends Controller<Usuarios> implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    
    private String password;
    private String passwordNueva;
    private String passwordNueva2;
    
    @RequiredStringValidator(message = "El campo 'Contraseña actual' no debe estar vacío.")
    public void setPassword(String password) {
        this.password = password;
    }
    @RequiredStringValidator(message = "El campo 'Contraseña nueva' no debe estar vacío.")
    public void setPasswordNueva(String passwordNueva) {
        this.passwordNueva = passwordNueva;
    }
    @RequiredStringValidator(message = "El campo 'Repetir contraseña nueva' no debe estar vacío.")
    public void setPasswordNueva2(String passwordNueva2) {
        this.passwordNueva2 = passwordNueva2;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordNueva() {
        return passwordNueva;
    }

    public String getPasswordNueva2() {
        return passwordNueva2;
    }
    
    public UsuariosController() {
        this.dao = (UsuariosDAO) new UsuariosDAO();
    }
    
    @Override
    public UsuariosDAO getDao() {
        return (UsuariosDAO) dao;
    }

    public void setDao(UsuariosDAO dao) {
        this.dao = dao;
    }
    
    public boolean login(String user, String pass){
        boolean b = false;
        this.dao = (UsuariosDAO) new UsuariosDAO();
        this.entity = new Usuarios();
        this.dao.iniciaOperacion();
        this.entities = dao.login(user,pass);
        this.dao.cerrarSession();
        if(this.entities.size() == 1){
            b = true;
            //this.entity = this.entities.get(0);
            this.entity.setAreas(this.entities.get(0).getAreas());
            this.entity.setId(this.entities.get(0).getId());
            this.entity.setNombre(this.entities.get(0).getNombre());
            this.entity.setPassword(this.entities.get(0).getPassword());
            this.entity.setUser(this.entities.get(0).getUser());
            this.entity.setSedes(this.entities.get(0).getSedes());
        }
        
        return b;
    }
    
    public String newPassword(){
        String res = SUCCESS;
        if(passwordNueva.equals(passwordNueva2)){
            Usuarios user = (Usuarios) sesion.getAttribute("user");
            if(user.getPassword().equals(password)){
                user.setPassword(passwordNueva);
                UsuariosDAO dao = new UsuariosDAO();
                dao.iniciaOperacion();
                boolean b = dao.update(user);
                dao.cerrarSession();
                if(b){
                    addActionMessage("La contraseña fue actualizada con éxito");
                    res = SUCCESS;
                }else{
                    addActionError("Ocurrió un error.");
                    res = ERROR;
                }
            }else{
                addActionError("La contraseña introducida no es igual a la registrada");
                res = ERROR;
            }
        }else{
            addActionError("Las contraseñas nueva y repetida deben ser iguales");
            res = ERROR;
        }
        return res;
    }
    
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = this.request.getSession();
    }
}