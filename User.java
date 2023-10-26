import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User extends GenericUser{
    private List<Booking> bookings;
    private Set<Taggable> tags;

    public User(String name_,
                String email_,
                String pass_,
                List<Booking> bookings_,
                Set<Taggable> tags_
                ) {
        super(name_, email_, pass_);
        this.bookings = bookings_;
        this.tags = tags_;
    }
    public User(String name_,
                String email_,
                String pass_
                ) {
        this(name_, email_, pass_,
            new ArrayList<Booking>(),
            new HashSet<Taggable>()
            );
    }

    public List<Booking> getBookings() {
        return this.bookings;
    }
    public void addBooking(Booking b) { // Investigate if this should fail
        this.getBookings().add(b);
    }

    public Set<Taggable> getTags() {
        return this.tags;
    }
    public void addTag(Taggable t) {
        this.tags.add(t);
    }
    public void setTags(Set<Taggable> tags_) {
        this.tags = tags_;
    }

}
