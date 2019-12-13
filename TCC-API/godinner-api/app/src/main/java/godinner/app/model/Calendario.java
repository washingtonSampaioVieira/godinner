package godinner.app.model;

import org.json.JSONObject;

import com.google.gson.JsonObject;

public class Calendario {

	private String janeiro;

	public String jsonMeses(String meses) {
		String[] array = meses.split(",");

		JSONObject json = new JSONObject();
		json.put("janeiro", array[0]);
		json.put("fevereiro", array[1]);
		json.put("marco", array[2]);
		json.put("abril", array[3]);
		json.put("maio", array[4]);
		json.put("junho", array[5]);
		json.put("julho", array[6]);
		json.put("agosto", array[7]);
		json.put("setembro", array[8]);
		json.put("outubro", array[9]);
		json.put("novembro", array[10]);
		json.put("dezembro", array[11]);

		

		return json.toString();
	}

}
