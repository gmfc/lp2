<%-- 
    Document   : listar-f
    Created on : 08/05/2014, 11:43:10
    Author     : 31319238
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista hoteis</title>
        <link href=".\assets\css\main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <header>
                <div id="banner">
                    <img src="assets/images/hotelapp.jpg" alt="Banner do website"/>
                </div>
                <a href="./index.jsp">Home</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=estado.listar">Menu estados</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=estado.preInserir">Inserir estado</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=estado.preEditar">Editar estado</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=estado.preExcluir">Excluir estado</a>

                <h1>Lista de hoteis por estado: </h1>
            </header>
            <div id="conteudo">
                <form method="POST" action="${pageContext.request.contextPath}/controller?opcao=estado.listarf">

                    <select name="estados" onchange="this.form.submit()">
                        <option id="" value="${dEscolhido.codigo}">${dEscolhido.nome}</option>
                        <c:forEach var="estado" items="${estados}">
                            <c:if test="${dEscolhido.codigo!=estado.codigo}">
                                <option value="${estado.codigo}">
                                    <c:out value="${estado.nome}"/></option>
                                </c:if>
                            </c:forEach>
                    </select>
                    <br>
                </form>
            </div>
            <c:forEach var="hotel" items="${hoteis}">
                <c:out value="${hotel.codigo}"/> - <c:out value="${hotel.nome}"/> - 
                <c:out value="${hotel.cidade.nome}"/> - <c:out value="${hotel.estado.nome}"/> <br/>
            </c:forEach>
        </div>	
        <footer class="clearfix">
            <p>Copyright:<br/>
                @Lucas Leal Mendonça - TIA: 31319238</p>
        </footer>
    </body>
</html>
