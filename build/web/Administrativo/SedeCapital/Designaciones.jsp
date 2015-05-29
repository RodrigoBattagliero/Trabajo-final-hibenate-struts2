<%-- 
    Document   : datosACompletar
    Created on : May 19, 2015, 5:11:41 PM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <hr />
        <s:fielderror />
        <s:actionerror />
        <s:form action="designacionPrepared">
            <s:textfield name="numeroResolucion" label="Número de resolución" />
            <s:textfield name="categoria" label="Categoria" />
            <s:textfield name="desde" label="Desde" />
            <s:textfield name="hasta" label="Hasta" />
            <s:textfield name="dedicacion" label="Dedicacion" />
            <s:textarea name="observaciones" label="observaciones" />
            
            <s:textfield name="numeroResolucion" label="Número de resolución" />
            <s:textfield name="categoria" label="Categoria" />
            <s:textfield name="desde" label="Desde" />
            <s:textfield name="hasta" label="Hasta" />
            <s:textfield name="dedicacion" label="Dedicacion" />
            <s:textarea name="observaciones" label="observaciones" />
            
            <s:submit value="Guardar" />
        </s:form>
    </body>
</html>
