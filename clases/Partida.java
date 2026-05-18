package clases;

import java.time.LocalDateTime;

public class Partida {
	
	private Tablero tablero;
	private LocalDateTime tiempoInicio;
	private int movimientos;
	private Dificultad dificultad;
	private EstadoPartida estado;
	
	public Partida(Tablero tablero, Dificultad didficultad) {
		this.tablero = tablero;
		this.dificultad = dificultad;
		this.tiempoInicio = LocalDateTime.now();
		this.movimientos = 0;
		this.estado = EstadoPartida.EN_CURSO;
	}
}
