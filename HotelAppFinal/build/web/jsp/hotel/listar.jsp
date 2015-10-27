<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Hoteis</title>
        <link href=".\assets\css\main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <header>
                <div id="banner">
                    <img src="assets/images/hotelapp.jpg" alt="Banner do website"/>
                </div>
                <p><a href="./index.jsp">Home</a>
                    <a href="${pageContext.request.contextPath}/controller?opcao=hotel.preInserir">Inserir Hotel</a>
                    <a href="${pageContext.request.contextPath}/controller?opcao=hotel.preEditar">Editar Hotel</a>
                    <a href="${pageContext.request.contextPath}/controller?opcao=hotel.preExcluir">Excluir Hoteis</a>
                <h1>Lista de hoteis:</h1>
            </header>

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
