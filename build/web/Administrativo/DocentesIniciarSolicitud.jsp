<%-- 
    Document   : DocentesInciarSolicitud
    Created on : May 16, 2015, 3:54:42 AM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../js/datetimepicker-master/jquery.datetimepicker.css"/>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li><a href="#">Iniciar solicitud</a></li>
                <li class="active">Datos del docente</li>
            </ol>
            <h1 class="page-header">Buscar docente</h1>
            <s:form action="BuscarDocente" theme="simple">
                <label>DNI</label>
                <div class="form-group input-group">
                    <s:textfield name="entity.dni" label="DNI" class="form-control"  />
                    <span class="input-group-btn" >
                        <button class="btn btn-default" type="submit">
                            <i class="fa fa-search"></i>
                        </button>
                    </span>
                </div>
            </s:form>
            
            <s:if test="entity.id" >
                <h1 class="page-header">Datos docentes</h1>
                <s:set var="url">DocentesUpdate</s:set>
            </s:if>
            <s:else>
                <h1 class="page-header">Datos docentes</h1>
                <s:set var="url">DatosDocentesPreparar</s:set>
            </s:else>
                
            <s:fielderror />
            <s:actionerror />
            <s:form action="%{url}" theme="simple">
                <div class="form-group">
                    <label>Departamento acádemico</label>
                    <s:select name="idDeptoAcademico" label="Departamento acádemico" list="listDeptosAcademicos" listKey="id" listValue="nombre" value="entity.departamentosAcademicos.id" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Nombre</label>
                    <s:textfield name="entity.nombre" label="Nombre" class="form-control"  />
                </div>
                <div class="form-group">
                    <label>Apellido</label>
                    <s:textfield name="entity.apellido" label="Apellido" class="form-control" />
                </div>
                <div class="form-group">
                    <label>DNI</label>
                    <s:textfield name="entity.dni" label="DNI" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Telefono</label>
                    <s:textfield name="entity.telefono" label="Telefono" class="form-control"  />
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <s:textfield name="entity.email" label="Email"  class="form-control" />
                </div>
                <div class="form-group">
                    <label>Lugar de residencia</label>
                    <s:textfield name="entity.lugarResidencia" label="Lugar de residencia" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Motivo Comisión</label>
                    <s:select name="entity.motivoComision" label="Motivo Comision" list="listMotivoComision" value="entity.motivoComision" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Fecha de inicio</label>
                    <s:textfield name="entity.fechaInicio" label="Fecha de inicio" class="form-control fecha"  />
                </div>
                <div class="form-group">
                    <label>Fecha finalización</label>
                    <s:textfield name="entity.fechaFinalizacion" label="Fecha finalización" class="form-control fecha" />
                </div>
                <div class="form-group">
                    <label>Observaciones</label>
                    <s:textarea name="entity.observaciones" label="Observaciones" class="form-control" />
                </div>
                <div class="form-group">
                    <label></label>
                    <s:submit value="Guardar" class="btn btn-primary" />
                </div>
            </s:form>
            
            <%@include file="partes/footer.jsp" %>
        </div>
            
        <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y'});
        </script>
    </body>
</html>
