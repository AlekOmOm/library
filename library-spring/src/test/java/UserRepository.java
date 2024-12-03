import com.Alek0m0m.library.spring.web.mvc.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    // Additional query methods if needed


    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE User AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

}