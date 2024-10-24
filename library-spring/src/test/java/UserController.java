
import com.Alek0m0m.library.spring.web.mvc.base.BaseRESTController;
import com.Alek0m0m.library.spring.web.mvc.base.BaseRepository;
import com.Alek0m0m.library.spring.web.mvc.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController extends BaseRESTController<User, Long, UserService> {

    @Autowired
    public UserController(BaseService<User, Long, BaseRepository<User, Long>> service) {
        super(service);
    }

    public void multiply(int a, int b) {
        service.multiply(a, b);
        service.findByName("name");
        getService().findByName("name");
        // getService().multiply(a, b);
        System.out.println(a * b);
    }

}