package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.dtos.UserDTO;
import nl.novi.techiteasy.exceptions.BadRequestException;
import nl.novi.techiteasy.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {

        List<UserDTO> userDtos = userService.getUsers();

        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("username") String username) {

        UserDTO optionalUser = userService.getUser(username);

        return ResponseEntity.ok().body(optionalUser);

    }

    @PostMapping
    public ResponseEntity<UserDTO> createKlant(@RequestBody UserDTO dto) {;

        String newUsername = userService.createUser(dto);
        userService.addRole(newUsername, "ROLE_USER");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<UserDTO> updateKlant(@PathVariable("username") String username, @RequestBody UserDTO dto) {

        userService.updateUser(username, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteKlant(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{username}/roles")
    public ResponseEntity<Object> getUserRoles(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getRoles(username));
    }

    @PostMapping(value = "/{username}/roles")
    public ResponseEntity<Object> addUserRole(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        // Role has to be prefixed with "ROLE_" to be compliant with Spring Security
        try {
            String roleName = (String) fields.get("role");
            userService.addRole(username, roleName);
            return ResponseEntity.noContent().build();
        }
        catch (BadRequestException ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{username}/roles/{role}")
    public ResponseEntity<Object> deleteUserRole(@PathVariable("username") String username, @PathVariable("role") String role) {
        userService.removeRole(username, role);
        return ResponseEntity.noContent().build();
    }

}