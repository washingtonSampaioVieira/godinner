package godinner.lab.com.godinner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import godinner.lab.com.godinner.adapter.CategoriasAdapter;
import godinner.lab.com.godinner.adapter.ListaRestaurantesAdapter;
import godinner.lab.com.godinner.adapter.RestaurantesProximosAdapter;
import godinner.lab.com.godinner.dao.ConsumidorDAO;
import godinner.lab.com.godinner.dao.TokenUsuarioDAO;
import godinner.lab.com.godinner.model.Categoria;
import godinner.lab.com.godinner.model.Consumidor;
import godinner.lab.com.godinner.model.RestauranteExibicao;
import godinner.lab.com.godinner.tasks.BuscarCategorias;
import godinner.lab.com.godinner.tasks.BuscarRestaurantesProximos;
import godinner.lab.com.godinner.tasks.RestaurantesMaisVisitados;
import godinner.lab.com.godinner.utils.NetworkManager;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    public static ArrayList<Categoria> categorias;
    public static ArrayList<RestauranteExibicao> restaurantesMaisVisitados;
    public static ArrayList<RestauranteExibicao> restaurantesProximos;
    private RecyclerView mRestaurantesProximos;
    private RecyclerView mCategorias;
    private RecyclerView mListaRestaurantes;
    private TextView txtEnderecoEntrega;
    private EditText txtBuscar;
    private String mParam1;

    private Context context;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        view.requestFocus();
        context = view.getContext();

        mRestaurantesProximos = view.findViewById(R.id.restaurantes_proximos);
        mCategorias = view.findViewById(R.id.categorias);
        mListaRestaurantes = view.findViewById(R.id.lista_restaurantes);
        txtEnderecoEntrega = view.findViewById(R.id.txt_endereco_entrega);
        txtBuscar = view.findViewById(R.id.txt_buscar);

        LinearLayoutManager horizontalLayoutManagerRestaurante = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManagerCategoria = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager verticalLayoutManagerRestaurante = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        mRestaurantesProximos.setLayoutManager(horizontalLayoutManagerRestaurante);
        mCategorias.setLayoutManager(horizontalLayoutManagerCategoria);
        mListaRestaurantes.setLayoutManager(verticalLayoutManagerRestaurante);

        if (!NetworkManager.isNetworkAvailable(context.getSystemService(Context.CONNECTIVITY_SERVICE))) {
            NetworkManager.getSnackBarNetwork(context, view.findViewById(R.id.fragment_home)).show();
        } else {
            try {
                ConsumidorDAO mConsumidorDAO = new ConsumidorDAO(context);
                Consumidor c = mConsumidorDAO.consultarConsumidor();

                txtEnderecoEntrega.setText(String.format("%s, %s", c.getEndereco().getLogradouro(), c.getEndereco().getNumero()));

                TokenUsuarioDAO mTokenUsuarioDAO = new TokenUsuarioDAO(context);
                String token = mTokenUsuarioDAO.consultarToken();

                BuscarRestaurantesProximos mRestaurantesProximos = new BuscarRestaurantesProximos(c.getIdServidor(), token, context);
                mRestaurantesProximos.execute().get();

                BuscarCategorias mBuscarCategorias = new BuscarCategorias(token, context);
                mBuscarCategorias.execute().get();

                RestaurantesMaisVisitados mRestaurantesMaisVisitados = new RestaurantesMaisVisitados(c.getIdServidor(), token, context);
                mRestaurantesMaisVisitados.execute().get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapterRestaurantesProximos();
        mAdapterCategorias();
        mAdapterListaDeRestaurantes();
    }

    private void mAdapterRestaurantesProximos() {
        RestaurantesProximosAdapter mRestaurantesProximosAdapter = new RestaurantesProximosAdapter(restaurantesProximos, context, new RestaurantesProximosAdapter.RestauranteOnClickListener() {
            @Override
            public void onClickRestaurante(View view, int index) {
                Intent abrirTelaRestaurante = new Intent(getActivity(), TelaRestaurante.class);
                RestauranteExibicao restauranteExibicao = restaurantesProximos.get(index);

                abrirTelaRestaurante.putExtra("restaurante", restauranteExibicao);
                startActivity(abrirTelaRestaurante);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        mRestaurantesProximos.setAdapter(mRestaurantesProximosAdapter);
    }

    private void mAdapterCategorias() {
        CategoriasAdapter categoriasAdapter = new CategoriasAdapter(categorias, context, new CategoriasAdapter.CategoriaOnClickListener() {
            @Override
            public void onClickCategoria(View view, int index) {
                //Toast.makeText(getActivity(), "Clicou id " + index, Toast.LENGTH_SHORT).show();
            }
        });
        mCategorias.setAdapter(categoriasAdapter);
    }

    private void mAdapterListaDeRestaurantes() {
        ListaRestaurantesAdapter mRestaurantesAdapter = new ListaRestaurantesAdapter(restaurantesMaisVisitados, context, new ListaRestaurantesAdapter.RestauranteOnClickListener() {
            @Override
            public void onClickRestaurante(View view, int index) {
                Intent abrirTelaRestaurante = new Intent(getActivity(), TelaRestaurante.class);
                RestauranteExibicao restauranteExibicao = restaurantesMaisVisitados.get(index);
                abrirTelaRestaurante.putExtra("restaurante", restauranteExibicao);
                startActivity(abrirTelaRestaurante);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        mListaRestaurantes.setAdapter(mRestaurantesAdapter);
    }
}
