<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="listado">Biblioteca Piero-Julen</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="listado">Listado</a></li>
			<li class="active"><a href="#">Libros</a></li>
			<li><a href="listadoalumnos">Alumnos</a></li>

		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">${usuario.nombre}</a></li>
			<li><a href="desconectar"><span class="glyphicon glyphicon-log-in"></span> Cerrar sesión</a></li>
		</ul>
	</div>
</nav>
<div class="container">
	<c:forEach items="${editoriales}" var="editorial2">
	<!-- Modal editorial -->
	<div class="modal fade" id="editorialModal${editorial2.ideditorial}" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Editar Editorial</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="editorial" method="post">
						<div class="form-group">
							<input type="hidden" name="accion" value="update2"/>
							<input type="hidden" id="hidden2" name="ideditorial" value="${editorial2.ideditorial}"/>
							<label class="control-label col-sm-2" for="nombre">Nombre:</label>
							<div class="col-sm-10">
								<input type="text" id="nombre" name="nombreEditorial" class="form-control" value="${editorial2.nombre}"/>
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
	<!-- End Modal Editorial -->
	<!-- Modal Editorial 2 -->
	<div class="modal fade" id="editorialModal2${editorial2.ideditorial}" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Borrar Editorial</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="editorial" method="post">
						<div class="form-group">
						<input type="hidden" name="accion" value="delete1"/>
						<input type="hidden" name="ideditorial" value="${editorial2.ideditorial}"/>
							<div class="col-sm-10">
								¿Estas seguro de que quieres borrar el libro ${editorial2.nombre} ?
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
	<!--End Modal EDITORIAL 2  -->
	</c:forEach>




	<c:forEach items="${libros}" var="libro2">
	<!-- Modal libro -->
	<div class="modal fade" id="libroModal${libro2.idlibros}" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Editar Libro</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="listadolibros" method="post">
						<div class="form-group">
								<input type="hidden" id="hidden" name="accion" value="update"/>
								<input type="hidden" id="hidden2" name="idlibro" value="${libro2.idlibros }"/>
							<label class="control-label col-sm-2" for="nombre">Titulo:</label>
							<div class="col-sm-10">
								<input type="text" id="nlibro" name="titulo" class="form-control" value="${libro2.titulo}"/>
							</div> 
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="apellido">Editorial:</label>
							<div class="col-sm-10">
								<select class="form-control" id="editorial" name="ideditorial" >
									<c:forEach items="${editoriales}" var="editorial">
										<option value="${editorial.ideditorial}">${editorial.nombre}</option>
									</c:forEach>
								</select>
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
	<!-- End Modal libro -->
	<!-- Modal libro 2 -->
	<div class="modal fade" id="libroModal2${libro2.idlibros }" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Borrar Libro</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="listadolibros" method="post">
						<div class="form-group">
						<input type="hidden" value="delete" name="accion"/>
						<input type="hidden" name="idlibro" value="${libro2.idlibros}"/>
							<div class="col-sm-10">
								¿Estas seguro de que quieres borrar el libro ${libro2.titulo} ?
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
	<!--End Modal libro 2  -->
	</c:forEach>
	<h1>Listado - Libros - Editoriales</h1>
	<p>
	<div class="row">
		<div class="col-lg-6">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Editorial</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${libros}" var="libro">
						<tr>
							<td>${libro.titulo}</td>
							<td>${libro.editorial.nombre}</td>
							<td></td>
							<td><a href="#" data-toggle="modal" data-target="#libroModal${libro.idlibros}">
								<span class="glyphicon glyphicon-pencil"></span></a> 
								<a href="#" data-toggle="modal" data-target="#libroModal2${libro.idlibros}">
								<span class="glyphicon glyphicon-remove"></span></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col-lg-6">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Editoriales</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${editoriales}" var="editorial">
						<tr>
							<td>${editorial.nombre}</td>
							<td>
								<a href="#"
									data-toggle="modal" data-target="#editorialModal${editorial.ideditorial}">
									<span class="glyphicon glyphicon-pencil"></span>
								</a>
								<a href="#" data-toggle="modal" data-target="#editorialModal2${editorial.ideditorial}">
									<span class="glyphicon glyphicon-remove"></span>
								</a>
						</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row marginbot">
			<div class="col-lg-6">
				<form action="listadolibros" method="post">
					<legend>Dar Alta Libro</legend>
					<div class="form-group">
						<label for="nombre">Nombre</label> <input type="hidden" name="accion" value="insert"> <input type="text"
							class="form-control" id="nombre" placeholder="Titulo" name="titulo" />
					</div>
					<div class="form-group">
						<label for="editorial">Editorial</label> <select
							class="form-control" id="editorial" name="ideditorial" >
							<c:forEach items="${editoriales}" var="editorial">
							<option value="${editorial.ideditorial}">${editorial.nombre}</option>
							</c:forEach>
						</select>
					</div>
					<button class="btn btn-primary">Registrar Libro</button>
				</form>
			</div>
			<div class="col-lg-6">
				<form action="editorial" method="post" id="altaeditorial">
					<legend>Dar Alta Editorial</legend>
					<div class="form-group">
						<label for="nombre">Nombre</label> <input type="text"
							class="form-control" id="nombre"
							placeholder="Inserta el nombre de la nueva editorial"
							name="nombreEditorial" />
							<input type="hidden" name="ideditorial" value="${editorial.ideditorial}">
							<input type="hidden" name="accion" value="insert1">
					</div>
					<button class="btn btn-primary">Registrar Editorial</button>
				</form>
			</div>
		</div>
	</div>

</div>
<%@ include file="/WEB-INF/includes/pie.jsp"%>