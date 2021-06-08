package sg.edu.rp.c347.id19007966.democooldrawer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] drawerItems;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    ArrayAdapter<String> aa;
    String currentTitle;
    ActionBar ab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerList = findViewById(R.id.leftDrawer);

        drawerItems = new String[] {"Bio", "Vaccination", "Anniversary"};
        ab = getSupportActionBar();

        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, drawerItems);

        drawerList.setAdapter(aa);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = null;
                if (i == 0) {
                    fragment = new BioFragment();
                }
                else if (i == 1) {
                    fragment = new VaccinationFragment();
                }
                else if (i == 2) {
                    fragment = new AnniversaryFragment();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.contentFrame, fragment);
                transaction.commit();

                drawerList.setItemChecked(i, true);
                currentTitle = drawerItems[i];
                ab.setTitle(currentTitle);
                drawerLayout.closeDrawer(drawerList);
            }
        });
    }
}