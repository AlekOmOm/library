import com.Alek0m0m.library.jpa.EntityToDTOMapper;

public class UserEntityToDTOMapper implements EntityToDTOMapper<User, UserDTO> {

    @Override
    public UserDTO apply(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        return dto;
    }

    @Override
    public UserDTO map(User entity) {
        return apply(entity);
    }
}
