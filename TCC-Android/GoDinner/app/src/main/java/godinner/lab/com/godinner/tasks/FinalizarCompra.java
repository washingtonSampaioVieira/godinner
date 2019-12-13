package godinner.lab.com.godinner.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.model.ProdutoPedido;
import godinner.lab.com.godinner.model.SacolaPedido;

public class FinalizarCompra extends AsyncTask {

    private SacolaPedido mSacolaPedido;
    private List<ProdutoPedido> mListPedidos;
    private String mToken;
    private String descricao;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public FinalizarCompra(SacolaPedido mSacolaPedido, List<ProdutoPedido> mListPedidos, String mToken, String descricao, Context context) {
        this.mSacolaPedido = mSacolaPedido;
        this.mListPedidos = mListPedidos;
        this.mToken = mToken;
        this.descricao = descricao;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        JSONStringer jsonPedidos = new JSONStringer();
        try {

            jsonPedidos.object();
            jsonPedidos.key("restaurante").object()
                    .key("id").value(mSacolaPedido.getIdRestaurante())
                    .endObject();
            jsonPedidos.key("valorEntrega").value(mSacolaPedido.getValorEntrega());
            jsonPedidos.key("valorTotal").value(mSacolaPedido.getValorEntrega() + mSacolaPedido.getValorTotalPedido());


            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jsonPedidos.key("dataDoPedido").value(f.format(new Date()));
            jsonPedidos.key("comissaoPaga").value(1);

            JSONArray jsonProdutos = new JSONArray();
            for (ProdutoPedido p : mListPedidos) {
                JSONStringer mOStringer = new JSONStringer();
                mOStringer.object()
                        .key("produto").object()
                        .key("id").value(p.getId())
                        .endObject()
                        .key("quantidade").value(p.getQuantidade())
                        .endObject();

                JSONObject object = new JSONObject(String.valueOf(mOStringer));

                jsonProdutos.put(object);
            }
            jsonPedidos.key("produtos").value(jsonProdutos);
            jsonPedidos.key("descricao").value(descricao);
            jsonPedidos.endObject();

            URL url = new URL(String.format("%s/pedidos", context.getResources().getString(R.string.ipServidor)));
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("token", mToken);
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);

            PrintStream outputStream = new PrintStream(conexao.getOutputStream());
            outputStream.print(jsonPedidos);

            conexao.connect();

            new Scanner(conexao.getInputStream());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
