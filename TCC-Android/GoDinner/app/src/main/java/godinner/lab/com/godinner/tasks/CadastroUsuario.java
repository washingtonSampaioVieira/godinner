package godinner.lab.com.godinner.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.model.Cadastro;
import godinner.lab.com.godinner.model.Contato;
import godinner.lab.com.godinner.model.Endereco;

public class CadastroUsuario extends AsyncTask<Void, Void, Boolean> {

    private Cadastro cadastro;
    private Contato contato;
    private Endereco endereco;
    private String tipoCadastro;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private Result mResult;

    public CadastroUsuario(Cadastro cadastro, Contato contato, Endereco endereco, String tipoCadastro, Context context, Result mResult) {
        this.cadastro = cadastro;
        this.contato = contato;
        this.endereco = endereco;
        this.tipoCadastro = tipoCadastro;
        this.context = context;
        this.mResult = mResult;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        JSONStringer jsonCadastro = new JSONStringer();

        try {
            jsonCadastro.object();
            jsonCadastro.key("nome").value(contato.getNome());
            jsonCadastro.key("email").value(cadastro.getEmail());
            jsonCadastro.key("senha").value(cadastro.getSenha());
            jsonCadastro.key("cpf").value(contato.getCpf());
            jsonCadastro.key("telefone").value(contato.getTelefone());
            jsonCadastro.key("fotoPerfil").value(cadastro.getFoto());

            if (tipoCadastro.equals("f")) {
                jsonCadastro.key("redeSocial").value("1");
            }

            jsonCadastro.key("endereco").object()
                    .key("cep").value(endereco.getCep())
                    .key("numero").value(endereco.getNumero())
                    .key("logradouro").value(endereco.getLogradouro())
                    .key("bairro").value(endereco.getBairro())
                    .key("complemento").value(endereco.getComplemento())
                    .key("referencia").value(endereco.getReferencia())
                    .key("cidade").object()
                    .key("id").value(endereco.getIdCidade())
                    .endObject()
                    .endObject();
            jsonCadastro.endObject();

            Log.d("CADASTRO ---", jsonCadastro.toString());

            URL url = new URL(String.format("%s/consumidor", context.getResources().getString(R.string.ipServidor)));

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);

            PrintStream outputStream = new PrintStream(conexao.getOutputStream());
            outputStream.print(jsonCadastro);

            conexao.connect();

            Scanner scanner = new Scanner(conexao.getInputStream());
            scanner.nextLine();
            return true;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        mResult.onResult(aBoolean);
    }

    public interface Result {
        void onResult(boolean bool);
    }
}
