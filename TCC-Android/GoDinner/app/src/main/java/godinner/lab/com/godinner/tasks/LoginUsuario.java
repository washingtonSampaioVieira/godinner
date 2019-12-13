package godinner.lab.com.godinner.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.model.Login;

public class LoginUsuario extends AsyncTask<Void, Void, String> {

    private Login login;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private LoginResult mLoginResult;

    public LoginUsuario(Login login, Context context, LoginResult mLoginResult) {
        this.login = login;
        this.context = context;
        this.mLoginResult = mLoginResult;
    }

    @Override
    protected String doInBackground(Void... voids) {
        JSONStringer jsonLogin = new JSONStringer();

        try {
            jsonLogin.object();
            jsonLogin.key("email").value(login.getEmail());
            jsonLogin.key("password").value(login.getSenha());
            jsonLogin.endObject();

            URL url = new URL(String.format("%s/login/consumidor", context.getResources().getString(R.string.ipServidor)));

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);

            PrintStream outputStream = new PrintStream(conexao.getOutputStream());
            outputStream.print(jsonLogin);

            conexao.connect();

            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();

            JSONObject mObject = new JSONObject(resposta);

            return mObject.has("token") ? mObject.getString("token") : "";
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if (mLoginResult != null) {
            mLoginResult.onResult(s);
        }

        super.onPostExecute(s);
    }

    public interface LoginResult {
        void onResult(String token);
    }
}
