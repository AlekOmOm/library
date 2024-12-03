import com.Alek0m0m.library.jpa.*;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Consumer;


@Service
public class UserMapper extends EntityToDTOMapperImpl<UserDTOInput, UserDTO, User> {

    @Override
    public UserDTO map(UserDTOInput dtoInput, User entity) {
        UserDTO dto = new UserDTO();
        if (entity == null) {
            dto.setId(dtoInput.getId())
                    .setName(dtoInput.getName())
                    .setEmail(dtoInput.getEmail())
                    .setPassword(dtoInput.getPassword())
                    .setRole(dtoInput.getRole());
        } else {
            entity.setId(dtoInput.getId())
                    .setName(dtoInput.getName())
                    .setEmail(dtoInput.getEmail())
                    .setPassword(dtoInput.getPassword())
                    .setRole(dtoInput.getRole());
        }
        return dto;
    }


}