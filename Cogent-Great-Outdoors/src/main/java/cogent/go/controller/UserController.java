package cogent.go.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cogent.go.entities.User;
import cogent.go.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/go/user")
public class UserController {
	
	@Autowired
    private UserService service;
	
	@PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        service.saveUser(user);
        return new ResponseEntity<>(user.getFirstName() + " was added.", HttpStatus.OK);
    }
}
