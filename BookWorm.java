package Exams.third;

import java.io.BufferedReader;
import java.util.Scanner;

public class BookWorm {
    private static final String END_COMMAND = "End";
    private static final Character PLAYER = 'P';
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String UP = "up";
    private static final String DOWN = "down";

    private static final Character EMPTY_CELL = '-';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String initialString = scanner.nextLine();
        int dimensions = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[dimensions][dimensions];

        populateMatrix(matrix, scanner);

        int[] playerCoordinates = findPlayerPositionOnField(matrix);

        String userInput = scanner.nextLine();

        while (!END_COMMAND.equals(userInput)) {
            String playerDirection = userInput;

            StringBuilder updatedText = new StringBuilder();
            updatedText.append(initialString);

            switch (playerDirection) {
                case LEFT:
                    handleMovement(playerCoordinates[0], playerCoordinates[1] - 1, matrix, updatedText);
                    break;
                case RIGHT:
                    handleMovement(playerCoordinates[0], playerCoordinates[1] + 1, matrix, updatedText);
                    break;
                case DOWN:
                    handleMovement(playerCoordinates[0] + 1, playerCoordinates[1], matrix, updatedText);
                    break;
                case UP:
                    handleMovement(playerCoordinates[0] - 1, playerCoordinates[1], matrix, updatedText);
                    break;

            }
            userInput = scanner.nextLine();
        }
    }

    private static void handleMovement(int row, int col, char[][] matrix, StringBuilder updatedText) {
        if (isInBound(row, col, matrix)) {
            if (matrix[row][col] != EMPTY_CELL) {

                //add it at the end of the initial text at the beginning:
                updatedText.append(matrix[row][col]);

                //make it disappear from the matrix:
                matrix[row][col] = EMPTY_CELL;
            }
        }
    }

    private static boolean isInBound(int row, int col, char[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static int[] findPlayerPositionOnField(char[][] matrix) {
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
            String currentRow = scanner.nextLine();
            for (int col = 0; col < currentRow.length(); col++) {
                matrix[row][col] = currentRow.charAt(col);
            }
        }
    }
}
