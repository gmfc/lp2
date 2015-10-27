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
        <title>JSP Page</title>
        <link href=".\assets\css\main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <header>
                <div id="banner">
                    <img src="assets/images/hotelapp.jpg" alt="Banner do website"/>
                </div>
                <h1>Editar cidade</h1>
            </header>
            <div id="conteudo">
                <form method="POST" action="${pageContext.request.contextPath}/controller?opcao=cidade.alterarPorId">

                    <select name="cidades" onchange="this.form.submit()">
                        <option id="" value="">--selecione</option>
                        <c:forEach var="cidade" items="${cidades}">
                            <option value="${cidade.codigo}">
                                <c:out value="${cidade.nome}"/></option>
                            </c:forEach>
                    </select>
                    <br>
                    <input type="button" onclick="location.href = '${pageContext.request.contextPath}/controller?opcao=cidade.listar'" value="Cancelar"/>
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