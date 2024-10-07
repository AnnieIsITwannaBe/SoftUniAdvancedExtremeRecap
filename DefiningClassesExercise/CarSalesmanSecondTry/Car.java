package DefiningClassesExercise.CarSalesmanSecondTry;

import DefiningClassesExercise.CarSalesmanSecondTry.Engine;

public class Car {
    public String model;
    public Engine engine;
    public int weight;
    public String color;

    public Car(String model, Engine engine, int weight) {
        this(model, engine, weight, null);
        this.model = model;
        this.engine = engine;
        this.weight = weight;
    }

    public Car(String model, Engine engine, String color) {
        this(model, engine, -1, color);
        this.model = model;
        this.engine = engine;
        this.color = color;
    }

    public Car(String model, Engine engine) {
        this(model, engine, -1, null);
        this.model = model;
        this.engine = engine;
    }

    public Car(String model, Engine engine, int weight, String color) {
        this.model = model;
        this.engine = engine;
        this.weight = weight;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
