package godinner.lab.com.godinner.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import godinner.lab.com.godinner.MainActivity;
import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.model.Produto;

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.ProdutoViewHolder> {

    private List<Produto> mProdutos;
    private Context context;
    private ProdutoOnClickListener mProdutoOnClickListener;

    public ProdutosAdapter(List<Produto> mProdutos, Context context, ProdutoOnClickListener mProdutoOnClickListener) {
        this.mProdutos = mProdutos;
        this.context = context;
        this.mProdutoOnClickListener = mProdutoOnClickListener;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.lista_produtos, viewGroup, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder produtoViewHolder, final int i) {
        Produto p = mProdutos.get(i);

        produtoViewHolder.imageProduto.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_food));
        produtoViewHolder.nomeProdto.setText(p.getNome());
        produtoViewHolder.descProduto.setText(p.getDescricao());
        DecimalFormat f = new DecimalFormat("0.00");
        produtoViewHolder.precoProduto.setText(String.format("R$ %s", f.format(p.getPreco())));

        produtoViewHolder.itemView.setOnClickListener(v -> mProdutoOnClickListener.onClickProduto(v, i));

        String url = context.getResources().getString(R.string.ipServidorFotos) + (p.getFotos().size() == 0 ? context.getResources().getString(R.string.fotoLanchePadrao) : p.getFotos().get(0).getFoto());
        Picasso.get().load(url).resize(100, 100).into(produtoViewHolder.imageProduto);
    }

    @Override
    public int getItemCount() {
        return mProdutos != null ? mProdutos.size() : 0;
    }

    public interface ProdutoOnClickListener {
        void onClickProduto(View view, int index);
    }

    class ProdutoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageProduto;
        private TextView nomeProdto;
        private TextView descProduto;
        private TextView precoProduto;

        ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);

            imageProduto = itemView.findViewById(R.id.image_produto);
            nomeProdto = itemView.findViewById(R.id.nome_produto);
            descProduto = itemView.findViewById(R.id.desc_produto);
            precoProduto = itemView.findViewById(R.id.preco_produto);
        }
    }
}