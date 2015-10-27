<%-- 
    Document   : pre-listar-f
    Created on : 08/05/2014, 11:08:38
    Author     : 31319238
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pré-Lista hoteis</title>
        <link href=".\assets\css\main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <header>
                <div id="banner">
                    <img src="assets/images/hotelapp.jpg" alt="Banner do website"/>
                </div>
                <h1>Listar Hoteis por estado:</h1>
            </header>
            <div id="conteudo">
                <form method="POST" action="${pageContext.request.contextPath}/controller?opcao=estado.listarf">

                    <select name="estados" onchange="this.form.submit()">
                        <option id="" value="">--selecione</option>
                        <c:forEach var="estado" items="${estados}">
                            <option value="${estado.codigo}">
                                <c:out value="${estado.nome}"/></option>
                            </c:forEach>
                    </select>
                    <br>
                    <input type="button" onclick="location.href = '${pageContext.request.contextPath}/controller?opcao=estado.listar'" value="Cancelar"/>
                </form>
            </div>
        </div>	
        <footer class="clearfix">
            <p>Copyright:<br/>
                @Lucas Leal Mendonça - TIA: 31319238</p>
        </footer>
    </body>
</html>
