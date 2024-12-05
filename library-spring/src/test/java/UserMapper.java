import com.Alek0m0m.library.jpa.*;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Consumer;


@Service
public class UserMapper extends EntityToDTOMapperImpl<UserDTOInput, UserDTO, User> {


    @Override
    public UserDTO toDTO(UserDTOInput dtoInput) {
        return new UserDTO(dtoInput);
    }

    @Override
    public UserDTO map(UserDTO dto, User entity) {
        if (entity == null && dto == null) {
            return null;
        }

        if (entity == null) {
            return dto;
        }

        if (dto == null) {
            return new UserDTO(entity);
        }

        return new UserDTO(entity)
                .setName(dto.getName() != null ? dto.getName() : entity.getName())
                .setEmail(dto.getEmail() != null ? dto.getEmail() : entity.getEmail())
                .setPassword(dto.getPassword() != null ? dto.getPassword() : entity.getPassword())
                .setRole(dto.getRole() != null ? dto.getRole() : entity.getRole());
    }


}