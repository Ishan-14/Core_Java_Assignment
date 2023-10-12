import java.util.Scanner;

class Shape {
    public double calculateArea() {
        return 0.0;
    }

    public double calculatePerimeter() {
        return 0.0;
    }

    public double calculateVolume() {
        return 0.0;
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Square extends Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * side;
    }
}

public class ShapeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select the shape to calculate area and perimeters for:");
            System.out.println("1. Circle");
            System.out.println("2. Square");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 3) {
                System.out.println("Exiting the program.");
                break;
            }

            Shape shape = null;

            try {
                if (choice == 1) {
                    System.out.print("Enter the radius: ");
                    double radius = scanner.nextDouble();
                    shape = new Circle(radius);
                } else if (choice == 2) {
                    System.out.print("Enter the side length: ");
                    double side = scanner.nextDouble();
                    shape = new Square(side);
                } else {
                    System.out.println("Invalid choice. Please select a valid option.");
                    continue;
                }

                System.out.println("Area: " + shape.calculateArea());
                System.out.println("Perimeter: " + shape.calculatePerimeter());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
