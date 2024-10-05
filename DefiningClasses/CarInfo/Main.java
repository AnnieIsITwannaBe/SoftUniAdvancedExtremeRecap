package DefiningClasses.CarInfo;

import DefiningClasses.Car;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       int n = Integer.parseInt(scanner.nextLine());
        List<Car> cars = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String brand = tokens[0];
            String model = tokens[1];
            int power = Integer.parseInt(tokens[2]);

            Car car = new Car(brand, model, power);
            Car second = new Car(brand, power);
            cars.add(car);
        }

        cars.forEach(System.out::println);
    }
}
