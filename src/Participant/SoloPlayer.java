package Participant;
import java.time.LocalDate;
import java.util.List;

import Enum.Gender;
import Enum.Sport;
import Notification.Notification;

public class SoloPlayer extends Individual implements GameParticipant{
    public SoloPlayer(String name_, 
                String desc,
                Sport sp,
                List<String> nick, 
                Gender g, 
                LocalDate dob,
                List <Notification> notes_
                ) {
        super(name_, desc, sp, nick, g, dob, notes_);
    }
    public SoloPlayer (String name_,
                   String desc,
                   Sport sp,
                   Gender g,
                   LocalDate dob
                   ) {
        super(name_, desc, sp, g, dob);
    }
}
