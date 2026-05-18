package clases;

public class Celda {
	private boolean tieneMina;
	private boolean descubierta;
	private boolean marcada;
	private int minasCercanas;
	
	public Celda() {
		this.tieneMina = false;
		this.descubierta = false;
		this.marcada = false;
		this.minasCercanas = 0;
	}
}
