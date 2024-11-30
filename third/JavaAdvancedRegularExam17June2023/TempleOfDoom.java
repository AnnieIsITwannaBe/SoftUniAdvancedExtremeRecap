package Exams.JavaAdvancedRegularExam17June2023;

import java.util.*;
import java.util.stream.Collectors;

public class TempleOfDoom {
    private static final String TEMPLE_IS_LOST = "Harry is lost in the temple. Oblivion awaits him.";

    private static final String HARRY_FOUND_ARTIFACT = "Harry found an ostracon, which is dated to the 6th century BCE";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //this will be the stack:
        List<Integer> sequence = getIntStream(scanner);

        //a little bit more functional but can be improved:
        ArrayDeque<Integer> queueTools = new ArrayDeque<>();

        sequence.forEach(queueTools::offer);

        //this will be the queue:
        List<Integer> secondSequence = getIntStream(scanner);

        ArrayDeque<Integer> substancesStack = new ArrayDeque<>();

        secondSequence.forEach(substancesStack::push);

        List<Integer> challenges = getIntStream(scanner);

        //to take the first tool from the tools sequence and the last substance from the substances sequence.
        // Multiply the values and check the result

        while (true) {

            if (queueTools.isEmpty()) {
                if (!challenges.isEmpty()) {
                    System.out.println(TEMPLE_IS_LOST);
                } else {
                    System.out.println(HARRY_FOUND_ARTIFACT);
                }
                break;
            }

            if (substancesStack.isEmpty()) {
                if (!challenges.isEmpty()) {
                    System.out.println(TEMPLE_IS_LOST);
                } else {
                    System.out.println(HARRY_FOUND_ARTIFACT);
                }
                break;
            }

            assert queueTools.peek() != null;
            int firstTool = queueTools.peek();

            assert substancesStack.peek() != null;
            int lastSubstance = substancesStack.peek();

            //Explicit Unboxing (Optional): // .intValue

            int result = lastSubstance * firstTool;

            //•	If the calculated result is equal to any of the elements from the challenges sequence,
            // the challenge is resolved. You need to remove both the tool and the substance from their sequences.
            // The challenge should also be removed from its sequence.

            boolean challengeIsResolved = false;
            for (int i = 0; i < challenges.size(); i++) {
                if (result == challenges.get(i)) {
                    queueTools.pop();
                    substancesStack.poll();

                    //challenge is resolved.
                    challenges.remove(i);
                    challengeIsResolved = true;
                    break;
                }
            }
            //Increase the value of the tool element by 1 and move the element to the back of the tools’ sequence.
            //Decrease the value of the substance element by 1 and return the element to the substance’s sequence.
            // If the value of the substance element reaches 0, remove it from the sequence
            if (!challengeIsResolved) {
                int updatedValueQueue = firstTool + 1;
                queueTools.poll();
                queueTools.offer(updatedValueQueue);

                int updatedValueStack = lastSubstance - 1;
                substancesStack.pop();
                substancesStack.push(updatedValueStack);
            }
        }
    }

    private static ArrayList<Integer> getIntStream(Scanner scanner) {
        return Arrays.stream(scanner.nextLine()
                        .split("\\s+")).map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
