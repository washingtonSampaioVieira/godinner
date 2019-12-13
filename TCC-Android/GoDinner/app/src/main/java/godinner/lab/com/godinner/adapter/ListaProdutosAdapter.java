package godinner.lab.com.godinner.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.model.ProdutoPedido;

public class ListaProdutosAdapter extends BaseAdapter {

    private List<ProdutoPedido> pedidos;
    private Context context;
    private OnMenuClick onMenuClick;

    public ListaProdutosAdapter(List<ProdutoPedido> pedidos, Context context, OnMenuClick onMenuClick) {
        this.pedidos = pedidos;
        this.context = context;
        this.onMenuClick = onMenuClick;
    }

    @Override
    public int getCount() {
        return this.pedidos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.pedidos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ProdutoPedido pedido = this.pedidos.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.lista_pedidos, null);

        final TextView txtNomePedido = view.findViewById(R.id.nome_pedido);
        final TextView txtPrecoPedido = view.findViewById(R.id.preco_pedido);
        final ImageView mOpcoesPedido = view.findViewById(R.id.opcoes_pedido);

        txtNomePedido.setText(String.format("%dx %s", pedido.getQuantidade(), pedido.getNome()));
        txtPrecoPedido.setText(String.format("R$ %s", pedido.getPreco() * pedido.getQuantidade()));

        mOpcoesPedido.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ViewHolder")
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, mOpcoesPedido);
                popup.inflate(R.menu.menu_pedidos);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        onMenuClick.onMenuItemClick(item, position);
                        return false;
                    }
                });
                popup.show();
            }
        });

        return view;
    }

    public interface OnMenuClick {
        void onMenuItemClick(MenuItem item, int position);
    }
}
