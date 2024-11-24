package Exams;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Meeting {
    private static final String SPLITERATOR = "\\s+";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> males = getIntStream(scanner);

        ArrayDeque<Integer> maleStack = addAllMalesToStack(males);

        List<Integer> females = getIntStream(scanner);

        ArrayDeque<Integer> femaleQueue = addToQueue(females);

        while (!maleStack.isEmpty() || !femaleQueue.isEmpty()) {

            Integer peekMale = maleStack.peek();
            int currentMale = 0;
            if (peekMale != null) {
                currentMale = peekMale;
            }

            Integer polledValue = femaleQueue.peek();
            int currentFemale = 0;

            if (polledValue != null) {
                currentFemale = polledValue;
            }

            checkForValuesEqualOrBelow0(currentMale, maleStack, currentFemale, femaleQueue);

            if (currentMale == currentFemale) {
                femaleQueue.poll();
                maleStack.pop();
            }

            if (currentFemale % 25 == 0) {
                femaleQueue.poll();
                femaleQueue.poll();
            }

            if (currentMale % 25 == 0) {
                maleStack.pop();
                maleStack.pop();
            }
        }
    }

    private static void checkForValuesEqualOrBelow0(int currentMale, ArrayDeque<Integer> maleStack, int currentFemale, ArrayDeque<Integer> femaleQueue) {
        if (currentMale <= 0) {
            maleStack.pop();
        }

        if (currentFemale <= 0) {
            femaleQueue.poll();
        }
    }

    private static ArrayDeque<Integer> addToQueue(List<Integer> females) {
        ArrayDeque<Integer> femaleQueue = new ArrayDeque<>();
        for (Integer female : females) {
            femaleQueue.push(female);
        }
        return femaleQueue;
    }

    private static ArrayDeque<Integer> addAllMalesToStack(List<Integer> males) {
        ArrayDeque<Integer> maleStack = new ArrayDeque<>();
        for (Integer male : males) {
            maleStack.addFirst(male);
        }
        return maleStack;
    }

    private static List<Integer> getIntStream(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split(SPLITERATOR)).map(Integer::parseInt)
                .toList();
    }
}

//The warning you are seeing about unboxing of femaleQueue.poll() potentially producing a NullPointerException is related to the fact that the behavior of Stack.pop() and Queue.poll() differs when the collection is empty.
//
//Key Differences Between Stack.pop() and Queue.poll()
//Stack.pop():
//
//Throws an EmptyStackException if the stack is empty.
//This exception is checked at runtime and does not trigger a warning from the IDE.
//Queue.poll():
//
//Returns null if the queue is empty.
//If you directly unbox the result of poll() (e.g., if the queue holds Integer objects and you assign to an int), it will result in a NullPointerException, because unboxing null is not allowed in Java.
//Why You Get a Warning for femaleQueue.poll()
//In your loop:
//
//java
//Копиране на код
//int currentLastMale = maleStack.pop(); // No warning
//int currentLastFemale = femaleQueue.poll(); // Warning about unboxing
//When maleStack.pop() is called, the stack being empty results in an exception being thrown immediately. This is a controlled failure and does not involve unboxing a null.
//When femaleQueue.poll() is called, if the queue is empty, null is returned. If currentLastFemale is a primitive int, Java attempts to unbox the null into an int, which causes a NullPointerException. Since this is a potential problem, the IDE issues a warning.
//Fixing the Warning
//To address the warning, you can:
//
//Check for null explicitly: Before assigning the value to a primitive, ensure the result is not null:
//
//java
//Копиране на код
//Integer polledValue = femaleQueue.poll();
//if (polledValue != null) {
//    currentLastFemale = polledValue;
//} else {
//    // Handle empty queue case (e.g., break or continue)
//}
//Use Wrapper Types: Change currentLastFemale to an Integer if you are okay with dealing with null values explicitly.
//
//Ensure Queue is Non-Empty: Modify the loop to include a condition to check that the queue is not empty before polling:
//
//java
//Копиране на код
//while (!maleStack.isEmpty() || !femaleQueue.isEmpty()) {
//    int currentLastMale = maleStack.pop();
//    if (!femaleQueue.isEmpty()) {
//        int currentLastFemale = femaleQueue.poll();
//    }
//}
//Use Optional for Clarity: Wrap the polled value in an Optional to make handling the null case explicit:
//
//java
//Копиране на код
//Optional<Integer> polledValue = Optional.ofNullable(femaleQueue.poll());
//polledValue.ifPresent(value -> {
//    currentLastFemale = value;
//});
//Why No Warning for maleStack.pop()
//Stack.pop() avoids this warning because:
//
//It directly throws an exception if the stack is empty.
//There is no concept of returning null in pop(), so unboxing warnings are not relevant.
//Summary
//You get the warning for femaleQueue.poll() because it may return null when the queue is empty, leading to a potential NullPointerException upon unboxing. To fix it, ensure you handle the null case before unboxing or modify the logic to avoid unboxing null.