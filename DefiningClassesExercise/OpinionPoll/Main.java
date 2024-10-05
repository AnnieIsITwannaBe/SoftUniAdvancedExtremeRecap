package DefiningClassesExercise.OpinionPoll;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<Person> people = addPeople(n, scanner);

        people.stream().sorted(Comparator.comparing(Person::getName)).forEach(System.out::println);
    }

    private static List<Person> addPeople(int n, Scanner scanner) {
        List<Person> people = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(scanner.nextLine());

            Person person = new Person(name, age);
            people.add(person);
        }
        return people;
    }
}
