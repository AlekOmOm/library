
import com.Alek0m0m.library.jpa.BaseEntityDTO;

public class UserDTO extends BaseEntityDTO<User> {

    private String name;
    private String email;
    private String password;
    private String role;

    @Override
    public User toEntity() {
        return new User()
                .setId(this.getId())
                .setName(this.getName())
                .setEmail(this.getEmail())
                .setPassword(this.getPassword())
                .setRole(this.getRole());
    }


    // Getters and Setters

    public UserDTO setId(long id) {
        super.setId(id);
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserDTO setRole(String role) {
        this.role = role;
        return this;
    }

}
