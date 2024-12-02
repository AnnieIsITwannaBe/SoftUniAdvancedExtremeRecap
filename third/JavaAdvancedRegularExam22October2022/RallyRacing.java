package Exams.JavaAdvancedRegularExam22October2022;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RallyRacing {

    //needs to be edited:
    private static final String TERMINATING_COMMAND = "End";
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final char CAR = 'C';
    private static final char FINISH_LINE = 'F';
    private static final char TUNNEL = 'T';

    private static final char EMPTY_SPACE = '.';
    private static int KILOMETERS_CAR_PASSED = 0;
    private static final int KILOMETERS_AMOUNT_PER_EACH_TURN = 10;
    private static final int KILOMETERS_AMOUNT_AFTER_PASSING_TROUGH_TUNNEL = 30;

    private static boolean FINISH_LINE_IS_REACHED = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimensions = Integer.parseInt(scanner.nextLine());
        int racingNumberOfTrackedCar = Integer.parseInt(scanner.nextLine());

        char[][] raceField = populateRaceField(dimensions, scanner);

        printCurrentStateOfField(raceField);

        //if we go through one, we exit through the other:
        List<int[]> coordinatesOfTwoTunnels = locateTunnelsOnBoard(raceField);

        //there is no need to explicitly initialize the coordinates, since the default values are 0 anyway:
        int[] carCoordinates = new int[2];


        String userInput = scanner.nextLine();

        while (!TERMINATING_COMMAND.equals(userInput)) {
            String command = scanner.nextLine();

            switch (command) {
                case UP:
                    move(-1, 0, raceField, carCoordinates, coordinatesOfTwoTunnels);
                    break;

                case DOWN:
                    move(+1, 0, raceField, carCoordinates, coordinatesOfTwoTunnels);
                    break;

                case LEFT:
                    move(0, -1, raceField, carCoordinates, coordinatesOfTwoTunnels);
                    break;

                case RIGHT:
                    move(0, +1, raceField, carCoordinates, coordinatesOfTwoTunnels);
                    break;
            }

            if (FINISH_LINE_IS_REACHED) {
                break;
            }

            printCurrentStateOfField(raceField);
            System.out.println();
        }
    }

    private static void printCurrentStateOfField(char[][] raceField) {
        for (char[] chars : raceField) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    private static List<int[]> locateTunnelsOnBoard(char[][] raceField) {
        List<int[]> coordinatesOfTwoTunnels = new ArrayList<>();
        for (int row = 0; row < raceField.length; row++) {
            for (int col = 0; col < raceField[row].length; col++) {
                if (raceField[row][col] == TUNNEL) {
                    coordinatesOfTwoTunnels.add(new int[]{row, col});
                }
            }
        }
        return coordinatesOfTwoTunnels;
    }

    private static void move(int row, int col, char[][] raceField, int[] carCoordinates, List<int[]> coordinatesOfTwoTunnels) {
        int newRow = carCoordinates[0] + row;
        int newCol = carCoordinates[1] + col;

        if (isInBound(raceField, newRow, newCol)) {
            char currentCellContent = raceField[newRow][newCol];

            switch (currentCellContent) {
                case FINISH_LINE:
                    FINISH_LINE_IS_REACHED = true;
                    break;

                case TUNNEL:
                    int[] currentTunnel = null;
                    int[] otherTunnel = null;

                    // Determine which tunnel the car entered
                    for (int[] coordinates : coordinatesOfTwoTunnels) {
                        if (coordinates[0] == newRow && coordinates[1] == newCol) {
                            currentTunnel = coordinates; // This is the current tunnel
                        } else {
                            otherTunnel = coordinates; // This is the other tunnel
                        }
                    }

                    //disappears from the current position:
                    raceField[newRow][newCol] = EMPTY_SPACE;

                    if (currentTunnel != null && otherTunnel != null) {
                        // the car goes out of the other tunnel: appears here
                        raceField[otherTunnel[0]][otherTunnel[1]] = CAR;

                        //update coordinates of the car particularly for this case:
                        carCoordinates[0] = otherTunnel[0];
                        carCoordinates[1] = otherTunnel[1];
                    }

                    KILOMETERS_CAR_PASSED += KILOMETERS_AMOUNT_AFTER_PASSING_TROUGH_TUNNEL;
                    break;

                case EMPTY_SPACE:
                    KILOMETERS_CAR_PASSED += KILOMETERS_AMOUNT_PER_EACH_TURN;
                    break;
            }

            if (currentCellContent != TUNNEL) {
                raceField[newRow][newCol] = CAR;
                raceField[carCoordinates[0]][carCoordinates[1]] = EMPTY_SPACE;

                carCoordinates[0] = newRow;
                carCoordinates[1] = newCol;
            }
        }
    }

    private static boolean isInBound(char[][] raceField, int newRow, int newCol) {
        return newRow >= 0 && newRow < raceField.length && newCol >= 0 && newCol < raceField[newRow].length;
    }

    private static char[][] populateRaceField(int dimensions, Scanner scanner) {
        char[][] raceField = new char[dimensions][dimensions];
        for (int row = 0; row < raceField.length; row++) {
            String currentLine = scanner.nextLine();
            int colCount = 0;
            for (int col = 0; col < currentLine.length() && colCount < raceField[row].length; col++) {
                char currentCellContent = currentLine.charAt(col);
                if (!Character.isWhitespace(currentCellContent)) {
                    raceField[row][colCount] = currentCellContent;
                    colCount++;
                }
            }
        }
        return raceField;
    }
}
