import com.Alek0m0m.library.jpa.BaseEntity;
import com.Alek0m0m.library.jpa.BaseEntityDTO;
import lombok.Data;

import java.util.HashMap;

@Data
public class UserDTO extends BaseEntityDTO<User> {

    private String name;
    private String email;
    private String password;
    private String role;

    @Override
    public User toEntity() {
        User user = new User();
        user.setId(this.getId());
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setRole(this.role);
        return user;
    }
}
