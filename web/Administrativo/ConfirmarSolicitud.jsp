<%-- 
    Document   : ConfirmarSolicitud
    Created on : May 16, 2015, 7:00:09 AM
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
            <h1 class="page-header">Confirmar Solicitud</h1>
            <s:a action="IniciarSolcitud" class="btn">Guardar</s:a>
            <s:a action="index" class="btn">Cancelar</s:a>
            
            <%@include file="partes/footer.jsp" %>
        </div>
    </body>
</html>
