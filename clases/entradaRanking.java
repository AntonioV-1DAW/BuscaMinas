package clases;

public class entradaRanking {
	private String nombreJugador;
	private int movimientos;
	private int dificultad;
	
	public entradaRanking(String nombreJugador, int movimientos, int dificultad) {
		this.nombreJugador = nombreJugador;
		this.movimientos = movimientos;
		this.dificultad = dificultad;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public int getDificultad() {
        return dificultad;
    }
}