package MultidimensionalArraysRedoExercises;

import java.util.Arrays;
import java.util.Scanner;

public class MaximalSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = getIntArray(scanner);

        int[][] rectangularMatrix = new int[dimensions[0]][dimensions[1]];

        populateMatrix(rectangularMatrix, scanner);

        int[][] subMatrixMax = new int[3][3];
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        for (int row = 0; row < rectangularMatrix.length - 2; row++) {
            for (int col = 0; col < rectangularMatrix[row].length - 2; col++) {
                currentSum = rectangularMatrix[row][col] + rectangularMatrix[row][col + 1] + rectangularMatrix[row][col + 2]
                        + rectangularMatrix[row + 1][col] + rectangularMatrix[row + 1][col + 1] + rectangularMatrix[row + 1][col + 2]
                        + rectangularMatrix[row + 2][col] + rectangularMatrix[row + 2][col + 1] + rectangularMatrix[row + 2][col + 2];

                if (currentSum > maxSum) {
                    maxSum = currentSum;

                    int[] firstRow = new int[]{rectangularMatrix[row][col], rectangularMatrix[row][col + 1], rectangularMatrix[row][col + 2]};
                    int[] secondRow = new int[]{rectangularMatrix[row + 1][col], rectangularMatrix[row + 1][col + 1], rectangularMatrix[row + 1][col + 2]};
                    int[] thirdRow = new int[]{rectangularMatrix[row + 2][col], rectangularMatrix[row + 2][col + 1], rectangularMatrix[row + 2][col + 2]};

                    subMatrixMax[0] = firstRow;
                    subMatrixMax[1] = secondRow;
                    subMatrixMax[2] = thirdRow;
                }
            }
        }

        for (int[] matrixMax : subMatrixMax) {
            for (int col = 0; col < matrixMax.length; col++) {
                System.out.print(matrixMax[col] + " ");
            }
            System.out.println();
        }

        System.out.println("Sum:" + maxSum);
    }

    private static void populateMatrix(int[][] rectangularMatrix, Scanner scanner) {
        for (int row = 0; row < rectangularMatrix.length; row++) {
            rectangularMatrix[row] = getIntArray(scanner);
        }
    }

    private static int[] getIntArray(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
    }
}
