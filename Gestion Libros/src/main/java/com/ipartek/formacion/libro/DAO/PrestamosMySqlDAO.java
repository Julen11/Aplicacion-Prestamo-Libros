package com.ipartek.formacion.libro.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ipartek.formacion.libro.modelos.Alumno;
import com.ipartek.formacion.libro.modelos.Editorial;
import com.ipartek.formacion.libro.modelos.Libro;

public class PrestamosMySqlDAO implements CrudAble <Libro> {

	String url = "jdbc:mysql://localhost:3307/biblioteca?serverTimezone=UTC&useSSL=false";
	String usuario = "root";
	String password = "admin";

	private static PrestamosMySqlDAO INSTANCE = null;

	public static synchronized PrestamosMySqlDAO getInstance() throws ClassNotFoundException {
		if (INSTANCE == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			INSTANCE = new PrestamosMySqlDAO();
		}

		return INSTANCE;
	}
	@Override
	public List<Libro> getAll() {
		ArrayList<Libro> prestamos = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, usuario, password)) {
			String sql = "select l.idlibros,l.alumnos_idalumnos,l.titulo, a.nombre, l.fecha_prestamo, l.fecha_devolucion \r\n" + 
					"from libros l\r\n" + 
					"inner join alumnos a on l.alumnos_idalumnos = a.idalumno\r\n" + 
					"where fecha_prestamo is not null;\r\n" + 
					"";

			try (PreparedStatement pst = conn.prepareStatement(sql)) {

				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						Alumno al = new Alumno(rs.getString("nombre"));
						prestamos.add(new Libro(rs.getLong("idlibros"),rs.getLong("alumnos_idalumnos"),rs.getString("titulo"),al,rs.getDate("fecha_prestamo"),rs.getDate("fecha_devolucion")));
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

		return prestamos;
	}
	
	public List<Libro> getNomEd() {
		ArrayList<Libro> libros = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, usuario, password)) {
			String sql = "Select l.idlibros, l.titulo, e.nombreE, l.editoriales_ideditorial\r\n" + 
					"from libros l\r\n" + 
					"inner join editoriales e on e.ideditorial = l.editoriales_ideditorial\r\n" + 
					"";


			try (PreparedStatement pst = conn.prepareStatement(sql)) {

				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						Editorial edit = new Editorial(rs.getString("nombreE"));
						libros.add(new Libro(rs.getLong("idlibros"),rs.getString("titulo"),rs.getLong("editoriales_ideditorial"),edit));
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

		return libros;
	}
	@Override
	public Libro getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Libro pojo) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			String sql = "INSERT INTO libros (titulo,editoriales_ideditorial) VALUES  (?,?)";

			try (PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, pojo.getTitulo());
				pst.setLong(2, pojo.getEditorial_ideditoriales());

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
	public void update(Libro pojo) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			String sql = "UPDATE libros SET titulo = ?, editoriales_ideditorial = ? WHERE idlibros = ?";
			
			try(PreparedStatement pst = con.prepareStatement(sql)){
				pst.setString(1, pojo.getTitulo());
				pst.setLong(2, pojo.getEditorial_ideditoriales());
				pst.setLong(3, pojo.getIdlibros());
				
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
			String sql = "DELETE FROM libros WHERE idlibros = ?";
			
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
	
	public void NuevoPrestamo(Libro pojo) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			String sql = "UPDATE libros SET fecha_prestamo = ? , alumnos_idalumnos = ? where idlibros = ?";

			try (PreparedStatement pst = con.prepareStatement(sql)) {
				Date fecha =new Date();
				pst.setDate(1, new java.sql.Date(fecha.getTime()));
				pst.setLong(2, pojo.getAlumnos_idalumnos());
				pst.setLong(3, pojo.getIdlibros());
			
				pst.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException("Fallo en la inserccion de datos", e);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Fallo en la inserccion de datos", e);
		}
		
	}
	
	public List<Libro> LibrosLibres() {
		ArrayList<Libro> libres = new ArrayList<Libro>();

		try (Connection conn = DriverManager.getConnection(url, usuario, password)) {
			String sql = "select titulo,idlibros from libros where fecha_prestamo is NULL";

			try (PreparedStatement pst = conn.prepareStatement(sql)) {

				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
					
						Libro li=new Libro(rs.getLong("idlibros"),rs.getString("titulo"));
						libres.add(li);
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
		
		return libres;
	
	}
	
	public void delete1(long id) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			String sql = "UPDATE libros SET fecha_prestamo = null, alumnos_idalumnos = null, fecha_prestamo = null, fecha_devolucion=null WHERE idlibros = ?";
			
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
	
	public void FechaDevolucion(Libro pojo) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			String sql = "UPDATE libros SET fecha_devolucion = DATE_ADD(fecha_prestamo, INTERVAL 14 DAY) where idlibros=?;";

			try (PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setLong(1, pojo.getIdlibros());

			
				pst.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException("Fallo en la inserccion de datos", e);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Fallo en la inserccion de datos", e);
		}
		
	}
	
	public void AmpliacionDias(long id) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			String sql = "UPDATE libros SET fecha_devolucion = DATE_ADD(fecha_devolucion, INTERVAL 7 DAY) where idlibros=?";

			try (PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setLong(1, id);

				pst.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException("Fallo en la inserccion de datos", e);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Fallo en la inserccion de datos", e);
		}
		
	}

}
