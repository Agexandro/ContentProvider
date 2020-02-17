package net.ivanvega.miclientecpcontactosb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static Context context;
    FrameLayout frameLayout;
    BottomNavigationView navigationView;

    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottom_nav);
        frameLayout = findViewById(R.id.framelayout);
        navigationView.setOnNavigationItemSelectedListener(naviCustomListView);
        fragmentTransaction(new InsertFragment());

    }

    private BottomNavigationView.OnNavigationItemSelectedListener naviCustomListView = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.insertar:
                    fragmentTransaction(new InsertFragment());
                    break;

                case R.id.actualizar:
                    fragmentTransaction(new UpdateFragment());
                    break;

                case R.id.eliminar:
                    fragmentTransaction(new DeleteFragment());
                    break;
            }
            return true;
        }
    };

    //Paso el contexto a los fragments para que funcione la insercion, modificacion y eliminacion
   public static Context getContext(){
       return context;
   }

   public void fragmentTransaction(Fragment fragment){
       fragmentManager = getSupportFragmentManager();
       transaction = fragmentManager.beginTransaction();
       transaction.replace(R.id.framelayout,fragment);
       transaction.commit();
   }
}
