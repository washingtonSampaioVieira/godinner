package godinner.lab.com.godinner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import godinner.lab.com.godinner.dao.PedidoDAO;
import godinner.lab.com.godinner.model.Produto;
import godinner.lab.com.godinner.model.ProdutoPedido;
import godinner.lab.com.godinner.model.RestauranteExibicao;
import godinner.lab.com.godinner.model.SacolaPedido;
import godinner.lab.com.godinner.utils.OnSingleClickListener;

public class DetalhesPedido extends AppCompatActivity {

    private Produto mProduto;
    private RestauranteExibicao mRestauranteExibicao;

    private Button btnValorTotal;
    private Button btnTotal;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_do_pedido);

        ImageView imageProduto = findViewById(R.id.image_produto_detalhes);
        TextView txtNomeProduto = findViewById(R.id.text_nome_produto);
        TextView txtDetalhesDoProduto = findViewById(R.id.text_descricao_produto);
        Button btnSomar = findViewById(R.id.btn_mais_um);
        Button btnSubtrair = findViewById(R.id.btn_menos_um);
        btnTotal = findViewById(R.id.btn_total_produtos);
        btnValorTotal = findViewById(R.id.valor_total);
        CollapsingToolbarLayout toolbar = findViewById(R.id.collapseToolbar);

        Intent intent = getIntent();
        mProduto = (Produto) intent.getSerializableExtra("produto_clicado");
        mRestauranteExibicao = (RestauranteExibicao) intent.getSerializableExtra("restaurante");

        String url = getResources().getString(R.string.ipServidorFotos) + "/" + (mProduto.getFotos().size() == 0 ? getResources().getString(R.string.fotoLanchePadrao) : mProduto.getFotos().get(0).getFoto());
        Picasso.get().load(url).resize(150, 200).into(imageProduto);

        PedidoDAO mPedidoDAO = new PedidoDAO(this);
        ProdutoPedido p = mPedidoDAO.consultarProdutoById(mProduto.getId());

        if (p.getQuantidade() != 0) {
            btnTotal.setText(String.format("%d", p.getQuantidade()));
        }

        txtNomeProduto.setText(mProduto.getNome());
        txtDetalhesDoProduto.setText(mProduto.getDescricao());

        if (mProduto.getDesconto() != 0) {
            Double desconto = mProduto.getPreco() * (mProduto.getDesconto() / 100);
            btnValorTotal.setText(String.format("R$ %s", mProduto.getPreco() - desconto));
        } else {
            btnValorTotal.setText(String.format("R$ %s", mProduto.getPreco().toString()));
        }

        toolbar.setTitle(mProduto.getNome());

        btnSomar.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                int total = Integer.parseInt(btnTotal.getText().toString());
                PedidoDAO mPedidoDAO = new PedidoDAO(DetalhesPedido.this);
                ProdutoPedido p = new ProdutoPedido();

                total++;
                btnTotal.setText(String.valueOf(total));
                Double preco = Double.parseDouble(btnValorTotal.getText().toString().replace("R$ ", ""));

                if (mPedidoDAO.sacolaIsNull()) {
                    SacolaPedido s = new SacolaPedido();

                    s.setIdRestaurante(mRestauranteExibicao.getId());
                    s.setNomeRestaurante(mRestauranteExibicao.getRazaoSocial());
                    s.setTempoEntrega(mRestauranteExibicao.getTempoEntrega());
                    s.setValorEntrega(mRestauranteExibicao.getValorEntrega());
                    s.setValorTotalPedido(mRestauranteExibicao.getValorEntrega() + preco * total);

                    mPedidoDAO.atualizarSacola(s);
                }

                p.setId(mProduto.getId());
                p.setNome(mProduto.getNome());
                p.setPreco(preco);
                p.setQuantidade(total);

                if (p.getQuantidade() == 1)
                    mPedidoDAO.salvarProduto(p, "novo");
                else
                    mPedidoDAO.salvarProduto(p, "editar");
                reset();
            }
        });

        btnSubtrair.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                int total = Integer.parseInt(btnTotal.getText().toString());
                PedidoDAO mPedidoDAO = new PedidoDAO(DetalhesPedido.this);
                ProdutoPedido p = new ProdutoPedido();

                if (total > 0) {
                    total--;
                    btnTotal.setText(String.valueOf(total));
                    Double preco = Double.parseDouble(btnValorTotal.getText().toString().replace("R$ ", ""));

                    p.setId(mProduto.getId());
                    p.setNome(mProduto.getNome());
                    p.setPreco(preco);
                    p.setQuantidade(total);

                    if (p.getQuantidade() > 0)
                        mPedidoDAO.salvarProduto(p, "editar");
                    else if (p.getQuantidade() == 0)
                        mPedidoDAO.salvarProduto(p, "excluir");
                    reset();
                }
            }
        });

        btnValorTotal.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                finish();
                reset();
            }
        });
    }
}
