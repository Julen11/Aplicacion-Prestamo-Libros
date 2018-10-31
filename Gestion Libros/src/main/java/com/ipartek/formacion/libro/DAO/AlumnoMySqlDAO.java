package com.ipartek.formacion.libro.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.libro.modelos.Alumno;

public class AlumnoMySqlDAO implements CrudAble <Alumno> {

	String url= "jdbc:mysql://localhost:3307/biblioteca?serverTimezone=UTC&useSSL=false";
	String usuario="root";
	String password = "admin";
	
	private static AlumnoMySqlDAO INSTANCE = null;
	
	public static synchronized AlumnoMySqlDAO getInstance() throws ClassNotFoundException {
		if (INSTANCE == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			INSTANCE = new AlumnoMySqlDAO();
		}
		
		return INSTANCE;
	}
	
	
	@Override
	public List<Alumno> getAll() {
		ArrayList<Alumno> alumnos = new ArrayList<>();
		
		try (Connection conn = DriverManager.getConnection(url, usuario, password)){
			String sql = "select idalumno,nombre,apellido,dni\r\n" + 
						"from alumnos";
			
			try(PreparedStatement pst = conn.prepareStatement(sql)){
				
				try(ResultSet rs = pst.executeQuery()){
					while (rs.next()) {
						alumnos.add(new Alumno(rs.getLong("idalumno"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("dni")));
						
					}
				} catch (Exception e) {
					throw new AccesoDatosException(e.getMessage(), e);
				}
			} catch (SQLException e) {
				throw new AccesoDatosException(e.getMessage(), e);
			}
		}catch (SQLException e) {
			throw new AccesoDatosException(e.getMessage(), e);
		}
		
		return alumnos;
	}

	@Override
	public Alumno getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Alumno pojo) {
		
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			String sql = "Insert into alumnos (nombre,apellido,dni) VALUES (?,?,?)";
			
			try(PreparedStatement pst = con.prepareStatement(sql)){
				pst.setString(1, pojo.getNombre());
				pst.setString(2, pojo.getApellido());
				pst.setString(3, pojo.getDni());
				
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
	public void update(Alumno pojo) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			String sql = "UPDATE alumnos SET nombre = ?, apellido = ?, dni = ? WHERE idalumno = ?";
			
			try(PreparedStatement pst = con.prepareStatement(sql)){
				pst.setString(1, pojo.getNombre());
				pst.setString(2, pojo.getApellido());
				pst.setString(3, pojo.getDni());
				pst.setLong(4, pojo.getId());
				
				int numFilas = pst.executeUpdate();
				
				if (numFilas != 1) {
					throw new RuntimeException("Fallo en la actualizacion de datos");
				}
			} catch (SQLException e) {
				throw new RuntimeException("Fallo en la actualizacion de datos");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Fallo en la a de datos");
		}
		
	}

	@Override
	public void delete(long id) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			String sql = "DELETE FROM alumnos WHERE idalumno = ?";
			
			try(PreparedStatement pst = con.prepareStatement(sql)){
				pst.setLong(1, id);
				
				int numFilas = pst.executeUpdate();
				
				if (numFilas != 1) {
					throw new RuntimeException("Fallo en la actualizacion de datos");
				}
			} catch (SQLException e) {
				throw new RuntimeException("Fallo en la actualizacion de datos");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Fallo en la a de datos");
		}
		
	}

}
