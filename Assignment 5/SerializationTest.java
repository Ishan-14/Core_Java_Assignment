import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class SerializationTest {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        // Create some students
        students.add(new Student("Alice", new Date(), new Address("City1", "State1", 12345, "Country1")));
        students.add(new Student("Bob", new Date(), new Address("City2", "State2", 54321, "Country2")));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(args[0]))) {
            oos.writeObject(students);
            System.out.println("Serialization successful.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}