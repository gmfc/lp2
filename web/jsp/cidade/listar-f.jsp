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
        <title>Lista hoteis por cidade</title>
        <link href=".\assets\css\main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <header>
                <div id="banner">
                    <img src="assets/images/hotelapp.jpg" alt="Banner do website"/>
                </div>
                <a href="./index.jsp">Home</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=cidade.listar">Menu cidades</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=cidade.preInserir">Inserir Cidade</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=cidade.preEditar">Editar Cidade</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=cidade.preExcluir">Excluir Cidade</a>

                <h1>Lista de hoteis por cidade: </h1>
            </header>
            <div id="conteudo">
                <form method="POST" action="${pageContext.request.contextPath}/controller?opcao=cidade.listarf">

                    <select name="cidades" onchange="this.form.submit()">
                        <option id="" value="${cEscolhido.codigo}">${cEscolhido.nome}</option>
                        <c:forEach var="cidade" items="${cidades}">
                            <c:if test="${cEscolhido.codigo!=cidade.codigo}">
                                <option value="${cidade.codigo}">
                                    <c:out value="${cidade.nome}"/></option>
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
