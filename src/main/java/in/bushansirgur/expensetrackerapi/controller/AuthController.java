package in.bushansirgur.expensetrackerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bushansirgur.expensetrackerapi.service.UserService;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	@GetMapping("/loginUser")
	public ResponseEntity<String> login() {
		return new ResponseEntity<String>("User is logged in ", HttpStatus.OK);
	}

	@GetMapping("/register")
	public ResponseEntity<String> save() {
		return new ResponseEntity<String>("register form", HttpStatus.CREATED);
	}

}
