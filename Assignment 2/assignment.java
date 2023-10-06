import java.util.Scanner;

// Custom exceptions
class InvalidShapeException extends Exception {
    public InvalidShapeException(String message) {
        super(message);
    }
}

class NegativeValueException extends Exception {
    public NegativeValueException(String message) {
        super(message);
    }
}

// Shape interface
interface Shape {
    double calculateArea();
    double calculatePerimeter();
    double calculateVolume();
}

// 2D shapes
class Circle implements Shape {
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

    @Override
    public double calculateVolume() {
        return 0; // Not applicable for 2D shapes
    }
}

class Square implements Shape {
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

    @Override
    public double calculateVolume() {
        return 0; // Not applicable for 2D shapes
    }
}

// 3D shapes
class Sphere implements Shape {
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return 4 * Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 0; // Not applicable for spheres
    }

    @Override
    public double calculateVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select the shape to calculate area, perimeter, and volume for:");
            System.out.println("1. Circle");
            System.out.println("2. Square");
            System.out.println("3. Sphere");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            if (choice == 4) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                Shape shape = null;
                String shapeName = "";

                switch (choice) {
                    case 1:
                        System.out.print("Enter radius: ");
                        double radius = scanner.nextDouble();
                        if (radius < 0) {
                            throw new NegativeValueException("Radius cannot be negative");
                        }
                        shape = new Circle(radius);
                        shapeName = "Circle";
                        break;
                    case 2:
                        System.out.print("Enter side length: ");
                        double side = scanner.nextDouble();
                        if (side < 0) {
                            throw new NegativeValueException("Side length cannot be negative");
                        }
                        shape = new Square(side);
                        shapeName = "Square";
                        break;
                    case 3:
                        System.out.print("Enter radius: ");
                        double sphereRadius = scanner.nextDouble();
                        if (sphereRadius < 0) {
                            throw new NegativeValueException("Radius cannot be negative");
                        }
                        shape = new Sphere(sphereRadius);
                        shapeName = "Sphere";
                        break;
                    default:
                        throw new InvalidShapeException("Invalid choice");
                }

                System.out.println("Calculating for " + shapeName + ":");
                System.out.println("Area: " + shape.calculateArea());
                System.out.println("Perimeter: " + shape.calculatePerimeter());
                if (shape.calculateVolume() > 0) {
                    System.out.println("Volume: " + shape.calculateVolume());
                }
            } catch (InvalidShapeException | NegativeValueException e) {
                System.err.println("Error: " + e.getMessage());
            }

            System.out.println("-------------------------");
        }

        scanner.close();
    }
}
