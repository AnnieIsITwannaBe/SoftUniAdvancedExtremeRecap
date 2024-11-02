package MultidimensionalArraysRedoExercises;

import java.util.Scanner;

public class FillTheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] matrixInfo = scanner.nextLine().split(",\\s+");

        int dimensions = Integer.parseInt(matrixInfo[0]);
        String patternType = matrixInfo[1];

        int[][] matrix = new int[dimensions][dimensions];

        switch (patternType) {
            case "A":
                handleTypeA(matrix);
                break;
            case "B":
                handleTypeB(matrix);
                break;
        }
        handleOutput(matrix);
    }

    private static void handleOutput(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    private static void handleTypeB(int[][] matrix) {
        int index = 1;
        for (int row = 0; row < matrix.length; row++) {
            if (row % 2 == 0) {
                for (int col = 0; col < matrix[row].length; col++) {
                    matrix[col][row] = index;
                    index++;
                }
            } else {
                for (int col = matrix[row].length - 1; col >= 0; col--) {
                    matrix[col][row] = index;
                    index++;
                }
            }
        }
    }

    private static void handleTypeA(int[][] matrix) {
        int index = 1;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[col][row] = index;
                index++;

            }
        }
    }
}
