package com.ipartek.formacion.libro.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.libro.DAO.EditorialMySqlDAO;
import com.ipartek.formacion.libro.DAO.PrestamosMySqlDAO;
import com.ipartek.formacion.libro.modelos.Editorial;

@WebServlet("/editorial")
public class EditorialController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		String id = request.getParameter("id");


		if (accion != null && id != null) {
			
			try {
				Editorial editorial = EditorialMySqlDAO.getInstance().getById(id);
				request.setAttribute("editorial", editorial);
				request.getRequestDispatcher("listadolibros").forward(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			}
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ideditorial = request.getParameter("ideditorial");
		String accion = request.getParameter("accion");
		String nombreEditorial = request.getParameter("nombreEditorial");
		
		switch(accion) {
		case "update2":
			if (ideditorial == null || nombreEditorial == null) {
				throw new RuntimeException("Faltan campos por rellenar");
			}
			Editorial editorial1 = new Editorial(Long.parseLong(ideditorial),nombreEditorial);
			
			try {
				EditorialMySqlDAO.getInstance().update(editorial1);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("listadolibros").forward(request, response);
			break;
		case "insert1":
			if (nombreEditorial == null) {
				throw new RuntimeException("Campo vacio");
			}

			Editorial editorial = new Editorial(nombreEditorial);

			try {
				EditorialMySqlDAO.getInstance().insert(editorial);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("listadolibros").forward(request, response);
			break;
		case "delete1":
			if (ideditorial == null) {
				throw new RuntimeException("Campo vacio");
			}
			long longId;
			longId = Long.parseLong(ideditorial);
			try {
				EditorialMySqlDAO.getInstance().delete(longId);
			}catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("listadolibros").forward(request, response);
			break;
		}
		

		

		
	}

}
