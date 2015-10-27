<%-- 
    Document   : confirmar-alteracao
    Created on : 02/04/2014, 10:16:20
    Author     : 31319238
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar edição cidade</title>
        <link href=".\assets\css\main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <header>
                <div id="banner">
                    <img src="assets/images/hotelapp.jpg" alt="Banner do website"/>
                </div>
                <h1>Confirmar edição cidade</h1>
            </header>
            <div id="conteudo">
                <form method="POST" action="controller?opcao=cidade.alterar">
                    <label for="Codigo">Código:</label>
                    <input type="text" size="6" readonly="true" name="codigo" value="${cidade.codigo}"/>
                    <br/>
                    <label for="nome">Nome:</label>
                    <input type="text" size="40" name="nome" value="${cidade.nome}"/>
                    <br/>
                    <input type="submit" value="Alterar"/>
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
