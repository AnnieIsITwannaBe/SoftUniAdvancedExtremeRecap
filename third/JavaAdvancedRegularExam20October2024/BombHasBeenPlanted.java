package Exams.JavaAdvancedRegularExam20October2024;

import java.util.Arrays;
import java.util.Scanner;

public class BombHasBeenPlanted {
    private static final char COUNTER_TERRORIST = 'C';
    private static final char TERRORIST = 'T';
    private static final char BOMB = 'B';

    private static final char DIFFUSED_BOMB = 'D';

    private static final char DEAD_TERRORIST = 'X';
    private static final char EMPTY_CELL = '*';

    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final int TIME_PASSING_PER_EACH_TURN_IN_SECONDS = 1;
    private static final int UNUSED_DIFFUSED_COMMAND_PASSING_TIME_IN_SECONDS = 2;
    private static final String DIFFUSE = "diffuse";
    private static final String COUNTER_TERRORIST_TRIES_TO_STEP_OUTSIDE_THE_MAP_WARNING_MESSAGE = "Time keeps tickin' away\n";
    private static final String TERRORIST_WON_AND_DEFUSED_BOMB_DISPLAY_MESSAGE = "Counter-terrorist wins!\nBomb has been defused: %d second/s remaining.\n";
    private static final String TERRORIST_KILLED_COUNTER_TERRORIST_AND_WON_GAME = "Terrorists win!\n";
    private static final int TIME_TO_DIFFUSE_THE_BOMB_IN_SECONDS = 4;
    private static int TIME_TO_COMPLETE_MISSION_IN_SECONDS = 16;
    private static boolean BOMB_IS_READY_TO_BE_DIFFUSED = false;
    private static boolean BOMB_IS_SUCCESSFULLY_DIFFUSED = false;
    private static boolean COUNTER_TERRORIST_IS_KILLED_BY_TERRORIST = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int rows = dimensions[0];
        int cols = dimensions[1];

        char[][] layout = new char[rows][cols];

        populateMap(layout, scanner);

        displayCurrentStateOfLayout(layout);

        int[] counterTerroristCoordinates = locateCounterTerroristOnField(layout);

        while (TIME_TO_COMPLETE_MISSION_IN_SECONDS > 0) {

            if (COUNTER_TERRORIST_IS_KILLED_BY_TERRORIST) {
                break;
            }

            String command = scanner.nextLine();

            //note to self: you can simplify the code by adding this to the diffuse switch case command:
            if (BOMB_IS_READY_TO_BE_DIFFUSED && command.equals(DIFFUSE)) {
                int timeRemaining = TIME_TO_COMPLETE_MISSION_IN_SECONDS - TIME_TO_DIFFUSE_THE_BOMB_IN_SECONDS;

                if (timeRemaining >= 0) {
                    BOMB_IS_SUCCESSFULLY_DIFFUSED = true;
                    layout[counterTerroristCoordinates[0]][counterTerroristCoordinates[1]] = DIFFUSED_BOMB;
                } else {
                    layout[counterTerroristCoordinates[0]][counterTerroristCoordinates[1]] = DEAD_TERRORIST;
                }
                displayCurrentStateOfLayout(layout);
                break;

            } else if (!BOMB_IS_READY_TO_BE_DIFFUSED && command.equals(DIFFUSE)) {
                TIME_TO_COMPLETE_MISSION_IN_SECONDS -= UNUSED_DIFFUSED_COMMAND_PASSING_TIME_IN_SECONDS;
            }

            switch (command) {
                case UP:
                    move(-1, 0, layout, counterTerroristCoordinates);
                    break;

                case DOWN:
                    move(+1, 0, layout, counterTerroristCoordinates);
                    break;

                case LEFT:
                    move(0, -1, layout, counterTerroristCoordinates);
                    break;

                case RIGHT:
                    move(0, +1, layout, counterTerroristCoordinates);
                    break;
            }
        }

        if (BOMB_IS_SUCCESSFULLY_DIFFUSED) {
            System.out.printf(TERRORIST_WON_AND_DEFUSED_BOMB_DISPLAY_MESSAGE, TIME_TO_COMPLETE_MISSION_IN_SECONDS);
        }

        if (COUNTER_TERRORIST_IS_KILLED_BY_TERRORIST) {
            System.out.println(TERRORIST_KILLED_COUNTER_TERRORIST_AND_WON_GAME);
        }
    }

    private static void displayCurrentStateOfLayout(char[][] layout) {
        for (char[] chars : layout) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }

    private static void move(int row, int col, char[][] layout, int[] counterTerroristCoordinates) {
        int newRow = counterTerroristCoordinates[0] + row;
        int newCol = counterTerroristCoordinates[1] + col;

        if (isInBound(layout, newRow, newCol)) {
            char currentCellContent = layout[newRow][newCol];

            switch (currentCellContent) {
                case TERRORIST:
                    COUNTER_TERRORIST_IS_KILLED_BY_TERRORIST = true;
                    break;
                case BOMB:
                    BOMB_IS_READY_TO_BE_DIFFUSED = true;
                    break;
            }
        } else {
            System.out.println(COUNTER_TERRORIST_TRIES_TO_STEP_OUTSIDE_THE_MAP_WARNING_MESSAGE);
        }
        TIME_TO_COMPLETE_MISSION_IN_SECONDS -= TIME_PASSING_PER_EACH_TURN_IN_SECONDS;
    }

    private static boolean isInBound(char[][] layout, int newRow, int newCol) {
        return newRow >= 0 && newRow < layout.length && newCol >= 0 && newCol < layout[newRow].length;
    }

    private static int[] locateCounterTerroristOnField(char[][] filedLayout) {
        int[] counterTerroristCoordinates = new int[2];
        for (int row = 0; row < filedLayout.length; row++) {
            for (int col = 0; col < filedLayout[row].length; col++) {
                if (filedLayout[row][col] == COUNTER_TERRORIST) {
                    counterTerroristCoordinates = new int[]{row, col};
                }
            }
        }
        return counterTerroristCoordinates;
    }

    private static void populateMap(char[][] filedLayout, Scanner scanner) {
        for (int row = 0; row < filedLayout.length; row++) {
            String currentLine = scanner.nextLine();
            for (int col = 0; col < filedLayout[row].length; col++) {
                filedLayout[row][col] = currentLine.charAt(col);
            }
        }
    }
}
//only this is currently missing:
//•	If the bomb has exploded OR the counter-terrorist was defusing and did NOT have enough time, calculate the time needed for successful defuse and print on the console:
//o	"Terrorists win!"
//o	"Bomb was not defused successfully!"
//o	"Time needed: {needed_time_for_bomb_defuse} second/s."