package godinner.lab.com.godinner.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import godinner.lab.com.godinner.R;

public class ValidarDadosCadastro extends AsyncTask<Void, Void, Boolean> {

    private ValidarCampo validarCampo;
    private String url;

    public ValidarDadosCadastro(String tipo, String data, Context context, ValidarCampo validarCampo) {
        this.validarCampo = validarCampo;
        switch (tipo) {
            case "cpf":
                url = context.getResources().getString(R.string.ipServidor) + "/consumidor/valida/cpf/" + data;
                break;
            case "email":
                url = context.getResources().getString(R.string.ipServidor) + "/consumidor/valida/email/" + data;
                break;
            case "facebook":
                url = context.getResources().getString(R.string.ipServidor) + "/consumidor/verifica/email/" + data;
                break;
        }
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            URL url = new URL(this.url);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream inputStream = conexao.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String dados = bufferedReader.readLine();

            return Boolean.parseBoolean(dados);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean b) {
        if (this.validarCampo != null) {
            validarCampo.Request(b);
        }
    }

    public interface ValidarCampo {
        void Request(Boolean result);
    }
}
