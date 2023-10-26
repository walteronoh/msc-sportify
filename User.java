import java.util.List;

public class User extends GenericUser{
    private List<Booking> bookings;

    public User(String name_,
                String email_,
                String pass_) {
        super(name_, email_, pass_);
    }

    public List<Booking> getBookings() {
        return this.bookings;
    }
    public void addBooking(Booking b) { // Investigate if this should fail
        this.getBookings().add(b);
    }
}
