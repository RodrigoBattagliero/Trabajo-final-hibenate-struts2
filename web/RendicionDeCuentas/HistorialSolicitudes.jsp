<%-- 
    Document   : historialSolicitudes
    Created on : 03-Jun-2015, 00:30:17
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rendición de cuentas</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css" />
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li class="active">Historial del solicitudes procesadas</li>
            </ol>
            <h1 class="page-header">Solicitudes</h1>
            <table class="table table-bordered table-hover">
                <thead>
                    <tr class="success">
                        <th>N solicitud</th>
                        <th>Docente</th>
                        <th>Fecha de presentación</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="entities" var="list">
                        <tr >
                            <td><s:property value="#list[0].solicitudes.numeroSolicitud" /></td>
                            <td><s:property value="#list[1].nombre" /></td>
                            <td><s:property value="#list[0].solicitudes.fechaAlta" /></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
            
            <s:include value="partes/footer.jsp" />
        </div>
    </body>
</html>
