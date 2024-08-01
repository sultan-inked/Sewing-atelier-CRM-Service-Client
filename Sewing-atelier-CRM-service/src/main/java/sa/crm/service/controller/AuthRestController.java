package sa.crm.service.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sa.crm.service.controller.payload.JwtRequest;
import sa.crm.service.controller.payload.NewUserPayload;
import sa.crm.service.controller.payload.UserPayload;
import sa.crm.service.service.AuthService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthRestController {
	
	private final AuthService authService;
	
	@PostMapping("/auth")
	public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
		return authService.createAuthToken(authRequest);
	}
	
	@PostMapping("/registration")
	public ResponseEntity<?> createNewUser(@RequestBody NewUserPayload newUserPayload) {
//		return authService.createNewUser(newUserPayload);
		ResponseEntity<Optional<UserPayload>> ok = ResponseEntity.ok(authService.createNewUser(newUserPayload));
		return ok;
	}
}
