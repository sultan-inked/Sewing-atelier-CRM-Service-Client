package sa.crm.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CRMUserDto {
	
	private Long id;
	
	private String username;
	
	private String email;
}
