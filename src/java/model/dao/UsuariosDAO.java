/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.List;
import model.entities.Usuarios;

/**
 *
 * @author rodrigo
 */
public class UsuariosDAO extends DAO {

    public UsuariosDAO() {
        tableName = "Usuarios";
    }
    
    @Override
    public void iniciaOperacion(){
        super.iniciaOperacion();
    }
    
    @Override
    public void cerrarSession(){
        super.cerrarSession();
    }
    
    @Override
    public List<Usuarios> login(String user, String pass){
        List<Usuarios> users;
        String sql = "FROM "+tableName+" AS u INNER JOIN FETCH u.areas WHERE u.user = '"+user+"' AND u.password = '"+pass+"'";
        users = sesion.createQuery(sql).list();
        return users;
    }
    
}
