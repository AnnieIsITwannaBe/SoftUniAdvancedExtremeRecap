package Exams.JavaAdvancedRegularExam17June2023;

import java.util.Arrays;
import java.util.Scanner;

public class MouseInTheKitchenMatrixToBeContinued {
    private static final String SPLITERATOR_REGEX = "\\s+";
    private static final char MOUSE = 'M';
    private static final char CHEESE = 'C';
    private static final char EMPTY_POSITION = '*';
    private static final char WALL_ON_CUPBOARD = '@';
    private static final char TRAP = 'T';
    private static final String TERMINATE_OPERATION = "danger";
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String CHEESE_IS_OVER_DISPLAY_MESSAGE = "No more cheese for tonight";
    private static final String MOUSE_STEPPED_IN_TRAP_DISPLAY_MESSAGE = "Mouse is trapped";
    private static final String DANGER_BEFORE_EATING_ALL_THE_CHEESE_DISPLAY_MESSAGE = "Mouse will come back later";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine()
                .split(SPLITERATOR_REGEX)).mapToInt(Integer::parseInt).toArray();

        int rows = dimensions[0];
        int cols = dimensions[1];

        char[][] cupBoardArea = new char[rows][cols];

        populateKitchen(cupBoardArea, scanner);

        int[] mouseCoordinates = locateMouseOnGrid(cupBoardArea);

        int totalAmountOfCheese = countCheesePieces(cupBoardArea);

        String userInput = scanner.nextLine();

        while (!TERMINATE_OPERATION.equals(userInput)) {
            String direction = userInput;

            totalAmountOfCheese = switch (direction) {
                case UP -> moveMouse(-1, 0, cupBoardArea, mouseCoordinates, totalAmountOfCheese);
                case DOWN -> moveMouse(+1, 0, cupBoardArea, mouseCoordinates, totalAmountOfCheese);
                case LEFT -> moveMouse(0, -1, cupBoardArea, mouseCoordinates, totalAmountOfCheese);
                case RIGHT -> moveMouse(0, +1, cupBoardArea, mouseCoordinates, totalAmountOfCheese);
                default -> totalAmountOfCheese;
            };

            if (totalAmountOfCheese == 0) {
                System.out.println(CHEESE_IS_OVER_DISPLAY_MESSAGE);
                //print last position of the cat:

            }
            userInput = scanner.nextLine();
        }

        if (totalAmountOfCheese > 0) {
            System.out.println(DANGER_BEFORE_EATING_ALL_THE_CHEESE_DISPLAY_MESSAGE);
        }
    }

    private static int countCheesePieces(char[][] cupBoardArea) {
        int counter = 0;
        for (char[] chars : cupBoardArea) {
            for (char aChar : chars) {
                if (aChar == CHEESE) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static int moveMouse(int row, int col, char[][] cupBoardArea, int[] mouseCoordinates, int totalAmountOfCheese) {
        int newRow = mouseCoordinates[0] + row;
        int newCol = mouseCoordinates[1] + col;

        if (isInBound(cupBoardArea, newRow, newCol)) {
            char currentCellContent = cupBoardArea[newRow][newCol];

            switch (currentCellContent) {
                case CHEESE:
                    totalAmountOfCheese -= 1;
                    //set new position:
                    cupBoardArea[newRow][newCol] = EMPTY_POSITION;
                    //reset prev position of cat:
                    break;
                case WALL_ON_CUPBOARD:
                    //Break out of the method immediately when hitting a wall
                    return totalAmountOfCheese;
                //read more about: https://www.geeksforgeeks.org/unreachable-code-error-in-java/
                case TRAP:
                    System.out.println(MOUSE_STEPPED_IN_TRAP_DISPLAY_MESSAGE);
                    System.exit(0);
                    break;
            }
        } else {
            System.exit(0);
            //the mouse goes out of bound and the cat starts attacking
        }

        cupBoardArea[mouseCoordinates[0]][mouseCoordinates[1]] = EMPTY_POSITION;

        // Update mouseCoordinates with the new position
        //don't do those inside the switch statements:
        mouseCoordinates[0] = newRow;
        mouseCoordinates[1] = newCol;

        return totalAmountOfCheese;
    }

    private static boolean isInBound(char[][] cupBoardArea, int newRow, int newCol) {
        return newRow >= 0 && newRow < cupBoardArea.length && newCol >= 0 && newCol <= cupBoardArea[newRow].length;
    }

    private static int[] locateMouseOnGrid(char[][] cupBoardArea) {
        int[] mouseCoordinates = new int[2];
        for (int row = 0; row < cupBoardArea.length; row++) {
            for (int col = 0; col < cupBoardArea[row].length; col++) {
                if (cupBoardArea[row][col] == MOUSE) {
                    mouseCoordinates = new int[]{row, col};
                }
            }
        }
        return mouseCoordinates;
    }

    private static void populateKitchen(char[][] kitchen, Scanner scanner) {
        for (int row = 0; row < kitchen.length; row++) {
            String currentLine = scanner.nextLine();
            for (int col = 0; col < kitchen[row].length; col++) {
                kitchen[row][col] = currentLine.charAt(col);
            }
        }
    }
}
