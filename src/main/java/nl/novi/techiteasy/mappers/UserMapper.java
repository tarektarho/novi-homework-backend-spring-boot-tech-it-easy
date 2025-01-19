package nl.novi.techiteasy.mappers;

import nl.novi.techiteasy.dtos.UserDTO;
import nl.novi.techiteasy.models.User;

public class UserMapper {

    public static User toUser(UserDTO userDto) {
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }

    public static UserDTO fromUser(User user){

        UserDTO dto = new UserDTO();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.email = user.getEmail();
        dto.roles = user.getRoles();

        return dto;
    }

}
