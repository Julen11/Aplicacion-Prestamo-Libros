package com.ipartek.formacion.libro.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.libro.DAO.UsuariosMySqlDAO;
import com.ipartek.formacion.libro.modelos.Usuario;



@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/jsps/Login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("contra");
		
		if (nombre == null || password == null) {
			throw new RuntimeException ("Uno de los 2 campos esta vacio, tienes que rellentar ambos");
		}
		
			try {
				if(UsuariosMySqlDAO.getInstance().ComprobarPassword(nombre, password)) {
					
					Usuario usu = new Usuario(nombre, password);
					request.getSession().setAttribute("usuario", usu);
					request.getRequestDispatcher("listado").forward(request, response);
				}
				else {
					throw new Exception("fallo en los datos insertados");
				}
			} catch (Exception e) {
				throw new ControladroException("Fallo en los datos", e);
			}
			
			
	}
			

}
