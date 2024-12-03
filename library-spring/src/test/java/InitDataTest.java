import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

class InitDataTest {

    @Mock
    private UserService userService;

    private UserMapper userMapper;

    @InjectMocks
    private InitData initData;

    // This is the data that we will use for the test
    // private UserDTOInput[] users = {};


    void setUp() {
        MockitoAnnotations.openMocks(this);
        initData = new InitData(userService, userMapper);
    }

    @Test
    void shouldInitUsers() {
        // Given
        initData.initUsers();

        // When
        List<UserDTO> usersRepo = userService.findAll();


        // Then
        List<UserDTO> users = Arrays.stream(initData.users)
                .map(userMapper::toDTO)
                .toList();

        debug("shouldInitUsers", " - usersRepo: " + usersRepo+"\n"+
                " - users: " + users);

        assert(usersRepo.containsAll(users));
    }

    private void debug(String method, String message) {
        System.out.println();
        System.out.println("debug check for"+ method);
        System.out.println(" "+message);
        System.out.println();
    }
}
