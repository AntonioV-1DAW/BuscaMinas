package clases;

public class Tablero {
	private Celdas[][] celda;
	private int filas;
	private int columnas;
	
	public Tablero(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.celda = new Celdas[filas][columnas];
		
		inicializarCeldas();
	}
	
	private void inicializarCeldas() {
		for (int i=0; i<filas; i++) {
			for(int j=0; j<columnas; j++) {
				celda[i][j] = new Celdas();
			}
		}
	}
}
