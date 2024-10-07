package DefiningClassesExercise.Google;

import MultidimensionalArraysExercises.MatrixOfPalindromes;

import java.util.*;

public class Main {
    private static final String BREAK_COMMAND = "End";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Map<String, Person> people = new HashMap<>();

        while (!BREAK_COMMAND.equals(input)) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String personName = tokens[0];
            String type = tokens[1];


            if (!people.containsKey(personName)) {
                people.put(personName, new Person());
            }

            switch (type) {
                case "car":

                    String model = tokens[2];
                    int speed = Integer.parseInt(tokens[3]);
                    Car car = new Car(model, speed);
                    people.get(personName).setCar(car);
                    break;

                case "child":

                    String childName = tokens[2];
                    String childBirthday = tokens[3];
                    Child child = new Child(childName, childBirthday);
                    people.get(personName).getChildren().add(child);
                    break;

                case "company":
                    String companyName = tokens[2];
                    String department = tokens[3];
                    int salary = Integer.parseInt(tokens[4]);

                    Company company = new Company(companyName, department, salary);
                    people.get(personName).setCompany(company);
                    break;

                case "parent":
                    String parentName = tokens[2];
                    String parentBirthday = tokens[3];
                    Parent parent = new Parent(parentName, parentBirthday);

                    people.get(personName).getParents().add(parent);

                    break;
                case "pokemon":
                    String pokemonName = tokens[2];
                    String pokemonType = tokens[3];

                    Pokemon pokemon = new Pokemon(pokemonName, pokemonType);
                    people.get(personName).getPokemons().add(pokemon);

                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }

            input = scanner.nextLine();
        }

        String targetPerson = scanner.nextLine();

        Map.Entry<String, Person> stringPersonEntry1 = people.entrySet().stream()
                .filter(stringPersonEntry -> stringPersonEntry.getKey().equals(targetPerson)).findFirst().orElse(null);

        assert stringPersonEntry1 != null;


        //TODO: finish printing
        String personName = stringPersonEntry1.getKey();
        String companyName = stringPersonEntry1.getValue().getCompany().getCompanyName();
        String car = stringPersonEntry1.getValue().getCar().getName();
        List<Pokemon> pokemons = stringPersonEntry1.getValue().getPokemons();
        pokemons.forEach(System.out::println);
        List<Child> children = stringPersonEntry1.getValue().getChildren();
        children.forEach(System.out::println);
        List<Parent> parents = stringPersonEntry1.getValue().getParents();
        parents.forEach(System.out::println);


    }
}
