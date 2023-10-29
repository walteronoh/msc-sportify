package Participant;
import java.time.LocalDate;
import java.util.List;

import Notification;
import Enum.Gender;
import Enum.Sport;

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
