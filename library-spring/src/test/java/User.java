

import com.Alek0m0m.library.jpa.BaseEntity;
import com.Alek0m0m.library.spring.web.mvc.annotation.GenerateDTO;
import com.Alek0m0m.library.spring.web.mvc.annotation.RelationAnnotationProcessor;
import jakarta.persistence.Entity;

import java.util.HashSet;
import java.util.Set;

@Entity
@GenerateDTO
public class User extends BaseEntity<Long> {

    private String email;
    private String password;
    private String role;

    public User() {
    }

    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String name, String email, String password, String role) {
        super.setId(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static void main(String[] args) {
        User user = new User();
        RelationAnnotationProcessor relationAnnotationProcessor = new RelationAnnotationProcessor();

        System.out.println(user.getId());
        user.setId(1L);
        System.out.println(user.getId());
    }
}
