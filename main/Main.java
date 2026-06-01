package main;

import java.util.Scanner;
import clases.partida;

public class Main {
	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        partida partida = new partida();

        System.out.println("====== BUSCAMINAS ======");
        System.out.println("1. Fácil");
        System.out.println("2. Media");
        System.out.println("3. Difícil");

        int dificultad = sc.nextInt();

        partida.nuevaPartida(dificultad);

        partida.ejecutarPartida();

        sc.close();
    }
}
