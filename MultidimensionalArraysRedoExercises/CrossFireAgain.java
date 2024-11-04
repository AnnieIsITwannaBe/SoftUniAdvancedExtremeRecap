package MultidimensionalArraysRedoExercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CrossFireAgain {
    private static final String END_COMMAND = "Nuke it from orbit";

    private static final String SPLITERATOR = "\\s+";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = getIntArray(scanner);

        int rows = dimensions[0];
        int cols = dimensions[1];

        List<List<Integer>> matrix = new ArrayList<>();

        fillInMatrix(rows, cols, matrix);

        String userInput = scanner.nextLine();

        while (!END_COMMAND.equals(userInput)) {
            String[] tokens = userInput.split(SPLITERATOR);

            int targetRow = Integer.parseInt(tokens[0]);
            int targetCol = Integer.parseInt(tokens[1]);
            int radius = Integer.parseInt(tokens[2]);


            for (int row = targetRow - radius; row < targetRow + radius; row++) {
                if (isInBound(targetRow, targetCol, matrix)) {
                    matrix.get(targetRow).set(targetCol, 0);
                }
            }

            for (int col = targetCol - radius; col < targetCol + radius; col++) {
                if (isInBound(targetRow, targetCol, matrix)) {
                    matrix.get(targetRow).set(col, 0);
                }
            }

            for (int row = 0; row < matrix.size(); row++) {
                List<Integer> currentRow = matrix.get(row);
                currentRow.removeAll(List.of(0));

                if (currentRow.isEmpty()) {
                    matrix.remove(currentRow);
                    row--;
                    //много внимателно, когато се премахват елементи от цикъл динамично 
                }

            }
            userInput = scanner.nextLine();
        }

    }

    private static boolean isInBound(int targetRow, int targetCol, List<List<Integer>> matrix) {
        return targetRow >= 0 && targetRow < matrix.size() &&
                targetCol >= 0 && targetCol < matrix.get(targetRow).size();
    }

    private static void fillInMatrix(int rows, int cols, List<List<Integer>> matrix) {
        int counter = 0;
        for (int row = 0; row < rows; row++) {
            List<Integer> arrayList = new ArrayList<>();
            for (int col = 0; col < cols; col++) {
                counter++;
                arrayList.add(counter);
            }
            matrix.add(arrayList);
        }
    }

    private static int[] getIntArray(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
    }
}
