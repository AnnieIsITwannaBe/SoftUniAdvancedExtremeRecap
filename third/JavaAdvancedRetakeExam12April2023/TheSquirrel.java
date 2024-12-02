package Exams.JavaAdvancedRetakeExam12April2023;

import java.util.Scanner;

public class TheSquirrel {
    private static final String REGEX_SPLITERATOR = ",\\s+";

    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    private static final char SQUIRREL = 's';
    private static final char HAZELNUT = 'h';
    private static final char EMPTY_POSITION = '*';
    private static final char TRAP = 'T';

    private static final int HAZELNUTS_REQUIRED = 3;

    private static int HAZELNUTS_COLLECTED = 0;
    private static final int HAZELNUT_QUANTITY_PER_ENCOUNTER = 1;

    private static final String SQUIRREL_IS_OUT_OF_FIELD_DISPLAY_MESSAGE = "The squirrel is out of the field.\n";

    private static final String SQUIRREL_STEPPED_ON_TRAP_MESSAGE = "Unfortunately, the squirrel stepped on a trap...\n";

    private static final String SQUIRREL_HAS_MORE_HAZELNUTS_TO_COLLECT_DISPLAY_MESSAGE = "There are more hazelnuts to collect.\n";

    private static final String SQUIRREL_HAS_COLLECTED_ALL_HAZELNUTS = "Good job! You have collected all hazelnuts!\n";

    public static void main(String[] args) {
        //•	s - represents the squirrel's position.
        //•	h – represents a hazelnut.
        //•	* – the asterisk represents an empty position.
        //•	t – represents a trap.
        Scanner scanner = new Scanner(System.in);
        int fieldDimension = Integer.parseInt(scanner.nextLine());
        String[] directions = scanner.nextLine().split(REGEX_SPLITERATOR);

        char[][] field = populateField(scanner, fieldDimension);

        displayCurrentStateOfField(field);

        int[] squirrelCoordinates = locateSquirrelOnField(field);

        for (String direction : directions) {
            switch (direction) {
                case UP:
                    moveSquirrel(-1, 0, field, squirrelCoordinates);
                    break;

                case DOWN:
                    moveSquirrel(+1, 0, field, squirrelCoordinates);
                    break;

                case LEFT:
                    moveSquirrel(0, -1, field, squirrelCoordinates);
                    break;

                case RIGHT:
                    moveSquirrel(0, +1, field, squirrelCoordinates);
                    break;
            }
        }

        if (HAZELNUTS_COLLECTED < HAZELNUTS_REQUIRED) {
            System.out.println(SQUIRREL_HAS_MORE_HAZELNUTS_TO_COLLECT_DISPLAY_MESSAGE);
        }

        if (HAZELNUTS_COLLECTED == HAZELNUTS_REQUIRED) {
            System.out.println(SQUIRREL_HAS_COLLECTED_ALL_HAZELNUTS);
        }
    }

    private static void displayCurrentStateOfField(char[][] field) {
        for (char[] chars : field) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    private static void moveSquirrel(int row, int col, char[][] field, int[] squirrelCoordinates) {
        int newRow = squirrelCoordinates[0] + row;
        int newCol = squirrelCoordinates[1] + col;

        if (isInBound(field, newRow, newCol)) {
            char currentCellContent = field[newRow][newCol];

            switch (currentCellContent) {
                case HAZELNUT:
                    HAZELNUTS_COLLECTED += HAZELNUT_QUANTITY_PER_ENCOUNTER;
                    //as per description, if it were to me to decide i would have gone with == Squirrel
                    field[newRow][newCol] = EMPTY_POSITION;
                    break;
                case TRAP:
                    System.out.println(SQUIRREL_STEPPED_ON_TRAP_MESSAGE);
                    displayCurrentStateOfField(field);
                    System.exit(0);
                    break;

                default:
                    field[newRow][newCol] = SQUIRREL;
            }
            //eliminate trace:
            field[squirrelCoordinates[0]][squirrelCoordinates[1]] = EMPTY_POSITION;

            //update the new coordinates:
            squirrelCoordinates[0] = newRow;
            squirrelCoordinates[1] = newCol;

        } else {
            System.out.println(SQUIRREL_IS_OUT_OF_FIELD_DISPLAY_MESSAGE);
            System.exit(0);
        }
    }

    private static boolean isInBound(char[][] field, int newRow, int newCol) {
        return newRow >= 0 && newRow < field.length && newCol >= 0 && newCol < field.length;
    }

    private static int[] locateSquirrelOnField(char[][] field) {
        int[] squirrelCoordinates = new int[2];
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                if (field[row][col] == SQUIRREL) {
                    squirrelCoordinates = new int[]{row, col};
                }
            }
        }
        return squirrelCoordinates;
    }

    private static char[][] populateField(Scanner scanner, int dimension) {
        char[][] field = new char[dimension][dimension];
        for (int row = 0; row < field.length; row++) {
            String currentLine = scanner.nextLine();
            for (int col = 0; col < field[row].length; col++) {
                field[row][col] = currentLine.charAt(col);
            }
        }
        return field;
    }
}
