import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Enum.Gender;
import Enum.Sport;

public class PositionalPlayer extends Individual implements Taggable{
    private String position; //Defender, Attacker etc

    public PositionalPlayer(String name_,
                            String desc,
                            Sport sp,
                            List<String> nick,
                            Gender g,
                            LocalDate dob,
                            String pos,
                            List <Notification> notes_
                            ) {
        super(name_, desc, sp, nick, g, dob, notes_);
        this.position = pos;
    }
    public PositionalPlayer(String name_,
                            String desc,
                            Sport sp,
                            Gender g,
                            LocalDate dob,
                            String pos
                            ) {
        this(name_, desc, sp, new ArrayList<String> (), 
            g, dob, pos, 
            new ArrayList<Notification>());
    }

    public String getPosition() {
        return this.position;
    }
    public void setPosition(String pos) {
        this.position = pos;
    }
}
