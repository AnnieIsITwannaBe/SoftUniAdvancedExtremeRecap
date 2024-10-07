package DefiningClassesExercise.Google;

public class Car {
    //•	"{Name} car {carModel} {carSpeed}"

    private String name;

    private int carSpeed;

    public Car(String name, int carSpeed) {
        this.name = name;
        this.carSpeed = carSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCarSpeed() {
        return carSpeed;
    }

    public void setCarSpeed(int carSpeed) {
        this.carSpeed = carSpeed;
    }
}
