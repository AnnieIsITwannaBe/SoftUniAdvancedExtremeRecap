package Exams.JavaAdvancedRegularExam21October2023.Halloween;

import java.util.ArrayList;
import java.util.List;

public class House {
    public int capacity;
    public List<Kid> kids;

    public House(int capacity) {
        this.capacity = capacity;
        this.kids = new ArrayList<>();
    }

    public void addKid(Kid kid) {
        this.kids.add(kid);
    }

    public boolean removeKid(String name) {
        Kid kidToBeRemoved = this.kids.stream().filter(kid -> kid.getName().equals(name)).findFirst().orElse(null);
        if (kidToBeRemoved != null) {
            this.kids.remove(kidToBeRemoved);
            return true;
        } else {
            return false;
        }
    }
    public Kid getKid(String name) {
        return this.kids.stream()
                .filter(kid -> kid.getName().equals(name)).findFirst().orElse(null);

    }
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("Children who visited a house for candy:\n");

        for (Kid kid : kids) { // Assuming "kids" is the collection
            sb.append(String.format("  %s from %s street%n", kid.getName(), kid.getStreet()));
        }
        return sb.toString();
    }

    public int getAllKids() {
        return kids.size();
    }

    //Next, write a Java class House with data (a collection that stores the Kids)
    // . All entities inside the repository have the same fields. Also, the House class should have those fields:
    //•	capacity: int
    //•	data: List<Kid>
    //The class constructor should receive capacity. Also, it should initialize the data with a new collection instance. Implement the following features:
    //•	Method addKid(Kid kid) – adds an entity to the data if there is an empty space for the kid.
    //•	Method removeKid(String name) – removes the kid by given name, if such exists, and returns boolean.
    //•	Method getKid(String street) – returns the kid of a given street or null if no such kid exists.
    //•	Getter getAllKids() – returns the number of kids.
    //•	getStatistics() – returns a String in the following format:
    //" Children who visited a house for candy:
    //		 {name} from {street} street
    //          {name} from {street} street
    //          (…)"
}
