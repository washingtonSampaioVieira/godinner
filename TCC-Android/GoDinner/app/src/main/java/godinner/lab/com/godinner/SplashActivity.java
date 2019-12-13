package godinner.lab.com.godinner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import godinner.lab.com.godinner.dao.TokenUsuarioDAO;
import godinner.lab.com.godinner.model.Cidade;
import godinner.lab.com.godinner.model.Estado;
import godinner.lab.com.godinner.tasks.ValidarToken;
import godinner.lab.com.godinner.utils.NetworkManager;

public class SplashActivity extends Activity {

    public static ArrayList<Estado> estados;
    public static ArrayList<Cidade> cidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Glide.with(this).load(R.drawable.logo2).into((ImageView) findViewById(R.id.imageView));
    }

    @Override
    protected void onResume() {
        final TokenUsuarioDAO mTokenUsuarioDAO = new TokenUsuarioDAO(this);
        String mToken = mTokenUsuarioDAO.consultarToken();

        if (NetworkManager.isNetworkAvailable(getSystemService(Context.CONNECTIVITY_SERVICE))) {
            if (!mToken.equals("")) {

                ValidarToken validarToken = new ValidarToken(mToken, this, result -> {
                    if (result) {
                        mStartInicialActivity();
                    } else {
                        mStartMainActivity();
                    }
                });

                validarToken.execute();
            } else {
                mStartMainActivity();
            }
        } else {
            NetworkManager.getSnackBarNetwork(this, findViewById(R.id.splash)).show();
        }
        super.onResume();
    }

    private void mStartMainActivity() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout r = findViewById(R.id.splash);

        if (r != null) {
            r.clearAnimation();
            r.startAnimation(anim);
        }

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }, 2000);
    }

    private void mStartInicialActivity() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout r = findViewById(R.id.splash);

        if (r != null) {
            r.clearAnimation();
            r.startAnimation(anim);
        }

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent abrirTelaInicial = new Intent(SplashActivity.this, TelaInicialActivity.class);
            startActivity(abrirTelaInicial);
            SplashActivity.this.finish();
        }, 2000);
    }

//        final TokenUsuarioDAO mTokenUsuarioDAO = new TokenUsuarioDAO(SplashActivity.this);
//        final CidadeEstadoDAO mCidadeEstadoDAO = new CidadeEstadoDAO(SplashActivity.this);
//
//        if(!mCidadeEstadoDAO.EstadoAlreadyPopuled()) {
//            try {
//                BuscarEstados mEstados = new BuscarEstados();
//                mEstados.execute();
//                mEstados.get();
//                mCidadeEstadoDAO.addEstados(estados);
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        if(!mCidadeEstadoDAO.CidadeAlreadyPopuled()){
//            try {
//                BuscarCidades mCidades = new BuscarCidades();
//                mCidades.execute();
//                mCidades.get();
//                mCidadeEstadoDAO.addCidades(cidades);
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
}
