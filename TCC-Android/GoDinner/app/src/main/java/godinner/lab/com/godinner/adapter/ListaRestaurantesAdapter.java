package godinner.lab.com.godinner.adapter;

import android.annotation.SuppressLint;
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

import java.util.List;

import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.model.RestauranteExibicao;

public class ListaRestaurantesAdapter extends RecyclerView.Adapter<ListaRestaurantesAdapter.RestauranteViewHolder> {

    private List<RestauranteExibicao> mRestaurantes;
    private Context context;
    private RestauranteOnClickListener mRestauranteOnClickListener;

    public ListaRestaurantesAdapter(List<RestauranteExibicao> mRestaurantes, Context context, RestauranteOnClickListener mRestauranteOnClickListener) {
        this.mRestaurantes = mRestaurantes;
        this.context = context;
        this.mRestauranteOnClickListener = mRestauranteOnClickListener;
    }

    @NonNull
    @Override
    public RestauranteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.lista_restaurantes, viewGroup, false);
        return new RestauranteViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull RestauranteViewHolder restauranteViewHolder, final int i) {
        RestauranteExibicao r = mRestaurantes.get(i);

        restauranteViewHolder.imgRestaurante.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_chef));
        restauranteViewHolder.nome.setText(r.getRazaoSocial());

        if (i < 3) {
            restauranteViewHolder.rank.setVisibility(View.VISIBLE);
            restauranteViewHolder.rank.setText(String.format("%dº mais visitado.", i + 1));
        } else {
            restauranteViewHolder.rank.setVisibility(View.INVISIBLE);
        }

        restauranteViewHolder.avaliacao.setText(r.getNota());
        restauranteViewHolder.distancia.setText(r.getDistancia());
        restauranteViewHolder.preco.setText(String.format("R$ %s", r.getNota()));
        restauranteViewHolder.tempo.setText(r.getTempoEntrega());
        restauranteViewHolder.imgRestaurante.setImageDrawable(ContextCompat.getDrawable(context, R.color.colorWhite));

        restauranteViewHolder.itemView.setOnClickListener(v -> mRestauranteOnClickListener.onClickRestaurante(v, i));

        Picasso.get().load(context.getResources().getString(R.string.ipServidorFotos) + r.getFoto()).resize(100, 100).into(restauranteViewHolder.imgRestaurante);
    }

    @Override
    public int getItemCount() {
        return mRestaurantes != null ? mRestaurantes.size() : 0;
    }

    public interface RestauranteOnClickListener {
        void onClickRestaurante(View view, int index);
    }

    class RestauranteViewHolder extends RecyclerView.ViewHolder {
        private TextView nome;
        private TextView rank;
        private TextView avaliacao;

        private TextView distancia;
        private TextView preco;
        private TextView tempo;
        private ImageView imgRestaurante;

        RestauranteViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nome_restaurante);
            rank = itemView.findViewById(R.id.rank_restaurante);
            avaliacao = itemView.findViewById(R.id.avaliacao_restaurante);
            distancia = itemView.findViewById(R.id.distancia);
            preco = itemView.findViewById(R.id.preco);
            tempo = itemView.findViewById(R.id.tempo);
            imgRestaurante = itemView.findViewById(R.id.image);
        }
    }
}
