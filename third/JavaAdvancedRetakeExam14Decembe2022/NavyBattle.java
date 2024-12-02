package Exams.JavaAdvancedRetakeExam14Decembe2022;

import java.util.Scanner;

public class NavyBattle {
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final char SUBMARINE = 'S';
    private static final char MINE = '*';
    private static final char EMPTY_CELL = '-';
    private static final char BATTLE_CRUISER = 'C';

    private static boolean BATTLE_CRUISER_IS_REACHED = false;
    private static int SUBMARINE_MINE_DAMAGE_TAKEN = 0;

    private static final int SUBMARINE_MINE_DAMAGE_LIMIT = 2;

    private static final String SUBMARINE_DROWNED_MISSION_UNSUCCESSFUL_DISPLAY_MESSAGE = "\"Mission failed, U-9 disappeared! Last known coordinates [%d, %d]!\n";

    private static final String MISSION_SUCCESSFUL_DISPLAY_MESSAGE = "Mission accomplished, U-9 has destroyed all battle cruisers of the enemy!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimensions = Integer.parseInt(scanner.nextLine());

        char[][] battleField = populateField(scanner, dimensions);

        int[] submarineCoordinates = locateSubmarineCoordinates(battleField);

        while (true) {
            String direction = scanner.nextLine();

            switch (direction) {
                case UP:
                    move(-1, 0, battleField, submarineCoordinates);
                    break;

                case DOWN:
                    move(+1, 0, battleField, submarineCoordinates);
                    break;

                case LEFT:
                    move(0, -1, battleField, submarineCoordinates);
                    break;

                case RIGHT:
                    move(0, +1, battleField, submarineCoordinates);
                    break;
            }

            if (SUBMARINE_MINE_DAMAGE_TAKEN > SUBMARINE_MINE_DAMAGE_LIMIT) {
                System.out.printf(SUBMARINE_DROWNED_MISSION_UNSUCCESSFUL_DISPLAY_MESSAGE, submarineCoordinates[0], submarineCoordinates[1]);
                break;
            }

            if (BATTLE_CRUISER_IS_REACHED) {
                System.out.println(MISSION_SUCCESSFUL_DISPLAY_MESSAGE);
                break;

            }
        }
    }

    private static void move(int row, int col, char[][] battleField, int[] submarineCoordinates) {
        int newRow = submarineCoordinates[0] + row;
        int newCol = submarineCoordinates[1] + col;

        if (isInBounds(battleField, newRow, newCol)) {
            char currentCellContent = battleField[newRow][newCol];

            switch (currentCellContent) {
                case MINE:
                    break;
                case BATTLE_CRUISER:
                    BATTLE_CRUISER_IS_REACHED = true;
                    break;
            }

            if (currentCellContent == MINE) {
                SUBMARINE_MINE_DAMAGE_TAKEN += 1;
            }

            battleField[newRow][newCol] = SUBMARINE;

            battleField[submarineCoordinates[0]][submarineCoordinates[1]] = EMPTY_CELL;

            submarineCoordinates[0] = newRow;
            submarineCoordinates[1] = newCol;
        } else {


            System.exit(0);
        }
    }

    private static boolean isInBounds(char[][] battleField, int newRow, int newCol) {
        return newRow >= 0 && newRow < battleField.length && newCol >= 0 && newCol < battleField[newRow].length;
    }

    private static int[] locateSubmarineCoordinates(char[][] battleField) {
        int[] submarineCoordinates = new int[2];
        for (int row = 0; row < battleField.length; row++) {
            for (int col = 0; col < battleField[row].length; col++) {
                if (battleField[row][col] == SUBMARINE) {
                    submarineCoordinates = new int[]{row, col};
                }
            }
        }
        return submarineCoordinates;
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
