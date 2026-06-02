package clases;

import conexion.Conexion;

public class ModeloRanking {
	 private Conexion bd;
	 
	 public ModeloRanking() {
		 bd = new Conexion();
	 }

	 public void guardarResultado(EntradaRanking entrada) {
		 bd.guardarRanking(entrada.getNombre(),entrada.getDificultad(),entrada.getPuntos());
	 }

	 public void cerrar() {
		 bd.cerrarConexionBD();
	 }
}
