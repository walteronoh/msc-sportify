import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Booking> bookings;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public List<Booking> getBookings() {
        return this.bookings;
    }
}
