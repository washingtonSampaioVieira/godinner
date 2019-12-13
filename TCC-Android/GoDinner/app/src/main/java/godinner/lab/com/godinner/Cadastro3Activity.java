package godinner.lab.com.godinner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.santalu.maskedittext.MaskEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import godinner.lab.com.godinner.model.Cadastro;
import godinner.lab.com.godinner.model.Cidade;
import godinner.lab.com.godinner.model.Contato;
import godinner.lab.com.godinner.model.Endereco;
import godinner.lab.com.godinner.model.Estado;
import godinner.lab.com.godinner.tasks.CadastroUsuario;
import godinner.lab.com.godinner.tasks.ConsultarCep;
import godinner.lab.com.godinner.utils.ValidaCampos;

public class Cadastro3Activity extends AppCompatActivity {

    public static Endereco endereco;
    private MaskEditText txtCep;
    private TextView txtNumero;
    private TextView txtReferencia;
    private TextView txtLogradouro;
    private TextView txtComplemento;
    private TextView txtBairro;
    private Spinner spinnerEstado;
    private Spinner spinnerCidade;
    private TextInputLayout txtCepLayout;
    private TextInputLayout txtNumeroLayout;
    private TextInputLayout txtLogradouroLayout;
    private TextInputLayout txtBairroLayout;
    private TextView txtErrorEstado;
    private TextView txtErrorCidade;
    private Cadastro cadastroIntent;
    private Contato contatoIntent;
    private String tipoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro3);
        Glide.with(this).load(R.drawable.logo).into((ImageView) findViewById(R.id.logo));

        txtCep = findViewById(R.id.txt_cep);
        txtNumero = findViewById(R.id.txt_numero);
        txtReferencia = findViewById(R.id.txt_referencia);
        txtLogradouro = findViewById(R.id.txt_logradouro);
        txtComplemento = findViewById(R.id.txt_complemento);
        txtBairro = findViewById(R.id.txt_bairro);
        spinnerEstado = findViewById(R.id.spinner_estado);
        spinnerCidade = findViewById(R.id.spinner_cidade);

        txtCepLayout = findViewById(R.id.txt_cep_layout);
        txtNumeroLayout = findViewById(R.id.txt_numero_layout);
        TextInputLayout txtReferenciaLayout = findViewById(R.id.txt_referencia_layout);
        TextInputLayout txtComplementoLayout = findViewById(R.id.txt_complemento_layout);
        txtLogradouroLayout = findViewById(R.id.txt_logradouro_layout);
        txtBairroLayout = findViewById(R.id.txt_bairro_layout);
        txtErrorEstado = findViewById(R.id.txt_error_estado);
        txtErrorCidade = findViewById(R.id.txt_error_cidade);

        Button btnFinalizar = findViewById(R.id.btn_finalizar);
        ImageButton btnVoltar = findViewById(R.id.btn_voltar);

        Intent intent = getIntent();
        cadastroIntent = (Cadastro) intent.getSerializableExtra("cadastro");
        contatoIntent = (Contato) intent.getSerializableExtra("contato");
        tipoCadastro = intent.getStringExtra("type");

        txtCep.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (Objects.requireNonNull(txtCep.getText()).length() == 9) {
                    try {
                        ConsultarCep consultarCep = new ConsultarCep(txtCep.getRawText(), this);
                        consultarCep.execute().get();

                        if (endereco != null) {
                            txtLogradouro.setText(endereco.getLogradouro());
                            txtBairro.setText(endereco.getBairro());

                            List<Estado> estados = new ArrayList<>();

                            Estado e1 = new Estado();
                            e1.setEstado("Escolha um Estado");
                            e1.setIdEstado(0);
                            estados.add(e1);

                            if (endereco.getIdEstado() != null) {
                                Estado e2 = new Estado();
                                e2.setEstado(endereco.getEstadoNome());
                                e2.setIdEstado(endereco.getIdEstado());
                                estados.add(e2);
                            }

                            ArrayAdapter<Estado> mAdapter = new ArrayAdapter<>(Cadastro3Activity.this, android.R.layout.simple_list_item_1, estados);

                            List<Cidade> cidades = new ArrayList<>();
                            Cidade c1 = new Cidade();
                            c1.setCidade("Escolha uma Cidade");
                            c1.setIdCidade(0);
                            cidades.add(c1);

                            if (endereco.getIdCidade() != null) {
                                Cidade c2 = new Cidade();
                                c2.setCidade(endereco.getCidadeNome());
                                c2.setIdCidade(endereco.getIdCidade());
                                cidades.add(c2);
                            }

                            ArrayAdapter<Cidade> mAdapter2 = new ArrayAdapter<>(Cadastro3Activity.this, android.R.layout.simple_list_item_1, cidades);

                            spinnerEstado.setAdapter(mAdapter);
                            spinnerEstado.setSelection(1);
                            spinnerCidade.setAdapter(mAdapter2);
                            spinnerCidade.setSelection(1);
                        } else {
                            Toast.makeText(Cadastro3Activity.this, "CEP não encontrado.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btnFinalizar.setOnClickListener(v -> {
            if (validarCampos()) {
                Endereco e = new Endereco();
                e.setCep(Objects.requireNonNull(txtCep.getText()).toString());
                e.setNumero(txtNumero.getText().toString());
                e.setReferencia(txtReferencia.getText().toString());
                e.setLogradouro(txtLogradouro.getText().toString());
                e.setComplemento(txtComplemento.getText().toString());
                e.setBairro(txtBairro.getText().toString());
                Cidade cidade = (Cidade) spinnerCidade.getSelectedItem();
                Estado estado = (Estado) spinnerEstado.getSelectedItem();
                e.setIdCidade(cidade.getIdCidade());
                e.setIdEstado(estado.getIdEstado());

                try {
                    if (tipoCadastro.equals("normal")) {
                        CadastroUsuario cadastroUsuario = new CadastroUsuario(cadastroIntent, contatoIntent, e, "n", this, bool -> {
                            if (bool) {
                                Intent abrirBemVindo = new Intent(Cadastro3Activity.this, BemVindoActivity.class);
                                startActivity(abrirBemVindo);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            } else {
                                Toast.makeText(Cadastro3Activity.this, "Erro no Cadastro.", Toast.LENGTH_LONG).show();
                            }
                        });
                        cadastroUsuario.execute().get();
                    } else if (tipoCadastro.equals("facebook")) {
                        CadastroUsuario cadastroUsuario = new CadastroUsuario(cadastroIntent, contatoIntent, e, "f", this, bool -> {
                            if (bool) {
                                Intent abrirBemVindo = new Intent(Cadastro3Activity.this, BemVindoActivity.class);
                                startActivity(abrirBemVindo);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            } else {
                                Toast.makeText(Cadastro3Activity.this, "Erro no Cadastro.", Toast.LENGTH_LONG).show();
                            }
                        });
                        cadastroUsuario.execute().get();
                    }

                    LocalBroadcastManager.getInstance(Cadastro3Activity.this).sendBroadcast(new Intent("fecharActivity"));
                    Cadastro3Activity.this.finish();
                } catch (ExecutionException | InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnVoltar.setOnClickListener(v -> {
            Intent abrirCadastro2 = new Intent(getApplicationContext(), Cadastro2Activity.class);
            abrirCadastro2.putExtra("cadastro", cadastroIntent);
            abrirCadastro2.putExtra("contato", contatoIntent);
            abrirCadastro2.putExtra("type", tipoCadastro);
            startActivity(abrirCadastro2);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    @Override
    public void onBackPressed() {
        Intent abrirCadastro2 = new Intent(getApplicationContext(), Cadastro2Activity.class);
        abrirCadastro2.putExtra("cadastro", cadastroIntent);
        abrirCadastro2.putExtra("contato", contatoIntent);
        abrirCadastro2.putExtra("type", tipoCadastro);
        startActivity(abrirCadastro2);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.onBackPressed();
    }

    @SuppressLint("SetTextI18n")
    public boolean validarCampos() {
        boolean semErro = true;

        if (!ValidaCampos.isValidCep(Objects.requireNonNull(txtCep.getText()).toString())) {
            txtCepLayout.setErrorEnabled(true);
            txtCepLayout.setError("O cep é inválido.");
            semErro = false;
        }

        if (txtNumero.getText().toString().trim().isEmpty()) {
            txtNumeroLayout.setErrorEnabled(true);
            txtNumeroLayout.setError("O número é obrigatório.");
            semErro = false;
        }

        if (txtLogradouro.getText().toString().trim().isEmpty()) {
            txtLogradouroLayout.setErrorEnabled(true);
            txtLogradouroLayout.setError("O logradouro é obrigatório.");
            semErro = false;
        }

        if (txtBairro.getText().toString().trim().isEmpty()) {
            txtBairroLayout.setErrorEnabled(true);
            txtBairroLayout.setError("O bairro é obrigatório.");
            semErro = false;
        }

        if (ValidaCampos.isValidEstado((Estado) spinnerEstado.getSelectedItem())) {
            txtErrorEstado.setVisibility(View.VISIBLE);
            txtErrorEstado.setText("Escolha um estado.");
            semErro = false;
        }

        if (ValidaCampos.isValidCidade((Cidade) spinnerCidade.getSelectedItem())) {
            txtErrorCidade.setVisibility(View.VISIBLE);
            txtErrorCidade.setText("Escolha uma cidade.");
            semErro = false;
        }

        return semErro;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        CidadeEstadoDAO mCidadeEstadoDAO = new CidadeEstadoDAO(Cadastro3Activity.this);
//
//        Estado e = new Estado();
//        e.setEstado("Escolha um Estado");
//        e.setIdEstado(0);
//
//        List estados = new ArrayList();
//        estados.add(e);
//        estados.addAll(mCidadeEstadoDAO.getEstados());
//
//        ArrayAdapter mAdapter2 = new ArrayAdapter(Cadastro3Activity.this, android.R.layout.simple_list_item_1, estados);
//        spinnerEstado.setAdapter(mAdapter2);
//        spinnerEstado.setSelection(0);
    }
}
