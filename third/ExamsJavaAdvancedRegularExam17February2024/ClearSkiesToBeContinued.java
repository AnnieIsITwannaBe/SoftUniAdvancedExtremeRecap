package ExamsJavaAdvancedRegularExam17February2024;

import java.util.Scanner;

public class ClearSkiesToBeContinued {
    private static final char JET_FIGHTER = 'J';
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final char EMPTY_CELL = '-';
    private static final char ENEMY_PLANE = 'E';
    private static final char REPAIR = 'R';
    private static int JET_FIGHTER_UNITS_OF_ARMOR = 300;
    private static final int FIGHT_DAMAGE = 100;

    private static final int REPAIR_HEALTH_ADDITIONAL_ARMOR = 300;

    private static int BATTLE_COUNTER = 0;
    private static final int BATTLE_LIMIT = 3;

    private static final String JET_FIGHTER_DIED_AT_BATTLE_DISPLAY_MESSAGE = "Mission failed, your jetfighter was shot down! Last coordinates [%d, %d]!\n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimensions = Integer.parseInt(scanner.nextLine());

        char[][] protectedAirspace = new char[dimensions][dimensions];

        setMatrix(protectedAirspace, scanner);

        int[] jetFighterCoordinates = locateJetFighterOnField(protectedAirspace);

        String userInput = scanner.nextLine();

        while (true) {
            String command = userInput;

            switch (command) {
                case UP:
                    move(-1, 0, protectedAirspace, jetFighterCoordinates);

                    break;
                case DOWN:
                    move(+1, 0, protectedAirspace, jetFighterCoordinates);

                    break;
                case LEFT:
                    move(0, -1, protectedAirspace, jetFighterCoordinates);
                    break;
                case RIGHT:
                    move(0, +1, protectedAirspace, jetFighterCoordinates);

                    break;
            }
        }


    }

    private static void move(int row, int col, char[][] protectedAirspace, int[] jetFighterCoordinates) {
        int newRow = jetFighterCoordinates[0] + row;
        int newCol = jetFighterCoordinates[1] + col;

        if (isInBound(protectedAirspace, newRow, newCol)) {
            char currentCellContent = protectedAirspace[newRow][newCol];

            switch (currentCellContent) {
                case REPAIR:
                    JET_FIGHTER_UNITS_OF_ARMOR += REPAIR_HEALTH_ADDITIONAL_ARMOR;
                    break;
                case ENEMY_PLANE:
                    JET_FIGHTER_UNITS_OF_ARMOR -= FIGHT_DAMAGE;
                    break;
            }

            if (BATTLE_COUNTER == BATTLE_LIMIT) {
                System.out.printf(JET_FIGHTER_DIED_AT_BATTLE_DISPLAY_MESSAGE, newRow, newCol);
                System.exit(0);
            }
        }
    }

    private static boolean isInBound(char[][] protectedAirspace, int newRow, int newCol) {
        return newRow >= 0 && newRow < protectedAirspace.length && newCol >= 0 && newCol < protectedAirspace[newRow].length;
    }

    private static int[] locateJetFighterOnField(char[][] protectedAirspace) {
        int[] jetFighterCoordinates = new int[2];
        for (int row = 0; row < protectedAirspace.length; row++) {
            for (int col = 0; col < protectedAirspace[row].length; col++) {
                if (protectedAirspace[row][col] == JET_FIGHTER) {
                    jetFighterCoordinates = new int[]{row, col};
                }
            }
        }
        return jetFighterCoordinates;
    }

    private static void setMatrix(char[][] protectedAirspace, Scanner scanner) {
        for (int row = 0; row < protectedAirspace.length; row++) {
            String currentLine = scanner.nextLine();
            for (int col = 0; col < protectedAirspace[row].length; col++) {
                protectedAirspace[row][col] = currentLine.charAt(col);
            }
        }
    }
}
