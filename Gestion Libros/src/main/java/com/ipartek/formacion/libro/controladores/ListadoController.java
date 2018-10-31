package com.ipartek.formacion.libro.controladores;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.libro.DAO.AlumnoMySqlDAO;
import com.ipartek.formacion.libro.DAO.PrestamosMySqlDAO;
import com.ipartek.formacion.libro.modelos.Alumno;
import com.ipartek.formacion.libro.modelos.Libro;

@WebServlet("/listado")
public class ListadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			ArrayList<Libro> l = (ArrayList<Libro>) PrestamosMySqlDAO.getInstance().getAll();
			ArrayList<Libro> libres = (ArrayList<Libro>) PrestamosMySqlDAO.getInstance().LibrosLibres();
			ArrayList<Alumno> alumnos = (ArrayList<Alumno>) AlumnoMySqlDAO.getInstance().getAll();
			request.setAttribute("libros", l);
			request.setAttribute("libres", libres);
			request.setAttribute("alumnos", alumnos);
			request.getRequestDispatcher("/WEB-INF/jsps/listado.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			throw new ControladroException("Fallo en el controlador", e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if (accion != null) {
			String idlibros = request.getParameter("idlibros");
			String idalumno = request.getParameter("idalumno");
			
			
			switch(accion) {
			case "delete1":
				if(idlibros == null) {
					throw new RuntimeException ("Campo vacio id");
				}
				long idlong;
				idlong = Long.parseLong(idlibros);
				try {
					PrestamosMySqlDAO.getInstance().delete1(idlong);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				doGet(request, response);
				break;
			case "insert1":
				if (idlibros != null && idalumno != null) {

					Libro libroprestado = new Libro(Long.parseLong(idlibros), Long.parseLong(idalumno), new Date(),new Date());

					try {
						PrestamosMySqlDAO.getInstance().NuevoPrestamo(libroprestado);
						PrestamosMySqlDAO.getInstance().FechaDevolucion(libroprestado);
					} catch (ClassNotFoundException e) {
						throw new ControladroException("fallo en la insercion del dato", e);
					}
				}else {
					throw new RuntimeException("Faltan datos para el insert");
				}
				doGet(request, response);
				break;
			}
		}else {
			doGet(request,response);
		}
		
		
		

	}

}
