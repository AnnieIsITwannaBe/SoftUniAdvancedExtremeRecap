package Exams.JavaAdvancedRetakeExam13December2023;

import java.util.Scanner;

public class TheGamblerMatrixToBeContinued {
    private static final String TERMINATING_COMMAND = "End";
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    private static final int WIN_MONEY_GAIN = 100;
    private static final int PENALTY_MONEY_LOSS = 200;
    private static final int JACKPOT_MONEY_GAIN = 100_000;

    private static final char WIN = 'W';
    private static final char PENALTY = 'P';
    private static final char JACKPOT = 'J';
    private static final char GAMBLER = 'G';
    private static final char EMPTY_CELL = '*';

    private static final String GAMBLER_WON_JACKPOT_DISPLAY_MESSAGE = "You win the Jackpot!\nEnd of the game. Total amount: %d$\n";

    private static final String ORDINARY_END_OF_GAME_DISPLAY_MESSAGE = "End of the game. Total amount: %d$\n";

    private static final String GAMBLER_VIOLATED_BOARD_BOUNDARIES = "Game over! You lost everything!\n";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimensions = Integer.parseInt(scanner.nextLine());

        char[][] gameBoard = new char[dimensions][dimensions];

        setBoardForGameplay(gameBoard, scanner);

        String userInput = scanner.nextLine();

        int gamblerCurrentBalance = 0;

        int[] gamblerCoordinatesOnGrid = locatePlayerOnGrid(gameBoard);

        //•	the gambler leaves the board boundaries
        //•	command 'end' is received
        //•	the gambler's total winning amount is equal to or drops below 0(zero)
        //•	the position marked with 'J' is reached

        while (!TERMINATING_COMMAND.equals(userInput)) {

            if(gamblerCurrentBalance < 0){
                break;
            }

            String command = userInput;

            gamblerCurrentBalance = switch (command) {
                case UP -> moveGambler(-1, 0, gameBoard, gamblerCoordinatesOnGrid, gamblerCurrentBalance);
                case DOWN -> moveGambler(+1, 0, gameBoard, gamblerCoordinatesOnGrid, gamblerCurrentBalance);
                case LEFT -> moveGambler(0, -1, gameBoard, gamblerCoordinatesOnGrid, gamblerCurrentBalance);
                case RIGHT -> moveGambler(0, +1, gameBoard, gamblerCoordinatesOnGrid, gamblerCurrentBalance);
                default -> gamblerCurrentBalance;
            };
            userInput = scanner.nextLine();
        }
    }
    private static int moveGambler(int row, int col, char[][] gameBoard, int[] gamblerCoordinatesOnGrid, int gamblerCurrentBalance) {
        int newRow = gamblerCoordinatesOnGrid[0] + row;
        int newCol = gamblerCoordinatesOnGrid[1] + col;

        if (isInBound(gameBoard, newRow, newCol)) {
            char currentCellContent = gameBoard[newRow][newCol];

            switch (currentCellContent) {
                case JACKPOT:
                    gamblerCurrentBalance += JACKPOT_MONEY_GAIN;

                    System.out.println(GAMBLER_WON_JACKPOT_DISPLAY_MESSAGE);
                    //gamble wins and program ends
                    System.exit(0);
                    break;
                case PENALTY:
                    gamblerCurrentBalance -= PENALTY_MONEY_LOSS;
                    break;
                case WIN:
                    gamblerCurrentBalance += WIN_MONEY_GAIN;
                    break;
            }
        } else {
            System.out.println(GAMBLER_VIOLATED_BOARD_BOUNDARIES);
            System.exit(0);
        }

        gameBoard[newRow][newCol] = GAMBLER;

        gameBoard[gamblerCoordinatesOnGrid[0]][gamblerCoordinatesOnGrid[1]] = EMPTY_CELL;

        gamblerCoordinatesOnGrid[0] = newRow;
        gamblerCoordinatesOnGrid[1] = newCol;

        return gamblerCurrentBalance;
    }

    private static boolean isInBound(char[][] gameBoard, int newRow, int newCol) {
        return newRow >= 0 && newRow < gameBoard.length && newCol >= 0 && newCol < gameBoard[newRow].length;
    }

    private static int[] locatePlayerOnGrid(char[][] gameBoard) {
        int[] gamblerCoordinatesOnGrid = new int[2];
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                if (gameBoard[row][col] == GAMBLER) {
                    gamblerCoordinatesOnGrid = new int[]{row, col};
                }
            }
        }
        return gamblerCoordinatesOnGrid;
    }

    private static void setBoardForGameplay(char[][] gameBoard, Scanner scanner) {
        for (int row = 0; row < gameBoard.length; row++) {
            String currentLine = scanner.nextLine();
            for (int col = 0; col < gameBoard[row].length; col++) {
                gameBoard[row][col] = currentLine.charAt(col);
            }
        }
    }
}
