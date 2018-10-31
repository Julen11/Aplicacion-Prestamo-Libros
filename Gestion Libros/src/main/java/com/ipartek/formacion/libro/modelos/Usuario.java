package com.ipartek.formacion.libro.modelos;

public class Usuario {

		private long idUsuario;
		private String nombre;
		private String contra;
		
		
		public Usuario(String nombre, String contra) {
			super();
			this.nombre = nombre;
			this.contra = contra;
		}
		public long getIdUsuario() {
			return idUsuario;
		}
		public void setIdUsuario(long idUsuario) {
			this.idUsuario = idUsuario;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getContra() {
			return contra;
		}
		public void setContra(String contra) {
			this.contra = contra;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((contra == null) ? 0 : contra.hashCode());
			result = prime * result + (int) (idUsuario ^ (idUsuario >>> 32));
			result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
			Usuario other = (Usuario) obj;
			if (contra == null) {
				if (other.contra != null)
					return false;
			} else if (!contra.equals(other.contra))
				return false;
			if (idUsuario != other.idUsuario)
				return false;
			if (nombre == null) {
				if (other.nombre != null)
					return false;
			} else if (!nombre.equals(other.nombre))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", contra=" + contra + "]";
		}
		
		
	

}
