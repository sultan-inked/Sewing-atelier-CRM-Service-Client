package sa.crm.service.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import sa.crm.service.controller.payload.JwtRequest;
import sa.crm.service.controller.payload.JwtResponse;
import sa.crm.service.controller.payload.NewUserPayload;
import sa.crm.service.controller.payload.UserPayload;
import sa.crm.service.entity.CRMUser;
import sa.crm.service.exceptions.AppError;
import sa.crm.service.util.JwtTokenUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final CRMUserService userService;
	
	private final JwtTokenUtils jwtTokenUtils;
	
	private final AuthenticationManager authenticationManager;
	
	
	public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
		} catch (BadCredentialsException exc) {
			return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"),
										HttpStatus.UNAUTHORIZED);
		}
		
		UserDetails userDetails = userService.loadUserByUsername(authRequest.username());
		String token = jwtTokenUtils.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	public Optional<UserPayload> createNewUser(NewUserPayload newUserPayload) {

//		if(!newUserPayload.password().equals(newUserPayload.password())) {
//			return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"),
//										HttpStatus.BAD_REQUEST);
//		}
//		if(userService.findByUsername(newUserPayload.username()).isPresent()) {
//			return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"),
//										HttpStatus.BAD_REQUEST);
//		}
		CRMUser user = userService.createNewUser(newUserPayload);
//
//		return ResponseEntity.ok(new UserPayload(user.getId(), user.getUsername(), user.getEmail()));

		return Optional.of(new UserPayload(user.getId(), user.getUsername(), user.getEmail()));
	}
}









































































