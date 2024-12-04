package Exams.JavaAdvancedRetakeExam13April2022;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Armory {
    private static final char ARMY_OFFICER = 'A';

    private static final char MIRROR = 'M';

    private static final char EMPTY_CELL = '-';

    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    private static final int GOLDEN_BLADE_REQUIRED_VALUE = 65;
    private static int CURRENT_VALUE_ACCUMULATION_OF_GOLDEN_BLADE = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimensions = Integer.parseInt(scanner.nextLine());

        char[][] armoryField = populateField(scanner, dimensions);

        int[] armoryOfficerCoordinates = locateArmyOfficer(armoryField);

        // List<int[]> mirrorPortalsCoordinates = findTwoMirrorPortals(armoryField);

        Map<Integer, int[]> mirrorCoordinates = findTwoMirrorPortals(armoryField);

        printArmoryState(armoryField);

        while (CURRENT_VALUE_ACCUMULATION_OF_GOLDEN_BLADE < GOLDEN_BLADE_REQUIRED_VALUE) {
            String command = scanner.nextLine();

            switch (command) {
                case UP:
                    moveArmoryOfficer(-1, 0, armoryField, armoryOfficerCoordinates, mirrorCoordinates);
                    break;

                case DOWN:
                    moveArmoryOfficer(+1, 0, armoryField, armoryOfficerCoordinates, mirrorCoordinates);
                    break;

                case LEFT:
                    moveArmoryOfficer(0, -1, armoryField, armoryOfficerCoordinates, mirrorCoordinates);
                    break;

                case RIGHT:
                    moveArmoryOfficer(0, +1, armoryField, armoryOfficerCoordinates, mirrorCoordinates);
                    break;
            }
        }

        printArmoryState(armoryField);
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

//    private static List<int[]> findTwoMirrorPortals(char[][] armoryField) {
//        List<int[]> mirrorPortalsCoordinates = new ArrayList<>();
//        for (int row = 0; row < armoryField.length; row++) {
//            for (int col = 0; col < armoryField[row].length; col++) {
//                if (armoryField[row][col] == MIRROR) {
//                    mirrorPortalsCoordinates.add(new int[]{row, col});
//                }
//            }
//        }
//        return mirrorPortalsCoordinates;
//    }

    private static void moveArmoryOfficer(int row, int col, char[][] armoryField, int[] armoryOfficerCoordinates, Map<Integer, int[]> mirrorPortalCoordinates) {
        int newRow = armoryOfficerCoordinates[0] + row;
        int newCol = armoryOfficerCoordinates[1] + col;

        if (isInBound(armoryField, newRow, newCol)) {
            char currentCellContent = armoryField[newRow][newCol];

            if (currentCellContent == MIRROR) {
                //The teleport method iterates over a map and removes the current portal,
                // but modifying a collection while iterating can cause a ConcurrentModificationException
                //тва е доста тъп начин да се направи, но за друг в момента не знам:
                teleport(mirrorPortalCoordinates, newRow, newCol, armoryField);

                goTroughTheOtherPortal(armoryOfficerCoordinates, mirrorPortalCoordinates);

                //place the officer on the other side of the matrix:
                armoryField[armoryOfficerCoordinates[0]][armoryOfficerCoordinates[1]] = ARMY_OFFICER;
            } else {
                if (Character.isDigit(currentCellContent)) {
                    int goldenBladeValue = Character.getNumericValue(currentCellContent);

                    CURRENT_VALUE_ACCUMULATION_OF_GOLDEN_BLADE += goldenBladeValue;
                }

                armoryField[newRow][newCol] = ARMY_OFFICER;
                armoryOfficerCoordinates[0] = newRow;
                armoryOfficerCoordinates[1] = newCol;
            }
        }
    }

    private static void teleport(Map<Integer, int[]> mirrorPortalCoordinates, int newRow, int newCol, char[][] armyField) {

        Iterator<Map.Entry<Integer, int[]>> iterator = mirrorPortalCoordinates.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, int[]> entry = iterator.next();
            int[] portalCoordinates = entry.getValue();
            if (portalCoordinates[0] == newRow && portalCoordinates[1] == newCol) {
                iterator.remove();
                armyField[newRow][newCol] = EMPTY_CELL;
            }
        }

//in order to circumvent the concurrent
//        for (Map.Entry<Integer, int[]> entry : mirrorPortalCoordinates.entrySet()) {
//            int[] portalCoordinates = entry.getValue();
//            Integer portalNumber = entry.getKey();
//            for (int i = 0; i < portalCoordinates.length; i++) {
//                if (portalCoordinates[0] == newRow && portalCoordinates[1] == newCol) {
//                    //remove the current:
//                    mirrorPortalCoordinates.remove(portalNumber);
//                    //remove the portal from the field as well:
//                    armyField[newRow][newCol] = EMPTY_CELL;
//                }
//            }
//        }
    }
    private static void goTroughTheOtherPortal(int[] armoryOfficerCoordinates, Map<Integer, int[]> mirrorPortalCoordinates) {
        for (Map.Entry<Integer, int[]> entry : mirrorPortalCoordinates.entrySet()) {
            int[] otherPortalCoordinates = entry.getValue();
            for (int i = 0; i < otherPortalCoordinates.length; i++) {

                armoryOfficerCoordinates[0] = otherPortalCoordinates[0];
                armoryOfficerCoordinates[1] = otherPortalCoordinates[1];
            }
        }
    }

    private static boolean isInBound(char[][] armoryField, int newRow, int newCol) {
        return newRow >= 0 && newRow < armoryField.length && newCol >= 0 && newCol < armoryField[newRow].length;
    }

    private static int[] locateArmyOfficer(char[][] armoryField) {
        int[] armoryFieldCoordinates = new int[2];
        for (int row = 0; row < armoryField.length; row++) {
            for (int col = 0; col < armoryField[row].length; col++) {
                if (armoryField[row][col] == ARMY_OFFICER) {
                    armoryFieldCoordinates = new int[]{row, col};
                }
            }
        }
        return armoryFieldCoordinates;
    }

    private static void printArmoryState(char[][] armorySize) {
        for (char[] chars : armorySize) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }

    private static char[][] populateField(Scanner scanner, int dimensions) {
        char[][] armorySize = new char[dimensions][dimensions];
        for (int row = 0; row < armorySize.length; row++) {
            String currentLine = scanner.nextLine();
            for (int col = 0; col < armorySize[row].length; col++) {
                armorySize[row][col] = currentLine.charAt(col);
            }
        }
        return armorySize;
    }
}
