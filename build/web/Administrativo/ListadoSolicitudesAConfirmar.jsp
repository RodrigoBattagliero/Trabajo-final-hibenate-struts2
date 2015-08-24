<%-- 
    Document   : ListadoSolicitudesAConfirmar
    Created on : May 20, 2015, 11:21:13 PM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>Administrativo</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li><a href="#">Solicitudes</a></li>
                <li class="active">Solicitudes a confirmar</li>
            </ol>
            <div class="row">
               <div class="col-sm-12">
                    <h1 class="page-header">Confirmar solicitudes</h1>
                    <s:form action="ConfirmarSolicitudesPrepared" theme="simple" method="post">
                    <table class="table table-bordered table-hover">
                    <thead>
                        <tr class="success">
                            <th>Numero de solicitud</th>
                            <th>Docente</th>
                            <th>Fecha de presentacion</th>
                            <th>Estado</th>
                            <th>Confirmado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.RegistrosAConfirmar" var="list">
                            <tr>
                                <td><s:property value="#list[2].numeroSolicitud" /></td>
                                <td><s:property value="#list[3].apellido" />, <s:property value="#list[3].nombre" /></td>
                                <td><s:property value="#list[2].fechaAlta" /></td>
                                <td><s:property value="#list[1].nombre" /></td>
                                <td>
                                    <s:checkbox name="%{#list[0].id}" value="true" />
                                </td>
                            </tr>
                        </s:iterator>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="5"><s:submit value="Confirmar" class="btn btn-primary" /></td>
                            </tr>
                        </tfoot>
                       </table>
                    </s:form>
                </div>
            </div>
            
            <%@include file="partes/footer.jsp" %>
        </div>
    </body>
</html>
