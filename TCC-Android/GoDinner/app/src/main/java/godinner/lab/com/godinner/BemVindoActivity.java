package godinner.lab.com.godinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BemVindoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnEntrar;
    private ImageButton btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);
        Glide.with(this).load(R.drawable.logo).into((ImageView) findViewById(R.id.logo));

        btnEntrar = findViewById(R.id.btn_entrar);
        btnVoltar = findViewById(R.id.btn_voltar);

        btnEntrar.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent abrirMainActivity = new Intent(BemVindoActivity.this, MainActivity.class);
        startActivity(abrirMainActivity);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
