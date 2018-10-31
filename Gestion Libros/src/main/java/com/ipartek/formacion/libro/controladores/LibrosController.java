package com.ipartek.formacion.libro.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.libro.DAO.EditorialMySqlDAO;
import com.ipartek.formacion.libro.DAO.PrestamosMySqlDAO;
import com.ipartek.formacion.libro.modelos.Editorial;
import com.ipartek.formacion.libro.modelos.Libro;

@WebServlet("/listadolibros")
public class LibrosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ArrayList<Libro> libros = (ArrayList<Libro>) PrestamosMySqlDAO.getInstance().getNomEd();
			ArrayList<Editorial> e = (ArrayList<Editorial>) EditorialMySqlDAO.getInstance().getEditoriales();
			request.setAttribute("libros", libros);
			request.setAttribute("editoriales", e);

		} catch (ClassNotFoundException e) {
			throw new ControladroException("Fallo en el controlador");
		}
		request.getRequestDispatcher("/WEB-INF/jsps/libros.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ideditorial = request.getParameter("ideditorial");

		String idlibro = request.getParameter("idlibro");
		String titulo = request.getParameter("titulo");
		String editoriallibro = request.getParameter("ideditorial");
		String accion = request.getParameter("accion");

		if (accion != null) {

			switch (accion) {
			case "update":
				if (idlibro == null || titulo == null || editoriallibro == null) {
					throw new RuntimeException("Campos vacios");
				}
				Libro libro2 = new Libro(Long.parseLong(idlibro), titulo, Long.parseLong(editoriallibro));
				try {
					PrestamosMySqlDAO.getInstance().update(libro2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				doGet(request, response);
				break;
			case "insert":
				if (titulo == null || ideditorial == null) {
					throw new RuntimeException("Uno de los 2 campos esta vacio, tienes que rellentar ambos");
				}
				Libro lib = new Libro(titulo, Long.parseLong(ideditorial));
				try {
					PrestamosMySqlDAO.getInstance().insert(lib);
				} catch (Exception e) {
					e.printStackTrace();
				}
				doGet(request, response);
				break;
			case "delete":
				if (idlibro == null) {
					throw new RuntimeException("Campos vacios");
				}
				long longId;
				longId = Long.parseLong(idlibro);
				try {
					PrestamosMySqlDAO.getInstance().delete(longId);
				}catch (Exception e) {
					e.printStackTrace();
				}
				doGet(request, response);
				break;
		
			}
		}
		doGet(request,response);
	}

}
