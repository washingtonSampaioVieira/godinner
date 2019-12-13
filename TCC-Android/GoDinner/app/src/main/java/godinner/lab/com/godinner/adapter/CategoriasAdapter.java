package godinner.lab.com.godinner.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import godinner.lab.com.godinner.MainActivity;
import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.model.Categoria;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.CategoriasViewholder> {

    private List<Categoria> mCategorias;
    private Context context;
    private CategoriaOnClickListener mCategoriaOnClickListener;

    public CategoriasAdapter(List<Categoria> mCategorias, Context context, CategoriaOnClickListener mCategoriaOnClickListener) {
        this.mCategorias = mCategorias;
        this.context = context;
        this.mCategoriaOnClickListener = mCategoriaOnClickListener;
    }

    @NonNull
    @Override
    public CategoriasViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.lista_image, viewGroup, false);
        return new CategoriasViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriasViewholder categoriasViewholder, final int i) {
        Categoria c = mCategorias.get(i);

        categoriasViewholder.txtCategoria.setText(c.getNome());

        categoriasViewholder.itemView.setOnClickListener(v -> mCategoriaOnClickListener.onClickCategoria(v, i));

        Picasso.get().load(context.getResources().getString(R.string.ipServidorFotos) + c.getUrlImage()).resize(100, 100).into(categoriasViewholder.imgCategoria);
    }

    @Override
    public int getItemCount() {
        return mCategorias != null ? mCategorias.size() : 0;
    }

    public interface CategoriaOnClickListener {
        void onClickCategoria(View view, int index);
    }

    class CategoriasViewholder extends RecyclerView.ViewHolder {
        private ImageView imgCategoria;
        private TextView txtCategoria;

        private CategoriasViewholder(@NonNull View itemView) {
            super(itemView);

            imgCategoria = itemView.findViewById(R.id.image_item);
            txtCategoria = itemView.findViewById(R.id.title_item);
        }
    }
}
