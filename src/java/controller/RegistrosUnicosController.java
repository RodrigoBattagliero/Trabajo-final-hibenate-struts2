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
import model.entities.Comprobantes;
import model.entities.ComprobantesComidaAlojamientos;
import model.entities.ComprobantesTraslados;
import model.entities.Estados;
import model.entities.RegistrosUnicos;
import model.entities.Solicitudes;
import model.entities.Usuarios;
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
    
    // Variables para consultar registro
    private String nombreDocente;
    private String apellidoDocente;
    private Date fechaDePresentacion;
    
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

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getApellidoDocente() {
        return apellidoDocente;
    }

    public void setApellidoDocente(String apellidoDocente) {
        this.apellidoDocente = apellidoDocente;
    }

    public Date getFechaDePresentacion() {
        return fechaDePresentacion;
    }

    public void setFechaDePresentacion(Date fechaDePresentacion) {
        this.fechaDePresentacion = fechaDePresentacion;
    }
    
    public String setSolicitudesACompletar(){
        setAreaLogueada();
        dao.iniciaOperacion();
        listSolicitudesACompletar = (List) this.dao.selectACompletar(areaLogueada);
        dao.cerrarSession();
        return SUCCESS;
    }
    
    public void setAreaLogueada(){
        Usuarios us = (Usuarios) this.sesion.getAttribute("user");
        areaLogueada = us.getAreas();
    }
    
    public String setAdministrarRegistroUnicoForm(){
        String idSol;
        setAreaLogueada();
        selectEstados();
        dao.iniciaOperacion();
        idSol = String.valueOf(sesion.getAttribute("idSolicitudSelected"));
        
        this.entity = this.dao.selectRegistroUnicoAdministrar(getAreaLogueada(),Integer.parseInt(idSol));
        this.entity.setFechaSalida(new Date());
        dao.cerrarSession();
        return SUCCESS;
    }
    
    public String setAdministrarRegistroUnicoActividadDocente(){
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
    
    public String setRegistrosExpediente(){
        String res = SUCCESS;
        setAreaLogueada();
        this.dao.iniciaOperacion();
        this.entities = (List<RegistrosUnicos>) this.dao.selectExpedientes(getAreaLogueada());
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
    
    public String setAdministrarRegistroUnicoSAFForm(){
        //Usuarios user = (Usuarios) sesion.getAttribute("user");
        String idSol = String.valueOf(this.request.getParameter("idSolicitudSelected"));
        sesion.setAttribute("idSolicitudSelected",idSol );
        
        setAreaLogueada();
        selectEstados();
        dao.iniciaOperacion();
        
        this.entity = this.dao.selectRegistroUnicoAdministrar(getAreaLogueada(),Integer.parseInt(idSol));
        this.entity.setFechaSalida(new Date());
        dao.cerrarSession();
        return SUCCESS;
    }
    
    public String setHistorialSolicutdes(){
        
        setAreaLogueada();
        this.dao.iniciaOperacion();
        this.entities = this.dao.selectHistorial(getAreaLogueada());
        this.dao.cerrarSession();
        return SUCCESS;
    }
    
    public String consultarRegistro(){
        this.dao.iniciaOperacion();
        this.entities = this.dao.consultarRegistro(this.nombreDocente,this.apellidoDocente,this.fechaDePresentacion);
        this.dao.cerrarSession();
        return SUCCESS;
    }
    
    public String consultarDetalle(){
        String idSolstr = this.request.getParameter("idSolicitudSelected");
        int idSol = Integer.parseInt(idSolstr);
        // Solicitud
        SolicitudesController solController = new SolicitudesController();
        solController.selectOne(idSol);
        // Sedes
        SedesController sedController = new SedesController();
        sedController.selectOne(solController.getEntity().getSedes().getId());
        // RegistroUnico
        this.dao.iniciaOperacion();
        this.entities = this.dao.select(idSol);
        this.dao.cerrarSession();
        // Docente
        DocentesController docController = new DocentesController();
        docController.selectRelatedWithDepto(idSol);
        // Comprobantes
        ComprobantesController comController = new ComprobantesController();
        comController.selectRelated(idSol);
        // Traslado 
        ComprobantesTrasladosController trasController;
        // Alojamiento
        ComprobantesComidaAlojamientoController alojController;
        // Lista de comprobantes
        List<ComprobantesTraslados> comTraslado = new ArrayList();
        List<ComprobantesComidaAlojamientos> comAloja = new ArrayList();
        for(Comprobantes c : comController.getEntities() ){
            trasController = new ComprobantesTrasladosController();
            trasController.selectRelated(c.getId());
            if(trasController.getEntities() != null && !trasController.getEntities().isEmpty()){
                comTraslado.add(trasController.getEntities().get(0));
            }else{
                alojController = new ComprobantesComidaAlojamientoController();
                alojController.selectRelated(c.getId());
                if(alojController.getEntities() != null && !alojController.getEntities().isEmpty())
                    comAloja.add(alojController.getEntities().get(0));
            }
        }
        // Designaciones y actividades docentes
        DesignacionesController degController = new DesignacionesController();
        degController.selectRelatedAll(idSol);
        // Liquidacion
        LiquidacionesController liqController = new LiquidacionesController();
        liqController.selectRelated(idSol);
        this.sesion.setAttribute("Solicitudes", solController.getEntity());
        this.sesion.setAttribute("Sedes", sedController.getEntity());
        this.sesion.setAttribute("Docentes", docController.getEntities());
        this.sesion.setAttribute("Traslados", comTraslado);
        this.sesion.setAttribute("Alojamientos", comAloja);
        this.sesion.setAttribute("Designaciones", degController.getEntities());
        this.sesion.setAttribute("Liquidaciones", liqController.getEntities());
        this.sesion.setAttribute("RegistroUnico", this.entities);
        return SUCCESS;
    }
    
    public String crearPrepared(){
        Solicitudes sl = (Solicitudes) sesion.getAttribute("SolicitudesForm");
        this.entity.setFechaEntrada(sl.getFechaAlta());
        return SUCCESS;
    }
    
    public String crear(){
        
        this.entity.setEstados(getEstado());
        this.entity.setAreas(getArea());
        this.entity.setConfirmado(true);
        this.entity.setFechaSalida(this.entity.getFechaEntrada());
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

        sesion.setAttribute("RegistroUnicoForm", this.entity);
        return SUCCESS;
    }
    
    public String preparedActividadDocente(){
        setAreaLogueada();
        setAdministrarRegistroUnicoForm();
        this.entity.setAreas(getAreaLogueada());
        this.entity.setEstados(getEstado());
        this.entity.setConfirmado(false);
        this.entity.setFechaSalida(new Date());
        this.entity.setObservaciones(AdministrarObservaciones);
 
        sesion.setAttribute("RegistroUnicoForm", this.entity);
        return SUCCESS;
    }
    
    public String ConfirmarSolicitudesPrepared(){
        String res = SUCCESS;
        Usuarios user = (Usuarios) sesion.getAttribute("user");
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
                this.entity = (RegistrosUnicos) this.dao.selectOneWithSolicitud(Integer.parseInt(key));
                this.dao.cerrarSession();
                this.entity.setConfirmado(Boolean.TRUE);
                this.entities.add(this.entity);
                
                // Si es distinto de SAF agrega el registro para el area siguiente. Sino no, porque tiene qeu conformarse el expediente.
                if(user.getAreas().getId() != 6){
                    this.idEstadoSelected = 1;
                    RegistrosUnicos rNuevo = new RegistrosUnicos();
                    rNuevo.setAreas(getProximaArea());
                    rNuevo.setConfirmado(Boolean.FALSE);
                    rNuevo.setEstados(getEstado());
                    rNuevo.setFechaEntrada(new Date());
                    rNuevo.setFechaSalida(null);
                    listRegistros.add(rNuevo);
                }
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
        setAreaLogueada();
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
