package Exams.JavaAdvancedRegularExam21October2023;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OffroadChallengeWithNotesToBeContinued {
    private static final String SPLITERATOR_REGEX = "\\s+";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //there is a much better way to this but for now okay:

        //this will represent the stack:
        List<Integer> initialFuelQuantity = getIntSequence(scanner);

        ArrayDeque<Integer> fuelQuantityStack = populateStack(initialFuelQuantity);

        //this will be representing the queue;
        List<Integer> additionalConsumptionIndex = getIntSequence(scanner);

        ArrayDeque<Integer> additionalConsumptionIndexQueue = populateQueue(additionalConsumptionIndex);

        List<Integer> amountOfFuelNeeded = getIntSequence(scanner);
        //representing values equal to the necessary amount of fuel
        //needed to reach the corresponding altitude in the challenge.

        //Your task is to take the last fuel quantity from the fuel sequence
        //and the first index from the additional consumption index sequence. Subtract the values and check the result.

        Integer lastFuelQuantity = fuelQuantityStack.peek();
        Integer currentAdditionalConsumptionIndex = additionalConsumptionIndexQueue.peek();

        //check the result:
        int result = lastFuelQuantity - currentAdditionalConsumptionIndex;


    }

    private static ArrayDeque<Integer> populateQueue(List<Integer> additionalConsumptionIndex) {
        ArrayDeque<Integer> additionalConsumptionIndexQueue = new ArrayDeque<>();
        for (Integer consumptionIndex : additionalConsumptionIndex) {
            additionalConsumptionIndexQueue.offer(consumptionIndex);

        }
        return additionalConsumptionIndexQueue;
    }

    private static ArrayDeque<Integer> populateStack(List<Integer> initialFuelQuantity) {
        ArrayDeque<Integer> fuelQuantityStack = new ArrayDeque<>();
        for (Integer integer : initialFuelQuantity) {
            fuelQuantityStack.push(integer);
        }
        return fuelQuantityStack;
    }

    private static List<Integer> getIntSequence(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split(SPLITERATOR_REGEX)).map(Integer::parseInt).toList();
    }
}

//https://www.researchgate.net/publication/251724926_Impact_of_altitude_on_fuel_consumption_of_a_gasoline_passenger_car