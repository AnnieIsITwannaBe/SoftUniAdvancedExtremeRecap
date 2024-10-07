package DefiningClassesExercise.RawDataFullyReady;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FRAGILE_CARGO = "fragile";

    private static final String FLAMMABLE_CARGO = "flammable";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<Car> cars = addCarsToInventory(n, scanner);

        String command = scanner.nextLine();

        //After the N lines, you will receive a single line with one of 2 commands "fragile" or "flamable",
        // if the command is "fragile" print all cars whose Cargo Type is "fragile" with a tire whose pressure is  < 1

        if (command.equals(FRAGILE_CARGO)) {
            cars.stream().filter(car -> car.getCargo().getCargoType().equals(FRAGILE_CARGO))
                    .filter(car -> car.getTires().stream().anyMatch(tire -> tire.getPressure() < 1))
                    .forEach(car -> System.out.println(car.getModel()));
        } else {
            //if the command is "flamable" print all cars whose Cargo Type is "flamable"
            // and have Engine Power > 250. The cars should be printed in order of appearing in the input.
            cars.stream().filter(car -> car.getCargo().getCargoType().equals(FLAMMABLE_CARGO))
                    .filter(car -> car.getEngine().getEnginePower() > 250).
                    forEach(car -> System.out.println(car.getModel()));

        }
    }

    private static List<Car> addCarsToInventory(int n, Scanner scanner) {
        List<Car> cars = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String model = tokens[0];

            int engineSpeed = Integer.parseInt(tokens[1]);
            int enginePower = Integer.parseInt(tokens[2]);
            Engine engine = new Engine(engineSpeed, enginePower);

            int cargoWeight = Integer.parseInt(tokens[3]);
            String cargoType = tokens[4];
            Cargo cargo = new Cargo(cargoWeight, cargoType);

            List<Tire> tires = getTires(tokens);

            Car car = new Car(model, engine, cargo, tires);
            cars.add(car);
        }
        return cars;
    }

    private static List<Tire> getTires(String[] tokens) {
        List<Tire> tires = new LinkedList<>();
        for (int j = 5; j < tokens.length; j += 2) {
            int tireAge = Integer.parseInt(tokens[j]);
            double tirePressure = Double.parseDouble(tokens[j + 1]);
            Tire tire = new Tire(tirePressure, tireAge);
            tires.add(tire);
        }
        return tires;
    }
}
