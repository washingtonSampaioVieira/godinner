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
import java.util.List;

import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.TelaRestaurante;
import godinner.lab.com.godinner.model.FotoProduto;
import godinner.lab.com.godinner.model.Produto;

public class BuscarProdutosRestaurante extends AsyncTask {

    private int idRestaurante;
    private String token;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public BuscarProdutosRestaurante(int idRestaurante, String token, Context context) {
        this.idRestaurante = idRestaurante;
        this.token = token;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL(String.format("%s/produto/restaurante/%s", context.getResources().getString(R.string.ipServidor), idRestaurante));

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
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
            ArrayList<Produto> produtos = new ArrayList<>();
            Produto produto;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject mObject = (JSONObject) jsonArray.get(i);
                produto = new Produto();
                produto.setId(mObject.getInt("id"));
                produto.setNome(mObject.getString("nome"));
                produto.setPreco(mObject.getDouble("preco"));
                produto.setDescricao(mObject.getString("descricao"));
                produto.setDesconto(mObject.getDouble("desconto"));
                produto.setVendidos(mObject.getInt("vendidos"));

                JSONArray fotos = mObject.getJSONArray("foto");
                JSONObject f = fotos.get(0) == null ? null : (JSONObject) fotos.get(0);
                FotoProduto foto = new FotoProduto();
                assert f != null;
                foto.setId(f.getInt("id"));
                foto.setFoto(f.getString("foto"));
                foto.setLegenda(f.getString("legenda"));

                List<FotoProduto> f1 = new ArrayList<>();
                f1.add(foto);
                produto.setFotos(f1);
                produtos.add(produto);
            }

            TelaRestaurante.mProdutosTodos = produtos;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
