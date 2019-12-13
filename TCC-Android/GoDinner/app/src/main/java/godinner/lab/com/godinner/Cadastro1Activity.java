package godinner.lab.com.godinner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

import godinner.lab.com.godinner.model.Cadastro;
import godinner.lab.com.godinner.tasks.ValidarDadosCadastro;
import godinner.lab.com.godinner.utils.ValidaCampos;

public class Cadastro1Activity extends AppCompatActivity {

    private TextView txtEmail;
    private TextView txtSenha;
    private TextInputLayout txtEmailLayout;
    private TextInputLayout txtSenhaLayout;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro1);
        Glide.with(this).load(R.drawable.logo).into((ImageView) findViewById(R.id.logo));

        txtEmail = findViewById(R.id.txt_email);
        txtSenha = findViewById(R.id.txt_senha);
        txtEmailLayout = findViewById(R.id.txt_email_layout);
        txtSenhaLayout = findViewById(R.id.txt_senha_layout);

        Button btnProximo = findViewById(R.id.btn_proximo);
        ImageButton btnVoltar = findViewById(R.id.btn_voltar);

        Intent intent = getIntent();
        Cadastro cadastroIntent = (Cadastro) intent.getSerializableExtra("cadastro");

        if (cadastroIntent != null) {
            txtEmail.setText(cadastroIntent.getEmail());
            txtSenha.setText(cadastroIntent.getSenha());
        }

        btnProximo.setOnClickListener(v -> {
            if (validarCampos()) {
                ValidarDadosCadastro mValidarDadosCadastro = new ValidarDadosCadastro("email", txtEmail.getText().toString(), getApplicationContext(), result -> {
                    if (!result) {
                        txtEmailLayout.setErrorEnabled(true);
                        txtEmailLayout.setError("E-mail já cadastrado.");
                    } else {
                        txtEmailLayout.setErrorEnabled(false);

                        Intent abrirCadastro2 = new Intent(getApplicationContext(), Cadastro2Activity.class);

                        Cadastro c = new Cadastro();
                        c.setEmail(txtEmail.getText().toString());
                        c.setSenha(txtSenha.getText().toString());
                        c.setFoto("");

                        abrirCadastro2.putExtra("cadastro", c);
                        abrirCadastro2.putExtra("type", "normal");

                        startActivity(abrirCadastro2);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });

                try {
                    mValidarDadosCadastro.execute().get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        btnVoltar.setOnClickListener(v -> {
            Intent openMainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(openMainActivity);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("fecharActivity"));
    }

    @Override
    public void onBackPressed() {
        Intent abrirMainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(abrirMainActivity);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.onBackPressed();
    }

    public boolean validarCampos() {
        boolean semErro = true;

        if (!ValidaCampos.isValidEmail(txtEmail.getText().toString())) {
            txtEmailLayout.setErrorEnabled(true);
            txtEmailLayout.setError("E-mail inválido.");
            semErro = false;
        } else {
            txtEmailLayout.setErrorEnabled(false);
        }

        if (txtSenha.getText().toString().trim().length() < 8) {
            txtSenhaLayout.setErrorEnabled(true);
            txtSenhaLayout.setError("A senha deve conter no minímo 8 caracteres.");
            semErro = false;
        } else {
            txtSenhaLayout.setErrorEnabled(false);
        }

        return semErro;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }
}
