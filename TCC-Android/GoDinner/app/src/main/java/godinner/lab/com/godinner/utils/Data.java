package godinner.lab.com.godinner.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {

    public static String getHoraAtual() {
        Date data = new Date();
        DateFormat dateFormat = new SimpleDateFormat("EEE kk:mm");
        String dataFormatada = dateFormat.format(data);

        return StringUtils.capitalize(dataFormatada.toUpperCase());
    }
}
