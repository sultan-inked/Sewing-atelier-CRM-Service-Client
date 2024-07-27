package sa.crm.service.config;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sa.crm.service.util.JwtTokenUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private final JwtTokenUtils jwtTokenUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			jwt = authHeader.substring(7);
			try {
				username = jwtTokenUtils.getUsername(jwt);
			} catch (ExpiredJwtException exc) {
				log.debug("Время жизни токена вышло");
			} catch (SignatureException exc) {
				log.debug("Не корректная подпись");
			}
		}
		
		// Принцип складывания пользователей в контекст, что бы не перезаписывать существующие данные, вдруг в контексте есть записи, поэтому есть проверка:
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					username,
					null,
					jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
			);
			
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	
}
