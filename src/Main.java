import User.User;
import Venue.Venue;

import java.time.LocalDate;

import Enum.Gender;
import Enum.Sport;
import Participant.SoloPlayer;
import Tagging.Taggable;

public class Main {
    public static void main(String[] args) {
        var u1 = new User("Bob", "u@home.com", "1234");
        
        u1.addTag(Sport.FOOTBALL);
        u1.addTag(Sport.ATHLETICS);

        var dob = LocalDate.now();

        var plyr = new SoloPlayer("jack", "tall", Sport.FOOTBALL, Gender.Male,dob);

        u1.addTag(plyr);
        var stadium = new Venue("Nyayo", "Nairobi", "Beatiful");
        u1.addTag(stadium);

        for(Taggable t : u1.getTags()) {
            System.out.println(t.getName());
        }
    } 
}
