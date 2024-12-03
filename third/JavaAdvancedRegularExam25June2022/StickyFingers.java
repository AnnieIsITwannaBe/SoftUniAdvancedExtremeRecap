package Exams.JavaAdvancedRegularExam25June2022;

import java.util.Scanner;

public class StickyFingers {
    private static final char REGULAR_POSITION = '+';
    private static final char HOUSE = '$';
    private static final char DILLINGER = 'D';
    private static final char POLICE = 'P';
    private static final char BUSTED_POSITION = '#';

    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static int DILLINGER_STOLEN_MONEY = 0;

    private static boolean DILLINGER_GOT_CAUGHT = false;

    private static final String DILLINGER_SUCCESSFULLY_ROBBED_A_HOUSE = "You successfully stole %d$.\n";
    private static final String DILLINGER_ATTEMPTS_TO_LEAVE_TOWN = "You cannot leave the town, there is police outside!\n";
    private static final String DILLINGER_GOT_CAUGHT_WITH_X_AMOUNT_OF_MONEY_DISPLAY_MESSAGE = "Your last theft has finished successfully with %d$ in your pocket.\n";
    private static final String SUCCESSFUL_THEFT_FINAL_MESSAGE = "Your last theft has finished successfully with %d$ in your pocket.\n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimensions = Integer.parseInt(scanner.nextLine());

        String[] predeterminedDirections = scanner.nextLine().split(",\\s+");

        char[][] field = new char[dimensions][dimensions];

        populateField(field, scanner);

        int[] dillingerCoordinates = locateDillingerOnField(field);

        printCurrentStateOfField(field);

        int sum = 0;
        for (String direction : predeterminedDirections) {
            switch (direction) {
                case UP:
                    moveDillinger(-1, 0, field, dillingerCoordinates);
                    break;

                case DOWN:
                    moveDillinger(+1, 0, field, dillingerCoordinates);
                    break;

                case LEFT:
                    moveDillinger(0, -1, field, dillingerCoordinates);
                    break;

                case RIGHT:
                    moveDillinger(0, +1, field, dillingerCoordinates);
                    break;
            }

            //to observe each move visually:
            printCurrentStateOfField(field);

            if (DILLINGER_GOT_CAUGHT) {
                break;
            }
        }

        if (!DILLINGER_GOT_CAUGHT) {
            System.out.printf(SUCCESSFUL_THEFT_FINAL_MESSAGE, DILLINGER_STOLEN_MONEY);
        }
    }

    private static int moveDillinger(int row, int col, char[][] field, int[] dillingerCoordinates) {
        int newRow = dillingerCoordinates[0] + row;
        int newCol = dillingerCoordinates[1] + col;

        if (isInBound(field, newRow, newCol)) {
            char currentCellContent = field[newRow][newCol];

            switch (currentCellContent) {
                case REGULAR_POSITION:
                    //update new position on field:
                    field[newRow][newCol] = DILLINGER;
                    break;

                case HOUSE:
                    int sumRobbed = calculateMoneyBeingStolen(newRow, newCol);
                    System.out.println();
                    DILLINGER_STOLEN_MONEY += sumRobbed;
                    System.out.printf(DILLINGER_SUCCESSFULLY_ROBBED_A_HOUSE, sumRobbed);

                    //update new position on field as per problem description:
                    field[newRow][newCol] = REGULAR_POSITION;
                    break;

                case POLICE:
                    System.out.printf(DILLINGER_GOT_CAUGHT_WITH_X_AMOUNT_OF_MONEY_DISPLAY_MESSAGE, DILLINGER_STOLEN_MONEY);
                    DILLINGER_GOT_CAUGHT = true;

                    //update new position on field as per problem description:
                    field[newRow][newCol] = BUSTED_POSITION;
            }
            //here we remove the previous trace:
            field[dillingerCoordinates[0]][dillingerCoordinates[1]] = REGULAR_POSITION;

            //update new coordinates of dillinger:
            dillingerCoordinates[0] = newRow;
            dillingerCoordinates[1] = newCol;
        } else {
            System.out.println(DILLINGER_ATTEMPTS_TO_LEAVE_TOWN);
        }

        return DILLINGER_STOLEN_MONEY;
    }

    private static boolean isInBound(char[][] field, int newRow, int newCol) {
        return newRow >= 0 && newRow < field.length && newCol >= 0 && newCol < field.length;
    }

    private static int[] locateDillingerOnField(char[][] field) {
        int[] dillingerCoordinates = new int[2];
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                if (field[row][col] == DILLINGER) {
                    dillingerCoordinates = new int[]{row, col};
                    break;
                }
            }
        }
        return dillingerCoordinates;
    }

    private static int calculateMoneyBeingStolen(int row, int col) {
        return row * col;
        //how can we fix this:
    }

    private static void printCurrentStateOfField(char[][] field) {
        for (char[] chars : field) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }

    private static void populateField(char[][] field, Scanner scanner) {
        for (int row = 0; row < field.length; row++) {
            String currentLine = scanner.nextLine();
            int currentCol = 0;
            for (int col = 0; col < currentLine.length() && currentCol < field[row].length; col++) {
                char currentCellContent = currentLine.charAt(col);
                if (!Character.isWhitespace(currentCellContent)) {
                    field[row][currentCol] = currentCellContent;
                    currentCol++;
                }
            }
        }
    }
}

//Therefore, the name Dillinger can be interpreted to mean one who comes from the town of Dillingen.
// In history, the name Dillinger gained significant attention through the notorious American criminal,
// John Dillinger.
// Active during the Great Depression era, Dillinger was a bank robber and a gangster.