package tableroGrafico;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clases.Celda;
import clases.EntradaRanking;
import clases.ModeloRanking;
import clases.Tablero;

public class VentanaBuscaminas extends JFrame{
	private Tablero tableroJuego;
    private JButton[][] botones;
    private JPanel jpnTablero;
    private JLabel lblAyuda;
    
    private int filas;
    private int columnas;
    private int dificultad;
    private boolean primerClick = true;
    
    private ModeloRanking modeloRanking = new ModeloRanking();


    public VentanaBuscaminas() {
    	dificultad = elegirDificultad();
    	
    	if (dificultad == 0) {
            filas = 8; columnas = 8;
            tableroJuego = new Tablero(filas, columnas, 10);
        } else if (dificultad == 1) {
            filas = 12; columnas = 12;
            tableroJuego = new Tablero(filas, columnas, 20);
        } else {
            filas = 16; columnas = 16;
            tableroJuego = new Tablero(filas, columnas, 40);
        }

        botones = new JButton[filas][columnas];
        
        setTitle("Buscaminas");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //TABLERO
        jpnTablero = new JPanel();
        jpnTablero.setLayout(new GridLayout(filas, columnas));
        add(jpnTablero, BorderLayout.CENTER);

        //AYUDA
        lblAyuda = new JLabel("Clic izquierdo = Descubrir | Clic derecho = Marcar bandera",JLabel.CENTER);
        add(lblAyuda, BorderLayout.SOUTH);
        
        crearBotones();
        setVisible(true);
    }
    
    private int elegirDificultad() {
        String[] opciones = {"Fácil","Media","Difícil"};
        return JOptionPane.showOptionDialog(null,"Selecciona dificultad","Buscaminas",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
    }
    
    private void crearBotones() {

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                JButton boton = new JButton();
                botones[fila][columna] = boton;
                
                int f = fila;
                int c = columna;

                boton.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (e.getButton() == MouseEvent.BUTTON1) {
                            descubrirCasilla(f, c);
                        }

                        if (e.getButton() == MouseEvent.BUTTON3) {
                            marcarCasilla(f, c);
                        }
                    }
                });

                jpnTablero.add(boton);
            }
        }
    }

    private void descubrirCasilla(int fila, int columna) {
    	 //PRIMER CLICK
        if (primerClick) {
            tableroJuego.colocarMinasPrimerClick(fila, columna);
            primerClick = false;
        }
    	
    	tableroJuego.descubrir(fila, columna);
        actualizarVista();

        if (tableroJuego.hayMina(fila, columna)) {
            String nombre = JOptionPane.showInputDialog(this,"Nombre jugador");
            EntradaRanking entrada = new EntradaRanking(nombre,elegirDificultad(),0);

            modeloRanking.guardarResultado(entrada);
            modeloRanking.cerrar();

            JOptionPane.showMessageDialog(this, "HAS PERDIDO");
            dispose();
            return;
        }

        if (tableroJuego.comprobarVictoria()) {
        	String nombre = JOptionPane.showInputDialog(this, "Nombre jugador:");
            EntradaRanking entrada = new EntradaRanking(nombre,elegirDificultad(),1);

            modeloRanking.guardarResultado(entrada);
            modeloRanking.cerrar();

            JOptionPane.showMessageDialog(this, "HAS GANADO");
            dispose();
        }
    }
    
    private void marcarCasilla(int fila, int columna) {
        tableroJuego.marcar(fila, columna);

        Celda c = tableroJuego.getCelda(fila, columna);

        if (c.isMarcada()) {
            botones[fila][columna].setText("F");
        } else {
            botones[fila][columna].setText("");
        }
    }

    private void actualizarVista() {

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {

                Celda c = tableroJuego.getCelda(fila, columna);

                if (c.isDescubierta()) {
                	System.out.println("[" + fila + "," + columna + "] -> " + c.getMinaCerca());
                    if (c.isMina()) {
                        botones[fila][columna].setText("*");
                    } else if (c.getMinaCerca() == 0) {
                        botones[fila][columna].setText("");
                    } else {
                        botones[fila][columna].setText(String.valueOf(c.getMinaCerca()));
                    }
                    botones[fila][columna].setEnabled(false);
                }
            }
        }
    }
}
