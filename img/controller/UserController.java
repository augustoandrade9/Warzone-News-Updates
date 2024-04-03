package br.puc.tis3.controller;

import br.puc.tis3.model.User;
import br.puc.tis3.model.dto.UserDTO;
import br.puc.tis3.model.response.Response;
import br.puc.tis3.service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user/")
@CrossOrigin(originPatterns = {"localhost:3000/", "http://localhost:3000/"})
public class UserController {

    /* Dependency Injection */

    private final UserService USER_SERVICE;

    /* Constructor */

    public UserController(UserService userService) {
        this.USER_SERVICE = userService;
    }

    /* Methods */

    @PostMapping
    public ResponseEntity<?> _registerUser(
            @RequestBody UserDTO dto
    ){

        if(dto == null)
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));

        Response response = USER_SERVICE._createUser(dto);

        return ResponseEntity.status(response.getStatus_code()).body(response);
    }

    @PutMapping
    public ResponseEntity<?> _changeUser(
            @RequestBody UserDTO dto
    ){
        if(dto == null)
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));

        Response response = USER_SERVICE._changeUser(dto);

        return ResponseEntity.status(response.getStatus_code()).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> _getUserById(
            @PathVariable UUID id
    ){
        if(id == null)
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));

        Optional<User> user = USER_SERVICE._getUserById(id);

        if(user.isEmpty())
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));

        return ResponseEntity.ok(user.get());
    }

    @GetMapping("login/{mail}/{password}")
    public ResponseEntity<?> _realizeLogin(
            @PathVariable String mail,
            @PathVariable String password
    ){
        if(mail == null || password == null)
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));

        Response response = USER_SERVICE._realizeLogin(mail, password);

        return ResponseEntity.status(response.getStatus_code()).body(response);
    }
}
