package Exams.third.Rabbits;

public class Rabbit {

    public String name;
    public String species;

    public boolean available;

    public Rabbit(String name, String species) {
        this.name = name;
        this.species = species;
    }
    //First, write a Java class Rabbit with the following fields:
    //•	name: String

    public String getSpecies() {
        return species;
    }

    @Override
    public String toString() {
        return String.format("(%s): %s\n", this.species, this.name);
    }

    public String getName() {
        return name;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }
}
