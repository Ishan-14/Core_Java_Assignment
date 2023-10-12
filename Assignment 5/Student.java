import java.io.Serializable;
import java.util.Date;

class Student implements Serializable {
    private static final long serialVersionUID = 1L; // Serial Version UID
    private String firstName;
    private Date dateOfBirth;
    private Address address;

    public Student(String firstName, Date dateOfBirth, Address address) {
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }
}
