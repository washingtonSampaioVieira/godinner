package godinner.app.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Date {
	
	public String getData() {
		return String.valueOf(System.currentTimeMillis());
	}
	
	public String formataDataString(String data) {
		
		String dia = data.substring(0, 10 );
		
		String[] parts = dia.split("-");
		
		String formatoData = parts[2] + "/"  + parts[1] + "/"  + parts[0];
		
		return formatoData;
	
	}
}
