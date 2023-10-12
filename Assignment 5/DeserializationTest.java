import java.io.*;
import java.util.List;

public class DeserializationTest {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(args[0]))){
            List<Student> students = (List<Student>) ois.readObject();
            for (Student student : students) {
                System.out.println("First Name: " + student.getFirstName());
                System.out.println("Date of Birth: " + student.getDateOfBirth());
                System.out.println("Address: " + student.getAddress().getCity() + ", " + student.getAddress().getState());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}