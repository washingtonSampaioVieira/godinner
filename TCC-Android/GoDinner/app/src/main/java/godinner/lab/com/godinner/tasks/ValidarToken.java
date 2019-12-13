package godinner.lab.com.godinner.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import godinner.lab.com.godinner.R;

public class ValidarToken extends AsyncTask<Void, Void, Boolean> {

    private String token;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private ResultRequest mListener;

    public ValidarToken(String token, Context context, @NonNull ResultRequest mListener) {
        this.token = token;
        this.context = context;
        this.mListener = mListener;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
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

            if (json.has("status") && json.has("error")) {
                int status = json.getInt("status");
                String error = json.getString("error");

                return status != 401 || !error.equals("Unauthorized");
            } else return json.length() != 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (mListener != null)
            mListener.Request(result);
    }

    public interface ResultRequest {
        void Request(boolean result);
    }
}
