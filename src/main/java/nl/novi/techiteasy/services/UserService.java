package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.UserDTO;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.UsernameNotFoundException;
import nl.novi.techiteasy.mappers.UserMapper;
import nl.novi.techiteasy.models.Role;
import nl.novi.techiteasy.models.User;
import nl.novi.techiteasy.repositories.UserRepository;
import nl.novi.techiteasy.utils.PasswordGenerator;
import nl.novi.techiteasy.utils.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserDTO> getUsers() {
        List<UserDTO> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(UserMapper.fromUser(user));
        }
        return collection;
    }

    public UserDTO getUser(String username) {
        return userRepository.findById(username)
                .map(UserMapper::fromUser)
                .orElseThrow(() -> new UsernameNotFoundException(username)); // TODO: why it always return 500?
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public String createUser(UserDTO userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);

        String encryptedPwd = PasswordGenerator.generateEncryptedPassword(userDto.getPassword());

        userDto.setPassword(encryptedPwd);

        User newUser = userRepository.save(UserMapper.toUser(userDto));
        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDTO newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();

        String encryptedPwd = PasswordGenerator.generateEncryptedPassword(newUser.getPassword());
        user.setPassword(encryptedPwd);
        userRepository.save(user);
    }

    public Set<Role> getRoles(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDTO userDto = UserMapper.fromUser(user);
        return userDto.getRoles();
    }

    public void addRole(String username, String role) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addRole(new Role(username, role));
        userRepository.save(user);
    }

    public void removeRole(String username, String role) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Role roleToRemove = user.getRoles().stream().filter((a) -> a.getRole().equalsIgnoreCase(role)).findAny().get();
        user.removeRole(roleToRemove);
        userRepository.save(user);
    }


}