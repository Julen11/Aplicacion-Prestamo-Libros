<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/includes/cabecera.jsp" %>



<body id="LoginForm">
<div class="container">
<div class="login-form">
<div class="main-div">
    <div class="panel">
   <h2>Hola, Bienvenido</h2>
   <p>Inserte sus datos</p>
   </div>
    <form id="Login" action="login" method="post">

        <div class="form-group">

			
            <input type="text" class="form-control" id="inputEmail" placeholder="Nombre de Usuario" name="nombre">

        </div>

        <div class="form-group">

            <input type="password" class="form-control" id="inputPassword" placeholder="Contraseña" name="contra">

        </div>
        <div class="forgot">
        <a href="reset.html">¿Olvidaste la contraseña?</a>
</div>
        <button class="btn btn-primary">Entrar</button>

    </form>
    </div>
</div></div>


<%@ include file="/WEB-INF/includes/pie.jsp" %>