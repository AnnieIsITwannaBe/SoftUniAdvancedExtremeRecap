package DefiningClassesExercise.Google;

public class Parent {
    //    •	"{Name} parents {parentName} {parentBirthday}"
    private String name;

    private String parentBirthday;

    public Parent(String name, String parentBirthday) {
        this.name = name;
        this.parentBirthday = parentBirthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentBirthday() {
        return parentBirthday;
    }

    public void setParentBirthday(String parentBirthday) {
        this.parentBirthday = parentBirthday;
    }
}
