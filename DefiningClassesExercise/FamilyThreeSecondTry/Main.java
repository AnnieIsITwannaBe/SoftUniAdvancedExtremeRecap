package DefiningClassesExercise.FamilyThreeSecondTry;

import java.util.*;

public class Main {
    private static final String BREAK_COMMAND = "End";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String personInfo = scanner.nextLine();

        String input = scanner.nextLine();

        Map<String, Person> byPersonName = new LinkedHashMap<>();

        List<String> inputData = new ArrayList<>();

        while (!BREAK_COMMAND.equals(input)) {
            //handle the only case that doesn't engage in tying family relations:
            if (!input.contains("-")) {
                String[] tokens = input.split("\\s+");
                String name = tokens[0];
                String birthDate = tokens[1];
                Person person = new Person(name, birthDate);
                byPersonName.putIfAbsent(name, person);
            } else {
                inputData.add(input);
            }

            for (String inputLine : inputData) {
                //the person of the left is the parent of the person on the right:
                String parent = inputLine.split("\\s+-\\s+")[0];
                String child = inputLine.split("\\s+-\\s+")[1];

                //you will either receive the birthdate of the person
                //or you will receive the name of the person

                //check's whether it's the parent's date of name:
                //it's the name:
                if (!parent.contains("-")) {
                    //find by name the person in our map:
                    Person targetParent = byPersonName.get(parent);
                    //add the child to the child list of the parent:
                    targetParent.getChildren().add(child);
                } else {
                    //it's the date:
                    //in order to find who is associated with that birthdate
                    //what we need to do is iterate through all the persons
                    //that we have stored up until now in our map:
                    for (Person personToFind : byPersonName.values()) {
                        //check if any of the persons there has the same birthdate:
                        if (personToFind.getBirthDate().equals(parent)) {
                            //once we find the person that contains the matching birthday data we increment its children:
                            personToFind.getChildren().add(child);
                            break;
                        }
                    }
                }

                //check's whether it's the child's name or birthdate:
                //if it's the child's name we:
                if (!child.contains("-")) {
                    //access the child object and
                    Person targetChild = byPersonName.get(child);
                    //add the parent to the parent list of the child:
                    targetChild.getParents().add(parent);
                } else {
                    //if it's the child's birthdate:
                    //we iterate through all the values in our map
                    //which represent Person objects and check every
                    //single person object that is contained withing that map
                    //if by any change it contains a birthday field that matches the one
                    //provided to us by the user:
                    for (Person personToFind : byPersonName.values()) {
                        if (personToFind.getBirthDate().equals(child)) {
                            //once we have found it if such exists, we break the loop
                            //because in the problem it's specifically mentioned that
                            //all birthdays are unique, so once we have found the
                            //person which corresponds to that birthday
                            //there is literally no need to iterate
                            //through the other elements in the list
                            personToFind.getParents().add(parent);
                            break;
                        }
                    }
                }
            }
            input = scanner.nextLine();
        }

        if (!personInfo.contains("/")) {
            Person person = byPersonName.get(personInfo);

            String name = person.getName();

            String birthDate = person.getBirthDate();

            System.out.printf("%s -> %s", name, birthDate);

            System.out.println("Parents:");
            List<String> parents = person.getParents();
            parents.forEach(System.out::println);

            System.out.println("Children:");
            List<String> children = person.getChildren();
            children.forEach(System.out::println);
        } else {
            //TODO: basically optimize it so this it's not repetitive and finish it but great work champ;
            Person person = byPersonName.values().stream().filter(targetPerson
                    -> targetPerson.getBirthDate().equals(personInfo)).findFirst().orElse(null);
            assert person != null;

            String name = person.getName();
            String birthDate = person.getBirthDate();
            List<String> parents = person.getParents();
            List<String> children = person.getChildren();
        }
    }


}
}
