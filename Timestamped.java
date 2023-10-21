import java.sql.Timestamp;
import java.util.Date;

public interface Timestamped {
    public default Timestamp now() {
        Timestamp t = new Timestamp(System.currentTimeMillis());
        return t;
    }    
    public default Date today() {
        Date d = new Date(now().getTime());
        return d;
    }
}
