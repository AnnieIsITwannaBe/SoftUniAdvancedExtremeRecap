package MultidimensionalArraysRedoExercises;

import java.util.Scanner;

public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[n][n];

        int sumPrimaryDiagonal = getSumPrimaryDiagonal(matrix);

        int lastIndex = matrix.length - 1;
        int sumSecondaryDiagonal = getSecondaryDiagonalSum(matrix, lastIndex);

        int abs = Math.max(sumPrimaryDiagonal, sumSecondaryDiagonal);

        int finalSum = 0;
        if (abs == sumPrimaryDiagonal) {
            finalSum = sumPrimaryDiagonal - sumSecondaryDiagonal;
        } else {
            finalSum = sumSecondaryDiagonal - sumPrimaryDiagonal;
        }

        System.out.println(finalSum);
    }
    private static int getSecondaryDiagonalSum(int[][] matrix, int lastIndex) {
        int sumSecondaryDiagonal = 0;
        for (int i = 0; i < matrix.length; i++) {
            sumSecondaryDiagonal += matrix[lastIndex - i][i];
        }
        return sumSecondaryDiagonal;
    }

    private static int getSumPrimaryDiagonal(int[][] matrix) {
        int sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            sum += matrix[row][row];
        }

        return sum;
    }
}

