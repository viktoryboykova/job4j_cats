<%--
  Created by IntelliJ IDEA.
  User: gurov
  Date: 25.02.2022
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.model.Ad" %>
<%@ page import="ru.job4j.store.HbmStore" %>
<%@ page import="ru.job4j.model.Cat" %>
<%@ page import="ru.job4j.model.CatBreed" %>
<%@ page import="ru.job4j.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
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
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

  <title>Кошачий приют</title>

  <script>
    const userEmail = sessionStorage.getItem("userEmail");

    function validate() {
      if ($('#description').val() === '') {
        alert('Заполните поле "Описание"');
        return false;
      }
      if ($('#catName').val() === '') {
        alert('Заполните поле "Имя"');
        return false;
      }
      if ($('#catAge').val() === '') {
        alert('Заполните поле "Возраст"');
        return false;
      }
      if ($('#catBreed').val() === '') {
        alert('Заполните поле "Порода"');
        return false;
      }
      if ($('#coatColor').val() === '') {
        alert('Заполните поле "Цвет шерсти"');
        return false;
      }
      if ($('#eyesColor').val() === '') {
        alert('Заполните поле "Цвет глаз"');
        return false;
      }

    }

    $(document).ready(function () {
      $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cats/breeds',
        dataType: 'json'
      }).done(function (data) {
        for (const breed of data) {
          $('#catBreed').append(`<option value="${breed.id}">${breed.breedName}</option>`)
        }
      }).fail(function (err) {
        alert(err);
      });
      document.getElementById("userEmail").value = userEmail;
    });
  </script>

</head>
<body>
<%
  String id = request.getParameter("id");
  HbmStore hbmStore = new HbmStore();
  Ad ad = new Ad("", false, new Cat("", 0, "", "", new CatBreed("")), new User("", "", ""));
  if (id != null) {
    ad = hbmStore.findAdById(Integer.parseInt(id));
  }
%>
<div class="container pt-3">

  <div class="row">
    <ul class="nav">
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">Главное меню</a>
      </li>
      <li class="nav-item">
        <a class="nav-link">Пользователь <c:out value="${user.name}"/></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">Выйти из профиля</a>
      </li>
    </ul>
  </div>

  <div class="row">
    <div class="card" style="width: 100%">
      <div class="card-header">
        <% if (id == null) { %>
        Новое объявление.
        <% } else { %>
        Редактирование объявления.
        <% } %>
      </div>
      <div class="card-body">
        <form action="<%=request.getContextPath()%>/ad.do" method="post" onsubmit="return validate();">
          <div class="form-group">
            <label>Описание</label>
            <input type="text" class="form-control" name="description" value="" id="description">
          </div>
          <div class="form-group">
            <label>Котик</label>
          </div>
          <div class="form-group">
            <label>Имя</label>
            <input type="text" class="form-control" name="catName" value="" id="catName">
          </div>
          <div class="form-group">
            <label>Возраст</label>
            <input type="number" class="form-control" name="catAge" value="" id="catAge">
          </div>
          <div class="form-group">
            <label>Порода</label>
            <select class="form-control" name="catBreed" value="" id="catBreed">
              <option></option>
            </select>
          </div>
          <div class="form-group">
            <label>Цвет шерсти</label>
            <input type="text" class="form-control" name="coatColor" value="" id="coatColor">
          </div>
          <div class="form-group">
            <label>Цвет глаз</label>
            <input type="text" class="form-control" name="eyesColor" value="" id="eyesColor">
          </div>
          <button type="submit" class="btn btn-primary">Далее</button>
          <input type="hidden" id="userEmail" name="userEmail" value="">
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
