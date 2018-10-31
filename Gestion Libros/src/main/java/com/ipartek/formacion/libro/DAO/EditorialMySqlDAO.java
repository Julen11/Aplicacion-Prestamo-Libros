package com.ipartek.formacion.libro.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.ipartek.formacion.libro.modelos.Editorial;

public class EditorialMySqlDAO implements CrudAble<Editorial> {

	String url = "jdbc:mysql://localhost:3307/biblioteca?serverTimezone=UTC&useSSL=false";
	String usuario = "root";
	String password = "admin";

	private static EditorialMySqlDAO INSTANCE = null;

	public static synchronized EditorialMySqlDAO getInstance() throws ClassNotFoundException {
		if (INSTANCE == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			INSTANCE = new EditorialMySqlDAO();
		}

		return INSTANCE;
	}
	


	@Override
	public List<Editorial> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Editorial> getEditoriales() {
		ArrayList<Editorial> editNom = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, usuario, password)) {
			String sql = "select ideditorial, nombreE from editoriales";

			try (PreparedStatement pst = conn.prepareStatement(sql)) {

				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						editNom.add(new Editorial(rs.getLong("ideditorial"),rs.getString("nombreE")));
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

		return editNom;
	}


	@Override
	public Editorial getById(String id) {
		
		Editorial editorial = null;
		
		try (Connection conn = DriverManager.getConnection(url, usuario, password)) {
			String sql = "select * from editoriales where ideditorial = ?";

			try (PreparedStatement pst = conn.prepareStatement(sql)) {
					
				
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						editorial = (new Editorial(rs.getLong("ideditorial"),rs.getString("nombreE")));
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

		return editorial;
				
	}
		

	@Override
	public void insert(Editorial pojo) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			String sql = "Insert into editoriales (nombreE) VALUES (?)";

			try (PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, pojo.getNombre());

				int numFilas = pst.executeUpdate();

				if (numFilas != 1) {
					throw new RuntimeException("Fallo en la inserccion de datos");
				}

			} catch (SQLException e) {
				throw new RuntimeException("Fallo en la inserccion de datos");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Fallo en la inserccion de datos");
		}

	}

	@Override
	public void update(Editorial pojo) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			String sql = "UPDATE editoriales SET nombreE = ? WHERE ideditorial = ?";
			
			try(PreparedStatement pst = con.prepareStatement(sql)){
				pst.setString(1, pojo.getNombre());
				pst.setLong(2, pojo.getIdeditorial());
				
				int numFilas = pst.executeUpdate();
				
				if (numFilas != 1) {
					throw new RuntimeException("Fallo en la actualizacion de datos1");
				}
			} catch (SQLException e) {
				throw new RuntimeException("Fallo en la actualizacion de datos2");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Fallo en la a de datos3");
		}

	}

	@Override
	public void delete(long id) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			String sql = "DELETE FROM editoriales WHERE ideditorial = ?";
			
			try(PreparedStatement pst = con.prepareStatement(sql)){
				pst.setLong(1, id);
				
				int numFilas = pst.executeUpdate();
				
				if (numFilas != 1) {
					throw new RuntimeException("Fallo en el borrado de datos");
				}
			} catch (SQLException e) {
				throw new RuntimeException("Fallo en el borrado de datos");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Fallo en el borrado de datos");
		}

	}

}
