<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
  <head>
	<%@include file="fragJSP/metacabecera.jspf"%>
    <title>Sistema Reservas Login</title>
	<%@include file="fragJSP/estilos.jspf"%>

  </head>

  <body class="body-signin">

    <div class="container">

      <form class="form-signin" action="controladores/comprobarLogin.do" method="POST">
        <h2 class="form-signin-heading">Datos de acceso</h2>
        <label for="inputEmail" class="sr-only">Email</label>
        <input type="email" id="inputEmail" name="inputEmail" class="form-control" placeholder="Dirección de correo" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" name="rememberme" value="rememberme"> Recordar
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
      </form>
      
       <%-- Mostrar mensaje de error en caso de fallo --%>
 		<c:if test="${not empty requestScope.msgError}">
 			<div class="alert alert-danger">
  				<strong><c:out value="${requestScope.msgError}"></c:out></strong>
			</div>
 		</c:if>

    </div> <!-- /container -->


    <%@include file="fragJSP/javascripts.jspf"%>
  </body>
</html>
