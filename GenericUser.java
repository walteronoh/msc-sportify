import Utils.CreateID;

public abstract class GenericUser {
    protected String id;
    protected String name;
    protected String email;
    protected String password;

    public GenericUser(String name_, String email_, String pass, String prefix) {
        this.name = name_;
        this.email = email_;
        this.password = pass;
        this.id = CreateID.createNamedID(prefix);
    }
    
    public boolean checkPass(String pass) {
        return this.password == pass; 
    }
    public void setPass(String pass) {
        this.password = pass;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String n) {
        this.name = n;
    }
    public String getName() {
        return this.name;
    }

    public void setEmail(String e) {
        this.email = e;
    }    
    public String getEmail() {
        return this.email;
    }

}
