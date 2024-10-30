
import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import com.Alek0m0m.library.spring.web.mvc.BaseRepository;
import com.Alek0m0m.library.spring.web.mvc.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController extends BaseRESTController<User, UserDTO, UserService, UserRepository> {

    @Autowired
    public UserController(UserService service) {
        super(service);
    }

    public void multiply(int a, int b) {
        service.multiply(a, b);
        service.findByName("name");
        getService().findByName("name");
        // getService().multiply(a, b);
        System.out.println(a * b);
    }

    @Override
    public ResponseEntity<UserDTO> create(BaseEntityDTO entityDTO) {
        return null;
    }
}
