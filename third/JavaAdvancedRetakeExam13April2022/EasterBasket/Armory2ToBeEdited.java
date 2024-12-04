package Exams.JavaAdvancedRetakeExam13April2022.EasterBasket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Armory2ToBeEdited {
        private static final char ARMY_OFFICER = 'A';
        private static final char MIRROR = 'M';
        private static final char EMPTY_CELL = '-';
        private static final String UP = "up";
        private static final String DOWN = "down";
        private static final String LEFT = "left";
        private static final String RIGHT = "right";
        private static final int GOLDEN_BLADE_REQUIRED_VALUE = 65;
        private static int CURRENT_VALUE_ACCUMULATION_OF_GOLDEN_BLADE = 0;
        private static char[][] armoryField;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int dimensions = Integer.parseInt(scanner.nextLine());

            armoryField = populateField(scanner, dimensions);
            int[] armoryOfficerCoordinates = locateArmyOfficer(armoryField);
            Map<Integer, int[]> mirrorCoordinates = findTwoMirrorPortals(armoryField);

            while (CURRENT_VALUE_ACCUMULATION_OF_GOLDEN_BLADE < GOLDEN_BLADE_REQUIRED_VALUE) {
                String command = scanner.nextLine();

                switch (command) {
                    case UP:
                        moveArmoryOfficer(-1, 0, armoryOfficerCoordinates, mirrorCoordinates);
                        break;
                    case DOWN:
                        moveArmoryOfficer(+1, 0, armoryOfficerCoordinates, mirrorCoordinates);
                        break;
                    case LEFT:
                        moveArmoryOfficer(0, -1, armoryOfficerCoordinates, mirrorCoordinates);
                        break;
                    case RIGHT:
                        moveArmoryOfficer(0, +1, armoryOfficerCoordinates, mirrorCoordinates);
                        break;
                }
            }
        }

        private static Map<Integer, int[]> findTwoMirrorPortals(char[][] armoryField) {
            Map<Integer, int[]> mirrorCoordinates = new HashMap<>();
            int counter = 1;
            for (int row = 0; row < armoryField.length; row++) {
                for (int col = 0; col < armoryField[row].length; col++) {
                    if (armoryField[row][col] == MIRROR) {
                        mirrorCoordinates.put(counter++, new int[]{row, col});
                    }
                }
            }
            return mirrorCoordinates;
        }

        private static void moveArmoryOfficer(int rowDelta, int colDelta, int[] officerCoordinates, Map<Integer, int[]> mirrorCoordinates) {
            int newRow = officerCoordinates[0] + rowDelta;
            int newCol = officerCoordinates[1] + colDelta;

            if (!isInBound(newRow, newCol)) {
                System.out.println("I do not need more swords!");
                printExitMessage();
                System.exit(0);
            }

            char cellContent = armoryField[newRow][newCol];
            armoryField[officerCoordinates[0]][officerCoordinates[1]] = EMPTY_CELL; // Clear old position

            if (cellContent == MIRROR) {
                teleport(officerCoordinates, mirrorCoordinates, newRow, newCol);
            } else if (Character.isDigit(cellContent)) {
                CURRENT_VALUE_ACCUMULATION_OF_GOLDEN_BLADE += Character.getNumericValue(cellContent);
                armoryField[newRow][newCol] = EMPTY_CELL;
            }

            officerCoordinates[0] = newRow;
            officerCoordinates[1] = newCol;
            armoryField[officerCoordinates[0]][officerCoordinates[1]] = ARMY_OFFICER;

            if (CURRENT_VALUE_ACCUMULATION_OF_GOLDEN_BLADE >= GOLDEN_BLADE_REQUIRED_VALUE) {
                System.out.println("Very nice swords, I will come back for more!");
                printExitMessage();
                System.exit(0);
            }
        }

        private static void teleport(int[] officerCoordinates, Map<Integer, int[]> mirrorCoordinates, int row, int col) {
            Iterator<Map.Entry<Integer, int[]>> iterator = mirrorCoordinates.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, int[]> entry = iterator.next();
                int[] portalCoordinates = entry.getValue();
                if (portalCoordinates[0] == row && portalCoordinates[1] == col) {
                    iterator.remove();
                    armoryField[row][col] = EMPTY_CELL;
                } else {
                    officerCoordinates[0] = portalCoordinates[0];
                    officerCoordinates[1] = portalCoordinates[1];
                }
            }
        }
        private static boolean isInBound(int newRow, int newCol) {
            return newRow >= 0 && newRow < armoryField.length && newCol >= 0 && newCol < armoryField[newRow].length;
        }

        private static int[] locateArmyOfficer(char[][] armoryField) {
            for (int row = 0; row < armoryField.length; row++) {
                for (int col = 0; col < armoryField[row].length; col++) {
                    if (armoryField[row][col] == ARMY_OFFICER) {
                        return new int[]{row, col};
                    }
                }
            }
            throw new IllegalStateException("No army officer found on the field!");
        }

        private static void printExitMessage() {
            System.out.println("The king paid " + CURRENT_VALUE_ACCUMULATION_OF_GOLDEN_BLADE + " gold coins.");
            System.out.println("Final state of the armory:");
            printArmoryState();
        }

        private static void printArmoryState() {
            for (char[] row : armoryField) {
                for (char cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        }

        private static char[][] populateField(Scanner scanner, int dimensions) {
            char[][] field = new char[dimensions][dimensions];
            for (int row = 0; row < dimensions; row++) {
                String line = scanner.nextLine();
                for (int col = 0; col < dimensions; col++) {
                    field[row][col] = line.charAt(col);
                }
            }
            return field;
        }
    }


