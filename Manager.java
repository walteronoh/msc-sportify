import java.time.LocalDate;
import java.util.List;

public class Manager extends Individual{
    
    protected Gender gender;
    protected LocalDate dateOfBirth;

    public Manager(String name_, 
                String desc,
                Sport sp,
                List<String> nick, 
                Gender g, 
                LocalDate dob 
                ) {
        super(name_, desc, sp, nick, g, dob);
    }
    public Manager(String name_,
                   String desc,
                   Sport sp,
                   Gender g,
                   LocalDate dob
                   ) {
        super(name_, desc, sp, g, dob);
    //comment
        //this.name= name_;
        //this.sport=sp
    }
}
