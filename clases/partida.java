package clases;

import java.util.Scanner;

public class partida {
	private tablero tablero;
	private int movimientos;
	private int puntos;
	private int dificultad;
	private boolean terminada;
	private boolean victoria;
	
	Scanner sc = new Scanner(System.in);

    public void nuevaPartida(int dificultad) {
    	
    	this.dificultad = dificultad;

        if (dificultad == 1) {
            tablero = new tablero(8, 8, 10);
        } else if (dificultad == 2) {
            tablero = new tablero(12, 12, 20);
        } else {
            tablero = new tablero(16, 16, 40);
        }
        
        movimientos = 0;
        puntos = 0;
        terminada = false;
        victoria = false;

        tablero.inicializarTablero();
    }

    public void ejecutarPartida() {

        while (!terminada) {

            tablero.representarTablero(false);

            System.out.println("1. Descubrir");
            System.out.println("2. Marcar");
            System.out.println("3. Salir");

            int opcion = sc.nextInt();
            
            if (opcion == 3) {
                terminada = true;
                break;
            }

            System.out.print("Fila: ");
            int fila = sc.nextInt();

            System.out.print("Columna: ");
            int columna = sc.nextInt();

            if (opcion == 1) {
            	if (tablero.hayMina(fila, columna)) {
            		tablero.descubrir(fila, columna);
                    terminada = true;
                    victoria = false;
                    
                    break;
                } else {
                	tablero.descubrir(fila, columna);
                }
            	movimientos++;
        	} else if (opcion == 2) {
        		tablero.marcar(fila, columna);
        	    movimientos++;
        	}

            if (tablero.comprobarVictoria()) {
                victoria = true;
                terminada = true;
                puntos++;
            }
        }

        tablero.representarTablero(true);

        if (victoria) {
            System.out.println("HAS GANADO - Punto: " + puntos);
        } else {
            System.out.println("HAS PERDIDO");
        }
    }
}
