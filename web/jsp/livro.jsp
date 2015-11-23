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
        <title><c:out value="${livro.titulo}"/></title>
        <script type="text/javascript">
            function updateTextInput(val) {
                document.getElementById('noteinput').innerHTML = val;
            }
        </script>
    </head>
    <body>
        <jsp:include page="../menu.jsp" />

        <div class="container padded">

            <h1 class="animated bounceInRight" ><i class="icon-info-sign"> </i><c:out value="${livro.titulo}"/></h1>
            <h3>Nota - <c:out value="${notamedia}"/></h3>
            <p >Gênero - <c:out value="${livro.genero}"/></p>
            <p >Autor - <c:out value="${livro.autor}"/></p>
            <p >Editora - <c:out value="${livro.editora}"/></p>
            <p >Publicação - <c:out value="${livro.publicacao}"/></p>
            <p >Preço - <c:out value="${livro.preco}"/> R$</p>


        </div>



        <div class="container">
            <form action="livro" method="post">
                <fieldset>
                    <legend>Deixe um comentario!</legend>
                    <div class="row">
                        <div class="one half padded">
                            <label for="name">Nome</label>
                            <input id="name" name="nome" type="text" placeholder="Seu nome aqui">
                        </div>
                    </div>


                    <div class="row">
                        <div class="one half padded">
                            <label for="message">Nota</label>
                            <spam id="noteinput">5</spam>
                            <input type="range" name="nota" min="0" max="10" onchange="updateTextInput(this.value);">
                        </div>
                    </div>
                    <div class="row">
                        <div class="one whole padded">
                            <input type="hidden" name="livro" value="<c:out value="${livro.idLivro}"/>"/>
                            <input class="info" type="submit" value="Enviar">

                            <input type="hidden" name="command" value="user.insert"/>
                        </div>
                    </div>

                </fieldset>
            </form>
                            <br>
        </div>

        <div class="container">
            <c:forEach var="nota" items="${notas}">
                <div class="box">
                    <p>Usuario: <c:out value="${nota.idUsuario.nome}"/></p>
                    <p>Nota <c:out value="${nota.nota}"/></p>
                </div>
                <br>
            </c:forEach>
        </div>

        <jsp:include page="../footer.jsp" />
    </body>
</html>