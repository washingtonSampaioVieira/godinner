package godinner.lab.com.godinner;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import godinner.lab.com.godinner.adapter.ListaProdutosAdapter;
import godinner.lab.com.godinner.dao.ConsumidorDAO;
import godinner.lab.com.godinner.dao.PedidoDAO;
import godinner.lab.com.godinner.dao.TokenUsuarioDAO;
import godinner.lab.com.godinner.model.Consumidor;
import godinner.lab.com.godinner.model.ProdutoPedido;
import godinner.lab.com.godinner.model.SacolaPedido;
import godinner.lab.com.godinner.tasks.FinalizarCompra;
import godinner.lab.com.godinner.utils.OnSingleClickListener;

public class SacolaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private TextView txtEntregarEm;
    private TextView txtValorEntrega;
    private TextView txtTempoEntrega;
    private TextView txtNomeRestaurante;
    private ListView mListaPedidos;
    private TextView txtValorPedido;
    private TextView txtValorEntrega2;
    private TextView txtTotalGeral;
    private Context context;
    private String mParam1;
    private int idConsumidor;

    public SacolaFragment() {
    }

    public static SacolaFragment newInstance(String param1, String param2) {
        SacolaFragment fragment = new SacolaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sacola, container, false);
        context = getContext();

        txtEntregarEm = view.findViewById(R.id.txt_endereco_entrega);
        txtValorEntrega = view.findViewById(R.id.txt_valor_entrega);
        txtTempoEntrega = view.findViewById(R.id.txt_tempo_entrega);
        txtNomeRestaurante = view.findViewById(R.id.txt_nome_restaurante);

        mListaPedidos = view.findViewById(R.id.lista_pedidos);

        txtValorPedido = view.findViewById(R.id.txt_valor_pedido);
        txtValorEntrega2 = view.findViewById(R.id.txt_valor_entrega2);
        txtTotalGeral = view.findViewById(R.id.txt_total_geral);

        MaterialButton btnEsvaziarSacola = view.findViewById(R.id.btn_esvaziar_sacola);
        MaterialButton btnFinalizarCompra = view.findViewById(R.id.btn_finalizar_compra);

        btnEsvaziarSacola.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                final PedidoDAO mPedidoDAO = new PedidoDAO(context);

                mPedidoDAO.esvaziarSacola();
                mPedidoDAO.close();
                reset();
                onResume();
            }
        });

        btnFinalizarCompra.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                final PedidoDAO mPedidoDAO = new PedidoDAO(context);

                if (txtNomeRestaurante.getText().equals("Nenhum Pedido")) {
                    Toast.makeText(context, "Sacola Vazia", Toast.LENGTH_SHORT).show();
                    reset();
                } else {
                    final AlertDialog.Builder mBuilder = new AlertDialog.Builder(SacolaFragment.this.getContext());
                    @SuppressLint("InflateParams") final View mView = getLayoutInflater().inflate(R.layout.finalizar_compra, null);

                    final LinearLayout layout = mView.findViewById(R.id.linear);

                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setCornerRadius(32);
                    layout.setBackground(gradientDrawable);

                    final EditText txtComentario = mView.findViewById(R.id.txt_comentario);
                    final MaterialButton btnCancelar = mView.findViewById(R.id.btn_cancelar);
                    final MaterialButton btnFinalizar = mView.findViewById(R.id.btn_finalizar);

                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    btnFinalizar.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            SacolaPedido s = mPedidoDAO.consultarSacola();
                            List<ProdutoPedido> l = mPedidoDAO.consultarProdutos();
                            TokenUsuarioDAO mTokenUsuarioDAO = new TokenUsuarioDAO(context);
                            String token = mTokenUsuarioDAO.consultarToken();
                            String desc = txtComentario.getText().toString();

                            FinalizarCompra mFinalizarCompra = new FinalizarCompra(s, l, token, desc, context);
                            try {
                                mFinalizarCompra.execute().get();
                                Toast.makeText(context, "Pedido em Andamento", Toast.LENGTH_SHORT).show();
                                mPedidoDAO.esvaziarSacola();
                                onResume();
                            } catch (ExecutionException | InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                dialog.dismiss();
                            }
                        }
                    });

                    btnCancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    reset();
                }
            }
        });

        return view;
    }

    public void mAtualizarSacola() {
        final PedidoDAO mPedidoDAO = new PedidoDAO(context);
        ConsumidorDAO mConsumidorDAO = new ConsumidorDAO(context);
        Consumidor c = mConsumidorDAO.consultarConsumidor();

        this.idConsumidor = c.getIdConsumidor();

        SacolaPedido mSacolaPedido = mPedidoDAO.consultarSacola();
        final List<ProdutoPedido> listaProdutos = mPedidoDAO.getProdutos();

        Double precoPedido = 0.0;
        Double valorEntrega = 0.0;
        String tempoEntrega = "0 min";
        String nomeRestaurante = "Nenhum Pedido";

        if (mSacolaPedido.getValorEntrega() != 0.0)
            valorEntrega = mSacolaPedido.getValorEntrega();

        if (!mSacolaPedido.getTempoEntrega().isEmpty())
            tempoEntrega = mSacolaPedido.getTempoEntrega();

        if (!mSacolaPedido.getNomeRestaurante().isEmpty())
            nomeRestaurante = mSacolaPedido.getNomeRestaurante();

        for (ProdutoPedido p : listaProdutos) {
            precoPedido += p.getPreco() * p.getQuantidade();
        }

        txtEntregarEm.setText(String.format("%s, %s", c.getEndereco().getLogradouro(), c.getEndereco().getNumero()));
        txtValorPedido.setText(String.format("R$ %s", precoPedido.toString().replace(".", ",")));
        txtValorEntrega.setText(String.format("Valor da Entrega: R$ %s", valorEntrega.toString().replace(".", ",")));
        txtTempoEntrega.setText(String.format("Tempo: %s", tempoEntrega));
        txtNomeRestaurante.setText(String.format("%s", nomeRestaurante));
        txtValorEntrega2.setText(String.format("R$ %s", valorEntrega.toString().replace(".", ",")));
        txtTotalGeral.setText(String.format("R$ %s", String.valueOf(precoPedido + valorEntrega).replace(".", ",")));

        ListaProdutosAdapter mAdapter = new ListaProdutosAdapter(listaProdutos, context, new ListaProdutosAdapter.OnMenuClick() {
            @Override
            public void onMenuItemClick(MenuItem item, int position) {
                ProdutoPedido pedido = listaProdutos.get(position);

                switch (item.getItemId()) {
                    case R.id.menu_remover_pedido:
                        mPedidoDAO.excluirProduto(pedido.getId());
                        break;
                }
                onResume();
            }
        });
        mListaPedidos.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mAtualizarSacola();
    }
}
