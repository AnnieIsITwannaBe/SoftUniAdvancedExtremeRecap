package Exams.JavaAdvancedRetakeExam12August2024;

import java.util.Scanner;

public class CollectingStarsGameToBeContinued {
    private static final char PLAYER = 'P';
    private static final char STAR = '*';
    private static final char OBSTACLE = '#';
    private static final char EMPTY_CELL = '-';
    private static final int STARS_NEEDED = 10;
    private static int STARS_COLLECTED = 0;
    private static final int SINGLE_STAR_VALUE = 1;

    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    private static final String PLAYER_LOOSES_ALL_STARS_DISPLAY_MESSAGE = "Game over! You are out of any stars.\n";
    private static final String PLAYER_WON_AND_COLLECTED_ALL_STARTS_NEEDED_DISPLAY_MESSAGE = "You won! You have collected 10 stars.\n";
    private static final String PLAYER_FINAL_POSITION_FINAL_MESSAGE = "Your final position is [%d, %d]\n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimension = Integer.parseInt(scanner.nextLine());

        char[][] sky = new char[dimension][dimension];

        populateSky(sky, scanner);

        printSky(sky);

        int[] playerCoordinates = locatePlayerCoordinatesOnSky(sky);

        while (STARS_COLLECTED < STARS_NEEDED) {

            String command = scanner.nextLine();

            switch (command) {
                case UP:
                    move(-1, 0, sky, playerCoordinates);
                    break;

                case DOWN:
                    move(+1, 0, sky, playerCoordinates);
                    break;

                case LEFT:
                    move(0, -1, sky, playerCoordinates);
                    break;

                case RIGHT:
                    move(0, +1, sky, playerCoordinates);
                    break;
            }
        }

        if (STARS_NEEDED == STARS_COLLECTED) {
            System.out.println(PLAYER_WON_AND_COLLECTED_ALL_STARTS_NEEDED_DISPLAY_MESSAGE);
        }

        System.out.printf(PLAYER_FINAL_POSITION_FINAL_MESSAGE, playerCoordinates[0], playerCoordinates[1]);
    }

    private static void printSky(char[][] sky) {
        for (int row = 0; row < sky.length; row++) {
            for (int col = 0; col < sky[row].length; col++) {
                System.out.print(sky[row][col] + " ");
            }
            System.out.println();

        }
    }

    private static void move(int row, int col, char[][] sky, int[] playerCoordinates) {
        int newRow = playerCoordinates[0] + row;
        int newCol = playerCoordinates[1] + col;

        if (isInBound(sky, newRow, newCol)) {
            char currentCellContent = sky[newRow][newCol];

            switch (currentCellContent) {
                case STAR:
                    STARS_COLLECTED += SINGLE_STAR_VALUE;
                    break;
                case OBSTACLE:
                    //looses all the stars collected up until now:
                    STARS_COLLECTED = 0;
                    System.out.println(PLAYER_LOOSES_ALL_STARS_DISPLAY_MESSAGE);
                    break;
            }

            sky[newRow][newCol] = PLAYER;

            sky[playerCoordinates[0]][playerCoordinates[1]] = EMPTY_CELL;

            playerCoordinates[0] = newRow;
            playerCoordinates[1] = newCol;
        }
    }

    private static boolean isInBound(char[][] sky, int newRow, int newCol) {
        return newRow >= 0 && newRow < sky.length && newCol >= 0 && newCol < sky[newRow].length;
    }

    private static int[] locatePlayerCoordinatesOnSky(char[][] sky) {
        int[] playerCoordinates = new int[2];
        for (int row = 0; row < sky.length; row++) {
            for (int col = 0; col < sky[row].length; col++) {
                if (sky[row][col] == PLAYER) {
                    playerCoordinates = new int[]{row, col};
                }
            }
        }
        return playerCoordinates;
    }

    private static void populateSky(char[][] sky, Scanner scanner) {
        for (int row = 0; row < sky.length; row++) {
            String currentLine = scanner.nextLine();
            int colCounter = 0;
            for (int col = 0; colCounter < sky[row].length && col < currentLine.length(); col++) {
                char currentCellContent = currentLine.charAt(col);
                if (!Character.isWhitespace(currentCellContent)) {
                    sky[row][colCounter] = currentCellContent;
                    colCounter++;
                }
            }
        }
    }
}
