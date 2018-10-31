<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="listado">Biblioteca - Piero Julen</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="listado">Listado</a></li>
			<li><a href="listadolibros">Libros</a></li>
			<li class="active"><a href="#">Alumnos</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">${usuario.nombre}</a></li>
			<li><a href="desconectar"><span class="glyphicon glyphicon-log-in"></span> Cerrar sesión</a></li>
		</ul>
	</div>
</nav>

<div class="container">
<!-- Modal1 -->
<c:forEach items="${alumnos}" var="alumno2">
	<div class="modal fade" id="myModal${alumno2.id }" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Editar Alumno</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="listadoalumnos" method="post">
						<div class="form-group">
							<input type="hidden" id="hidden" name="accion" value="update"/>
							<input type="hidden" id="hidden2" name="id" value="${alumno2.id }"/>
							<label class="control-label col-sm-2" for="nombre">Nombre:</label>
							<div class="col-sm-10">
								<input type="text" id="nombre" name="nombre" class="form-control" value="${alumno2.nombre }"/>
							</div> 
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="apellido">Apellido:</label>
							<div class="col-sm-10">
								<input type="text" id="apellido" name="apellido" class="form-control" value="${alumno2.apellido}"/>
							</div> 
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="dni">DNI:</label>
							<div class="col-sm-10">
								<input type="text" id="dni" name="dni" class="form-control" value="${alumno2.dni}"/>
							</div> 
						</div>
					
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">Editar</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<!-- End of Modal -->
	<!-- Modal2 -->
	<div class="modal fade" id="ModalBorrado${alumno2.id }" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Borrar editorial</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="listadoalumnos" method="post">
						<div class="form-group">
						<input type="hidden" value="delete" name="accion" id="hidden"/>
						<input type="hidden" value="${alumno2.id}" name="id" id="hidden2"/>
							<div class="col-sm-10">
								¿Estas seguro de que quieres borrar el alumno ${alumno2.nombre} ${alumno2.apellido} con DNI: ${alumno2.dni} ?
							</div> 
						</div>
					
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">Si</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<!-- End of Modal2 -->
	</c:forEach>

	<h1>Listado - Alumnos</h1>
	<p>
	<div class="row marginbot">
		<div class="col-lg-10">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>DNI</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${alumnos}" var="alumno">
					<tr>
						<td>${alumno.nombre}</td>
						<td>${alumno.apellido}</td>
						<td>${alumno.dni}</td>
						<td><a href="/listadoalumnos?id=${alumno.id}&nombre=${alumno.nombre}$apellido=${alumno.apellido}&dni=${alumno.dni}"
									data-toggle="modal" data-target="#myModal${alumno.id }">
									<span class="glyphicon glyphicon-pencil"></span></a>
							<a href="#" data-toggle="modal" data-target="#ModalBorrado${alumno.id }"><span class="glyphicon glyphicon-remove"></span></a>
						</td>	
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row marginbot">
			<div class="col-lg-10">
				<form action="listadoalumnos" method="post">
					<legend>Nuevo Alumno</legend>
					<div class="form-group">
						<input type="hidden" name="accion" value="insert"/>
						<label for="nombre">Nombre: </label> <input type="text"
							class="form-control" id="nombre" name="nombre" />
					</div>
					<div class="form-group">
						<label for="apellido">Apellidos: </label> <input type="text"
							class="form-control" id="apellido" name="apellido"/>
					</div>
					<div class="form-group">
						<label for="dni">Dni: </label> <input type="text"
							class="form-control" id="dni" name="dni"/>
					</div>
					<button class="btn btn-primary">Añadir</button>
				</form>
			</div>
		</div>
	
</div>

<%@ include file="/WEB-INF/includes/pie.jsp"%>