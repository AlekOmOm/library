

import com.Alek0m0m.library.spring.web.mvc.BaseRepositoryAbstract;
import com.Alek0m0m.library.spring.web.mvc.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {


}
