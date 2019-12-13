package godinner.lab.com.godinner.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import godinner.lab.com.godinner.R;

public class SuporteUsuario extends AsyncTask<Void, Void, String> {

    private String token;
    private int idConsumidor;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private ResultRequest resultRequest;

    public SuporteUsuario(String token, int idConsumidor, Context context, @NonNull ResultRequest resultRequest) {
        this.token = token;
        this.idConsumidor = idConsumidor;
        this.context = context;
        this.resultRequest = resultRequest;
    }

    @Override
    protected String doInBackground(Void... voids) {
        JSONStringer jsonSuporte = new JSONStringer();

        try {
            jsonSuporte.object();
            jsonSuporte.key("idConsumidor").value(idConsumidor);
            jsonSuporte.key("token").value(token);
            jsonSuporte.endObject();

            URL url = new URL(String.format("%s/suporte-usuario", context.getResources().getString(R.string.ipServidorSocket)));

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);

            PrintStream outputStream = new PrintStream(conexao.getOutputStream());
            outputStream.print(jsonSuporte);

            conexao.connect();
            Scanner scanner = new Scanner(conexao.getInputStream());

            return scanner.nextLine();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
        resultRequest.onResult(str);
    }

    public interface ResultRequest {
        void onResult(String mObject);
    }
}
