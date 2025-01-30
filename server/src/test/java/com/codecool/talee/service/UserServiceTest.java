package com.codecool.talee.service;

import com.codecool.talee.DTO.user.NewUserDTO;
import com.codecool.talee.DTO.user.UserDTO;
import com.codecool.talee.exception.EntityNotFoundException;
import com.codecool.talee.mapper.UserMapper;
import com.codecool.talee.model.users.Role;
import com.codecool.talee.model.users.UserEntity;
import com.codecool.talee.repository.RoleRepository;
import com.codecool.talee.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Nested;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private static final long TEST_USER_ID = 1L;
    private static final String TEST_USERNAME = "testuser";
    private static final String TEST_PASSWORD = "testuser";
    private static final String TEST_EMAIL = "test@gmail.com";

    private UserEntity mockUserEntity;
    private UserDTO mockUserDTO;
    private NewUserDTO newUserDTO;

    @BeforeEach
    void setUp() {
        mockUserEntity = new UserEntity();
        mockUserEntity.setId(TEST_USER_ID);
        mockUserEntity.setUsername(TEST_USERNAME);

        mockUserDTO = new UserDTO(TEST_USER_ID, TEST_USERNAME, Set.of(), Set.of(new Role("USER")));

        newUserDTO = new NewUserDTO(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL);
    }

    @Nested
    class GetUserTests {

        @Test
        void getUserById_WhenUserExists_ReturnsUserDTO() {
            when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.of(mockUserEntity));
            when(userMapper.userToUserDTO(mockUserEntity)).thenReturn(mockUserDTO);

            UserDTO userDTO = userService.getUserById(TEST_USER_ID);

            assertNotNull(userDTO);
            assertEquals(TEST_USER_ID, userDTO.id());
            assertEquals(TEST_USERNAME, userDTO.username());

            verify(userRepository).findById(TEST_USER_ID);
            verify(userMapper).userToUserDTO(mockUserEntity);
        }

        @Test
        void getUserById_WhenUserDoesNotExist_ThrowsException() {
            when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class, () -> userService.getUserById(TEST_USER_ID));

            verify(userRepository).findById(TEST_USER_ID);
        }

        @Test
        void getAllUsers_ReturnsUserList() {
            UserEntity anotherUser = new UserEntity();
            anotherUser.setId(2L);
            anotherUser.setUsername("anotheruser");

            UserDTO anotherUserDTO = new UserDTO(2L, "anotheruser", Set.of(), Set.of(new Role("USER")));

            when(userRepository.findAll()).thenReturn(List.of(mockUserEntity, anotherUser));
            when(userMapper.userToUserDTO(mockUserEntity)).thenReturn(mockUserDTO);
            when(userMapper.userToUserDTO(anotherUser)).thenReturn(anotherUserDTO);

            List<UserDTO> users = userService.getAllUsers();

            assertEquals(2, users.size());
            verify(userRepository).findAll();
        }
    }

    @Nested
    class CreateUserTests {

        @Test
        void createUser_SuccessfullyCreatesUser() {
            when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> {
                UserEntity savedUser = invocation.getArgument(0);
                savedUser.setId(TEST_USER_ID);
                return savedUser;
            });
            when(roleRepository.findByName(any())).thenReturn(Optional.of(new Role("USER")));
            when(passwordEncoder.encode(any())).thenReturn(TEST_PASSWORD);
            when(userMapper.userToUserDTO(any(UserEntity.class))).thenReturn(mockUserDTO);

            UserDTO createdUser = userService.createUser(newUserDTO);

            assertNotNull(createdUser);
            assertEquals(TEST_USER_ID, createdUser.id());
            assertEquals(TEST_USERNAME, createdUser.username());

            verify(userRepository).save(any(UserEntity.class));
            verify(userMapper).userToUserDTO(any(UserEntity.class));
        }
    }

    @Nested
    class ModifyUserTests {

        @Test
        void modifyUser_WhenUserExists_UpdatesAndReturnsUserDTO() {
            when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.of(mockUserEntity));
            when(userRepository.save(any(UserEntity.class))).thenReturn(mockUserEntity);
            when(userMapper.userToUserDTO(any(UserEntity.class))).thenReturn(mockUserDTO);

            UserDTO updatedUser = userService.modifyUser(mockUserDTO);

            assertNotNull(updatedUser);
            assertEquals(TEST_USER_ID, updatedUser.id());
            assertEquals(TEST_USERNAME, updatedUser.username());

            verify(userRepository).findById(TEST_USER_ID);
            verify(userRepository).save(any(UserEntity.class));
        }

        @Test
        void modifyUser_WhenUserDoesNotExist_ThrowsException() {
            when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class, () -> userService.modifyUser(mockUserDTO));

            verify(userRepository).findById(TEST_USER_ID);
        }
    }

    @Nested
    class DeleteUserTests {

        @Test
        void deleteUser_WhenUserExists_ReturnsTrue() {
            when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.of(mockUserEntity));

            boolean result = userService.deleteUser(TEST_USER_ID);

            assertTrue(result);
            verify(userRepository, times(1)).findById(TEST_USER_ID);
            verify(userRepository, times(1)).delete(mockUserEntity);
        }

        @Test
        void deleteUser_WhenUserDoesNotExist_ThrowsException() {
            when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.empty());
            assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(TEST_USER_ID));
        }
    }
}
