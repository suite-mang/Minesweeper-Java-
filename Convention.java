package minesweeper;

import java.util.HashSet;
import java.util.Random;

class Convention{
    private final int WIDTH = 9;
    private final int HEIGHT = 9;
    private final int totalMine;

    public Convention(int totalMine) {
        this.totalMine = totalMine;

    }

    protected String[][] createField(){
        String[][] field = new String[9][9];
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                field[row][col] = ".";
            }
        }
        return field;
    }
    protected String[][] addMine(String[][] minefield){
        Random random = new Random();
        HashSet<String> set = new HashSet<>();
        do{
            int row = random.nextInt(WIDTH);
            int column = random.nextInt(HEIGHT);
            String rowCOl=row + "," +column;
            if (!set.contains(rowCOl)){
                set.add(rowCOl);
                minefield[row][column] = "X";
            }
        }while (totalMine > set.size());
        return minefield;
    }


}
