package Exams.JavaAdvancedRetakeExam9August2023;

import java.util.Arrays;
import java.util.Scanner;

public class DeliveryBoy {
    private static final String SPLITERATOR_REGEX = "\\s+";
    private static final char DELIVERY_BOY = 'B';
    private static final char PIZZA_DELIVERY_ADDRESS = 'A';
    private static final char OBSTACLE = '*';
    private static final char PIZZA_RESTAURANT = 'P';

    private static final char PIZZA_RESTAURANT_CHECK = 'R';
    private static final char DELIVERY_BOY_PATH_TRACE = '.';

    private static boolean PIZZA_IS_COLLECTED = false;
    private static boolean PIZZA_IS_DELIVERED = false;

    private static boolean SKIP_A_MOVE = false;
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    private static final String PIZZA_IS_COLLECTED_10_MIN_DELIVERY_DISPLAY_MESSAGE = "Pizza is collected. 10 minutes for delivery";

    private static final String DELIVERY_IS_LATE_ORDER_IS_CANCELLED = "The delivery is late. Order is canceled";

    private static final String PIZZA_IS_SUCCESSFULLY_DELIVERED_DISPLAY_MESSAGE = "Pizza is delivered on time! Next order...\n";

    private static final String YOU_HAVE_HIT_AN_OBSTACLE_YOU_SKIP_A_MOVE_MESSAGE = "You need to change direction and the current move is wasted!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] fieldDimensions = getIntStream(scanner);

        int rows = fieldDimensions[0];
        int cols = fieldDimensions[1];

        char[][] neighbourhood = populateNeighbourhood(rows, cols, scanner);

        int[] deliveryBoyCoordinates = locateDeliveryBoySpawnPosition(neighbourhood);

        //you must keep the initial position because you will need it for later:
        int originalPositionRow = deliveryBoyCoordinates[0];
        int originalPositionCol = deliveryBoyCoordinates[1];

        //first step is to go to restaurant:
        //The delivery boy will always collect the pizza ('P'),
        //before heading to the address, marked as 'A', ensuring a successful delivery

        getPizzaFromRestaurant(scanner, neighbourhood, deliveryBoyCoordinates);

        deliverPizzaToAddress(scanner, neighbourhood, deliveryBoyCoordinates);

        printNeighbourhoodCurrentState(neighbourhood);
    }

    private static void deliverPizzaToAddress(Scanner scanner, char[][] neighbourhood, int[] deliveryBoyCoordinates) {
        while (true) {
            String command = scanner.nextLine();

            handleMovementAcrossGrid(command, neighbourhood, deliveryBoyCoordinates);

            if (PIZZA_IS_DELIVERED) {
                System.out.println(PIZZA_IS_SUCCESSFULLY_DELIVERED_DISPLAY_MESSAGE);
                break;
            }
        }
    }

    private static void getPizzaFromRestaurant(Scanner scanner, char[][] neighbourhood, int[] deliveryBoyCoordinates) {
        while (true) {
            String command = scanner.nextLine();

            handleMovementAcrossGrid(command, neighbourhood, deliveryBoyCoordinates);

            if (PIZZA_IS_COLLECTED) {
                System.out.print(PIZZA_IS_COLLECTED_10_MIN_DELIVERY_DISPLAY_MESSAGE);
                break;
            }
        }
    }

    private static void handleMovementAcrossGrid(String command, char[][] neighbourhood, int[] deliveryBoyCoordinates) {
        switch (command) {
            case UP:
                moveDeliveryBoy(-1, 0, neighbourhood, deliveryBoyCoordinates);
                break;

            case DOWN:
                moveDeliveryBoy(+1, 0, neighbourhood, deliveryBoyCoordinates);
                break;

            case LEFT:
                moveDeliveryBoy(0, -1, neighbourhood, deliveryBoyCoordinates);
                break;

            case RIGHT:
                moveDeliveryBoy(0, +1, neighbourhood, deliveryBoyCoordinates);
                break;
        }
    }

    private static void moveDeliveryBoy(int row, int col, char[][] neighbourhood, int[] deliveryBoyCoordinates) {

        int newRow = deliveryBoyCoordinates[0] + row;
        int newCol = deliveryBoyCoordinates[1] + col;

        if (isInBound(neighbourhood, newRow, newCol)) {
            char currentCellContent = neighbourhood[newRow][newCol];

            switch (currentCellContent) {
                case PIZZA_RESTAURANT:
                    PIZZA_IS_COLLECTED = true;
                    //assign value as per context of problem
                    neighbourhood[newRow][newCol] = PIZZA_RESTAURANT_CHECK;
                    break;

                case PIZZA_DELIVERY_ADDRESS:
                    //you cannot deliver pizza if you haven't previously collected it from restaurant:
                    if (PIZZA_IS_COLLECTED) {
                        PIZZA_IS_DELIVERED = true;
                        //assign value as per context of
                        // problem(i explicitly mention this only because i find it to make zero sense)
                        neighbourhood[newRow][newCol] = PIZZA_RESTAURANT;
                    }
                    break;

                case OBSTACLE:
                    SKIP_A_MOVE = true;
                    System.out.println(YOU_HAVE_HIT_AN_OBSTACLE_YOU_SKIP_A_MOVE_MESSAGE);
                    break;

                default:
                    neighbourhood[newRow][newCol] = DELIVERY_BOY;
            }
        } else {
            System.out.println(DELIVERY_IS_LATE_ORDER_IS_CANCELLED);
            printNeighbourhoodCurrentState(neighbourhood);
            System.exit(0);
        }


        if (!SKIP_A_MOVE) {
            //take care of delivery boy trace
            if (neighbourhood[deliveryBoyCoordinates[0]][deliveryBoyCoordinates[1]] != PIZZA_RESTAURANT_CHECK) {
                neighbourhood[deliveryBoyCoordinates[0]][deliveryBoyCoordinates[1]] = DELIVERY_BOY_PATH_TRACE;
            }
            deliveryBoyCoordinates[0] = newRow;
            deliveryBoyCoordinates[1] = newCol;
        }
    }

    private static boolean isInBound(char[][] neighbourhood, int newRow, int newCol) {
        return newRow >= 0 && newRow < neighbourhood.length && newCol >= 0 && newCol < neighbourhood[newRow].length;
    }

    private static int[] getIntStream(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split(SPLITERATOR_REGEX)).mapToInt(Integer::parseInt).toArray();
    }

    private static int[] locateDeliveryBoySpawnPosition(char[][] neighbourhood) {
        int[] deliveryBoyCoordinates = new int[2];
        for (int row = 0; row < neighbourhood.length; row++) {
            for (int col = 0; col < neighbourhood[row].length; col++) {
                if (neighbourhood[row][col] == DELIVERY_BOY) {
                    deliveryBoyCoordinates = new int[]{row, col};
                }
            }
        }
        return deliveryBoyCoordinates;
    }

    private static void printNeighbourhoodCurrentState(char[][] neighbourhood) {
        for (char[] chars : neighbourhood) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    private static char[][] populateNeighbourhood(int rows, int cols, Scanner scanner) {
        char[][] neighbourhood = new char[rows][cols];
        for (int row = 0; row < neighbourhood.length; row++) {
            String currentLine = scanner.nextLine();
            for (int col = 0; col < neighbourhood[row].length; col++) {
                neighbourhood[row][col] = currentLine.charAt(col);
            }
        }
        return neighbourhood;
    }
}

//Overall Rating:
//Code Readability: 8/10
//
//Clear structure but could benefit from removing redundant logic.
//Robustness: 7/10
//
//Handles boundaries and special cases well, but global variables and minor bugs in logic (boundary condition) could lead to unexpected behavior.
//Maintainability: 6/10
//
//Redundant methods and global state hinder scalability. Encapsulation and method parameterization could improve this.
//With some minor bug fixes and restructuring, this code can be even more robust and maintainable!