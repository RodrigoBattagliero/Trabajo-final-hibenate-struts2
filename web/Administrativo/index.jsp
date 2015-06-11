<%-- 
    Document   : menu
    Created on : May 16, 2015, 3:59:00 AM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" cont    ent="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
		
            <s:include value="partes/menu.jsp" />
        
            <s:if test="cantidadSolicitudesCompletar != 0">
                <div class="alert alert-warning" role="alert">
		    <p>Tiene <s:property value="cantidadSolicitudesCompletar" /> <s:a action="DatosCompletarForm">solicitudes para completar</s:a></p>
		</div>
            </s:if>
            <s:if test="cantidadSolicitudesDevueltas != 0">
                <div class="alert alert-warning" role="alert">
                    <p>Tiene <s:property value="cantidadSolicitudesDevueltas" /> <s:a action="SolicitudesDevueltasForm">solicitudes devueltas</s:a></p>
                </div>    
            </s:if>
            <s:if test="cantidadSolicitudesConfirmar != 0">
                <div class="alert alert-warning" role="alert">
                    <p>Tiene <s:property value="cantidadSolicitudesConfirmar" /> <s:a action="ConfirmarSolicitudesForm">solicitudes para confirmar</s:a></p>
                </div>    
            </s:if>
        </div>
    </body>
</html>
