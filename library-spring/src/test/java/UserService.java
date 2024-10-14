
import com.Alek0m0m.library.spring.web.mvc.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long> {

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }

}
