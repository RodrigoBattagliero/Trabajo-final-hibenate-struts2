<%-- 
    Document   : expedienteDetalle
    Created on : 17-Jun-2015, 23:46:08
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
        <title></title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            
            <table class="table table-striped">
                <thead>
                    <tr>
                        <td>N° solicitud</td>
                        <td>Docente</td>
                        <td>Fecha inicio</td>
                        <td>Importe declarado</td>
                        <td>Importe reconocido</td>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="solicitudes" var="sol">
                        <tr>
                            <td><s:property value="#sol.solicitudes.numeroSolicitud" /></td>
                            <td><s:property value="#sol.solicitudes.docenteses[0].apellido" />,<s:property value="#sol.docenteses[0].nombre" /></td>
                            <td><s:property value="#sol.solicitudes.fechaAlta" /></td>
                            <td><s:property value="#sol.solicitudes.liquidacioneses[0]reconocimientoImporteDeclarado" /></td>
                            <td><s:property value="#sol.solicitudes.liquidacioneses[0].reconocimientoImporteTotal" /></td>
                        </tr>
                    </s:iterator>
                        <tr>
                            <td>
                                <s:a action="ConfirmarTodos">Confirmar todos</s:a>
                            </td>
                        </tr>
                </tbody>
            </table>
            
            <s:include value="partes/footer.jsp" />
        </div>
    </body>
</html>
