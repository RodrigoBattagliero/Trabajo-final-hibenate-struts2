/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.AreasDAO;
import model.entities.Areas;

/**
 *
 * @author rodrigo
 */
public class AreasController extends Controller<Areas> {

    public AreasController() {
        dao = (AreasDAO) new AreasDAO();
    }
    
    @Override
    public AreasDAO getDao() {
        return (AreasDAO) dao;
    }

    public void setDao(AreasDAO dao) {
        this.dao = dao;
    }
    
    
    
    /*
    AreasDAO areasDAO;
    Areas area;
    ArrayList<Areas> areas;
    
    public AreasController(){
        areasDAO = new AreasDAO();
        area = null;
        areas = null;
    }
    
    public Areas getModel(){
        return area;
    }
    
    public String select(int id){
        String res = SUCCESS;
        area = (Areas) areasDAO.selectOne(id);
        return res;
    }
    
    public String select(){
        String res = SUCCESS;
        areas = (ArrayList) areasDAO.selectAll();
        return res;
    }

    public Areas getArea() {
        return area;
    }

    public void setArea(Areas area) {
        this.area = area;
    }

    public ArrayList<Areas> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Areas> areas) {
        this.areas = areas;
    }
    
    public String update(Areas a){
        String res = ERROR;
        if(areasDAO.update(a))
            res = SUCCESS;
        return res;
    }
    
    public String delete(int id){
        String res = ERROR;
        if(areasDAO.delete(id))
            res = SUCCESS;
        return res;
    }
    */
}   
