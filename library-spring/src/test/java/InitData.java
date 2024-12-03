import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InitData {

    public final UserDTOInput[] users = {
        new UserDTOInput(0, "name", "email", "password", "role"),
        new UserDTOInput(0, "Bob", "Bob@mail.com", "123", "user"),
        new UserDTOInput(0, "Alice", "Alice@mail.com", "123", "user"),
        new UserDTOInput(0, "Admin", "Admin@mail.com", "123", "admin")
    };

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public InitData(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        initUsers();
    }


    void initUsers() {
        // array of users.
        Arrays.stream(users).toList() // convert to list
                .stream()
                .map(userMapper::toDTO) // convert dtoInput to DTO
                .forEach(userService::save); // save to db

        // oop version:

        for (UserDTOInput user : users) {
            UserDTO dto = userService.save(userMapper.toDTO(user));
            System.out.println("User: " + user);
            System.out.println("UserDTO: " + dto);
            assert(user.equals(userMapper.toDTO(user)));
        }

    }

}
