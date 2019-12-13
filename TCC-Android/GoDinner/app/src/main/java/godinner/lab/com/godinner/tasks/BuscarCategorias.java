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
import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.model.Categoria;

public class BuscarCategorias extends AsyncTask {

    private String token;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public BuscarCategorias(String token, Context context) {
        this.token = token;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] o) {
        try {
            URL url = new URL(String.format("%s/categoria", context.getResources().getString(R.string.ipServidor)));

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("token", token);
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
            ArrayList<Categoria> categorias = new ArrayList<>();
            Categoria categoria;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject mObject = (JSONObject) jsonArray.get(i);
                categoria = new Categoria();
                categoria.setUrlImage(mObject.getString("foto"));
                categoria.setNome(mObject.getString("nome"));
                categorias.add(categoria);
            }

            HomeFragment.categorias = categorias;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
