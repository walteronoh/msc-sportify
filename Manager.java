import java.time.LocalDate;
import java.util.List;

public class Manager extends Individual{
    public Manager(String name_, 
                String desc,
                Sport sp,
                List<String> nick, 
                Gender g, 
                LocalDate dob ,
                List <Notification> notices_
                ) {
        super(name_, desc, sp, nick, g, dob, notices_);
    }
    public Manager(String name_,
                   String desc,
                   Sport sp,
                   Gender g,
                   LocalDate dob
                   ) {
        super(name_, desc, sp, g, dob);
    }
}
