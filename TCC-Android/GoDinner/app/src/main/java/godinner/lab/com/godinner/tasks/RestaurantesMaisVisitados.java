package godinner.lab.com.godinner.tasks;

import android.annotation.SuppressLint;
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

import godinner.lab.com.godinner.HomeFragment;
import godinner.lab.com.godinner.MainActivity;
import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.model.RestauranteExibicao;

public class RestaurantesMaisVisitados extends AsyncTask {

    private Integer idConsumidor;
    private String token;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public RestaurantesMaisVisitados(Integer idConsumidor, String token, Context context) {
        this.idConsumidor = idConsumidor;
        this.token = token;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL(String.format("%s/restaurante/exibicao/%s", context.getResources().getString(R.string.ipServidor), idConsumidor));

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("token", token);
            conexao.setDoInput(true);

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
            ArrayList<RestauranteExibicao> restaurantes = new ArrayList<>();
            RestauranteExibicao restaurante;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject mObject = (JSONObject) jsonArray.get(i);
                restaurante = new RestauranteExibicao();

                restaurante.setId(mObject.getInt("id"));
                restaurante.setRazaoSocial(mObject.getString("razaoSocial"));
                restaurante.setTelefone(mObject.getString("telefone"));
                restaurante.setValorEntrega(mObject.getDouble("valorEntrega"));
                restaurante.setFoto(mObject.getString("foto"));
                restaurante.setDistancia(mObject.getString("distancia"));
                restaurante.setTempoEntrega(mObject.getString("tempoEntrega"));
                restaurante.setNota(mObject.getString("nota"));
                restaurantes.add(restaurante);
            }

            HomeFragment.restaurantesMaisVisitados = restaurantes;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}