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
                <MyTags:BuscaPorServico servico="${param.servico}" data="${param.data}"/>
                <c:choose>
                    <c:when test="${not empty AtendimentoPorServico}">
                        <form action="FrontController?controller=AgendarAtendimentoController" method="POST">
                            <select name="opcao">
                                <option value="" disabled selected>Choose your option</option>
                                <c:forEach var="opcoes" items="${AtendimentoPorServico}">
                                    <option value="${opcoes.id}&${opcoes.horaInicio}&${opcoes.horaFim}">
                                        <b>Atendente:</b>${opcoes.atendente.nome}</br>
                                        <b>Horário:</b>${opcoes.horaInicio}/${opcoes.horaFim}
                                    </option>
                                </c:forEach>
                            </select>
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
                            <input type="hidden" name="data" value="${param.data}">
                            <input type="hidden" name="servico" value="${param.servico}">
                            <button class="btn" type="submit">Agendar</button>
                        </form> 
                    </c:when>
                </c:choose>
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
                        clear: 'Limpar',
                        close: 'Pronto',
                        labelMonthNext: 'Próximo mês',
                        labelMonthPrev: 'Mês anterior',
                        labelMonthSelect: 'Selecione um mês',
                        labelYearSelect: 'Selecione um ano',
                        format: 'dd/mm/yyyy'
                    });
                    $('select').material_select({});
                    $('.timepicker').timepicker();
                });
            </script>
    </body>
</html>