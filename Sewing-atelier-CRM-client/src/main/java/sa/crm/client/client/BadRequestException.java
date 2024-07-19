package sa.crm.client.client;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BadRequestException extends RuntimeException {
	
	private final List<String> errors;
	
	public BadRequestException(List<String> errors) {
		this.errors = errors;
	}
	
	public BadRequestException(String message, List<String> errors) {
		super(message);
		this.errors = errors;
	}
	
	public BadRequestException(String message, Throwable cause, List<String> errors) {
		super(message, cause);
		this.errors = errors;
	}
	
	public BadRequestException(Throwable cause, List<String> errors) {
		super(cause);
		this.errors = errors;
	}
	
	public BadRequestException(String message, Throwable cause, boolean enableSupression,
								boolean writableStackTrace, List<String> errors) {
		super(message, cause, enableSupression, writableStackTrace);
		this.errors = errors;
	}
}
