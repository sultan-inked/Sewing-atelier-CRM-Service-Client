package sa.crm.service.controller.payload;

public record JwtRequest(
		String username,
		String password
		) {
}
