
import com.Alek0m0m.library.spring.web.mvc.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long, UserRepository> {

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
        // repository = UserRepository; // TODO: Repository auto linkage

    }

    public void multiply(int a, int b) {
        // repository.multiply(a, b); // TODO: implement repository access for custom methods
        System.out.println(a * b);
    }

    @Override
    protected void mergeRelations(User entity, User existingEntity) {

    }
}
