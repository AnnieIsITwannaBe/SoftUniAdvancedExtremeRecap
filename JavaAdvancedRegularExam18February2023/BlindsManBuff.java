package Exams.JavaAdvancedRegularExam18February2023;

import java.util.Arrays;
import java.util.Scanner;

public class BlindsManBuff {
    private static final String TERMINATING_COMMAND = "Finish";
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    private static final char BLINDFOLDED_PLAYER = 'B';
    private static final char FURNITURE_OBSTACLE = '0';
    private static final char PLAYER_OPPONENT = 'P';
    private static final char EMPTY_POSITION = '-';

    private static final int OPPONENTS_COUNT = 3;
    private static int MOVES_MADE = 0;
    private static int TOUCHED_OPPONENTS_COUNT = 0;

    private static final String FINAL_MESSAGE = "Game over!\nTouched opponents: %d Moves made: %d";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] playFieldDimensions = getIntStream(scanner);

        char[][] playField = populatePlayField(playFieldDimensions, scanner);

        int[] blindFoldedPlayerCoordinates = locatePlayerOnField(playField);

        String command = scanner.nextLine();

        while (!TERMINATING_COMMAND.equals(command)) {
            switch (command) {
                case UP:
                    movePlayer(-1, 0, playField, blindFoldedPlayerCoordinates);
                    break;

                case DOWN:
                    movePlayer(+1, 0, playField, blindFoldedPlayerCoordinates);

                    break;

                case LEFT:
                    movePlayer(0, -1, playField, blindFoldedPlayerCoordinates);

                    break;

                case RIGHT:
                    movePlayer(0, +1, playField, blindFoldedPlayerCoordinates);

                    break;
            }

            if (TOUCHED_OPPONENTS_COUNT == OPPONENTS_COUNT) {
                break;
            }
            command = scanner.nextLine();
        }

        System.out.printf(FINAL_MESSAGE, TOUCHED_OPPONENTS_COUNT, MOVES_MADE);

    }

    private static void movePlayer(int row, int col, char[][] playField, int[] blindFoldedPlayerCoordinates) {
        int newRow = blindFoldedPlayerCoordinates[0] + row;
        int newCol = blindFoldedPlayerCoordinates[1] + col;

        if (isInBound(playField, newRow, newCol)) {
            char currentCellContent = playField[newRow][newCol];

            switch (currentCellContent) {
                case FURNITURE_OBSTACLE:
                    // we do not move: we keep currentPositionUnadulterated
                    keepCurrentPosition(playField, blindFoldedPlayerCoordinates);
                    break;
                case PLAYER_OPPONENT:
                    //as per problem description:
                    TOUCHED_OPPONENTS_COUNT += 1;
                    playField[newRow][newCol] = EMPTY_POSITION;
                    break;
                case EMPTY_POSITION:
                    MOVES_MADE++;
                    break;
            }

            if (currentCellContent != FURNITURE_OBSTACLE) {
                playField[blindFoldedPlayerCoordinates[0]][blindFoldedPlayerCoordinates[1]] = EMPTY_POSITION;

                blindFoldedPlayerCoordinates[0] = newRow;
                blindFoldedPlayerCoordinates[1] = newCol;
            }
        }
    }

    private static void keepCurrentPosition(char[][] playField, int[] blindFoldedPlayerCoordinates) {
        playField[blindFoldedPlayerCoordinates[0]][blindFoldedPlayerCoordinates[1]] = BLINDFOLDED_PLAYER;
    }

    private static boolean isInBound(char[][] playField, int newRow, int newCol) {
        return newRow >= 0 && newRow < playField.length && newCol >= 0 && newCol < playField[newRow].length;
    }

    private static int[] locatePlayerOnField(char[][] playField) {
        int[] blindFoldedPlayerCoordinates = new int[2];
        for (int row = 0; row < playField.length; row++) {
            for (int col = 0; col < playField[row].length; col++) {
                if (playField[row][col] == BLINDFOLDED_PLAYER) {
                    blindFoldedPlayerCoordinates = new int[]{row, col};
                }
            }
        }
        return blindFoldedPlayerCoordinates;
    }

    private static int[] getIntStream(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
    }

    private static char[][] populatePlayField(int[] playFieldDimensions, Scanner scanner) {
        char[][] playField = new char[playFieldDimensions[0]][playFieldDimensions[1]];
        for (int row = 0; row < playField.length; row++) {
            String currentLine = scanner.nextLine();
            int colCounter = 0;
            for (int col = 0; col < currentLine.length() && colCounter < playField[row].length; col++) {
                char currentCellContent = currentLine.charAt(col);
                if (!Character.isWhitespace(currentCellContent)) {
                    playField[row][colCounter] = currentCellContent;
                    colCounter++;
                }
            }
        }
        return playField;
    }
}
