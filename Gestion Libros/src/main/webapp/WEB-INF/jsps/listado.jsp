<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="listado">Biblioteca - Piero Julen</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="listado">Listado</a></li>
			<li><a href="listadolibros">Libros</a></li>
			<li><a href="listadoalumnos">Alumnos</a></li>

		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">${usuario.nombre}</a></li>
			<li><a href="desconectar"><span class="glyphicon glyphicon-log-in"></span> Cerrar sesión</a></li>
		</ul>
	</div>
</nav>

<div class="container">
<c:forEach items="${libros}" var="libro2">
	<!-- Modal1 -->
	<div class="modal fade" id="prestamoModal${libro2.idlibros}" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Borrar Prestamo</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="listado" method="post">
						<div class="form-group">
						<input type="hidden" value="delete1" name="accion"/>
						<input type="hidden" value="${libro2.idlibros}" name="idlibros"/>
							<div class="col-sm-10">
								¿Estas seguro de que quieres borrar el prestamo del libro ${libro2.titulo} a ${libro2.alumno.nombre} ?
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
	<h1>Listado - Libros prestados</h1>
	<p>
	<div class="row marginbot">
		<div class="col-lg-10">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Titulo</th>
						<th>Alumno</th>
						<th>Fecha</th>
						<th>Fecha devolucion</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${libros}" var="libro">
						<tr>
							<td>${libro.titulo }</td>
							<td>${libro.alumno.nombre }</td>
							<td>${libro.fecha}</td>
							<td>${libro.fecha_devo}</td>
							<td>
							<a href="#" data-toggle="modal" data-target="#prestamoModal${libro.idlibros}"><span class="glyphicon glyphicon-remove"></span></a>
							</td>
							<td>
							<form action="devolucion" method="post">
							<input type="hidden" name="idliac" value="${libro.idlibros}">
							<button class="btn btn-primary">Ampliar plazo</button>
							</form>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
		<div class="col-lg-10">
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#myModal">Nuevo Prestamo</button>
			</div>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Nuevo Prestamo</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="listado" method="post">
							<div class="form-group">
								<label class="control-label col-sm-2" for="libro">Libro:</label>
								<div class="col-sm-10">
								<input type="hidden" name="accion" value="insert1"/>
									<select class="form-control" id="libro" name="idlibros">
									<c:forEach items="${libres}" var="libre">
										<option value="${libre.idlibros}">${libre.titulo}</option>
									</c:forEach>	
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="alumno">Alumno:</label>
								<div class="col-sm-10">
									<select class="form-control" id="libro" name="idalumno">
									<c:forEach items="${alumnos}" var="alumno">
								
										<option value="${alumno.id}">${alumno.nombre}</option>
									</c:forEach>	
									</select>
								</div>
							</div>
							<!--  <div class="form-group">
								<label class="control-label col-sm-2" for="fecha" name="fecha">Fecha:</label>
								<div class="col-sm-10">
									<input type="date" class="form-control" id="fecha"/>
								</div>
							</div>
							-->
							<div class="modal-footer">
						<button class="btn btn-primary">Añadir</button>
					</div>
						</form>
					</div>
					
				</div>
			</div>
		</div>
		<!-- End of Modal -->


	</div>
	
</div>

<%@ include file="/WEB-INF/includes/pie.jsp"%>