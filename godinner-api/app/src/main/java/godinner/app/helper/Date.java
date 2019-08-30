package godinner.app.helper;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class Date {
	
	public String getData() {
		
		return String.valueOf(System.currentTimeMillis());
	}

}
