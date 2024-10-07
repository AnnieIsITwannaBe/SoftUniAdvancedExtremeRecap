package DefiningClassesExercise.FamilyThreeSecondTry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Person {
    private String name;
    private String birthDate;
    private List<String> parents;
    private List<String> children;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getParents() {
        return parents;
    }

    public void setParents(List<String> parents) {
        this.parents = parents;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public Person(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
