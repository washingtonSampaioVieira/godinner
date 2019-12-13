package godinner.lab.com.godinner;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import godinner.lab.com.godinner.adapter.TabsAdapter;

public class TelaInicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        setUpViewPager();
    }

    public void setUpViewPager() {
        final ViewPager viewPager = findViewById(R.id.viewPager);
        final TabLayout tabLayout = findViewById(R.id.tabLayout);

        if (viewPager != null) {
            TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager());
            tabsAdapter.addFragment(new HomeFragment(), "Início");
            tabsAdapter.addFragment(new SacolaFragment(), "Sacolas");
            tabsAdapter.addFragment(new PerfilFragment(), "Perfil");
            viewPager.setAdapter(tabsAdapter);
        }

        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(getIcon(i));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public int getIcon(int position) {
        switch (position) {
            case 0:
                return R.drawable.ic_home;
            case 1:
                return R.drawable.ic_bag;
            case 2:
                return R.drawable.ic_perfil;
        }

        return 0;
    }
}
