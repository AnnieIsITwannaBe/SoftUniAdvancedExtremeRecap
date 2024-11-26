package Exams.second;

import java.util.*;

public class FoodFinder {
    //this is about creating a frequency map
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstSequence = getUserInput(scanner);
        String secondSequence = getUserInput(scanner);

        //think about how to remove the white spaces in case you convert it to char array:
        char[] first = removeWhiteSpaceFromCharArray(firstSequence);

        char[] second = removeWhiteSpaceFromCharArray(secondSequence);

        List<String> words = populateWords();

        boolean hasAllLettersRequire = false;

        //this approach will only ever be effective if all the letters in the specific word are unique
        //TODO: think about how to handle this when the letters appear more 1 single time:
        Set<Character> firstSet = addCharactersToUniqueCollection(first);

        for (String word : words) {

        }


    }

    private static Set<Character> addCharactersToUniqueCollection(char[] first) {
        Set<Character> firstSet = new HashSet<>();
        for (Character c : first) {
            firstSet.add(c);
        }
        return firstSet;
    }

    private static List<String> populateWords() {
        List<String> words = new ArrayList<>();
        words.add("pear");
        words.add("flour");
        words.add("pork");
        words.add("olive");
        return words;
    }

    private static char[] removeWhiteSpaceFromCharArray(String sequence) {
        return sequence.chars()
                .filter(ch -> !Character.isWhitespace(ch)).mapToObj(e -> (char) e)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString().toCharArray();
    }

    private static Map<String, List<Character>> populateRequirements() {
        Map<String, List<Character>> targetWordsLetters = new HashMap<>();
        targetWordsLetters.put("pear", new ArrayList<>(List.of('p', 'e', 'a', 'r')));
        targetWordsLetters.put("flour", new ArrayList<>(List.of('f', 'l', 'o', 'u', 'r')));
        targetWordsLetters.put("pork", new ArrayList<>(List.of('p', 'o', 'r', 'k')));
        targetWordsLetters.put("olive", new ArrayList<>(List.of('o', 'l', 'i', 'v', 'e')));

        return targetWordsLetters;
    }

    private static String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }
}
//https://www.geeksforgeeks.org/charbuffer-chars-methods-in-java-with-examples/
