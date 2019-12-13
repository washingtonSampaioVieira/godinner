package godinner.lab.com.godinner.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.model.Consumidor;
import godinner.lab.com.godinner.model.Endereco;

public class BuscarConsumidor extends AsyncTask<Void, Void, Consumidor> {

    private String token;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private Result mResult;

    public BuscarConsumidor(String token, Context context, @NonNull Result result) {
        this.token = token;
        this.context = context;
        this.mResult = result;
    }

    @Override
    protected Consumidor doInBackground(Void... voids) {
        Consumidor mConsumidor = new Consumidor();
        try {
            URL url = new URL(String.format("%s/consumidor/este", context.getResources().getString(R.string.ipServidor)));

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("token", token);
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setDoInput(true);
            conexao.connect();

            InputStream inputStream = conexao.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha = "";
            StringBuilder dados = new StringBuilder();

            while (linha != null) {
                dados.append(linha);
                linha = bufferedReader.readLine();
            }

            JSONObject json = new JSONObject(dados.toString());
            mConsumidor.setIdServidor(json.getInt("id"));
            mConsumidor.setNome(json.getString("nome"));
            mConsumidor.setEmail(json.getString("email"));
            mConsumidor.setCpf(json.getString("cpf"));
            mConsumidor.setTelefone(json.getString("telefone"));
            mConsumidor.setFotoPerfil(json.getString("fotoPerfil"));
            Endereco endereco = new Endereco();
            endereco.setIdEndereco(json.getJSONObject("endereco").getInt("id"));
            endereco.setCep(json.getJSONObject("endereco").getString("cep"));
            endereco.setNumero(json.getJSONObject("endereco").getString("numero"));
            endereco.setLogradouro(json.getJSONObject("endereco").getString("logradouro"));
            endereco.setBairro(json.getJSONObject("endereco").getString("bairro"));
            endereco.setComplemento(json.getJSONObject("endereco").getString("complemento"));
            endereco.setReferencia(json.getJSONObject("endereco").getString("referencia"));
            endereco.setIdCidade(json.getJSONObject("endereco").getJSONObject("cidade").getInt("id"));
            endereco.setIdEstado(json.getJSONObject("endereco").getJSONObject("cidade").getJSONObject("estado").getInt("id"));
            endereco.setCidadeNome(json.getJSONObject("endereco").getJSONObject("cidade").getString("cidade"));
            endereco.setCidadeNome(json.getJSONObject("endereco").getJSONObject("cidade").getJSONObject("estado").getString("estado"));
            mConsumidor.setEndereco(endereco);

            return mConsumidor;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Consumidor consumidor) {
        super.onPostExecute(consumidor);
        if (mResult != null) {
            mResult.onResult(consumidor);
        }
    }

    public interface Result {
        void onResult(Consumidor consumidor);
    }
}

