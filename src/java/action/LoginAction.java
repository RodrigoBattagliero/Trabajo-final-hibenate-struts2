/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import controller.UsuariosController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class LoginAction extends ActionSupport implements ServletRequestAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    
    private String user;
    private String password;

    public LoginAction() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String execute(){
        String res = ERROR;
        
        UsuariosController userController = new UsuariosController();
        if(userController.login(getUser(), getPassword())){
            sesion.setAttribute("user", userController.getEntity());
            sesion.setAttribute("authenticated", true);
            if(userController.getEntity().getUser().equals("administrador"))
                sesion.setAttribute("role", "administrador");
            else
                sesion.setAttribute("role", userController.getEntity().getAreas().getNombre().toLowerCase());
            res = String.valueOf(this.sesion.getAttribute("role"));
        }
        
        return res;
    }
    
    public String logout(){
        this.sesion.invalidate();
        return SUCCESS;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        sesion = this.request.getSession();
    }
    
}
