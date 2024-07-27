package sa.crm.service.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sa.crm.service.dto.RegistrationUserDto;
import sa.crm.service.entity.CRMUser;
import sa.crm.service.repository.CRMUserRepository;

@Service
public class CRMUserService implements UserDetailsService {
	
	private final CRMUserRepository userRepository;
	
	private final CRMRoleService roleService;
	
	private PasswordEncoder passwordEncoder;

	public CRMUserService(CRMUserRepository userRepository, CRMRoleService roleService,
			@Lazy PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	public Optional<CRMUser> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		CRMUser user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
					String.format("Пользователь с таким именем не найден", username)));
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
										.collect(Collectors.toList()));
	}
	
	public CRMUser createNewUser(RegistrationUserDto registrationUserDto) {
		
		CRMUser user = new CRMUser();
		
		user.setUsername(registrationUserDto.getUsername());
		user.setEmail(registrationUserDto.getEmail());
		user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
		
		return userRepository.save(user);
	}
	
}



















