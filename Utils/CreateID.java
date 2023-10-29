package Utils;
import java.util.concurrent.atomic.AtomicLong;

// public abstract class CreateID {
public abstract class CreateID {
    
 // Mechanism to get new ids
    CreateID() {}
    protected static AtomicLong idCounter = new AtomicLong();

    public static String createID() {
    return String.valueOf(idCounter.getAndIncrement());
    }

    public static String createNamedID(String prefix) {
        return prefix.concat(createID());
    }

}
