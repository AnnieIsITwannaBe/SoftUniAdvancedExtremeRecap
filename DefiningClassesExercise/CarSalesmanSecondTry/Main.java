package DefiningClassesExercise.CarSalesmanSecondTry;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfEngines = Integer.parseInt(scanner.nextLine());

        List<Engine> engines = addEnginesToInventory(numberOfEngines, scanner);

        int numberOfCars = Integer.parseInt(scanner.nextLine());

        List<Car> cars = addCarsToInventory(numberOfCars, scanner, engines);

        cars.forEach(System.out::println);
    }

    private static List<Car> addCarsToInventory(int numberOfCars, Scanner scanner, List<Engine> engines) {
        List<Car> cars = new LinkedList<>();
        for (int i = 0; i < numberOfCars; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String model = tokens[0];
            String engineModel = tokens[1];

            Engine engine = engines.stream().filter(engine1 -> engine1.getModel().equals(engineModel)).findFirst().orElse(null);

            assert engine != null;

            Car car = switch (tokens.length) {
                case 2 -> handleOnlyMandatoryFieldsPresentCar(model, engine);
                case 3 -> handleOneOptionalFieldPresentCar(tokens, model, engine);
                default -> handleAllFieldsPresentCar(tokens, model, engine);
            };

            cars.add(car);
        }
        return cars;
    }

    private static Car handleAllFieldsPresentCar(String[] tokens, String model, Engine engine) {
        Car car;
        int weight = Integer.parseInt(tokens[2]);
        String color = tokens[3];
        car = new Car(model, engine, weight, color);
        return car;
    }

    private static Car handleOneOptionalFieldPresentCar(String[] tokens, String model, Engine engine) {
        Car car;
        String field = tokens[2];
        if (Character.isDigit(field.charAt(0))) {
            int weight = Integer.parseInt(tokens[2]);
            car = new Car(model, engine, weight);
        } else {
            car = new Car(model, engine, field);
        }
        return car;
    }

    private static Car handleOnlyMandatoryFieldsPresentCar(String model, Engine engine) {
        Car car;
        car = new Car(model, engine);
        return car;
    }

    private static List<Engine> addEnginesToInventory(int numberOfEngines, Scanner scanner) {
        List<Engine> engines = new LinkedList<>();
        for (int i = 0; i < numberOfEngines; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String engineModel = tokens[0];
            int enginePower = Integer.parseInt(tokens[1]);

            Engine engine = switch (tokens.length) {
                case 2 -> handleOnlyMandatoryFieldsPresentEngine(engineModel, enginePower);
                case 3 -> handleOneOptionalFieldPresentEngine(tokens, engineModel, enginePower);
                default -> handleAllFieldsPresentEngine(tokens, engineModel, enginePower);
            };

            engines.add(engine);

        }
        return engines;
    }

    private static Engine handleAllFieldsPresentEngine(String[] tokens, String engineModel, int enginePower) {
        Engine engine;
        int displacement = Integer.parseInt(tokens[2]);
        String efficiency = tokens[3];
        engine = new Engine(engineModel, enginePower, displacement, efficiency);
        return engine;
    }

    private static Engine handleOneOptionalFieldPresentEngine(String[] tokens, String engineModel, int enginePower) {
        Engine engine;
        String field = tokens[2];
        if (Character.isDigit(field.charAt(0))) {
            int displacement = Integer.parseInt(field);
            engine = new Engine(engineModel, enginePower, displacement);
        } else {
            engine = new Engine(engineModel, enginePower, field);
        }
        return engine;
    }

    private static Engine handleOnlyMandatoryFieldsPresentEngine(String engineModel, int enginePower) {
        Engine engine;
        engine = new Engine(engineModel, enginePower);
        return engine;
    }
}
