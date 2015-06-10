<%-- 
    Document   : RegistroUnicoInicarSolicitud
    Created on : May 17, 2015, 12:28:05 AM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <h1>Registro único</h1>
            <s:form action="RegistroUnicoPrepare">
                <s:textfield name="entity.fechaEntrada" label="Fecha de entrada" />
                <tr>
                    <td><label for="RegistroUnicoPrepare_idAreaSelected">Area</label></td>
                    <td>
                        <select name="idAreaSelected" id="RegistroUnicoPrepare_idAreaSelected">
                            <option value="2">Profesor</option>
                        </select>
                    </td>
                </tr>
                <s:select list="listEstados" listKey="id" listValue="nombre" name="idEstadoSelected" label="Estado" />
                <s:checkbox name="entity.confirmado" label="Confirmado" value="true" />
                <s:textarea name="entity.observaciones" label="Observaciones" />
                <s:submit value="Guardar" class="btn" />
            </s:form>
        </div>
    </body>
        
    

