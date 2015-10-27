<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Cidades</title>
        <link href=".\assets\css\main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <header>
                <div id="banner">
                    <img src="assets/images/hotelapp.jpg" alt="Banner do website"/>
                </div>
                <a href="./index.jsp">Home</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=cidade.preInserir">Inserir Cidade</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=cidade.preEditar">Editar Cidade</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=cidade.preExcluir">Excluir Cidade</a>
                <a href="${pageContext.request.contextPath}/controller?opcao=cidade.prelistarf">Listar hoteis por cidade</a>
                <h1>Lista de cidades:</h1>
            </header>

            <c:forEach var="cidade" items="${cidades}">
                <c:out value="${cidade.codigo}"/> - <c:out value="${cidade.nome}"/> <br/>
            </c:forEach>
        </div>
        <footer class="clearfix">
            <p>Copyright:<br/>
                @Lucas Leal Mendonça - TIA: 31319238</p>
        </footer>
    </body>
</html>
