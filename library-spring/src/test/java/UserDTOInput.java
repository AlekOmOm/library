

public class UserDTOInput {

    private long id;
    private String name;
    private String email;
    private String password;
    private String role;

    UserDTOInput() {
    }

    UserDTOInput(long id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public UserDTOInput setId(long id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }

    public UserDTOInput setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTOInput setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTOInput setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserDTOInput setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "UserDTOInput{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


}
