package Exams.JavaAdvancedRetakeExam10April2024;

import java.util.Scanner;

public class EscapeTheMazeToBeContinued {
    private static final char TRAVELLER = 'P';
    private static final char EXIT = 'X';
    private static final char MONSTER = 'M';
    private static final char HEALTH = 'H';
    private static final char MAZE_CORRIDOR = '-';
    private static int PLAYER_HP = 100;

    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimensions = Integer.parseInt(scanner.nextLine());

        char[][] maze = new char[dimensions][dimensions];

        populateMaze(maze, scanner);

        int[] travellerCoordinates = locateTravellerOnGrid(maze);

        String userInput = scanner.nextLine();

        while (true) {
            String command = userInput;

            switch (command) {
                case UP:
                    move(-1, 0, maze, travellerCoordinates);

                    break;
                case DOWN:
                    move(-1, 0, maze, travellerCoordinates);


                    break;
                case LEFT:
                    move(-1, 0, maze, travellerCoordinates);


                    break;

                case RIGHT:
                    move(-1, 0, maze, travellerCoordinates);

                    break;
            }
        }


    }

    private static void move(int row, int col, char[][] maze, int[] travellerCoordinates) {
    }

    private static int[] locateTravellerOnGrid(char[][] maze) {
        int[] travellerCoordinates = new int[2];
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                if (maze[row][col] == TRAVELLER) {
                    travellerCoordinates = new int[]{row, col};
                    break;
                }
            }
        }
        return travellerCoordinates;
    }

    private static void populateMaze(char[][] maze, Scanner scanner) {
        for (int row = 0; row < maze.length; row++) {
            String currentLine = scanner.nextLine();
            for (int col = 0; col < maze[row].length; col++) {
                maze[row][col] = currentLine.charAt(col);
            }
        }
    }
}
