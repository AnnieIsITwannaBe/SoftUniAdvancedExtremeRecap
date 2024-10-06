package DefiningClassesExercise.CatLady;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String END_COMMAND = "End";

    public static void main(String[] args) {
        //"Siamese", "Cymric" and the very famous Bulgarian breed "Street Extraordinaire"
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Cat> cats = addCatsToInventory(input, scanner);

        String targetCat = scanner.nextLine();

        Cat catSelected = cats.stream().filter(cat -> cat.getName().equals(targetCat)).findFirst().orElse(null);

        assert catSelected != null;

        System.out.println(catSelected);


    }

    private static List<Cat> addCatsToInventory(String input, Scanner scanner) {
        List<Cat> cats = new LinkedList<>();
        while (!END_COMMAND.equals(input)) {
            String[] tokens = input.split("\\s+");
            String type = tokens[0];
            String name = tokens[1];
            int catStat = Integer.parseInt(tokens[2]);

            Cat cat = switch (type) {
                case "Siamese" -> new Siamese(name, catStat);
                case "Cymric" -> new Cymric(name, catStat);
                case "StreetExtraordinaire" -> new StreetExtraordinaire(name, catStat);
                default -> null;
            };

            cats.add(cat);

            input = scanner.nextLine();
        }
        return cats;
    }
}
//тази задача се решева с inheritance
//представи си тази идея като идеята на платон за формите
//super -> superClass-> не просто клас, а КЛАСЪТ, от който произлизат всичко останали класове
//оттампроизлезлите -> наследяват всички характериситики на КЛАСЪТ, но имат и собствени специфики
//за да извикаме конструктора от КЛАСЪТ, в тялото на конструктура на класа, който го наследвява
//го извикваме използвайки ключовата дума super(), не като при constructor chain-ването
//от конструктора с най-млако параметри към класа с най-много параметри this()
//this is utilized withing the frame of the same class, in order to inherit a class's properties we need the super keyword
//create constructor matching super