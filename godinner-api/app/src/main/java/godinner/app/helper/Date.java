package godinner.app.helper;

import org.springframework.stereotype.Component;

@Component
public class Date {
	
	public String getData() {
		return String.valueOf(System.currentTimeMillis());
	}
}
