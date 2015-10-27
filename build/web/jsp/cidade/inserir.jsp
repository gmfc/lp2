<%-- 
    Document   : inserir
    Created on : 12/03/2014, 10:12:09
    Author     : 31319238
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserir Cidade</title>
        <link href=".\assets\css\main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <header>
                <div id="banner">
                    <img src="assets/images/hotelapp.jpg" alt="Banner do website"/>
                </div>
                <h1>Cidade: Inserir</h1>
            </header>
            <div id="conteudo">
                <form method="POST" action="${pageContext.request.contextPath}/controller?opcao=cidade.inserir"> <%-- "?" é pra passar como parâmetro pro controller--%> 
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" required="true" size="80"/>
                    </br>
                    <input type="submit"/>
                    <input type="button" value="Cancelar" onclick="location.href = '${pageContext.request.contextPath}/controller?opcao=cidade.listar'"/>
                </form>
            </div>
        </div>	
        <footer class="clearfix">
            <p>Copyright:<br/>
                @Lucas Leal Mendonça - TIA: 31319238</p>
        </footer>
    </body>
</html>
