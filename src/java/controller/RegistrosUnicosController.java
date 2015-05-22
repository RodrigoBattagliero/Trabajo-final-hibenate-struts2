/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.RegistrosUnicosDAO;
import model.entities.Areas;
import model.entities.Estados;
import model.entities.RegistrosUnicos;
import model.entities.Solicitudes;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author rodrigo
 */
public class RegistrosUnicosController extends Controller<RegistrosUnicos> implements ServletRequestAware, ParameterAware {
    
    private HttpServletRequest request;
    private HttpSession sesion;
    private int idAreaSelected;
    private int idEstadoSelected;
    private List<Estados> listEstados;
    private List<Solicitudes> listSolicitudesACompletar;
    private RegistrosUnicosDAO dao;
    private Areas areaLogueada;
    private int idSolicitudSelected;
    private String AdministrarObservaciones;
    private boolean[] confirmado;
    private int[] idRegistro;
    private Map<String, String[]> parametros;
    
    public RegistrosUnicosController() {
        dao = (RegistrosUnicosDAO) new RegistrosUnicosDAO();
        this.entity = new RegistrosUnicos();
        selectEstados();
    }
    
    @Override
    public RegistrosUnicosDAO getDao() {
        return (RegistrosUnicosDAO) dao;
    }
    
    public List<Estados> getListEstados(){
        return this.listEstados;
    }
    
    public void setAdministrarObservaciones(String AdministrarObservaciones) {
        this.AdministrarObservaciones = AdministrarObservaciones;
    }
    
    public List<Solicitudes> getListSolicitudesACompletar() {
        return listSolicitudesACompletar;
    }

    public Areas getAreaLogueada() {
        return areaLogueada;
    }
    
    public void setDao(RegistrosUnicosDAO dao) {
        this.dao = dao;
    }
    
    public void setIdAreaSelected(int id){
        this.idAreaSelected = id;
    }
    
    
    public void setIdEstadoSelected(int id){
        this.idEstadoSelected = id;
    }
    
    public void setIdSolicitudSelected(int id){
        this.idSolicitudSelected = id;
    }

    public void setConfirmado(boolean[] confirmado) {
        this.confirmado = confirmado;
    }

    public void setIdRegistro(int[] idRegistro) {
        this.idRegistro = idRegistro;
    }
    
    public String setSolicitudesACompletar(){
        setAreaLogueada();
        dao.iniciaOperacion();
        listSolicitudesACompletar = (List) this.dao.selectACompletar(areaLogueada);
        dao.cerrarSession();
        return SUCCESS;
    }
    
    public void setAreaLogueada(){
        AreasController ar = new AreasController();
        ar.selectOne(1);
        areaLogueada = ar.getEntity();
    }
    
    public String setAdministrarRegistroUnicoForm(){
        setAreaLogueada();
        selectEstados();
        dao.iniciaOperacion();
        String idSol = String.valueOf(sesion.getAttribute("idSolicitudSelected"));
        this.entity = this.dao.selectRegistroUnicoAdministrar(getAreaLogueada(),Integer.parseInt(idSol));
        this.entity.setFechaSalida(new Date());
        dao.cerrarSession();
        return SUCCESS;
    }
    
    public String setRegistrosAConfirmar(){
        String res = SUCCESS;
        setAreaLogueada();
        this.dao.iniciaOperacion();
        this.entities = (List<RegistrosUnicos>) this.dao.selectAConfirmar(getAreaLogueada());
        sesion.setAttribute("RegistrosAConfirmar", this.entities);
        this.dao.cerrarSession();
        return res;
    }
    
    public String setSolicitudesInciadasSedeInterior(){
        dao.iniciaOperacion();
        listSolicitudesACompletar = (List) this.dao.selectIniciadaSedeInterior();
        dao.cerrarSession();
        return SUCCESS;
    }
    
    public String setSolicitudesActividadDocente(){
        dao.iniciaOperacion();
        listSolicitudesACompletar = (List) this.dao.selectActividadACompletar();
        dao.cerrarSession();
        return SUCCESS;
    }
    
    public String crear(){
        this.entity.setEstados(getEstado());
        this.entity.setAreas(getArea());
        this.entity.setConfirmado(true);
        sesion.setAttribute("RegistroUnicoForm", this.entity);
        this.idAreaSelected = 1;
        RegistrosUnicos re = new RegistrosUnicos();
        re.setAreas(getArea());
        re.setFechaEntrada(new Date());
        this.idEstadoSelected = 1;
        re.setEstados(getEstado());
        re.setConfirmado(Boolean.FALSE);
        re.setObservaciones("");
        sesion.setAttribute("RegistroUnicoProximo",re );
        return SUCCESS;
    }
    
    public String prepared(){
        setAreaLogueada();
        setAdministrarRegistroUnicoForm();
        this.entity.setAreas(getAreaLogueada());
        this.entity.setEstados(getEstado());
        this.entity.setConfirmado(false);
        this.entity.setFechaSalida(new Date());
        this.entity.setObservaciones(AdministrarObservaciones);
        
        this.idEstadoSelected = 1;
                this.entity = new RegistrosUnicos();
                this.entity.setAreas(getProximaArea());
                this.entity.setConfirmado(Boolean.FALSE);
                this.entity.setEstados(getEstado());
                this.entity.setFechaEntrada(new Date());
                this.entity.setFechaSalida(null);
                List<RegistrosUnicos> listRegistros = new ArrayList();
                listRegistros.add(this.entity);
        sesion.setAttribute("RegistrosNuevosForm", listRegistros);      
        sesion.setAttribute("RegistroUnicoForm", this.entity);
        return SUCCESS;
    }
    
    public String ConfirmarSolicitudesPrepared(){
        String res = SUCCESS;
        int cant = parametros.size();
        Iterator it = parametros.keySet().iterator();
        this.entities = new ArrayList();
        List<RegistrosUnicos> listRegistros = new ArrayList();
        this.dao = new RegistrosUnicosDAO();
        while(it.hasNext()){
            String key = (String) it.next();
            String value[] = parametros.get(key);
            if(value[0].equals("true")){
                this.dao.iniciaOperacion();
                this.entity = (RegistrosUnicos) this.dao.selectOne(Integer.parseInt(key));
                this.dao.cerrarSession();
                this.entity.setConfirmado(Boolean.TRUE);
                this.entities.add(this.entity);
                
                this.idEstadoSelected = 1;
                this.entity = new RegistrosUnicos();
                this.entity.setAreas(getProximaArea());
                this.entity.setConfirmado(Boolean.FALSE);
                this.entity.setEstados(getEstado());
                this.entity.setFechaEntrada(new Date());
                this.entity.setFechaSalida(null);
                listRegistros.add(this.entity);
            }
                
        }
        sesion.setAttribute("RegistrosConfirmarForm", this.entities);
        sesion.setAttribute("RegistrosNuevosForm", listRegistros);
        return res; 
    }
    
    public String CompletarDatosCapital(){
        this.sesion.setAttribute("idSolicitudSelected", this.request.getParameter("idSolicitudSelected"));
        return SUCCESS;
    }
    
    private Estados getEstado(){
        EstadosController est = new EstadosController();
        est.selectOne(idEstadoSelected);
        return est.getEntity();
    }
    
    private Areas getArea(){
        AreasController ar = new AreasController();
        ar.selectOne(idAreaSelected);
        return ar.getEntity();
    }
    
    private Areas getProximaArea(){
        AreasController ar = new AreasController();
        ar.proximaArea(getAreaLogueada());
        return ar.getEntity();
        
    }
    
    private void selectEstados(){
        EstadosController es = new EstadosController();
        es.select();
        listEstados = es.getEntities();
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
        this.sesion = this.request.getSession();
    }

    @Override
    public void setParameters(Map<String, String[]> maps) {
        this.parametros = maps;
    }
    
    
}
