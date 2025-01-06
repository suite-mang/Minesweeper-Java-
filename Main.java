package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many mines do you want on the field? ");
        int totalMines= scanner.nextInt();

        Convention convention = new Convention(totalMines);
        GameField gameField = new GameField();
        Game game = new Game();

        String[][] minefield= gameField.countingMine(convention.addMine(convention.createField()));
        String[][] playerField = convention.createField();
        game.gameStart(minefield, playerField ,totalMines);


        scanner.close();


       

    }

}
