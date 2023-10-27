public abstract class GenericUser extends CreateID{
    protected String id;
    protected String name;
    protected String email;
    protected String password;

    public GenericUser(){}
    public GenericUser(String name_, String email_, String pass) {
        this.name = name_;
        this.email = email_;
        this.password = pass;
        this.id = createID();
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
