package com.ipartek.formacion.libro.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.libro.modelos.Usuario;

public class UsuariosMySqlDAO implements CrudAble<Usuario> {

	
	String url = "jdbc:mysql://localhost:3307/biblioteca?serverTimezone=UTC&useSSL=false";
	String usuario = "root";
	String password = "admin";

	private static UsuariosMySqlDAO INSTANCE = null;

	public static synchronized UsuariosMySqlDAO getInstance() throws ClassNotFoundException {
		if (INSTANCE == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			INSTANCE = new UsuariosMySqlDAO();
		}

		return INSTANCE;
	}
	@Override
	public List<Usuario> getAll() {
		ArrayList<Usuario> users = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, usuario, password)) {
			String sql = "SELECT nombre, contra FROM usuarios";

			try (PreparedStatement pst = conn.prepareStatement(sql)) {

				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						users.add(new Usuario(rs.getString("nombre"), rs.getString("contra")));
					}
				} catch (Exception e) {
					throw new AccesoDatosException(e.getMessage(), e);
				}
			} catch (SQLException e) {
				throw new AccesoDatosException(e.getMessage(), e);
			}

		} catch (SQLException e) {
			throw new AccesoDatosException(e.getMessage(), e);
		}

		return users;

	}

	@Override
	public Usuario getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Usuario pojo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Usuario pojo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean ComprobarPassword(String nombre, String password) throws SQLException {

		try (Connection con = DriverManager.getConnection(this.url, this.usuario, this.password)) {
			  String sql = "SELECT * FROM usuarios WHERE nombre='"+nombre+"' AND contra='"+password+"'";

			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ResultSet rs = ps.executeQuery();

				return rs.next();

			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}

	}
	

}
