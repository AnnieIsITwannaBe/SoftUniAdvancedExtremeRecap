package MultidimensionalArraysRedoExercises;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class again {
    private static final String END_COMMAND = "END";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> words = new LinkedList<>();

        int maxWordSize = Integer.MIN_VALUE;

        int rotationDegree = Integer.parseInt(scanner.nextLine()
                .split("\\(")[1].replaceAll("\\)", ""));

        int rotations = rotationDegree % 360;

        String input = scanner.nextLine();

        maxWordSize = processUserInput(input, maxWordSize, words, scanner);

        int rows = words.size();
        int cols = maxWordSize;

        char[][] matrix = new char[rows][cols];

        populateMatrix(matrix, words, maxWordSize);

        switch (rotations) {
            case 90:
                matrix = rotate90(matrix, cols, rows);
                break;
            case 180:
                matrix = rotate90(matrix, rows, cols);
                matrix = rotate90(matrix, cols, rows);
                break;
            case 270:
                matrix = rotate90(matrix, rows, cols);
                matrix = rotate90(matrix, cols, rows);
                matrix = rotate90(matrix, cols, rows);
                break;
        }

        printMatrix(matrix);
    }

    private static char[][] rotate90(char[][] matrix, int rows, int cols) {
        char[][] newRotatedMatrix = new char[cols][rows];
        for (int col = 0; col < matrix.length; col++) {
            int counter = 0;
            for (int row = matrix[col].length - 1; row >= 0; row--) {
                newRotatedMatrix[col][counter++] = matrix[col][row];
            }
        }
        return newRotatedMatrix;
    }

    private static int processUserInput(String line, int maxWordSize, List<String> words, Scanner scanner) {
        while (!END_COMMAND.equals(line)) {
            String currentWord = line;

            if (currentWord.length() > maxWordSize) {
                maxWordSize = currentWord.length();
            }
            words.add(currentWord);

            line = scanner.nextLine();
        }
        return maxWordSize;
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }

    private static void populateMatrix(char[][] matrix, List<String> words, int maxWordSize) {
        for (int row = 0; row < matrix.length; row++) {
            String currentWord = words.get(row);
            for (int col = 0; col < currentWord.length(); col++) {
                if (col < maxWordSize) {
                    matrix[row][col] = currentWord.charAt(col);
                } else {
                    matrix[row][col] = ' ';
                }
            }
        }
    }
}
