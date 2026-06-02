package clases;

import java.util.Random;

public class Tablero {
    private Celda[][] celdas;
    private int filas;
    private int columnas;
    private int minas;
    private boolean minasColocadas = false;

    public Tablero(int filas, int columnas, int minas) {
        this.filas = filas;
        this.columnas = columnas;
        this.minas = minas;

        celdas = new Celda[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda();
            }
        }
    }

    public void reiniciar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) { 
                celdas[i][j].setMina(false);
                celdas[i][j].setDescubierta(false);
                celdas[i][j].setMarcada(false);
                celdas[i][j].setMinaCerca(0);
            }
        }
        minasColocadas = false;
    }

    public void inicializarTablero() {
        reiniciar();
    }

    public void colocarMinasPrimerClick(int filaInicial, int columnaInicial) {
        if (minasColocadas) {
        	return;
        }

        Random random = new Random();
        int colocadas = 0;

        while (colocadas < minas) {

            int f = random.nextInt(filas);
            int c = random.nextInt(columnas);

            if ((f - filaInicial <= 1 && f - filaInicial >= -1) && (c - columnaInicial <= 1 && c - columnaInicial >= -1)) {
                continue;
            }

            if (!celdas[f][c].isMina()) {
            	celdas[f][c].setMina(true);
                colocadas++;
            }
        }
        calcularMinasCercanas();
        minasColocadas = true;
    }

    public void calcularMinasCercanas() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                if (celdas[i][j].isMina()) {
                	continue;
                }
                
                int contador = 0;

                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {

                        int nf = i + x;
                        int nc = j + y;

                        if (nf >= 0 && nf < filas && nc >= 0 && nc < columnas && celdas[nf][nc].isMina()) {
                            contador++;
                        }
                    }
                }

                celdas[i][j].setMinaCerca(contador);
            }
        }
    }

    public void descubrir(int fila, int columna) {

        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return;
    	}
    
        Celda celda = celdas[fila][columna];

        if (celda.isDescubierta() || celda.isMarcada()) {
            return;
		}
        celda.setDescubierta(true);

        if (!celda.isMina() && celda.getMinaCerca() == 0) {

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {

                    if (i == 0 && j == 0) {
                    	continue;
                    }
                    descubrir(fila + i, columna + j);
                }
            }
        }
    }

    public void marcar(int fila, int columna) {
        Celda celda = celdas[fila][columna];
        
        if (!celda.isDescubierta()) {
            celda.setMarcada(!celda.isMarcada());
        }
    }

    public boolean comprobarVictoria() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                if (!celdas[i][j].isMina() &&
                    !celdas[i][j].isDescubierta()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hayMina(int fila, int columna) {
        return celdas[fila][columna].isMina();
    }

    public void representarTablero(boolean mostrarTodo) {
        System.out.print("   ");
        for (int j = 0; j < columnas; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < filas; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < columnas; j++) {
                Celda celda = celdas[i][j];

                if (mostrarTodo && celda.isMina()) {
                    System.out.print("* ");
                } else if (celda.isMarcada()) {
                    System.out.print("F ");
                } else if (!celda.isDescubierta()) {
                    System.out.print("# ");
                } else if (celda.getMinaCerca() == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(celda.getMinaCerca() + " ");
                }
            }

            System.out.println();
        }
    }

    public Celda getCelda(int fila, int columna) {
        return celdas[fila][columna];
    }
}