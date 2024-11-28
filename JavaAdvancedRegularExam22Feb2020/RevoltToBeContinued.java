package Exams.JavaAdvancedRegularExam22Feb2020;

import java.util.Scanner;

public class RevoltToBeContinued {
    private static final Character PLAYER = 'f';
    private static final char TRAP = 'T';
    private static final char BONUS = 'B';
    private static final char EXIT = 'F';
    private static final Character EMPTY_CELL = '-';

    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int matrixDimensions = Integer.parseInt(scanner.nextLine());
        int numberOfCommands = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[matrixDimensions][matrixDimensions];

        populateMatrix(matrix, scanner);

        int[] playerCoordinates = locatePlayerOnField(matrix);

        //in this particular case we'll have to keep track of the player's position, so that in case we step
        //a trap we move the player back to his original place -> one move behind
        //not a list of all the coordinates, just a prev int[]

        while (matrix[playerCoordinates[0]][playerCoordinates[1]] != EXIT) {

            String direction = scanner.nextLine();

            switch (direction) {
                case UP:
                    movePlayer(-1, 0, matrix, playerCoordinates);
                    break;

                case DOWN:
                    movePlayer(+1, 0, matrix, playerCoordinates);
                    break;

                case LEFT:
                    movePlayer(0, -1, matrix, playerCoordinates);
                    break;

                case RIGHT:
                    movePlayer(0, +1, matrix, playerCoordinates);
                    break;
            }
        }
    }

    private static void movePlayer(int rowMovement, int colMovement, char[][] matrix, int[] playerCoordinates) {
        int newRow = playerCoordinates[0] + rowMovement;
        int newCol = playerCoordinates[1] + colMovement;

        if (isInBound(matrix, newRow, newCol)) {
            char cellContent = matrix[newRow][newCol];

            switch (cellContent) {
                case TRAP:
                    //one move behind
                    break;
                case BONUS:
                    //one move ahead
                    //one step forward in the direction he is going
                    break;
                case EXIT:
                    break;
            }
        }
    }

    private static boolean isInBound(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;

    }
    private static int[] locatePlayerOnField(char[][] matrix) {
        int[] playerCoordinates = new int[2];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == PLAYER) {
                    playerCoordinates = new int[]{row, col};
                    break;
                }
            }
        }
        return playerCoordinates;
    }

    private static void populateMatrix(char[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            String currentLine = scanner.nextLine();
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = currentLine.charAt(col);
            }
        }
    }
}
