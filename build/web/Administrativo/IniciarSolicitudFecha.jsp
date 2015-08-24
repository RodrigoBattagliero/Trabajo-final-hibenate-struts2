<%-- 
    Document   : menu
    Created on : May 16, 2015, 3:59:00 AM
    Author     : rodrigo
--%>

<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" cont    ent="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>Administrativo</title>
    </head>
    <body>
        <div class="container">
            
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li ><a href="#">Inicio</a></li>
                <li class="active">Error</li>
            </ol>
            <div class="row">
                <div class="col-sm-12">
                    <p class="bg-warning">
                        La fecha de presentación ingresada supera los 30 (treinta) días. ¿Desea continuar?
                        <br />
                        <strong>Nota: Para continuar agregue una observación.</strong>
                        <br />
                        <s:a action="DatosDocentesForm" cssClass="btn btn-primary">Continuar</s:a>
                        <s:a action="IniciarSolicitudForm" cssClass="btn btn-danger">Cancelar</s:a>
                    </p>
                </div>
            </div>
            <%@include file="partes/footer.jsp" %>
        </div>
    </body>
</html>