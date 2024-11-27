package Exams.third.Rabbits;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cage {
    public String name;
    public int capacity;
    public List<Rabbit> rabbits;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.rabbits = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(Rabbit rabbit) {
        this.rabbits.add(rabbit);
    }

    public boolean removeRabbit(String rabbitName) {
        //there definitely must be a smarter way to implement this:
        Rabbit rabbitToBeRemoved = this.rabbits.stream().filter(rabbit1 -> rabbit1.getName().equals(rabbitName)).findFirst().orElse(null);
        if (rabbitToBeRemoved != null) {
            this.rabbits.remove(rabbitToBeRemoved);
            return true;
        } else {
            return false;
        }
    }

    public void removeSpecies(String species) {
        ArrayList<Rabbit> rabbitsToBeRemoved = rabbits.stream().filter(rabbit -> rabbit.getSpecies().equals(species)).collect(Collectors.toCollection(ArrayList::new));

        this.rabbits.removeAll(rabbitsToBeRemoved);
    }

    public Rabbit sellRabbit(String name) {
        Rabbit targetRabbit = this.rabbits.stream().filter(rabbit -> rabbit.getName().equals(name)).findFirst().orElse(null);
        assert targetRabbit != null;
        targetRabbit.setAvailable(false);
        return targetRabbit;
    }

    public List<Rabbit> sellRabbitBySpecies(String species) {
        List<Rabbit> rabbitsToBeSold = this.rabbits.stream().filter(rabbit -> rabbit.getSpecies().equals(species)).collect(Collectors.toCollection(ArrayList::new));

        //think about how to convert this into a stream api
        for (Rabbit currentRabbit : rabbitsToBeSold) {
            currentRabbit.setAvailable(false);
        }
        return rabbitsToBeSold;
    }

    public int count() {
        return this.rabbits.size();
    }

    public void report() {
        this.rabbits.stream().filter(Rabbit::isAvailable).forEach(System.out::println);
    }
    //Implement the following features:

    //•	add(Rabbit rabbit) method - adds an entity to the data if there is room for it
    //•	removeRabbit(String name) method - removes a rabbit by given name, if such exists, and returns boolean
    //•	removeSpecies(String species) method - removes all rabbits by given species
    //•	sellRabbit(String name) method - sell (set its available property to false without removing it from the collection) the first rabbit with the given name, also return the rabbit
    //•	sellRabbitBySpecies(String species) method - sells and returns all rabbits from that species as a List
    //•	count() - returns the number of rabbits
    //•	report() - returns a String in the following format, including only not sold rabbits:
    //o	"Rabbits available at {cageName}:
    //{Rabbit 1}
    //{Rabbit 2}
    //(…)"
}
