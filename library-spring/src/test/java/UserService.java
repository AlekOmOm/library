
import com.Alek0m0m.library.spring.web.mvc.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long, UserRepository> {

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }

    public void multiply(int a, int b) {

        System.out.println(a * b);
    }

}
