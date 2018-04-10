<%-- 
    Document   : PesquisaFidelidade
    Created on : 17/03/2018, 22:23:40
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
                    <form action="PesquisaFidelidade.jsp" method="GET">
                        <div class="input-field col s12">
                            <div class="input-field"><select name="intervalo">
                                    <option value="" disabled selected>Escolha o período</option>
                                    <option value="1">1 mês</option>
                                    <option value="3">3 meses</option>
                                    <option value="6">6 meses</option>
                                </select>
                                <label>Quantos meses atrás?</label></div>
                        </div>
                        <input type="submit" class="btn" value="Pesquisar">
                    </form>
                    <div class="col s12">
                        <ul class="tabs">
                            <li class="tab col s6"><a href="#Gastos">Ordenados por gastos</a></li>
                            <li class="tab col s6"><a href="#Frequencia">Ordenados por frequência</a></li>
                        </ul>
                    </div>
                    <div id="Gastos">
                        <c:if test="${not empty param.intervalo}">
                            <MyTags:BuscaUsuarioByGastos intervalo="${param.intervalo}"/>
                            <c:choose>
                                <c:when test="${empty UsuariosGastos}">
                                    Ou não existem usuários cadastrados ainda
                                    ou ainda não foi selecionado um intervalo.
                                </c:when>
                                <c:otherwise>
                                    <ul class="collection">
                                        <c:forEach var="Usuarios" items="${UsuariosGastos}">
                                            <li class="colletion-item">
                                                Nome do usuario: ${Usuarios.nome}</br>
                                                Email do usuario: ${Usuarios.email}</br>
                                                <div class="divider"></div>
                                            </li>
                                        </c:forEach> 
                                    </ul> 
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                    <div id="Frequencia">
                        <c:if test="${not empty param.intervalo}">
                            <MyTags:BuscaUsuarioByFrenquencia intervalo="${param.intervalo}"/>
                            <c:choose>
                                <c:when test="${empty UsuariosFrequencia}">
                                    Ou não existem usuários cadastrados ainda
                                    ou ainda não foi selecionado um intervalo.
                                </c:when>
                                <c:otherwise>
                                    <ul class="collection">
                                        <c:forEach var="Usuarios" items="${UsuariosFrequencia}">
                                            <li class="colletion-item">
                                                Nome do usuario: ${Usuarios.nome}</br>
                                                Email do usuario: ${Usuarios.email}</br>
                                            </li>
                                        </c:forEach> 
                                    </ul> 
                                </c:otherwise>
                            </c:choose>         
                        </c:if>
                    </div>
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
