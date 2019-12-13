package godinner.lab.com.godinner;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import godinner.lab.com.godinner.adapter.MessageListAdapter;
import godinner.lab.com.godinner.dao.ConsumidorDAO;
import godinner.lab.com.godinner.dao.TokenUsuarioDAO;
import godinner.lab.com.godinner.model.Consumidor;
import godinner.lab.com.godinner.model.Mensagem;
import godinner.lab.com.godinner.tasks.SuporteUsuario;
import godinner.lab.com.godinner.utils.Data;
import godinner.lab.com.godinner.utils.OnSingleClickListener;
import io.socket.client.IO;
import io.socket.client.Socket;

public class SuporteActivity extends AppCompatActivity {

    private RecyclerView mMessageList;
    private EditText txtMessageChat;
    private Consumidor consumidor;
    private Socket socket;
    private MessageListAdapter messageListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suporte);

        ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Suporte");

        mMessageList = findViewById(R.id.message_list);
        txtMessageChat = findViewById(R.id.message_chat);
        Button btnEnviar = findViewById(R.id.btn_enviar);

        List<Mensagem> mMensagens = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mMessageList.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mMessageList.getContext(), 0);
        mMessageList.addItemDecoration(dividerItemDecoration);

        messageListAdapter = new MessageListAdapter(this, mMensagens);
        mMessageList.setAdapter(messageListAdapter);

        ConsumidorDAO mConsumidorDAO = new ConsumidorDAO(this);
        consumidor = mConsumidorDAO.consultarConsumidor();
        TokenUsuarioDAO mTokenUsuarioDAO = new TokenUsuarioDAO(this);
        String token = mTokenUsuarioDAO.consultarToken();

        try {
            socket = IO.socket(getResources().getString(R.string.ipServidorSocket));
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        SuporteUsuario mSuporteUsuario = new SuporteUsuario(token, consumidor.getIdServidor(), this, resposta -> {
            try {
                if (resposta != null) {
                    JSONObject mSala = new JSONObject(resposta);

                    if (mSala.has("sala")) {
                        int sala = mSala.getInt("sala");
                        joinChat(sala);
                    }
                }
            } catch (JSONException ignored) {
            }
        });
        mSuporteUsuario.execute();

        btnEnviar.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (socket.connected() && !txtMessageChat.getText().toString().trim().isEmpty()) {
                    try {
                        JSONStringer jsonMessage = new JSONStringer();
                        jsonMessage.object();
                        jsonMessage.key("message").value(txtMessageChat.getText().toString().trim());
                        jsonMessage.key("remetente").value("C");
                        jsonMessage.endObject();
                        txtMessageChat.setText("");

                        socket.emit("message", jsonMessage);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                reset();
            }
        });
    }

    private void joinChat(int sala) throws JSONException {
        JSONStringer jsonJoinChat = new JSONStringer();
        jsonJoinChat.object();
        jsonJoinChat.key("room").value(sala);
        jsonJoinChat.key("idConsumidor").value(consumidor.getIdServidor());
        jsonJoinChat.key("username").value(consumidor.getNome());
        jsonJoinChat.key("remetente").value("C");
        jsonJoinChat.endObject();

        socket.emit("join", jsonJoinChat);

        socket.on("message", args -> runOnUiThread(() -> {
            try {
                JSONObject o = new JSONObject(args[0].toString());

                Mensagem m = new Mensagem();
                m.setUsername(o.getString("nome"));
                m.setMessage(o.getString("message"));
                m.setRemetente(o.getString("remetente"));
                m.setCreatedAt(Data.getHoraAtual());

                messageListAdapter.refreshData(m);
                mMessageList.smoothScrollToPosition(messageListAdapter.getItemCount());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }));

        socket.on("userjoinedthechat", args -> runOnUiThread(() -> {
            String dados = (String) args[0];
            Toast.makeText(SuporteActivity.this, dados, Toast.LENGTH_LONG).show();
        }));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeAsUp:
                Log.d("BBBBBBBBB", "BBBBBBBBB");
                break;
            case R.id.showHome:
                Log.d("AAAAAAAAAAA", "AAAAAAAAA");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.close();
    }
}
