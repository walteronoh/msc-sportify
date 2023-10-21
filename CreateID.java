import java.util.concurrent.atomic.AtomicLong;

public abstract class CreateID {
    
 // Mechanism to get new ids
    protected static AtomicLong idCounter = new AtomicLong();

    public static String createID() {
    return String.valueOf(idCounter.getAndIncrement());
    }

}
