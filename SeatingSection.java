import java.util.ArrayList;
import java.util.List;

public class SeatingSection {
    private List< List<BookingStatus>> seats;
    private Integer capacity; 
    private Integer bookedCapacity;
    private float price;
    private VenueSection section;

    public SeatingSection (VenueSection sec, float p) {
        this.section = sec;
        this.capacity = sec.getCapacity();
        this.bookedCapacity = 0;
        this.price = p;
        List <List<BookingStatus>> rowList = new ArrayList<List<BookingStatus>>();
        this.section.getRows().forEach(i -> 
                        {
                            var l = new ArrayList<BookingStatus> ();   
                            for (int j = 0; j < i; j++) {
                               l.add(BookingStatus.Available); 
                            }
                            rowList.add(l);
                        });   
        this.seats = rowList; 
    }

    public SeatingSection (VenueSection sec) {
        this(sec, sec.getSeatPrice());
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public Integer getBookedCapacity() {
        return this.bookedCapacity;
    }

    public void setPrice(float p) {
        this.price = p;
    }
    public float getPrice () {
        return this.price;
    }

    public boolean book(Integer row, Integer col) {
       var status = this.seats.get(row).get(col); 
       if(status == BookingStatus.Reserved) {
          return false;
       }
       else {
          this.seats.get(row).set(col, BookingStatus.Reserved);
          this.bookedCapacity++;
          return true;
       }
    }
    public boolean unBook(Integer row, Integer col) {
        this.seats.get(row).set(col, BookingStatus.Available);
        this.bookedCapacity--;
        return true;
    }

    public Boolean checkAvailable(Integer row, Integer col) {
        return (BookingStatus.Available == this.seats.get(row).get(col));
    }

    public List <Pair <Integer, Integer>> getAvailableSeats () {
        var available = new ArrayList<Pair<Integer, Integer>> ();
        int row = 0;
        int col = 0;
    
        for (List <BookingStatus> l : this.seats) {
            for (BookingStatus s: l) {
                if (s == BookingStatus.Available) {
                    available.add(new Pair(row,col));
                    col++;
                }
            };
            row++;
        }

        return available;
    }
}