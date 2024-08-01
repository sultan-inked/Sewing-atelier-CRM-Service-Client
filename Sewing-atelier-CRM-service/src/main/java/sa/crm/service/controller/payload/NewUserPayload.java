package sa.crm.service.controller.payload;

import java.util.Collection;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sa.crm.service.entity.CRMRole;

public record NewUserPayload(
		@NotNull(message = "{crm.user.create.errors.username_is_null}")
		@Size(min = 3, max = 30, message = "{crm.user.create.errors.username_size")
		String username,
		String password,
		String confirmPassword,
		@Email(message = "{crm.user.create.errors.email")

		String email,
		Collection<CRMRole> roles
		) {

}
