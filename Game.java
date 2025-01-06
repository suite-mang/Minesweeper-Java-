package minesweeper;

import java.util.Objects;
import java.util.Scanner;

public class Game{
    protected void gameStart(String[][] mineField,String[][] playerField, int totalMines){
        printField(playerField);
        Scanner scanner = new Scanner(System.in);
        int totalMineFound = 0;
        boolean stepMine = false;
        do{
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            String[] coordinates = scanner.nextLine().split("\\s");
            int row = Integer.parseInt(coordinates[1]) - 1;
            int column = Integer.parseInt(coordinates[0]) - 1;
            String command = coordinates[2];

            switch(command) {
                case "free":
                    if (Objects.equals(mineField[row][column], ".")){
                        floodFill(mineField, playerField,row,column);
                        printField(playerField);
                    } else if(Objects.equals(mineField[row][column], "X")){
                        printField(mineField);
                        System.out.print("You stepped on a mine and failed!\n");
                        stepMine = true;
                    } else {
                        playerField[row][column] = mineField[row][column];
                        printField(playerField);
                    }
                    break;
                case "mine":
                    if (Objects.equals(mineField[row][column], "X")){

                        if (Objects.equals(playerField[row][column], "*")){
                            playerField[row][column] = ".";
                            totalMineFound -= 1;
                        }else{
                            playerField[row][column] = "*";
                            totalMineFound += 1;
                        }
                    }else if (Objects.equals(playerField[row][column], "*")){
                        playerField[row][column] = ".";
                    }else{
                        playerField[row][column] = "*";
                    }

                    printField(playerField);
                    break;
                default:
                    break;
            }
        }while (!stepMine && totalMines > totalMineFound && !isEmptyField(playerField, totalMines ));

        if ( isEmptyField(playerField, totalMines ) || totalMines == totalMineFound ){
            System.out.println("Congratulations! You found all the mines!");
        }

    }

    protected void printField(String[][] emptyField){
        System.out.println();
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int row = 0; row < 9; row++) {
            System.out.print((row + 1) + "|" );
            for (int col = 0; col < 9; col++) {
                System.out.print(emptyField[row][col]);
            }
            System.out.println( "|" );
        }
        System.out.println("-|---------|");
    }

    protected void floodFill(String[][] mineField,String[][] playerField,int row, int column){
        if (!isValidCoordinate(row,column)){
            return;
        }
        if (!Objects.equals(mineField[row][column], ".")){
            playerField[row][column] = mineField[row][column] ;
            return;
        }

        mineField[row][column] = "/";
        playerField[row][column] = "/";
        floodFill(mineField, playerField,row + 1,column);
        floodFill(mineField, playerField,row - 1,column);
        floodFill(mineField, playerField,row, column + 1);
        floodFill(mineField, playerField,row, column - 1);
        floodFill(mineField, playerField,row - 1, column - 1);
        floodFill(mineField, playerField,row - 1, column + 1);
        floodFill(mineField, playerField,row + 1, column - 1);
        floodFill(mineField, playerField,row + 1, column + 1);
    }

    protected boolean isEmptyField(String[][] playerField, int totalMines){
        int counter = 0;
        for(String[] cells : playerField){
            for (String cell : cells){
                if (Objects.equals(cell, ".")){
                    counter += 1;
                }
            }
        }
        return (81 - totalMines) == (81 - counter);
    }
    protected boolean isValidCoordinate(int row, int column){
        return (row >= 0 && row < 9) && (column >= 0 && column < 9);
    }

}
