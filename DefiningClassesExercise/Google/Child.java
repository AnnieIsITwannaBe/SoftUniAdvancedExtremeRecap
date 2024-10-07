package DefiningClassesExercise.Google;

public class Child {
    //•	"{Name} children {childName} {childBirthday}"

    private String name;
    private String childBirthday;

    public Child(String name, String childBirthday) {
        this.name = name;
        this.childBirthday = childBirthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChildBirthday() {
        return childBirthday;
    }

    public void setChildBirthday(String childBirthday) {
        this.childBirthday = childBirthday;
    }
}
