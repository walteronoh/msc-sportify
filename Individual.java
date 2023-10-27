import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Individual extends Participant{
    
    protected Gender gender;
    protected LocalDate dateOfBirth;

    public Individual(String name_, 
                String desc,
                Sport sp,
                List<String> nick, 
                Gender g, 
                LocalDate dob 
                ) {
        super(name_, desc, sp, nick);
        this.gender = g;
        this.dateOfBirth = dob;
    }
    public Individual(String name_,
                   String desc,
                   Sport sp,
                   Gender g,
                   LocalDate dob
                   ) {
        this(name_, desc, sp, new ArrayList<String>(), g, dob);
    }

    public Gender getGender() {
        return this.gender;
    }
    public void setGender(Gender g) {
        this.gender = g;
    }

    public LocalDate getDateOfBirth () {
        return this.dateOfBirth;
    }
    public void setDateOfBirth(LocalDate d) {
        this.dateOfBirth = d;
    }
}
