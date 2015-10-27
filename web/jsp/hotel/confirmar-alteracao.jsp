<%-- 
    Document   : confirmar-alteracao
    Created on : 02/04/2014, 10:16:20
    Author     : 31319238
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Confirmar edição</h1>
        <form method="POST" action="controller?opcao=hotel.editar">
            <label for="Codigo">Código:</label>
            <input type="text" size="6" readonly="true" name="codigo" value="${hotel.codigo}"/>
            <br/>
            <label for="Cidades">Cidades:</label>
            <select name="cidades">
                <option id="" value="">--selecione--</option>
                <c:forEach var="cidade" items="${cidades}">
                    <option value="${cidade.codigo}"><c:out value="${cidade.nome}"/></option>
                </c:forEach>
            </select>
            <br/>
            <label for="nome">Nome:</label>
            <input type="text" size="40" name="nome" value="${hotel.nome}"/>
            <br/>
            <input type="submit" value="Editar"/>
            <input type="button" value="Cancelar" onclick="location.href = 
                        '${pageContext.request.contextPath}/controller?opcao=hotel.listar'"/>
        </form>
    </body>
</html>
