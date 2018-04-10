<%-- 
    Document   : agendamentoDeHorarioAdmin
    Created on : 12/03/2018, 09:35:43
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
                    <form action="agendamentoDeHorarioAdminServico.jsp">
                        <select name="servico">
                            <option value="" disabled selected>Choose your option</option>
                            <MyTags:BuscaServicos/>
                            <c:choose>
                                <c:when test="${empty Servicos}">
                                    <h2>Não existem serviços cadastrados.</h2>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="servico" items="${Servicos}">
                                        <option value="${servico.nome}">
                                            <b>Serviço:</b>${servico.nome}
                                        </option>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select>
                        <label>Busca por serviço</label>
                        <div class="input-field">
                            <input name="data" type="text" class="datepicker">
                        </div>
                        <button class="btn" type="submit">Pesquisar</button>
                    </form>
                </div>
                <div id="BuscaAtendente" class="col s12">
                    <form action="agendamentoDeHorarioAdminAtendente.jsp">
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
                        <div class="input-field">
                            <input name="data" type="text" class="datepicker">
                        </div>
                        <button class="btn" type="submit">Pesquisar</button>
                    </form>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('.datepicker').pickadate({
                    selectMonths: true, // Creates a dropdown to control month
                    selectYears: 15,
                    monthsFull: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
                    monthsShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
                    weekdaysFull: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sabádo'],
                    weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
                    today: 'Hoje',
                    min: true,
                    max: false,
                    clear: 'Limpar',
                    close: 'Pronto',
                    labelMonthNext: 'Próximo mês',
                    labelMonthPrev: 'Mês anterior',
                    labelMonthSelect: 'Selecione um mês',
                    labelYearSelect: 'Selecione um ano',
                    format: 'dd/mm/yyyy'
                    
                });
                $('select').material_select({});
            });
        </script>
    </body>
</html>
