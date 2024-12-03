import com.Alek0m0m.library.spring.web.mvc.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<UserDTOInput, UserDTO, User, UserMapper, UserRepository> {

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected void resetIncrement() {
        getRepository().resetAutoIncrement();
    }

    public UserDTO findByUsername(String username) {

        UserDTOInput userDTOInput = new UserDTOInput();

        return null;
    }


    public List<UserDTO> findByNameAndEmail(String name, String email) {
        List<UserDTO> repoUsers = getDtoMapper().mapToDTOs(getRepository().findAll()).stream().toList();

        return repoUsers.stream()
                .filter(userDTO ->
                            userDTO.getName().equals(name)
                                    &&
                                    userDTO.getEmail().equals(email))
                .toList();


    }
}