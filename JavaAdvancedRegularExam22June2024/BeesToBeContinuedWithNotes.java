package Exams.JavaAdvancedRegularExam22June2024;

import java.util.Scanner;

public class BeesToBeContinuedWithNotes {
    private static final char BEE = 'B';
    private static final char HIVE = 'H';
    private static final char EMPTY_CELL = '-';
    private static int BEE_ENERGY_PER_UNITS = 15;
    private static final int SINGLE_MOVE_ENERGY_DECREASE_PER_UNIT = 1;
    private static final int UNITS_OF_NECTAR_REQUIRED_TO_MAKE_HONEY = 30;
    private static int UNITS_OF_NECTAR_COLLECTED = 0;

    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    private static final String BEE_RAN_OUT_OF_ENERGY = "This is the end! Beesy ran out of energy.";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimensions = Integer.parseInt(scanner.nextLine());

        char[][] beeField = new char[dimensions][dimensions];

        populateBeeField(beeField, scanner);

        int[] beeCoordinates = locateBeeOnField(beeField);

        String userInput = scanner.nextLine();

        while (UNITS_OF_NECTAR_COLLECTED < UNITS_OF_NECTAR_REQUIRED_TO_MAKE_HONEY) {

            String command = userInput;

            switch (command) {
                case UP:
                    moveBee(-1, 0, beeField, beeCoordinates);
                    break;

                case DOWN:
                    moveBee(+1, 0, beeField, beeCoordinates);
                    break;

                case LEFT:
                    moveBee(0, -1, beeField, beeCoordinates);
                    break;

                case RIGHT:
                    moveBee(0, +1, beeField, beeCoordinates);
                    break;
            }


            if (BEE_ENERGY_PER_UNITS <= 0) {
                System.out.println(BEE_RAN_OUT_OF_ENERGY);
                break;
            }
            userInput = scanner.nextLine();
        }

    }

    private static void moveBee(int row, int col, char[][] beeField, int[] beeCoordinates) {
        int newRow = beeCoordinates[0] + row;
        int newCol = beeCoordinates[1] + col;

        if (isInBound(beeField, newRow, newCol)) {
            char currentCellContent = beeField[newRow][newCol];

            if (currentCellContent == HIVE) {
            } else {
                if (Character.isDigit(currentCellContent)) {
                    int nectarFromFlowers = Character.getNumericValue(currentCellContent);
                    UNITS_OF_NECTAR_COLLECTED += nectarFromFlowers;

                    beeField[newRow][newCol] = EMPTY_CELL;

                    beeField[beeCoordinates[0]][beeCoordinates[1]] = EMPTY_CELL;
                }
            }
        } else {
            //in our case in particular we only move with one move at a time
            //•	If the bee leaves the field (goes out of the boundaries of the matrix) depending on the move command,
            // it will be moved to the opposite side of the field:

            // modulo arithmetic or conditional logic
            //модуларна аритметика https://www.geeksforgeeks.org/modular-arithmetic/

            // Handle out-of-bounds movement (wrapping to the opposite side)
            if (newRow < 0) {
                newRow = beeField.length - 1; // Wrap to last row
            } else if (newRow >= beeField.length) {
                newRow = 0; // Wrap to first row
            }

            if (newCol < 0) {
                newCol = beeField[newRow].length - 1; // Wrap to last column
            } else if (newCol >= beeField[newRow].length) {
                newCol = 0; // Wrap to first column
            }

        }
        beeCoordinates[0] = newRow;
        beeCoordinates[1] = newCol;

        BEE_ENERGY_PER_UNITS -= SINGLE_MOVE_ENERGY_DECREASE_PER_UNIT;
    }

    private static boolean isInBound(char[][] beeField, int newRow, int newCol) {
        return newRow >= 0 && newRow < beeField.length && newCol >= 0 && newCol < beeField[newRow].length;
    }

    private static int[] locateBeeOnField(char[][] beeField) {
        int[] beeCoordinates = new int[2];
        for (int row = 0; row < beeField.length; row++) {
            for (int col = 0; col < beeField[row].length; col++) {
                if (beeField[row][col] == BEE) {
                    beeCoordinates = new int[]{row, col};
                }
            }
        }
        return beeCoordinates;
    }

    private static void populateBeeField(char[][] beeField, Scanner scanner) {
        for (int row = 0; row < beeField.length; row++) {
            String currentLine = scanner.nextLine();
            for (int col = 0; col < beeField[row].length; col++) {
                beeField[row][col] = currentLine.charAt(col);
            }
        }
    }
}
