package sa.crm.service.controller.payload;

public record NewUserPayload(
		String username,
		String password,
		String confirmPassword,
		String email
		) {

}
