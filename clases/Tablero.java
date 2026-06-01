package clases;

import java.util.Random;

public class Tablero {
	private Celda[][] celdas;
	private int filas;
	private int columnas;
	private int minas;
	
	public Tablero(int filas, int columnas, int minas) {
		this.filas = filas;
		this.columnas = columnas;
		this.minas = minas;
		
		celdas = new Celda[filas][columnas];
		
		for (int i=0; i<filas; i++) {
			for(int j=0; j<columnas; j++) {
				celdas[i][j] = new Celda();
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

        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas)
            return;

        Celda celda = celdas[fila][columna];

        if (celda.isDescubierta() || celda.isMarcada()) {
            return;
        }

        celda.setDescubierta(true);

        if (celda.isMinaCerca() == 0 && !celda.isMina()) {

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
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

                Celda c = celdas[i][j];

                if (!c.isMina() && !c.isDescubierta()) {
                    return false;
                }
            }
        }

        return true;
    }
    
    public String representarTablero(boolean mostrarTodo) {
    	String tablero = "   ";

    	for (int i = 0; i < columnas; i++) {
    		tablero += i + " ";
    	}

    	tablero += "\n";

    	for (int i = 0; i < filas; i++) {
    		tablero += i + "  ";
    		for (int j = 0; j < columnas; j++) {
    			Celda c = celdas[i][j];

    			if (mostrarTodo && c.isMina()) {
    				tablero += "* ";
    			} else if (c.isMarcada()) {
    				tablero += "F ";
    			} else if (!c.isDescubierta()) {
    				tablero += "# ";
    			} else if (c.isMinaCerca() == 0) {
                tablero += ". ";
    			} else {
    				tablero += c.isMinaCerca() + " ";
    			}
    		}
    		tablero += "\n";
    	}
    	return tablero;
    }
    
    public Celda[][] getCeldas() {
        return celdas;
    }
}
