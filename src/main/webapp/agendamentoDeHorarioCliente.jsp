<%-- 
    Document   : agendamentoDeHorarioCliente
    Created on : 17/03/2018, 22:13:27
    Author     : ThigoYure
--%>

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
                    <ul class="tabs">
                        <li class="tab col s6"><a class="active" href="#BuscaServico">Busca Por Serviço</a></li>
                        <li class="tab col s6"><a href="#BuscaAtendente">Busca Por Atendente</a></li></li>
                    </ul>
                </div>
                <div id="BuscaServico" class="col s12">
                    <form action="agendamentoDeHorarioAdmin.jsp">
                        <div class="input-field col s12">
                            <select name="servico">
                                <option value="" disabled selected>Choose your option</option>
                                <MyTags:BuscaServicos/>
                                <c:choose>
                                    <c:when test="${empty Servicos}">
                                        <h2>Não existem serviços cadastrados.</h2>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="servico" items="${Servicos}">
                                            <option value="${servico.nome}">${servico.nome}</option>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                            <label>Busca por serviço</label>
                        </div>
                        <button class="btn" type="submit">Pesquisar</button>
                    </form>
                </div>
                <div id="BuscaAtendente" class="col s12">
                    <form action="agendamentoDeHorarioAdmin.jsp">
                        <div class="input-field col s12">
                            <select name="atendente">
                                <option value="" disabled selected>Choose your option</option>
                                <MyTags:BuscaAtendentes/>
                                <c:choose>
                                    <c:when test="${empty Atendentes}">
                                        <h2>Não existem Atendentes cadastrados.</h2>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="atendente" items="${Atendentes}">
                                            <option value="${atendente.nome}">${atendente.nome}</option>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                            <label>Busca por atendente</label>
                        </div>
                        <button class="btn" type="submit">Pesquisar</button>
                    </form>
                </div>
            </div>
            <MyTags:BuscaPorServico servico="${param.servico}"/>
            <c:choose>
                <c:when test="${not empty AtendimentoPorServico}">
                    <form action="FrontController?controller=AgendarAtendimentoController" method="POST">
                        <c:forEach var="opcoes" items="${AtendimentoPorServico}">
                            <div class="accent-1">
                                <input class="with-gap" name="opcao" type="radio" id="${opcoes.id}" value="${opcoes.id}" />
                                <label for="${opcoes.id}">
                                    <b>Atendente:</b>${opcoes.atendente.nome}</br>
                                    <b>Horário:</b>${opcoes.horaInicio}
                                </label>
                            </div>
                        </c:forEach>
                        <div class="input-field col s12">
                            <input type="hidden" name="cliente" value="${user.email}">
                        </div>
                        <input id="data" type="text" name="data" class="datepicker">
                        <label for="data">Data do Atendimento</label>
                        <button class="btn" type="submit">Agendar</button>
                    </form> 
                </c:when>
            </c:choose>
            <MyTags:BuscaPorAtendente atendente="${param.atendente}"/>
            <c:choose>
                <c:when test="${not empty AtendimentoPorAtendente}">
                    <form action="FrontController?controller=AgendarAtendimentoController" method="POST">
                        <c:forEach var="opcoes" items="${AtendimentoPorAtendente}">
                            <div class="accent-1">
                                <input class="with-gap" name="opcao" type="radio" id="${opcoes.id}" value="${opcoes.id}"/>
                                <label for="${opcoes.id}">
                                    <b>Serviço:</b>${opcoes.atendente.servico.nome}</br>
                                    <b>Horário:</b>${opcoes.horaInicio}
                                </label>
                            </div>
                        </c:forEach>
                        <div class="input-field col s12">
                            <select name="cliente">
                                <option value="" disabled selected>Choose your option</option>
                                <MyTags:BuscaUsuarios/>
                                <c:choose>
                                    <c:when test="${empty Usuarios}">
                                        <h2>Não existem usuários cadastrados.</h2>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="usuario" items="${Usuarios}">
                                            <option value="${usuario.email}">${usuario.nome}</option>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                            <label>Busca por serviço</label>
                        </div>
                        <input id="data" type="text" name="data" class="datepicker">
                        <label for="data">Data do Atendimento</label>
                        <button class="btn" type="submit">Agendar</button>
                    </form> 
                </c:when>
            </c:choose>
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
