<%-- 
    Document   : editar
    Created on : 02/04/2014, 09:34:58
    Author     : 31319238
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Excluir Hotel</title>
        <link href=".\assets\css\main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <header>
                <div id="banner">
                    <img src="assets/images/hotelapp.jpg" alt="Banner do website"/>
                </div>
                <h1>Excluir Hotel</h1>
            </header>
            <div id="conteudo">
                <form method="POST" action="${pageContext.request.contextPath}/controller?opcao=hotel.excluirPorId">
                    <select name="hoteis" onchange="this.form.submit()">
                        <option id="" value="">--selecione</option>
                        <c:forEach var="hotel" items="${hoteis}">
                            <option value="${hotel.codigo}">
                                <c:out value="${hotel.nome}"/></option>
                            </c:forEach>
                    </select>
                    <br>
                    <input type="button" onclick="location.href = '${pageContext.request.contextPath}/controller?opcao=hotel.listar'" value="Cancelar"/>
                    </select>
                </form>
            </div>
        </div>	
        <footer class="clearfix">
            <p>Copyright:<br/>
                @Lucas Leal Mendonça - TIA: 31319238</p>
        </footer>
    </body>
</html>
