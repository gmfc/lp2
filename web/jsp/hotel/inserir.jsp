<%-- 
    Document   : inserir
    Created on : 19/03/2014, 10:18:41
    Author     : 31319238
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserir Hotel</title>
        <link href=".\assets\css\main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <header>
                <div id="banner">
                    <img src="assets/images/hotelapp.jpg" alt="Banner do website"/>
                </div>
                <h1>Inserir Hotel</h1>
            </header>
            <div id="conteudo">

                <form method="POST" action="${pageContext.request.contextPath}/controller?opcao=hotel.inserir">

                    <label for="Cidade">Cidade: </label>
                    <select name="cidades" required="true">
                        <option id="" value="">--selecione</option>
                        <c:forEach var="cidade" items="${cidades}">
                            <option value="${cidade.codigo}">
                                <c:out value="${cidade.nome}"/></option>
                            </c:forEach>
                    </select>
                    </br>
                    <label for="Estado">Estado: </label>
                    <select name="estados" required="true">
                        <option id="" value="">--selecione</option>
                        <c:forEach var="estado" items="${estados}">
                            <option value="${estado.codigo}">
                                <c:out value="${estado.nome}"/></option>
                            </c:forEach>
                    </select>
                    </br>
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" required="true" size="80" required="true"/>
                    </br>
                    <input type="submit"/>
                    <input type="button" value="Cancelar" onclick="location.href = '${pageContext.request.contextPath}/controller?opcao=hotel.listar'"/>
                </form>
                </body>
            </div>
        </div>	
        <footer class="clearfix">
            <p>Copyright:<br/>
                @Lucas Leal Mendonça - TIA: 31319238</p>
        </footer>
</html>
