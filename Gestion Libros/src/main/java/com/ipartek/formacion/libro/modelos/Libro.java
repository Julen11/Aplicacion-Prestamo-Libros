package com.ipartek.formacion.libro.modelos;

import java.util.Date;

public class Libro {

	private long idlibros;
	private String titulo;
	private String isbn;
	private Date fecha;
	private Date fecha_devo;
	private long editorial_ideditoriales;
	private long alumnos_idalumnos;

	private Alumno alumno;
	private Editorial editorial;

	public long getEditorial_ideditoriales() {
		return editorial_ideditoriales;
	}

	public void setEditorial_ideditoriales(long editorial_ideditoriales) {
		this.editorial_ideditoriales = editorial_ideditoriales;
	}

	public Libro(String titulo, Editorial editorial) {
		this.titulo = titulo;
		this.setEditorial(editorial);
	}

	public Libro(String titulo, long editorial_ideditoriales) {
		this.titulo = titulo;
		this.editorial_ideditoriales = editorial_ideditoriales;
	}

	public Libro(long idlibros, String titulo, long editorial_ideditoriales) {
		this.idlibros = idlibros;
		this.titulo = titulo;
		this.editorial_ideditoriales = editorial_ideditoriales;
	}

	public Libro(long idlibros, String titulo, long editorial_ideditoriales, Editorial edit) {
		this.idlibros = idlibros;
		this.titulo = titulo;
		this.editorial_ideditoriales = editorial_ideditoriales;
		this.editorial = edit;
	}
	public Libro(long idlibro, long idalumno, Date date, Date dateDev) {
		this.idlibros = idlibro;
		this.alumnos_idalumnos = idalumno;
		this.fecha = date;
		this.setFecha_devo(dateDev);
	}

	public Libro(long idLibro, String titulo) {
		setIdlibros(idLibro);
		setTitulo(titulo);
	}

	public Libro(String titulo, Alumno al, java.sql.Date date) {
		this.titulo = titulo;
		this.fecha = date;
		this.alumno = al;
	}

	public Libro(long idlibro, long idalumno, Date date) {
		this.idlibros = idlibro;
		this.alumnos_idalumnos = idalumno;
		this.fecha = date;
	}

	public Libro(long idlibros) {
		this.idlibros = idlibros;
	}

	public Libro(long idlibro, long alumno_idalumno, String titulo, Alumno al, java.sql.Date date, java.sql.Date dateDev) {
		this.idlibros = idlibro;
		this.alumnos_idalumnos = alumno_idalumno;
		this.titulo = titulo;
		this.alumno = al;
		this.fecha = date;
		this.setFecha_devo(dateDev);
	}

	public long getIdlibros() {
		return idlibros;
	}

	public void setIdlibros(long idlibros) {
		this.idlibros = idlibros;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public long getAlumnos_idalumnos() {
		return alumnos_idalumnos;
	}

	public void setAlumnos_idalumnos(long alumnos_idalumnos) {
		this.alumnos_idalumnos = alumnos_idalumnos;
	}

	public Date getFecha_devo() {
		return fecha_devo;
	}

	public void setFecha_devo(Date fecha_devo) {
		this.fecha_devo = fecha_devo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alumno == null) ? 0 : alumno.hashCode());
		result = prime * result + (int) (alumnos_idalumnos ^ (alumnos_idalumnos >>> 32));
		result = prime * result + ((editorial == null) ? 0 : editorial.hashCode());
		result = prime * result + (int) (editorial_ideditoriales ^ (editorial_ideditoriales >>> 32));
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + (int) (idlibros ^ (idlibros >>> 32));
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (alumno == null) {
			if (other.alumno != null)
				return false;
		} else if (!alumno.equals(other.alumno))
			return false;
		if (alumnos_idalumnos != other.alumnos_idalumnos)
			return false;
		if (editorial == null) {
			if (other.editorial != null)
				return false;
		} else if (!editorial.equals(other.editorial))
			return false;
		if (editorial_ideditoriales != other.editorial_ideditoriales)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (idlibros != other.idlibros)
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Libro [idlibros=" + idlibros + ", titulo=" + titulo + ", isbn=" + isbn + ", fecha=" + fecha
				+ ", editorial_ideditoriales=" + editorial_ideditoriales + ", alumnos_idalumnos=" + alumnos_idalumnos
				+ ", alumno=" + alumno + ", editorial=" + editorial + "]";
	}

}
