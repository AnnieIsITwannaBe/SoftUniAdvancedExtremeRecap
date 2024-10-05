package DefiningClassesExercise.SpeedRacing;

public class Car {
    //"{Model} {FuelAmount} {FuelCostFor1km}", all cars start at 0 kilometers traveled.
    public String model;
    public double fuelAmount;
    public double distanceTravelled;

    public Car(String model, double fuelAmount) {
        this(model, fuelAmount, 0);
        this.model = model;
        this.fuelAmount = fuelAmount;
    }

    //default value-то на primitive int type is 0 anyway but let's see if this way is ok
    public Car(String model, double fuelAmount, double distanceTravelled) {
        this.model = model;
        this.fuelAmount = fuelAmount;
        this.distanceTravelled = distanceTravelled;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }
}
