package DefiningClassesExercise.SpeedRacing;

import java.util.*;

public class Main {
    private static final String DRIVE_COMMAND = "Drive";

    private static final String END_COMMAND = "end";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        String input = scanner.nextLine();

        List<Car> cars = new LinkedList<>();

        Map<String, Double> modelsPricePerKm = new LinkedHashMap<>();

        while (n-- > 0) {
            //няма абсолютно никакъв смисъл да си осложняваш живота с тва, но ще ти го оставя да си се смееш
//            if (input[0].equals(DRIVE_COMMAND)) {
//                break;
//            }
            String[] tokens = input.split("\\s+");
            String model = tokens[0];
            double fuelAmount = Double.parseDouble(tokens[1]);
            double fuelCost = Double.parseDouble(tokens[2]);

            Car car = new Car(model, fuelAmount);

            cars.add(car);

            input = scanner.nextLine();
        }

        //"Drive {CarModel} {amountOfKm}",
        input = scanner.nextLine();
        while (!END_COMMAND.equals(input)) {
            String[] tokens = input.split("\\s+");
            String model = tokens[1];
            double amountOfKm = Double.parseDouble(tokens[2]);

            Car targetCar = cars.stream().filter(car -> car.getModel().equals(model)).findFirst().orElse(null);

            assert targetCar != null;

            double fuelInTank = targetCar.getFuelAmount();
            //тука обаче тотално не знам
            if (fuelInTank > amountOfKm) {
                targetCar.setFuelAmount(targetCar.getFuelAmount() - amountOfKm);
                targetCar.setDistanceTravelled(targetCar.getDistanceTravelled() + amountOfKm);
            }

            input = scanner.nextLine();
        }


    }


}
