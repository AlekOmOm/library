import com.Alek0m0m.library.jpa.*;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class UserMapper extends EntityToDTOMapperImpl<UserDTOInput, UserDTO, User> {


    private final BiFunction<UserDTOInput, User, UserDTO> toDTO = (dtoInput, entity) -> {
        UserDTO dto = new UserDTO();
        if (entity == null) {
            dto.setId(dtoInput.getId());
            dto.setName(dtoInput.getName());
            dto.setEmail(dtoInput.getEmail());
            dto.setPassword(dtoInput.getPassword());
            dto.setRole(dtoInput.getRole());
        } else {
            entity.setId(dtoInput.getId());
            entity.setName(dtoInput.getName());
            entity.setEmail(dtoInput.getEmail());
            entity.setPassword(dtoInput.getPassword());
            entity.setRole(dtoInput.getRole());
        }
        return dto;
    };


    @Override
    public BiFunction<UserDTOInput, User, UserDTO> toDTO(UserDTOInput dtoInput, User entity) {
        return toDTO;
    }
}