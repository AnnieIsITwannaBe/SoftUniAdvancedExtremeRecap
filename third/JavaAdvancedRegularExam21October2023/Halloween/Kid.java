package Exams.JavaAdvancedRegularExam21October2023.Halloween;

public class Kid {

    public String name;
    public int age;
    public String street;

    public Kid(String name, int age, String street) {
        this.name = name;
        this.age = age;
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return String.format("%s, %d years old, from %s\n", this.name, this.age, this.street);
    }

    //First, write a Java class Kid with the following fields:
    //•	name: String
    //•	age: int
    //•	street: String
    //The class constructor should receive a name, an age, and the street it’s from. You need to create the appropriate getters and setters. The class should override the toString() method in the following format:
    //"{name}, {age} years old, from {street}"
}
