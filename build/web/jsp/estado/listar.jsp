<%-- 
    Document   : listar
    Created on : 07/05/2014, 10:09:16
    Author     : 31319238
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Estados</title>
        <link href=".\assets\css\main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <header>
                <div id="banner">
                    <img src="assets/images/hotelapp.jpg" alt="Banner do website"/>
                </div>
                <a href="./index.jsp">Home</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=estado.preInserir">Inserir Estado</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=estado.preEditar">Editar Estado</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=estado.preExcluir">Excluir Estado</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=estado.prelistarf">Listar hoteis por estado</a>
                <h1>Lista de estados:</h1>
            </header>

            <c:forEach var="estado" items="${estados}">
                <c:out value="${estado.codigo}"/> - <c:out value="${estado.nome}"/> <br/>
            </c:forEach>
        </div>
        <footer class="clearfix">
            <p>Copyright:<br/>
                @Lucas Leal Mendonça - TIA: 31319238</p>
        </footer>
    </body>
</html>
