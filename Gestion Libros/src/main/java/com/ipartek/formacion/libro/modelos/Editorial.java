package com.ipartek.formacion.libro.modelos;

public class Editorial {
		private long ideditorial;
		private String nombre;
		
		
		
		public Editorial(String nombre) {
			this.nombre=nombre;
		}

		public Editorial(long ideditorial, String nombre) {
			this.ideditorial=ideditorial;
			this.nombre=nombre;
		}

		public long getIdeditorial() {
			return ideditorial;
		}
		public void setIdeditorial(long ideditorial) {
			this.ideditorial = ideditorial;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (ideditorial ^ (ideditorial >>> 32));
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
			Editorial other = (Editorial) obj;
			if (ideditorial != other.ideditorial)
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
			return "Editorial [ideditorial=" + ideditorial + ", nombre=" + nombre + "]";
		}
		
		
}
