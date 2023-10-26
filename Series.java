import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Series extends CreateID implements Notifiable{
    private List<Game> games;
    private List <Notification> notices;
    private String id;
    private String title;
    private String description;
    
    protected String seriesIdentifier(Integer n) {
        return "Game Week ".concat(n.toString());
    }

    public Series (String title_, 
                   String desc, 
                   List<Game> gameList, 
                   List<Notification> notices_
                   ) {
        this.id = createID();
        this.title = title_;
        this.description = desc;
        this.games = gameList;
        this.notices = notices_;
    }
    public Series (String title_, String desc) {
        this(title_, desc, 
             new ArrayList<Game>(),
             new ArrayList<Notification>()
             );
    }

    public String getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title_) {
        this.title = title_;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String desc) {
        this.description = desc;
    }
    public List<Game> getGames() {
        return this.games;
    }
    public void setGames(List<Game> gameList) {
        this.games = gameList;
    }
    public void addGame(Game g) {
        this.games.add(g);
    }
    public void addIndexGame(int i, Game g) {
        this.games.add(i, g);
    }
    
    public void makeNotification(String description, Severity severity) {
        var note = new Notification(this, description, severity);
        this.notices.add(note);
    }
    public List<Notification> getNotices () {
        return this.notices;
    }

    public Optional<String> getGameIdentifier(Game g) {
        int index = this.games.indexOf(g);
        if (index == -1) {
            return Optional.empty();
        }
        else {
            return Optional.of( this.seriesIdentifier(index + 1));
        }
    }

    public Optional<Game> getNextGame (Game g) {
        var index = this.games.indexOf(g);
        if (index == -1) { //Game g not in series
            return Optional.empty();
        }
        else if (this.games.size() == (index + 1)) { //Game g is last game
            return Optional.empty();
        }
        else {
            return Optional.of(this.games.get(index + 1));
        }
    }
    public Optional<Game> getPrevGame (Game g) {
        var index = this.games.indexOf(g);
        if (index == -1) { //Game g not in series
            return Optional.empty();
        }
        else if (index == 0) { //Game g is first game
            return Optional.empty();
        }
        else {
            return Optional.of(this.games.get(index - 1));
        }
    }
}