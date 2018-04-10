<%@taglib prefix="MyTags" uri="/tlds/MyTags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col s12">
                    <MyTags:BuscaAtendimentosPendentes/>
                    <c:choose>
                        <c:when test="${empty AtendimentosPendentes}">
                            <h2>Não existem atendimentos pendentes</h2>
                        </c:when>
                        <c:otherwise>
                            <ul class="collection">
                                <c:forEach var="atendimentos" items="${AtendimentosPendentes}">
                                    <li class="collection-item">
                                        <form action="FrontController?controller=ConfirmarAtendimentoController" method="POST">
                                            <p><b>Data:</b>${atendimentos.data}</p>
                                            <p><b>Cliente:</b>${atendimentos.cliente.nome}</p>
                                            <p><b>Atendente:</b>${atendimentos.atendente.nome}</p>
                                            <p><b>Serviço:</b>${atendimentos.servico.nome}</p>
                                            <p><b>Horário:</b>${atendimentos.horaInicio} / ${atendimentos.horaFim}</p>
                                            <input type="hidden" name="idAtendimento" value="${atendimentos.id}">
                                            <button type="submit" class="btn green">Confirmar</button> 
                                        </form>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript" src="js/initialize.js"/>
        <script type="text/javascript">
            $(document).ready(function () {
                $('select').material_select();
            });
        </script>
    </body>
</html>