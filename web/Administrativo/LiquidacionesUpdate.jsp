<%-- 
    Document   : RendicionDeCuentasForm
    Created on : 25-May-2015, 23:10:22
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
        <h1>Rendicion de cuentas</h1>
        <hr />
        <s:form action="LiquidacionesUpdate">
            <s:textfield name="entity.id" label="Id" />
            <s:textfield name="entity.reconocimientoGastoComida" label="reconocimientoGastoComida" />
            <s:textfield name="entity.reconocimientoGastoAlojamiento" label="reconocimientoGastoAlojamiento" />
            <s:textfield name="entity.reconocimientoGastoCombustible" label="reconocimientoGastoCombustible" />
            <s:textfield name="entity.reconocimientoGastoPasajes" label="reconocimientoGastoPasajes" />
            <s:textfield name="entity.importeDeclarado" label="importeDeclarado" />
            <s:textfield name="entity.reconocimientoImporteTotal" label="reconocimientoImporteTotal" />
            <s:textarea name="entity.observaciones" label="observaciones" />
            
            <s:submit name="Guardar" value="Guardar" /> 
        </s:form>
    </body>
</html>
