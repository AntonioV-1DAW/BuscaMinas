package clases;

import java.util.Random;

public class tablero {
	private celda[][] celdas;
	private int filas;
	private int columnas;
	private int minas;
	
	public tablero(int filas, int columnas, int minas) {
		this.filas = filas;
		this.columnas = columnas;
		this.minas = minas;
		
		celdas = new celda[filas][columnas];
		
		for (int i=0; i<filas; i++) {
			for(int j=0; j<columnas; j++) {
				celdas[i][j] = new celda();
			}
		}
	}
	
	public void inicializarTablero() {
		Random random = new Random();
		int minasColocadas = 0;
		
		while (minasColocadas < minas) {
			int fila = random.nextInt(filas);
			int columna = random.nextInt(columnas);
			if(!celdas[fila][columna].isMina()) {
				celdas[fila][columna].setMina(true);
				minasColocadas++;
			}
		}
		calcularMinasCercanas();
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
                		int nuevaFila = i + x;
                		int nuevaCol = j + y;
                		if (nuevaFila >= 0 && nuevaFila < filas && nuevaCol >= 0 && nuevaCol < columnas && celdas[nuevaFila][nuevaCol].isMina()) {
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
        celda celda = celdas[fila][columna];

        if (celda.isDescubierta() || celda.isMarcada()) {
            return;
        }
        celda.setDescubierta(true);

        if (!celda.isMina() && celda.isMinaCerca() == 0) {

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    descubrir(fila + i, columna + j);
                }
            }
        }
    }

    public void marcar(int fila, int columna) {
        celda celda = celdas[fila][columna];

        if (!celda.isDescubierta()) {
            celda.setMarcada(!celda.isMarcada());
        }
    }

    public boolean comprobarVictoria() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!celdas[i][j].isMina() && !celdas[i][j].isDescubierta()) {
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
                celda celda = celdas[i][j];
                if (mostrarTodo && celda.isMina()) {
                    System.out.print("* ");
                } else if (celda.isMarcada()) {
                    System.out.print("F ");
                } else if (!celda.isDescubierta()) {
                    System.out.print("# ");
                } else if (celda.isMinaCerca() == 0) {
                    System.out.print(". ");
                } else {
                	System.out.print(celda.isMinaCerca() + " ");
                }
            }
            System.out.println();
        }
    }
}
