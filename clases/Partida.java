package clases;

import java.util.Scanner;

public class Partida {
	private Tablero tablero;
	private int puntos;
	private boolean terminada;
	private boolean victoria;
	
	Scanner sc = new Scanner(System.in);

    public void nuevaPartida(int dificultad) {
    	
    	if (dificultad == 1) {
            tablero = new Tablero(8, 8, 10);
        } else if (dificultad == 2) {
            tablero = new Tablero(12, 12, 20);
        } else {
            tablero = new Tablero(16, 16, 40);
        }
        
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
        	} else if (opcion == 2) {
        		tablero.marcar(fila, columna);
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
