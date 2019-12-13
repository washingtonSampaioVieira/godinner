package godinner.lab.com.godinner.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import godinner.lab.com.godinner.Cadastro3Activity;
import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.model.Endereco;

public class ConsultarCep extends AsyncTask {

    private String cep;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public ConsultarCep(String cep, Context context) {
        this.cep = cep;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL(String.format("%s/endereco/cep/%s", context.getResources().getString(R.string.ipServidor), cep));

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
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

            JSONObject mObject = new JSONObject(dados.toString());

            if (!mObject.getString("cep").equals("null")) {
                Endereco endereco = new Endereco();

                endereco.setLogradouro(mObject.getString("logradouro"));
                endereco.setBairro(mObject.getString("bairro"));
                endereco.setCep(mObject.getString("cep"));

                endereco.setIdCidade(mObject.getJSONObject("cidade").getInt("id"));
                endereco.setCidadeNome(mObject.getJSONObject("cidade").getString("cidade"));
                endereco.setIdEstado(mObject.getJSONObject("cidade").getJSONObject("estado").getInt("id"));
                endereco.setEstadoNome(mObject.getJSONObject("cidade").getJSONObject("estado").getString("estado"));

                Cadastro3Activity.endereco = endereco;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
