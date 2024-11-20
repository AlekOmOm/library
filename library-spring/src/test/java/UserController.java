
import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController extends BaseRESTController<User, UserDTO, UserService, UserRepository> {

    @Autowired
    public UserController(UserService service) {
        super(service);
    }


}
