package clases;

public class EntradaRanking {
	private String nombre;
	private int tiempoSegundos;
	private int movimientos;
	private Dificultad dificultad;
	
	public EntradaRanking(String nombre, int tiempoSegundos, int movimientos, Dificultad dificultad) {
		this.nombre = nombre;
		this.tiempoSegundos = tiempoSegundos;
		this.movimientos = movimientos;
		this.dificultad = dificultad;
	}
}
