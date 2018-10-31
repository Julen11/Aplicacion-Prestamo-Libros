package com.ipartek.formacion.libro.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.libro.DAO.AlumnoMySqlDAO;
import com.ipartek.formacion.libro.modelos.Alumno;


@WebServlet("/listadoalumnos")
public class AlumnosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ArrayList<Alumno> a = (ArrayList<Alumno>) AlumnoMySqlDAO.getInstance().getAll();
			request.setAttribute("alumnos", a);
		} catch (ClassNotFoundException e) {
			throw new ControladroException("Fallo en el controlador");
		}
		request.getRequestDispatcher("/WEB-INF/jsps/alumnos.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String nombreAlumno = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String dni = request.getParameter("dni");
		
		String accion = request.getParameter("accion");
		
		

		
		switch(accion) {
		case "update":
			if(nombreAlumno == null || id == null || apellido == null || dni == null) {
				throw new RuntimeException("Campos vacios");
			}
			Alumno alumno2 = new Alumno(Long.parseLong(id),nombreAlumno,apellido,dni);
			try {
				AlumnoMySqlDAO.getInstance().update(alumno2);
			}catch(Exception e) {
				e.printStackTrace();
			}
			doGet(request,response);
			break;
		case "insert":
			if(nombreAlumno == null || apellido == null || dni == null) {
				throw new RuntimeException("Campos vacios");
			}
			Alumno alumno = new Alumno(nombreAlumno,apellido,dni);
			try {
				AlumnoMySqlDAO.getInstance().insert(alumno);
			} catch (Exception e) {
				e.printStackTrace();
			}
			doGet(request, response);
			break;
		case "delete":
			if(id == null) {
				throw new RuntimeException("Campos vacios");
			}
			long longId;
			longId = Long.parseLong(id);
			try {
				AlumnoMySqlDAO.getInstance().delete(longId);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doGet(request, response);
			break;
		default:
			throw new ControladroException("No se admite una petición que no sea insert, update o delete");
		}
		
	}

}
