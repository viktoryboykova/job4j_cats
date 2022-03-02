<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: gurov
  Date: 28.02.2022
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Кошачий приют</title>
    <script type="text/javascript">
        $(document).ready(function () {
            <c:forEach items="${adsAboutCats}" var="ad">
            $('#date_<c:out value="${ad.id}"/>').append(moment(new Date(`<c:out value="${ad.created}"/>`)).format('DD.MM.YYYY HH:mm'));
            </c:forEach>
        });
    </script>
</head>
<body>
<div class="container pt-3">

    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">Главное меню</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/posts.do">Заявки на укотовление</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/cats.do">Котики</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/ad.jsp">Добавить объявление</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" >Пользователь <c:out value="${user.name}"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">Выйти</a>
            </li>
        </ul>
    </div>

    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Котики
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Описание</th>
                        <th scope="col">Имя котика</th>
                        <th scope="col">Возраст</th>
                        <th scope="col">Порода</th>
                        <th scope="col">Цвет шерсти</th>
                        <th scope="col">Цвет глаз</th>
                        <th scope="col">Фото</th>
                        <th scope="col">Автор объявления</th>
                        <th scope="col">Статус</th>
                        <th scope="col">Дата создания</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${adsAboutCats}" var="ad">
                        <c:if test="${ad.active}">
                            <tr>
                                <td>
                                    <a href='<c:url value="/ad.jsp?id=${ad.id}"/>'>
                                        <i class="fa fa-edit mr-3"></i>
                                    </a>
                                    <c:out value="${ad.description}"/>
                                </td>
                                <td>
                                    <c:out value="${ad.cat.name}"/>
                                </td>
                                <td>
                                    <c:out value="${ad.cat.age}"/>
                                </td>
                                <td>
                                    <c:out value="${ad.cat.catBreed.breedName}"/>
                                </td>
                                <td>
                                    <c:out value="${ad.cat.coatColor}"/>
                                </td>
                                <td>
                                    <c:out value="${ad.cat.eyesColor}"/>
                                </td>
                                <td>
                                    <img src="<c:url value='/download?id=${ad.cat.id}'/>" width="100px" height="100px"/>
                                </td>
                                <td>
                                    Имя: <c:out value="${ad.creator.name}"/>
                                    Почта: <c:out value="${ad.creator.email}"/>
                                </td>
                                <td>
                                    Активно
                                </td>
                                <td id="date_<c:out value="${ad.id}"/>">
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>

