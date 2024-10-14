


import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.Alek0m0m.library.spring.web.mvc.BaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController extends BaseRESTController<User, Long> {

    @Autowired
    public UserController(BaseService<User, Long> service) {
        super(service);
    }

}
