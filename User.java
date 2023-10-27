import java.util.List;

public class User extends CreateID{
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Booking> bookings;

    // Constructor for the User class
    public User(int id, String name, String email, String password, List<Booking> bookings) {
        // Initialize the fields of the User class
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bookings = bookings;
    }
    public int getId() {
        return this.id;
    }

    public void setName(String n) {
        this.name = n;
    }
    public String getName() {
        return this.name;
    }

    public void setEmail(String e) {
        this.email = e;
    }    
    public String getEmail() {
        return this.email;
    }

    public List<Booking> getBookings() {
        return this.bookings;
    }
    public void addBooking(Booking b) { // Investigate if this should fail
        this.getBookings().add(b);
    }
}
