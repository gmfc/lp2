<%-- 
    Document   : confirmar-edicao
    Created on : 23/04/2014, 09:52:40
    Author     : 31319238
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar edição hotel</title>
        <link href=".\assets\css\main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <header>
                <div id="banner">
                    <img src="assets/images/hotelapp.jpg" alt="Banner do website"/>
                </div>
                <h1>Confirmar edição hotel</h1>
            </header>
            <div id="conteudo">
                <form method="POST" action="controller?opcao=hotel.editar">
                    <label for="codigo">Código:</label>
                    <input type="text" size="6" readonly="true" name="codigo" value="${hotel.codigo}"/>
                    <br/>
                    <label for="Cidade">Cidade: </label>
                    <select name="cidades">
                        <option id="" value="${hotel.cidade.codigo}">${hotel.cidade.nome}</option>
                        <c:forEach var="cidade" items="${cidades}">
                            <c:if test="${hotel.cidade.codigo!=cidade.codigo}">
                                <option value="${cidade.codigo}"><c:out value="${cidade.nome}"/></option>
                            </c:if>
                        </c:forEach>
                    </select>
                    </br>
                    <label for="Estado">Estado: </label>
                    <select name="estados">
                        <option id="" value="${hotel.estado.codigo}">${hotel.estado.nome}</option>
                        <c:forEach var="estado" items="${estados}">
                            <c:if test="${hotel.estado.codigo!=estado.codigo}">
                                <option value="${estado.codigo}">
                                    <c:out value="${estado.nome}"/></option>
                                </c:if>
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
            </div>
        </div>	
        <footer class="clearfix">
            <p>Copyright:<br/>
                @Lucas Leal Mendonça - TIA: 31319238</p>
        </footer>
    </body>
</html>
