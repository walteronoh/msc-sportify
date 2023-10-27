import java.util.List;

public interface Notifiable {
    public void makeNotification(String desc, Severity sev);
    public List<Notification> getNotices();
}
