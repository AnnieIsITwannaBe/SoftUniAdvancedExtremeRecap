package MultidimensionalArraysRedoExercises;

import java.util.Arrays;
import java.util.Scanner;

//The player always starts at 18500 hit points; Heigan starts at 3,000,000 hit points. Each

public class TheHeiganDance {
    private static final int CHAMBER_DIMENSIONS = 15;

    private static final String SPLITERATOR = "\\s+";

    private static int PLAYER_HIT_POINTS = 18_500;

    private static int VILLAIN_HIT_POINTS = 3_000_000;

    private static final String ATTACK_TYPE_ERUPTION = "Eruption";

    private static final String ATTACK_TYPE_CLOUD = "Cloud";

    private static final char PLAYER = 'P';

    private static final char PLAYER_IS_UNDER_ATTACK = 'X';

    private static final char DAMAGED_CELL = '#';

    private static final int ERUPTION_DAMAGE = 6_000;

    private static final int CLOUD_DAMAGE = 3_000;

    private static String PREVIOUS_ATTACK = " ";

    public static void main(String[] args) {

        char[][] chamber = new char[CHAMBER_DIMENSIONS][CHAMBER_DIMENSIONS];

        int center = getCenter();

        setChamberForUpcomingCombat(chamber);

        setPlayerInitialPosition(chamber, center);

        //at the beginning:
        displayCurrentStateOfCombatField(chamber);

        Scanner scanner = new Scanner(System.in);

        int playerAttackDamage = Integer.parseInt(scanner.nextLine());

        int[] playerCoordinates = new int[2];

        updateCoordinates(playerCoordinates, center, center);

        String line = scanner.nextLine();

        while (PLAYER_HIT_POINTS >= 0 || VILLAIN_HIT_POINTS >= 0) {
            String[] tokens = line.split(SPLITERATOR);

            String villainAttackType = tokens[0];

            boolean SKIP_TURN = PREVIOUS_ATTACK.equals(ATTACK_TYPE_CLOUD);

            if (SKIP_TURN) {
                VILLAIN_HIT_POINTS -= playerAttackDamage;
                return;
            }
            //because the return will skip those steps

            int rowTarget = Integer.parseInt(tokens[1]);
            int colTarget = Integer.parseInt(tokens[2]);

            //to track the not updated position:
            int playerRow = playerCoordinates[0];
            int playerCol = playerCoordinates[1];

            switch (villainAttackType) {

                case ATTACK_TYPE_ERUPTION:
                    handleAttack(ATTACK_TYPE_ERUPTION, chamber, playerCoordinates, rowTarget, colTarget, playerRow, playerCol);

                    setNewPreviousAttack(ATTACK_TYPE_ERUPTION);

                    break;

                case ATTACK_TYPE_CLOUD:
                    handleAttack(ATTACK_TYPE_CLOUD, chamber, playerCoordinates, rowTarget, colTarget, playerRow, playerCol);

                    setNewPreviousAttack(ATTACK_TYPE_CLOUD);

                    break;
            }

            //Each turn, the player does damage to Heigan.
            VILLAIN_HIT_POINTS -= playerAttackDamage;

            displayCurrentStateOfCombatField(chamber);

            line = scanner.nextLine();
        }


    }

    private static void setNewPreviousAttack(String attackType) {
        PREVIOUS_ATTACK = attackType;
    }

    private static void handleAttack(String attackType, char[][] chamber, int[] playerCoordinates, int rowTarget, int colTarget, int playerRow, int playerCol) {
        boolean playerIsWithinAreaOfDamage;

        handleAccordingToPreviousAttackType(chamber, playerCoordinates);

        //if we hit player, we mark it with X so that we know he's currently in damaged cell:
        handleEntireVillainDamageRegion(rowTarget, colTarget, chamber);

        playerIsWithinAreaOfDamage = chamber[playerRow][playerCol] == PLAYER_IS_UNDER_ATTACK;

        if (playerIsWithinAreaOfDamage) {
            //attemptEscape:
            handleAllEscapeTurns(playerRow, playerCol, chamber, playerCoordinates);

            boolean successfullyEscaped = chamber[playerRow][playerCol] == DAMAGED_CELL && chamber[
                    playerCoordinates[0]][playerCoordinates[1]] == PLAYER;

            if (!successfullyEscaped) {
                if (attackType.equals(ATTACK_TYPE_ERUPTION)) {
                    PLAYER_HIT_POINTS -= ERUPTION_DAMAGE;
                } else {
                    PLAYER_HIT_POINTS -= CLOUD_DAMAGE;
                }
            }
        }
    }

    private static void handleAccordingToPreviousAttackType(char[][] chamber, int[] playerCoordinates) {
        switch (PREVIOUS_ATTACK) {
            case ATTACK_TYPE_ERUPTION:
                // WE DO A RESET OF THE CHAMBER BC DAMAGE FROM ERUPTION LASTS ONLY ONE MOVE
                //reset means we remove the damaged cells and player stays at his position:
                setChamberForUpcomingCombat(chamber);
                chamber[playerCoordinates[0]][playerCoordinates[1]] = PLAYER;
                break;

            case ATTACK_TYPE_CLOUD:
                //we do not do a reset because it lasts 2 moves
                //since we cannot reset the board and the player
                //couldn't move the previous time it means that
                //he will be unable to move this time as well
                PLAYER_HIT_POINTS -= CLOUD_DAMAGE;
                break;
        }
    }

    private static void handleAllEscapeTurns(int playerRow, int playerCol, char[][] chamber, int[] playerCoordinates) {
        if (escapeIsSuccessful(playerRow, playerCol + 1, chamber, playerCoordinates)) {
            return;
        }
        if (escapeIsSuccessful(playerRow, playerCol - 1, chamber, playerCoordinates)) {
            return;
        }
        if (escapeIsSuccessful(playerRow + 1, playerCol, chamber, playerCoordinates)) {
            return;
        }
        if (escapeIsSuccessful(playerRow + 1, playerCol + 1, chamber, playerCoordinates)) {
            return;
        }
        if (escapeIsSuccessful(playerRow + 1, playerCol - 1, chamber, playerCoordinates)) {
            return;
        }
        if (escapeIsSuccessful(playerRow - 1, playerCol, chamber, playerCoordinates)) {
            return;
        }
        if (escapeIsSuccessful(playerRow - 1, playerCol + 1, chamber, playerCoordinates)) {
            return;
        }
        if (escapeIsSuccessful(playerRow - 1, playerCol - 1, chamber, playerCoordinates)) {
            return;
        }
    }

    private static boolean escapeIsSuccessful(int playerRow, int playerCol, char[][] chamber, int[] playerCurrentCoordinates) {
        if (isInBound(playerRow, playerCol, chamber) && chamber[playerRow][playerCol] != '#') {

            //remove the x and set it to just regular damaged cell:
            chamber[playerCurrentCoordinates[0]][playerCurrentCoordinates[1]] = DAMAGED_CELL;

            updateCoordinates(playerCurrentCoordinates, playerRow, playerCol);

            //put the player to his new position:
            chamber[playerCurrentCoordinates[0]][playerCurrentCoordinates[1]] = PLAYER;
            return true;
        }
        return false;
    }

    private static void updateCoordinates(int[] playerCoordinates, int playerRow, int playerCol) {
        playerCoordinates[0] = playerRow;
        playerCoordinates[1] = playerCol;
    }

    private static void handleEntireVillainDamageRegion(int rowTarget, int colTarget, char[][] chamber) {
        handleVillainDamagePerCell(rowTarget, colTarget, chamber);


        handleVillainDamagePerCell(rowTarget, colTarget + 1, chamber);


        handleVillainDamagePerCell(rowTarget, colTarget - 1, chamber);


        handleVillainDamagePerCell(rowTarget + 1, colTarget, chamber);


        handleVillainDamagePerCell(rowTarget + 1, colTarget + 1, chamber);


        handleVillainDamagePerCell(rowTarget + 1, colTarget - 1, chamber);


        handleVillainDamagePerCell(rowTarget - 1, colTarget, chamber);


        handleVillainDamagePerCell(rowTarget - 1, colTarget + 1, chamber);


        handleVillainDamagePerCell(rowTarget - 1, colTarget - 1, chamber);
    }

    private static void handleVillainDamagePerCell(int rowTarget, int colTarget, char[][] chamber) {
        if (isInBound(rowTarget, colTarget, chamber)) {
            if (playerIsHit(rowTarget, colTarget, chamber)) {
                chamber[rowTarget][colTarget] = PLAYER_IS_UNDER_ATTACK;
            } else {
                chamber[rowTarget][colTarget] = DAMAGED_CELL;
            }
        }
    }

    private static boolean playerIsHit(int row, int col, char[][] chamber) {
        return chamber[row][col] == PLAYER;
    }

    private static boolean isInBound(int rowTarget, int colTarget, char[][] chamber) {
        return rowTarget >= 0 && rowTarget < chamber.length && colTarget >= 0 && colTarget < chamber[colTarget].length;
    }

    private static int[] trackCoordinates(int row, int col) {
        int[] playerCoordinates = new int[2];
        playerCoordinates[0] = row;
        playerCoordinates[1] = col;

        return playerCoordinates;
    }

    private static int getCenter() {
        return CHAMBER_DIMENSIONS / 2;
    }

    private static void setChamberForUpcomingCombat(char[][] chamber) {
        for (char[] chars : chamber) {
            Arrays.fill(chars, '*');
        }
    }

    private static void displayCurrentStateOfCombatField(char[][] chamber) {
        for (char[] chars : chamber) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    private static void setPlayerInitialPosition(char[][] chamber, int center) {
        chamber[center][center] = PLAYER;
    }
}

//Yes, placing this logic in a method like handleAllTurns will work,
// and using return instead of break to exit the method after a successful escape attempt is
// a valid approach. This way, as soon as the player finds a valid escape route,
// you can immediately exit handleAllTurns, skipping the remaining checks.