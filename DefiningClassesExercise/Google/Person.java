package DefiningClassesExercise.Google;

import java.util.ArrayList;
import java.util.List;

public class Person {
    //•	"{Name} company {companyName} {department} {salary}"
    //•	"{Name} pokemon {pokemonName} {pokemonType}"
    //•	"{Name} parents {parentName} {parentBirthday}"
    //•	"{Name} children {childName} {childBirthday}"


    //•	"{Name} car {carModel} {carSpeed}"


    public Company company;
    public List<Pokemon> pokemons;
    public List<Parent> parents;
    public List<Child> children;
    public Car car;

    public Person() {
        this.pokemons = new ArrayList<>();
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public Person(Company company, List<Pokemon> pokemon, List<Parent> parents, List<Child> children, Car car) {
        this.company = company;
        this.pokemons = pokemon;
        this.parents = parents;
        this.children = children;
        this.car = car;
    }

    //връща ни на този обект за който е извиан, стойноста на компанията
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


}