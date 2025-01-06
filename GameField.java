package minesweeper;

import java.util.Objects;

public class GameField{

    protected String[][] countingMine(String[][] minefield){
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if(Objects.equals(minefield[row][column], ".")){
                    int counter = 0;
                    switch (setDirection(row , column)){

                        case MIDDLE:
                            counter += top(minefield, row, column);
                            counter += bottom(minefield, row, column);
                            counter += left(minefield, row, column);
                            counter += right(minefield, row, column);
                            counter += bottomDiagonalLeft(minefield, row, column);
                            counter += bottomDiagonalRight(minefield, row, column);
                            counter += topDiagonalLeft(minefield, row, column);
                            counter += topDiagonalRight(minefield, row, column);
                            if (counter > 0){
                                minefield[row][column] = String.valueOf(counter);
                            }
                            break;
                        case CONNER:
                            if (row == 0 && column== 0){
                                counter += right(minefield, row, column);
                                counter += bottom(minefield, row, column);
                                counter += bottomDiagonalRight(minefield, row, column);
                            } else if (row == 0 && column== 8) {
                                counter += left(minefield, row, column);
                                counter += bottom(minefield, row, column);
                                counter += bottomDiagonalLeft(minefield, row, column);
                            }else if (row == 8 && column== 0) {
                                    counter += right(minefield, row, column);
                                    counter += top(minefield, row, column);
                                    counter += topDiagonalRight(minefield, row, column);
                            } else {
                                    counter += left(minefield, row, column);
                                    counter += top(minefield, row, column);
                                    counter += topDiagonalLeft(minefield, row, column);
                            }

                            if (counter > 0){
                                minefield[row][column] = String.valueOf(counter);
                            }
                            break;
                        case SIDE:
                            if (row == 0 && column < 8){
                                counter += left(minefield, row, column);
                                counter += right(minefield, row, column);
                                counter += bottom(minefield, row, column);
                                counter += bottomDiagonalLeft(minefield, row, column);
                                counter += bottomDiagonalRight(minefield, row, column);
                            }else if (row == 8 && column < 8){
                                counter += left(minefield, row, column);
                                counter += right(minefield, row, column);
                                counter += top(minefield, row, column);
                                counter += topDiagonalLeft(minefield, row, column);
                                counter += topDiagonalRight(minefield, row, column);
                            } else if (row < 8 && column == 0) {
                                counter += top(minefield, row, column);
                                counter += topDiagonalRight(minefield, row, column);
                                counter += right(minefield, row, column);
                                counter += bottom(minefield, row, column);
                                counter += bottomDiagonalRight(minefield, row, column);
                            }else {
                                counter += top(minefield, row, column);
                                counter += topDiagonalLeft(minefield, row, column);
                                counter += left(minefield, row, column);
                                counter += bottom(minefield, row, column);
                                counter += bottomDiagonalLeft(minefield, row, column);
                            }

                            if (counter > 0){
                                minefield[row][column] = String.valueOf(counter);
                            }
                            break;

                        default:
                                break;
                    }
                }
            }
        }
        return minefield;
    }

    private Directions setDirection(int row, int column){
        if((row == 0 || row == 8) && (column == 0 || column == 8)){
            return Directions.CONNER;
        }else if ((row > 0 && (column ==0 ||column ==8))
                || (row == 0 || row == 8) && column > 0){
            return Directions.SIDE;
        }
        return Directions.MIDDLE;
    }
    private int top(String[][] minefield, int row, int column){
        if(Objects.equals(minefield[row - 1][column], "X")){
            return 1;
        }
        return 0;
    }
    private int bottom(String[][] minefield, int row, int column){
        if(Objects.equals(minefield[row + 1][column], "X")){
            return 1;
        }
        return 0;
    }

    private int left(String[][] minefield, int row, int column){
        if(Objects.equals(minefield[row ][column - 1], "X")){
            return 1;
        }
        return 0;
    }
    private int right(String[][] minefield, int row, int column){
        if(Objects.equals(minefield[row ][column + 1], "X")){
            return 1;
        }
        return 0;
    }
    private int bottomDiagonalLeft(String[][] minefield, int row, int column){
        if(Objects.equals(minefield[row + 1][column - 1], "X")){
            return 1;
        }
        return 0;
    }
    private int bottomDiagonalRight(String[][] minefield, int row, int column){
        if(Objects.equals(minefield[row + 1][column + 1], "X")){
            return 1;
        }
        return 0;
    }
    private int topDiagonalLeft(String[][] minefield, int row, int column){
        if(Objects.equals(minefield[row - 1][column - 1], "X")){
            return 1;
        }
        return 0;
    }
    private int topDiagonalRight(String[][] minefield, int row, int column){
        if(Objects.equals(minefield[row - 1][column + 1], "X")){
            return 1;
        }
        return 0;
    }



}
