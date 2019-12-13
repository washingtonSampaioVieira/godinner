package godinner.lab.com.godinner;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import godinner.lab.com.godinner.dao.ConsumidorDAO;
import godinner.lab.com.godinner.dao.TokenUsuarioDAO;
import godinner.lab.com.godinner.model.Cadastro;
import godinner.lab.com.godinner.model.Login;
import godinner.lab.com.godinner.tasks.BuscarConsumidor;
import godinner.lab.com.godinner.tasks.LoginUsuario;
import godinner.lab.com.godinner.tasks.ValidarDadosCadastro;
import godinner.lab.com.godinner.utils.LoadingDialog;
import godinner.lab.com.godinner.utils.OnSingleClickListener;
import godinner.lab.com.godinner.utils.ValidaCampos;

public class MainActivity extends AppCompatActivity {

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken != null) {
                loadUserProfile(currentAccessToken);
            }
        }
    };
    private CallbackManager callbackManager;
    private TextView txtEmail;
    private TextView txtSenha;
    private TextInputLayout txtEmailLayout;
    private TextInputLayout txtSenhaLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Glide.with(this).load(R.drawable.logo2).into((ImageView) findViewById(R.id.logo));

        MaterialButton btnLogar = findViewById(R.id.btn_logar);
        MaterialButton btnCadastrar = findViewById(R.id.btn_cadastrar);
        LoginButton loginButton = findViewById(R.id.login_button);

        txtEmail = findViewById(R.id.txt_email);
        txtSenha = findViewById(R.id.txt_password);
        txtEmailLayout = findViewById(R.id.txt_email_layout);
        txtSenhaLayout = findViewById(R.id.txt_password_layout);

        final TokenUsuarioDAO mTokenUsuarioDAO = new TokenUsuarioDAO(this);

        btnLogar.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (validarCampos()) {
                    final AlertDialog dialog = LoadingDialog.showLoadingDialog(getLayoutInflater(), MainActivity.this);
                    dialog.show();

                    Login login = new Login();
                    login.setEmail(txtEmail.getText().toString());
                    login.setSenha(txtSenha.getText().toString());

                    try {
                        LoginUsuario mLogin = new LoginUsuario(login, getApplicationContext(), token -> {
                            if (token.isEmpty()) {
                                dialog.dismiss();
                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Não foi desta vez.")
                                        .setMessage("Usuário ou senha incorretos.")
                                        .setPositiveButton("Fechar", null)
                                        .show();
                            } else {
                                mTokenUsuarioDAO.salvarToken(token);

                                try {
                                    BuscarConsumidor mBuscarConsumidor = new BuscarConsumidor(token, getApplicationContext(), consumidor -> {
                                        if (consumidor != null) {
                                            ConsumidorDAO mConsumidorDAO = new ConsumidorDAO(getApplicationContext());
                                            mConsumidorDAO.salvarConsumidorLogado(consumidor);
                                            dialog.dismiss();

                                            Intent abrirTelaInicial = new Intent(getApplicationContext(), TelaInicialActivity.class);
                                            startActivity(abrirTelaInicial);
                                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                            MainActivity.this.finish();
                                        }
                                    });
                                    mBuscarConsumidor.execute().get();

                                } catch (ExecutionException | InterruptedException ignored) {}
                            }
                        });
                        mLogin.execute().get();

                    } catch (ExecutionException | InterruptedException ignored) {} finally {
                        dialog.dismiss();
                        reset();
                    }
                }
            }
        });

        btnCadastrar.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Intent abrirCadastro = new Intent(getApplicationContext(), Cadastro1Activity.class);
                startActivity(abrirCadastro);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                reset();
            }
        });

        callbackManager = CallbackManager.Factory.create();
        loginButton.setPermissions(Arrays.asList("email", "public_profile"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadUserProfile(AccessToken token) {
        // Enviando uma solicitação ao Facebook para pegar os dados do usuário através da API Graph
        GraphRequest request = GraphRequest.newMeRequest(token, (object, response) -> {
            try {
                final String first_name = object.getString("first_name");
                final String last_name = object.getString("last_name");
                final String email = object.getString("email");
                final String id = object.getString("id");
                final String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                final TokenUsuarioDAO mTokenUsuarioDAO = new TokenUsuarioDAO(this);

                ValidarDadosCadastro mValidarDadosCadastro = new ValidarDadosCadastro("facebook", email, getApplicationContext(), result -> {
                    final AlertDialog dialog = LoadingDialog.showLoadingDialog(getLayoutInflater(), MainActivity.this);
                    dialog.show();

                    if (result) {
                        Login login = new Login();
                        login.setEmail(email);
                        login.setSenha(id + "_consumidor");

                        try {
                            LoginUsuario mLogin = new LoginUsuario(login, this, token1 -> {
                                if (token1.isEmpty()) {
                                    dialog.dismiss();
                                    new AlertDialog.Builder(MainActivity.this)
                                            .setTitle("Não foi desta vez.")
                                            .setMessage("Usuário ou senha incorretos.")
                                            .setPositiveButton("Fechar", null)
                                            .show();
                                } else {
                                    mTokenUsuarioDAO.salvarToken(token1);

                                    try {
                                        BuscarConsumidor mBuscarConsumidor = new BuscarConsumidor(token1, getApplicationContext(), consumidor -> {
                                            if (consumidor != null) {
                                                ConsumidorDAO mConsumidorDAO = new ConsumidorDAO(getApplicationContext());
                                                mConsumidorDAO.salvarConsumidorLogado(consumidor);
                                                dialog.dismiss();

                                                Intent abrirTelaInicial = new Intent(getApplicationContext(), TelaInicialActivity.class);
                                                startActivity(abrirTelaInicial);
                                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                                MainActivity.this.finish();
                                            }
                                        });
                                        mBuscarConsumidor.execute().get();

                                    } catch (ExecutionException | InterruptedException ignored) {
                                    }
                                }
                            });

                            mLogin.execute().get();
                        } catch (ExecutionException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Cadastro cadastro = new Cadastro();
                        cadastro.setNome(String.format("%s %s", first_name, last_name));
                        cadastro.setEmail(email);
                        cadastro.setFoto(image_url);
                        cadastro.setSenha(id + "_consumidor");
                        dialog.dismiss();

                        Intent abrirCadastro = new Intent(MainActivity.this, Cadastro2Activity.class);
                        abrirCadastro.putExtra("cadastro", cadastro);
                        abrirCadastro.putExtra("type", "facebook");

                        startActivity(abrirCadastro);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });
                mValidarDadosCadastro.execute().get();
            } catch (InterruptedException | ExecutionException | JSONException e) {
                e.printStackTrace();
            }
        });
        Bundle parameters = new Bundle();

        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void checkLoginStatus() {
        if (AccessToken.getCurrentAccessToken() != null) {
            AccessToken.setCurrentAccessToken(null);
        }
    }

    private boolean validarCampos() {
        boolean semErros = true;

        if (!ValidaCampos.isValidEmail(txtEmail.getText().toString())) {
            txtEmailLayout.setErrorEnabled(true);
            txtEmailLayout.setError("Insira um e-mail válido.");
            semErros = false;
        }

        if (txtSenha.getText().toString().length() < 6) {
            txtSenhaLayout.setErrorEnabled(true);
            txtSenhaLayout.setError("Insira uma senha válida.");
            semErros = false;
        }

        return semErros;
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLoginStatus();
        findViewById(R.id.scrollView).requestFocus();
    }
}
