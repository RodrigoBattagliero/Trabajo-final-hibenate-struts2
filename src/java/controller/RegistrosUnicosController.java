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
import model.entities.Docentes;
import model.entities.Estados;
import model.entities.Liquidaciones;
import model.entities.RegistrosUnicos;
import model.entities.Solicitudes;
import model.entities.Usuarios;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import resources.SesionRemove;

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
    private List<Areas> listAreas;
    private List<Solicitudes> listSolicitudesACompletar;
    private RegistrosUnicosDAO dao;
    private Areas areaLogueada;
    private int idSolicitudSelected;
    private int idRegistroUnico;
    private String AdministrarObservaciones;
    private boolean[] confirmado;
    private int[] idRegistro;
    private int cantidadSolicitudesDevueltas;
    private int cantidadSolicitudesCompletar;
    private int cantidadSolicitudesConfirmar;
    private Map<String, String[]> parametros;
    private List<Object> historial;
    private List<Object> consulta;
    private List<Object> devueltas;
    
    // Variables para consultar registro
    private String nombreDocente;
    private String apellidoDocente;
    private Date fechaDePresentacion;
    private int numeroSol;
    private String dni;
    
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

    public List<Areas> getListAreas() {
        return listAreas;
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

    public void setIdRegistroUnico(int idRegistroUnico) {
        this.idRegistroUnico = idRegistroUnico;
    }

    public void setConfirmado(boolean[] confirmado) {
        this.confirmado = confirmado;
    }

    public void setIdRegistro(int[] idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getCantidadSolicitudesDevueltas() {
        return cantidadSolicitudesDevueltas;
    }

    public int getCantidadSolicitudesCompletar() {
        return cantidadSolicitudesCompletar;
    }

    public int getCantidadSolicitudesConfirmar() {
        return cantidadSolicitudesConfirmar;
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

    public void setNumeroSol(int numeroSol) {
        this.numeroSol = numeroSol;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Object> getHistorial() {
        return historial;
    }

    public List<Object> getConsulta() {
        return consulta;
    }

    public List<Object> getDevueltas() {
        return devueltas;
    }
    
    public String setSolicitudesACompletar(){
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        
        setAreaLogueada();
        dao.iniciaOperacion();
        Usuarios user = new Usuarios();
        user = (Usuarios) this.sesion.getAttribute("user");
        listSolicitudesACompletar = (List) this.dao.selectACompletar(areaLogueada,user.getSedes());
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
        
        listEstados = new ArrayList();
        listEstados.add(new Estados(2, "Aprobado", null));
        listEstados.add(new Estados(3, "Rechazado", null));
        //listEstados.add(new Estados(4, "Imputable a la administración", null));
        return SUCCESS;
    }
    
    public String setAdministrarRegistroUnicoFormInterior(){
        String idSol;
        selectEstados();
        idAreaSelected = 4;
        idSol = String.valueOf(sesion.getAttribute("idSolicitudSelected"));
        dao.iniciaOperacion();
        this.entity = this.dao.selectRegistroUnicoAdministrar(this.getArea(),Integer.parseInt(idSol));
        dao.cerrarSession();
        this.entity.setFechaSalida(new Date());
        setAreaLogueada();
        this.entity.setAreas(getArea());
        
        listEstados = new ArrayList();
        listEstados.add(new Estados(2, "Aprobado", null));
        listEstados.add(new Estados(3, "Rechazado", null));
        return SUCCESS;
    }
    
    public String setAdministrarRegistroUnicoActividadDocente(){
        Usuarios user = (Usuarios) this.sesion.getAttribute("user");
        if(user.getAreas().getId() == 5)
            idAreaSelected = 5;
        else
            idAreaSelected = 4;
        
        selectEstados();
        String idSol = String.valueOf(sesion.getAttribute("idSolicitudSelected"));
        dao.iniciaOperacion();
        this.entity = this.dao.selectRegistroUnicoAdministrar(getArea(),Integer.parseInt(idSol));
        this.entity.setFechaSalida(new Date());
        this.areaLogueada = getArea();
        dao.cerrarSession();
        return SUCCESS;
    }
    
    public String setRegistrosAConfirmar(){
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        
        String res = SUCCESS;
        setAreaLogueada();
        Usuarios user = new Usuarios();
        user = (Usuarios) this.sesion.getAttribute("user");
        this.dao.iniciaOperacion();
        this.entities = (List<RegistrosUnicos>) this.dao.selectAConfirmar(getAreaLogueada(),user.getSedes());
        sesion.setAttribute("RegistrosAConfirmar", this.entities);
        this.dao.cerrarSession();
        return res;
    }
    
    public String setRegistrosAConfirmarActividadesInterior(){
        
            // Eliminar datos de sesion
            SesionRemove sR = new SesionRemove();
            sR.removeAllSession(this.sesion);
        
        String res = SUCCESS;
        idAreaSelected = 4;
        Usuarios user = new Usuarios();
        user = (Usuarios) this.sesion.getAttribute("user");
        this.dao.iniciaOperacion();
        this.entities = (List<RegistrosUnicos>) this.dao.selectAConfirmar(getArea(),user.getSedes());
        sesion.setAttribute("RegistrosAConfirmar", this.entities);
        this.dao.cerrarSession();
        return res;
    }
    
    public String setRegistrosExpediente(){
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        
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
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        
        Usuarios user = new Usuarios();
        user = (Usuarios) this.sesion.getAttribute("user");
        dao.iniciaOperacion();
        if(user.getSedes().getId() == 1)
            listSolicitudesACompletar = (List) this.dao.selectActividadACompletar();
        else
            listSolicitudesACompletar = (List) this.dao.selectActividadACompletarInterior(user.getSedes());
        dao.cerrarSession();
        return SUCCESS;
    }
    
    public String setAdministrarRegistroUnicoSAFForm(){
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
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        
        setAreaLogueada();
        Usuarios user = new Usuarios();
        this.historial = new ArrayList();
        user = (Usuarios) this.sesion.getAttribute("user");
        this.dao.iniciaOperacion();
        List<RegistrosUnicos> list = this.dao.selectHistorial(getAreaLogueada(),user.getSedes());
        this.dao.cerrarSession();
        for(RegistrosUnicos ru : list){
            Object a[][] = new Object[1][2];
            a[0][0] = ru;
            Iterator it = ru.getSolicitudes().getDocenteses().iterator();
            Docentes d = new Docentes();
            
            while(it.hasNext()){
                d = (Docentes) it.next();
            }
            a[0][1] = d;
            this.historial.add(a[0]);
        }
        return SUCCESS;
    }
    
    // Solicituds para la OGAGTD, para que las mande al area correspondiente.
    public String setSolicitudesDevueltas(){
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        this.devueltas = new ArrayList();
        this.dao.iniciaOperacion();
        List<RegistrosUnicos> list = this.dao.selectDevueltas();
        this.dao.cerrarSession();
        for(RegistrosUnicos ru : list){
            Object a[][] = new Object[1][2];
            a[0][0] = ru;
            Iterator it = ru.getSolicitudes().getDocenteses().iterator();
            Docentes d = new Docentes();
            while(it.hasNext()){
                 d = (Docentes) it.next();
            }
            a[0][1] = d;
            this.devueltas.add(a[0]);
        }
        return SUCCESS;
    }
    
    public String setSolicitudesDevueltasAreas(){
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        
        setAreaLogueada();
        if(getAreaLogueada().getId() == 1)
            this.sesion.setAttribute("action","1");
        this.dao.iniciaOperacion();
        this.entities = this.dao.selectDevueltasAreas(this.getAreaLogueada());
        this.dao.cerrarSession();
        
        return SUCCESS;
    }
    
    public String setRegistroUnicoSolicitudesDevueltas(){
        String idSolStr = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
        setAreaLogueada();
        this.dao.iniciaOperacion();
        this.entity = this.dao.selectRegistroDevueltasAreas(this.getAreaLogueada(),Integer.parseInt(idSolStr));
        this.dao.cerrarSession();
        this.sesion.setAttribute("idRegistroUnico", this.entity.getId());
        return SUCCESS;
    }
    
    public String setMensajes(){
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        
        setAreaLogueada();
        cantidadSolicitudesCompletar = 0;
        cantidadSolicitudesDevueltas = 0;
        cantidadSolicitudesConfirmar = 0;
        this.dao.iniciaOperacion();
        if(this.getAreaLogueada().getId() == 1)
            this.entities = this.dao.selectDevueltas();
        else
            this.entities = this.dao.selectDevueltasAreas(this.getAreaLogueada());
        cantidadSolicitudesDevueltas = this.entities.size();
        
        Usuarios user = new Usuarios();
        user = (Usuarios) this.sesion.getAttribute("user");
        // Agregar funciones para cantidad de solicitudes 
        if(this.getAreaLogueada().getId() == 3)
            listSolicitudesACompletar = (List) this.dao.selectACompletar(areaLogueada,user.getSedes());
        else
            listSolicitudesACompletar = (List) this.dao.selectACompletar(areaLogueada,user.getSedes());
        cantidadSolicitudesCompletar = listSolicitudesACompletar.size();
        this.setRegistrosAConfirmar();
        this.dao.iniciaOperacion();
        cantidadSolicitudesConfirmar = this.dao.selectAConfirmar(getAreaLogueada(),user.getSedes()).size();
        this.dao.cerrarSession();
        
        return SUCCESS;
    }
    
    public String consultarRegistro(){
        
        // Eliminar datos de sesion
        SesionRemove sR = new SesionRemove();
        sR.removeAllSession(this.sesion);
        this.consulta = new ArrayList();
        this.dao.iniciaOperacion();
        List<RegistrosUnicos> list = this.dao.consultarRegistro(this.nombreDocente,this.apellidoDocente,this.fechaDePresentacion,this.dni,this.numeroSol);
        this.dao.cerrarSession();
        for(RegistrosUnicos ru : list){
            Object a[][] = new Object[1][2];
            a[0][0] = ru;
            Iterator it = ru.getSolicitudes().getDocenteses().iterator();
            Docentes d = new Docentes();
            while(it.hasNext()){
                 d = (Docentes) it.next();
            }
            a[0][1] = d;
            this.consulta.add(a[0]);
        }
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
        listEstados = new ArrayList();
        listEstados.add(new Estados(2, "Aprobado", null));
        listEstados.add(new Estados(3, "Rechazado", null));
        return SUCCESS;
    }
    
    public String crear(){
        
        this.entity.setEstados(getEstado());
        idAreaSelected = 2;
        this.entity.setAreas(getArea());
        this.entity.setConfirmado(true);
        this.entity.setId(0);
        this.entity.setFechaSalida(this.entity.getFechaEntrada());
        sesion.setAttribute("RegistroUnicoForm", this.entity);
        
        // Si el estado es 'aprobado' sigue el circuito
        RegistrosUnicos re;
        if(this.entity.getEstados().getId() == 2){
            Usuarios user = new Usuarios();
            user = (Usuarios) this.sesion.getAttribute("user");
            this.idAreaSelected = user.getAreas().getId();
            re = new RegistrosUnicos();
            re.setAreas(getArea());
            re.setFechaEntrada(new Date());
            this.idEstadoSelected = 1;
            re.setEstados(getEstado());
            re.setConfirmado(Boolean.FALSE);
            re.setId(0);
            re.setObservaciones("");
            sesion.setAttribute("RegistroUnicoProximo",re );
        }else{
            re = new RegistrosUnicos();
        }
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
//        AreasController us = new AreasController();
//        us.selectOne(4);
        setAreaLogueada();
        if(getAreaLogueada().getId() == 3){
            setAdministrarRegistroUnicoFormInterior();
            idAreaSelected = 4;
            this.entity.setAreas(getArea());
        }else{
            setAdministrarRegistroUnicoForm();
            this.entity.setAreas(getAreaLogueada());
        }
        
        this.entity.setEstados(getEstado());
        this.entity.setConfirmado(false);
        this.entity.setFechaEntrada(new Date());
        this.entity.setObservaciones(AdministrarObservaciones);
 
        sesion.setAttribute("RegistroUnicoForm", this.entity);
        return SUCCESS;
    }
    
    public String setSolicitudDevuelta(){
        selectAreas();
        String idSolStr = this.request.getParameter("idSolicitudSelected");
        this.sesion.setAttribute("idSolicitudSelected",idSolStr);
        SolicitudesController solCont = new SolicitudesController();
        solCont.selectOne(Integer.parseInt(idSolStr));
        this.entity = new RegistrosUnicos();
        RegistrosUnicosDAO dao = new RegistrosUnicosDAO();
        this.dao.iniciaOperacion();
        this.entity = this.dao.selectDevueltas(solCont.getEntity());
        this.dao.cerrarSession();
        this.entity.setFechaSalida(new Date());
        return SUCCESS;
    }
    
    public String preparedSolicitudesDevueltas(){
        String idSolStr = String.valueOf(sesion.getAttribute("idSolicitudSelected"));
        SolicitudesController solCont = new SolicitudesController();
        solCont.selectOne(Integer.parseInt(idSolStr));
        setAreaLogueada();
        EstadosController es = new EstadosController();
        es.selectOne(8);
        
        RegistrosUnicosDAO dao = new RegistrosUnicosDAO();
        dao.iniciaOperacion();
        RegistrosUnicos aux = dao.selectDevueltas(solCont.getEntity());
        dao.cerrarSession();
        aux.setConfirmado(false);
        aux.setAreas(getAreaLogueada());
        aux.setEstados(es.getEntity());
        aux.setSolicitudes(solCont.getEntity());
        
        
        dao.iniciaOperacion();
        dao.update(aux);
        dao.cerrarSession();
        
        RegistrosUnicos ru = new RegistrosUnicos();
        ru.setAreas(getArea());
        ru.setConfirmado(false);
        es.selectOne(9);
        ru.setEstados(es.getEntity());
        sesion.setAttribute("RegistroUnicoForm", ru);
        return SUCCESS;
    }

    
    public String UpdateDevuelta(){
        String res = SUCCESS;
        String idRegStr = String.valueOf(this.sesion.getAttribute("idRegistroUnico"));
        String idSolStr = String.valueOf(this.sesion.getAttribute("idSolicitudSelected"));
        SolicitudesController solCont = new SolicitudesController();
        solCont.selectOne(Integer.parseInt(idSolStr));
        setAreaLogueada();
        // Si el estado es 'aprobado', se cambia el estado a 'Esperando confirmación de reintegro en área'
        // para control interno del registro unico. 
        if(getEstado().getId() == 2)
            this.idEstadoSelected = 10;
        else
            this.idEstadoSelected = 3;
        this.entity.setAreas(getAreaLogueada());
        this.entity.setEstados(getEstado());
        this.entity.setConfirmado(false);
        this.entity.setFechaEntrada(new Date());
        this.entity.setObservaciones(AdministrarObservaciones);
        this.entity.setId(Integer.parseInt(idRegStr));
        this.entity.setSolicitudes(solCont.getEntity());
        RegistrosUnicosDAO dao = new RegistrosUnicosDAO();
        dao.iniciaOperacion();
        if(!dao.update(this.entity))
            res = ERROR;
        dao.cerrarSession();
        return res;
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
                
                // Si el estado es 'Esperando confirmación de reintegro en área'
                // Le cambio el estado a 'Reintegrada' y la proxima area es SAF para continuar con el circuito 
                // de la solicitud devuelta (imputable a la administracion).
                if(this.entity.getEstados().getId() == 10){
                    setAreaLogueada();
                    this.idEstadoSelected = 8;
                    this.entity.setEstados(getEstado());
                    this.entity.setAreas(getAreaLogueada());
                    this.entity.setConfirmado(true);
                    this.entity.setFechaSalida(new Date());
                    this.entities.add(this.entity);
                    
                    this.idEstadoSelected = 1;
                    this.idAreaSelected = 6;
                    RegistrosUnicos rNuevo = new RegistrosUnicos();
                    rNuevo.setAreas(getArea());
                    rNuevo.setConfirmado(false);
                    rNuevo.setEstados(getEstado());
                    rNuevo.setFechaEntrada(new Date());
                    rNuevo.setFechaSalida(null);
                    listRegistros.add(rNuevo);
                }
                // Si el estado es "Reintegrada" busca el estado 
                // "Esperando reintegracion" para cambiarle el estado 
                // para ser procesada por el area correspondiente
                else if(this.entity.getEstados().getId() == 9){
                    this.idEstadoSelected = 8;
                    setAreaLogueada();
                    this.dao.iniciaOperacion();
                    List<RegistrosUnicos> list = dao.selectFromParametros(this.entity.getSolicitudes(), areaLogueada, getEstado());
                    this.dao.cerrarSession();
                    if(list.size() > 0){
                        list.get(0).setConfirmado(true);
                        list.get(0).setFechaSalida(new Date());
                        this.entities.add(list.get(0));
                    }else{
                        return ERROR;
                    }
                    this.idEstadoSelected = 8;
                    this.entity.setEstados(getEstado());
                    this.entity.setConfirmado(true);
                    this.entity.setFechaEntrada(new Date());
                    this.entities.add(this.entity);
                }else{
                    // Listado de registros para actualizar
                    this.entities.add(this.entity);
                }
                // Si el estado es "imputable a la administracion" agrego el campo para 
                if(this.entity.getEstados().getId() == 4){
                        
                    this.idEstadoSelected = 4;
                    this.idAreaSelected = 1;
                    RegistrosUnicos rNuevo = new RegistrosUnicos();
                    rNuevo.setAreas(getArea());
                    rNuevo.setConfirmado(false);
                    rNuevo.setEstados(getEstado());
                    rNuevo.setFechaEntrada(new Date());
                    rNuevo.setFechaSalida(null);
                    listRegistros.add(rNuevo);
                }
                // Si es distinto de SAF agrega el registro para el area siguiente. Sino no, porque tiene qeu conformarse el expediente.
                if(user.getAreas().getId() != 6 && this.entity.getEstados().getId() != 4 && this.entity.getEstados().getId() != 8 && this.entity.getEstados().getId() != 3 ){
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
    
    public String ConfirmarSolicitudesPreparedActividadInterior(){
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
                    this.idAreaSelected = 5;
                    rNuevo.setAreas(getArea());
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
    
    public String setSolicitudSelected(){
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
    
    private void selectAreas(){
        AreasController es = new AreasController();
        es.select();
        listAreas = es.getEntities();
    }
    
    public void selectFromParametros(Areas area,Estados estado){
        RegistrosUnicosDAO dao = new RegistrosUnicosDAO();
        dao.iniciaOperacion();
        this.entities = dao.selectFromParametros(area, estado);
        dao.cerrarSession();
    }
    public void selectFromParametros(Solicitudes sol, Areas area,Estados estado){
        RegistrosUnicosDAO dao = new RegistrosUnicosDAO();
        dao.iniciaOperacion();
        this.entities = dao.selectFromParametros(sol,area, estado);
        dao.cerrarSession();
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
    
    public boolean validar(){
        boolean b = true;
        if(this.entity.getAreas() == null){
            addFieldError("areas", "ERROR: no se definió el área");
            b = false;
        }
        if(this.entity.getEstados() == null){
            addFieldError("areas", "ERROR: no se definió el estado");
            b = false;
        }
        if(this.entity.getFechaEntrada() == null){
            addFieldError("areas", "ERROR: no se definió el área");
            b = false;
        }
        return b;
    }
    
}
