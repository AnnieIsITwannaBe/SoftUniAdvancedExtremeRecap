package Exams.JavaAdvancedRegularExam21October2023;

import java.util.Scanner;

public class FishingCompetitionToBeCompleted {
    private static final String FINAL_COMMAND = "collect the nets";
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final char SEA_MAN = 'S';
    private static final char WHIR_POOL = 'W';
    private static final char EMPTY_CELL = '-';

    private static final String SHIP_FELL_INTO_A_WHIR_POOL = "You fell into a whirlpool! The ship sank and you lost the fish you caught. Last coordinates of the ship: %d %d \n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimensions = Integer.parseInt(scanner.nextLine());
        char[][] sea = new char[dimensions][dimensions];

        populateSeaData(sea, scanner);

        int[] seaManCoordinates = locateSeaManOnGrid(sea);

        String userInput = scanner.nextLine();

        int fishCollected = 0;

        while (!FINAL_COMMAND.equals(userInput)) {

            String direction = userInput;

            fishCollected = switch (direction) {
                case UP -> moveFisherMan(-1, 0, sea, seaManCoordinates, fishCollected);
                case DOWN -> moveFisherMan(+1, 0, sea, seaManCoordinates, fishCollected);
                case LEFT -> moveFisherMan(0, -1, sea, seaManCoordinates, fishCollected);
                case RIGHT -> moveFisherMan(0, +1, sea, seaManCoordinates, fishCollected);
                default -> fishCollected;
            };
            userInput = scanner.nextLine();
        }
    }

    private static int moveFisherMan(int row, int col, char[][] sea, int[] seaManCoordinates, int fishCollected) {
        int newRow = seaManCoordinates[0] + row;
        int newCol = seaManCoordinates[1] + col;

        //switch case requires constant values
        if (isInBound(sea, newRow, newCol)) {
            char currentCellContent = sea[newRow][newCol];

            //predefined constant
            if (currentCellContent == WHIR_POOL) {
                //handle whirPoll logic: which is the ship sinks
                System.out.printf(SHIP_FELL_INTO_A_WHIR_POOL, newRow, newCol);
                System.exit(0);
            } else {
                if (Character.isDigit(currentCellContent)) {
                    int numericValue = Character.getNumericValue(currentCellContent);
                    fishCollected += numericValue;

                    //the passage disappears and should be replaced by '-'.
                    sea[newRow][newCol] = EMPTY_CELL;
                }
            }
        }
        return fishCollected;
    }

    private static boolean isInBound(char[][] sea, int newRow, int newCol) {
        return newRow >= 0 && newRow < sea.length && newCol >= 0 && newCol < sea.length;
    }

    private static int[] locateSeaManOnGrid(char[][] sea) {
        int[] seaManDimensions = new int[2];
        for (int row = 0; row < sea.length; row++) {
            for (int col = 0; col < sea[row].length; col++) {
                if (sea[row][col] == SEA_MAN) {
                    seaManDimensions = new int[]{row, col};
                }
            }
        }
        return seaManDimensions;
    }

    private static void populateSeaData(char[][] sea, Scanner scanner) {
        for (int row = 0; row < sea.length; row++) {
            String currentLine = scanner.nextLine();
            for (int col = 0; col < currentLine.length(); col++) {
                sea[row][col] = currentLine.charAt(col);
            }
        }
    }
}

//think about how this is supposed to happen:
//•	If you leave the fishing area (go out of the boundaries of the matrix)
// depending on the move command you will be moved to the opposite side of the one you were on.