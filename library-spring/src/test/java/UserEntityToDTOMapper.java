import com.Alek0m0m.library.jpa.*;
import com.Alek0m0m.library.spring.web.mvc.*;
public class UserEntityToDTOMapper extends EntityToDTOMapperImpl<UserDTOInput, UserDTO, User> {

    @Override
    public UserDTO toDTO(UserDTOInput dtoInput) {
        UserDTO dto = new UserDTO();
        dto.setId(dtoInput.getId());
        dto.setName(dtoInput.getName());
        dto.setEmail(dtoInput.getEmail());
        dto.setPassword(dtoInput.getPassword());
        dto.setRole(dtoInput.getRole());
        return dto;
    }

    @Override
    public User toEntity(UserDTO dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        return entity;
    }

    @Override
    public UserDTO toDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        return dto;
    }

}
