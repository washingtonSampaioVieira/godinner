package godinner.lab.com.godinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import godinner.lab.com.godinner.adapter.ProdutosAdapter;
import godinner.lab.com.godinner.adapter.PromocoesAdapter;
import godinner.lab.com.godinner.dao.TokenUsuarioDAO;
import godinner.lab.com.godinner.model.Produto;
import godinner.lab.com.godinner.model.RestauranteExibicao;
import godinner.lab.com.godinner.tasks.BuscarProdutosRestaurante;
import godinner.lab.com.godinner.tasks.BuscarPromocoesRestaurante;

public class TelaRestaurante extends AppCompatActivity implements View.OnClickListener {

    public static ArrayList<Produto> mProdutosPromocao;
    public static ArrayList<Produto> mProdutosTodos;
    private RecyclerView mPromocoes;
    private RecyclerView mTodosProdutos;
    private RestauranteExibicao mRestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_restaurante);

        TextView txtRestaurante = findViewById(R.id.nome_restaurante);
        TextView txtPreco = findViewById(R.id.preco);
        TextView txtEntrega = findViewById(R.id.tempo_entrega);
        TextView txtAvaliacao = findViewById(R.id.avaliacao_restaurante);
        ImageButton btnVoltar = findViewById(R.id.btn_voltar);
        mPromocoes = findViewById(R.id.promocoes);
        mTodosProdutos = findViewById(R.id.todos);

        LinearLayoutManager linearLayoutManagerHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mPromocoes.setLayoutManager(linearLayoutManagerHorizontal);

        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mTodosProdutos.setLayoutManager(linearLayoutManagerVertical);

        Intent mIntent = getIntent();
        mRestaurante = (RestauranteExibicao) mIntent.getSerializableExtra("restaurante");

        txtRestaurante.setText(mRestaurante.getRazaoSocial());
        txtPreco.setText(String.format("R$ %s", mRestaurante.getValorEntrega()));
        txtEntrega.setText(mRestaurante.getTempoEntrega());
        txtAvaliacao.setText(mRestaurante.getNota());
        btnVoltar.setOnClickListener(this);

        try {
            TokenUsuarioDAO mTokenUsuarioDAO = new TokenUsuarioDAO(TelaRestaurante.this);
            String token = mTokenUsuarioDAO.consultarToken();

            BuscarPromocoesRestaurante mPromocoesRestaurante = new BuscarPromocoesRestaurante(mRestaurante.getId(), token, this);
            mPromocoesRestaurante.execute().get();

            BuscarProdutosRestaurante mProdutosRestaurante = new BuscarProdutosRestaurante(mRestaurante.getId(), token, this);
            mProdutosRestaurante.execute().get();

            mAdapterPromocoes();
            mAdapterProdutos();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.onBackPressed();
    }

    public void mAdapterPromocoes() {
        PromocoesAdapter mAdapter = new PromocoesAdapter(mProdutosPromocao, this, new PromocoesAdapter.PromocaoOnClickListener() {
            @Override
            public void onClickPromocao(View view, int index) {
                Intent intentDetalhesProduto = new Intent(getApplicationContext(), DetalhesPedido.class);
                intentDetalhesProduto.putExtra("produto_clicado", mProdutosPromocao.get(index));
                intentDetalhesProduto.putExtra("restaurante", mRestaurante);
                startActivity(intentDetalhesProduto);
            }
        });
        mPromocoes.setAdapter(mAdapter);
    }

    public void mAdapterProdutos() {
        ProdutosAdapter mAdapter = new ProdutosAdapter(mProdutosTodos, this, new ProdutosAdapter.ProdutoOnClickListener() {
            @Override
            public void onClickProduto(View view, int index) {
                Intent intentDetalhesProduto = new Intent(getApplicationContext(), DetalhesPedido.class);
                intentDetalhesProduto.putExtra("produto_clicado", mProdutosTodos.get(index));
                intentDetalhesProduto.putExtra("restaurante", mRestaurante);
                startActivity(intentDetalhesProduto);
            }
        });
        mTodosProdutos.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_voltar:
                finish();
                break;
        }
    }
}
