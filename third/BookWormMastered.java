package Exams.third;

import java.util.Scanner;

public class BookWormMastered {
    private static final String END_COMMAND = "end";
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

        StringBuilder updatedText = new StringBuilder();
        updatedText.append(initialString);

        String userInput = scanner.nextLine();

        while (!END_COMMAND.equals(userInput)) {
            String playerDirection = userInput;

            switch (playerDirection) {
                case LEFT:
                    playerCoordinates = handleMovement(playerCoordinates[0], playerCoordinates[1] - 1, matrix, updatedText, playerCoordinates);
                    //make the previous position of the player a position of an empty cell:
                    resetPlayerPreviousPosition(matrix, playerCoordinates);
                    break;

                case RIGHT:
                    playerCoordinates = handleMovement(playerCoordinates[0], playerCoordinates[1] + 1, matrix, updatedText, playerCoordinates);
                    resetPlayerPreviousPosition(matrix, playerCoordinates);
                    break;

                case DOWN:
                    playerCoordinates = handleMovement(playerCoordinates[0] + 1, playerCoordinates[1], matrix, updatedText, playerCoordinates);
                    resetPlayerPreviousPosition(matrix, playerCoordinates);
                    break;

                case UP:
                    playerCoordinates = handleMovement(playerCoordinates[0] - 1, playerCoordinates[1], matrix, updatedText, playerCoordinates);
                    resetPlayerPreviousPosition(matrix, playerCoordinates);
                    break;
            }
            userInput = scanner.nextLine();
        }

        System.out.println(updatedText.toString());
        printMatrixCurrentState(matrix);
    }

    private static void resetPlayerPreviousPosition(char[][] matrix, int[] playerCoordinates) {
        matrix[playerCoordinates[0]][playerCoordinates[1]] = EMPTY_CELL;
    }

    private static void printMatrixCurrentState(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");

            }
            System.out.println();
        }
    }

    private static int[] handleMovement(int row, int col, char[][] matrix, StringBuilder updatedText, int[] playerCoordinates) {
        if (isInBound(row, col, matrix)) {
            if (matrix[row][col] != EMPTY_CELL) {

                char newValue = matrix[row][col];
                //add it at the end of the initial text at the beginning:
                updatedText.append(newValue);

                //set the new position to the position of the player:
                matrix[row][col] = PLAYER;

                //update the player coordinates:
                playerCoordinates = new int[]{row, col};
            }
        }
        return playerCoordinates;
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
