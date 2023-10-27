import java.util.ArrayList;
import java.util.List;

public abstract class Participant extends CreateID {
    protected String name;
    protected String description;
    protected String id;
    protected Sport sport;
    protected List<String> nickNames;    

    public Participant(String name_, String desc, Sport sp, List<String> nick) {
        this.id = createID();
        this.name = name_;
        this.description = desc;
        this.sport = sp;
        this.nickNames = nick;
    }
    public Participant(String name_, String desc, Sport sp) {
        this(name_,desc, sp, new ArrayList<String>());
    }

    public String getID() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String n) {
        this.name = n;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String desc) {
        this.description = desc;
    }

    public Sport getSport() {
        return this.sport;
    }
    public void setSport(Sport s) {
        this.sport = s;
    }

    public List<String> getNickNames() {
        return this.nickNames;
    }
    public void setNickName(List<String> ns) {
        this.nickNames = ns;
    }
    public void addNickName( String n) {
        this.nickNames.add(n.toLowerCase());
    }
    public boolean isNickName (String n) {
        return this.nickNames.contains(n.toLowerCase());
    }
}
