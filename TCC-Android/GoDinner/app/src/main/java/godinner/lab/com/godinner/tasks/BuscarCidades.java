package godinner.lab.com.godinner.tasks;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.SplashActivity;
import godinner.lab.com.godinner.model.Cidade;

public class BuscarCidades extends AsyncTask {

    private Context context;

    public BuscarCidades(Context context) {
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL(String.format("%s/cidade", context.getResources().getString(R.string.ipServidor)));

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream inputStream = conexao.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha = "";
            StringBuilder dados = new StringBuilder();

            while (linha != null) {
                linha = bufferedReader.readLine();
                dados.append(linha);
            }

            JSONArray jsonArray = new JSONArray(dados.toString());
            ArrayList<Cidade> cidades = new ArrayList<>();
            Cidade cidade;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject mObject = (JSONObject) jsonArray.get(i);
                cidade = new Cidade();
                cidade.setIdCidade(mObject.getInt("id"));
                cidade.setIdEstado(mObject.getJSONObject("estado").getInt("id"));
                cidade.setCidade(mObject.getString("cidade"));

                cidades.add(cidade);
            }

            SplashActivity.cidades = cidades;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
