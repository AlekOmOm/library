import com.Alek0m0m.library.spring.web.mvc.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, UserDTO, UserRepository> {

    @Autowired
    public UserService(UserRepository repository, UserEntityToDTOMapper entityToDtoMapper) {
        super(repository, entityToDtoMapper);
    }

    public void multiply(int a, int b) {
        System.out.println(a * b);
    }
}