package clases;

public class EntradaRanking {
	private String nombre;
	private int puntos;
	private int dificultad;
	
	public EntradaRanking(String nombre, int puntos, int dificultad) {
		this.nombre = nombre;
		this.puntos = puntos;
		this.dificultad = dificultad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getDificultad() {
        return dificultad;
    }
}
