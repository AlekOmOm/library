import com.Alek0m0m.library.jpa.*;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class UserMapper extends EntityToDTOMapperImpl<UserDTOInput, UserDTO, User> {


    private final BiFunction<UserDTOInput, User, UserDTO> toDTO = (dtoInput, entity) -> {
        UserDTO dto = new UserDTO();
        if (entity == null) {
            dto
                    .setId(dtoInput.getId())
                    .setName(dtoInput.getName())
                    .setEmail(dtoInput.getEmail())
                    .setPassword(dtoInput.getPassword())
                    .setRole(dtoInput.getRole());
        } else {
            entity
                    .setId(dtoInput.getId())
                    .setName(dtoInput.getName())
                    .setEmail(dtoInput.getEmail())
                    .setPassword(dtoInput.getPassword())
                    .setRole(dtoInput.getRole());
        }
        return dto;
    };


    @Override
    public BiFunction<UserDTOInput, User, UserDTO> toDTO(UserDTOInput dtoInput, User entity) {
        return toDTO;
    }
}