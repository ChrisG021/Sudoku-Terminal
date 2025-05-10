package main;

import java.util.Scanner;
import model.SudokuBoard;

public class SudokuGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SudokuBoard board = new SudokuBoard();

        while (true) {
            board.printBoard();
            System.out.println("Digite linha (0-8), coluna (0-8) e valor (1-9), ou -1 para sair:");

            System.out.print("Linha: ");
            int row = scanner.nextInt();
            if (row == -1) break;

            System.out.print("Coluna: ");
            int col = scanner.nextInt();
            if (col == -1) break;

            System.out.print("Valor: ");
            int val = scanner.nextInt();
            if (val == -1) break;

            if (board.setCell(row, col, val)) {
                System.out.println("Valor inserido com sucesso.");
                if (board.isComplete()) {
                    board.printBoard();
                    System.out.println("Parabéns! Você completou o Sudoku corretamente!");
                    break;
                }
            } else {
                System.out.println("Movimento inválido! Tente novamente.");
            }
        }

        System.out.println("Jogo finalizado.");
        scanner.close();
    }
}
