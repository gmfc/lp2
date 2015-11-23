<%-- 
    Document   : index
    Created on : Nov 21, 2015, 3:39:34 PM
    Author     : Gabriel
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../head.jsp" />
        <title>Lista de livros</title>
    </head>
    <body>
        <jsp:include page="../menu.jsp" />
        
        <div class="container padded animated fadeIn">
            <h1 >Lista comleta de livros</h1>
            <div class="padded">
            <c:forEach var="livro" items="${livros}">
                
                <div class="box">
                    <h2><a href="/app/controller?opcao=livro.get-<c:out value="${livro.idLivro}"/>"><i class="icon-book"></i> <c:out value="${livro.titulo}"/></a></h2>
                    <p>Autor: <c:out value="${livro.autor}"/></p>
                    <p>Gênero: <c:out value="${livro.genero}"/></p>
                </div>
                <br>
                
            </c:forEach>
            </div>
            
        </div>

        <jsp:include page="../footer.jsp" />
    </body>
</html>
