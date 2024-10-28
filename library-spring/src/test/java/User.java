

import com.Alek0m0m.library.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private String name;
    private String email;
    private String password;
    private String role;

}
