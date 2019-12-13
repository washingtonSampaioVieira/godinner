package godinner.lab.com.godinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import godinner.lab.com.godinner.dao.TokenUsuarioDAO;
import godinner.lab.com.godinner.utils.OnSingleClickListener;

public class PerfilFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public PerfilFragment() {}

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        view.requestFocus();

        Button btnDeslogar = view.findViewById(R.id.btn_deslogar);
        Button btnChat = view.findViewById(R.id.btn_chat);

        btnChat.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Intent abrirSuporte = new Intent(getActivity(), SuporteActivity.class);
                startActivity(abrirSuporte);
                reset();
            }
        });
        
        btnDeslogar.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                TokenUsuarioDAO mTokenUsuarioDAO = new TokenUsuarioDAO(getContext());
                mTokenUsuarioDAO.limparToken();

                Intent abrirMain = new Intent(getActivity(), MainActivity.class);
                startActivity(abrirMain);
                getActivity().finish();
                reset();
            }
        });

        return view;
    }
}
