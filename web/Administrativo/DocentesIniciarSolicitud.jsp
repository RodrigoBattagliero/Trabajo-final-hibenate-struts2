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
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <s:if test="entity.id" >
                <h1>Datos docentes</h1>
                <s:set var="url">DocentesUpdate</s:set>
            </s:if>
            <s:else>
                <h1>Datos docentes</h1>
                <s:set var="url">DatosDocentesPreparar</s:set>
            </s:else>
            <s:form action="%{url}">
                <s:fielderror />
                <s:actionerror />
                <s:select name="idDeptoAcademico" label="Departamento acádemico" list="listDeptosAcademicos" listKey="id" listValue="nombre" value="entity.departamentosAcademicos.id" />
                <s:textfield name="entity.nombre" label="Nombre"  />
                <s:textfield name="entity.apellido" label="Apellido"  />
                <s:textfield name="entity.dni" label="DNI"  />
                <s:textfield name="entity.telefono" label="Telefono"  />
                <s:textfield name="entity.email" label="Email"  />
                <s:textfield name="entity.lugarResidencia" label="Lugar de residencia" />
                <s:select name="entity.motivoComision" label="Motivo Comision" list="listMotivoComision" value="entity.motivoComision" />
                <s:textfield name="entity.fechaInicio" label="Fecha de inicio"  />
                <s:textfield name="entity.fechaFinalizacion" label="Fecha finalización"/>
                <s:textarea name="entity.observaciones" label="Observaciones"  />
                <s:submit value="Guardar" class="btn" />
            </s:form>
        </div>
    </body>
</html>
