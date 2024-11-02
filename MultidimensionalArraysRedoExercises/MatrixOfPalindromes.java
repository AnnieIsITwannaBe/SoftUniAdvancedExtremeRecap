package MultidimensionalArraysRedoExercises;

import java.util.Arrays;
import java.util.Scanner;

public class MatrixOfPalindromes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        String[][] matrix = new String[dimensions[0]][dimensions[1]];
        char currentSymbol = 'a';

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                char outerSymbol = (char) (currentSymbol + row);
                char innerSymbol = (char) (outerSymbol + col);

                String result = String.valueOf(outerSymbol) +
                        innerSymbol + outerSymbol;

                matrix[row][col] = result;
            }
        }
        for (String[] strings : matrix) {
            for (String string : strings) {
                System.out.print(string + " ");
            }

            System.out.println();

        }
    }
}
