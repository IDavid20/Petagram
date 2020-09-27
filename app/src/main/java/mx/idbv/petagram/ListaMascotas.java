package mx.idbv.petagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaMascotas extends AppCompatActivity {

    protected ArrayList<Mascota> datosMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);
        Toolbar customActionBar = findViewById(R.id.custom_action_bar);
        setSupportActionBar(customActionBar);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(true);

        initData();
        RecyclerView recyclerViewListaMascotas = findViewById(R.id.recycler_view_layout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewListaMascotas.setLayoutManager(layoutManager);
        CustomAdapter adapter = new CustomAdapter(datosMascotas);
        recyclerViewListaMascotas.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu_app_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, getIntent());
                return true;
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favoritos:
                // User chose the "Favorite" action, mark the current item
                // take us to the activity favoritos
                Intent intent = new Intent(this, MascotasFavoritas.class);

                intent.putParcelableArrayListExtra("ArrayList", datosMascotas);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void initData() {
        datosMascotas = new ArrayList<>();
        datosMascotas.add(new Mascota(R.drawable.bird, getString(R.string.nombre1)));
        datosMascotas.add(new Mascota(R.drawable.dog1, getString(R.string.nombre2)));
        datosMascotas.add(new Mascota(R.drawable.dog2, getString(R.string.nombre3)));
        datosMascotas.add(new Mascota(R.drawable.dog3, getString(R.string.nombre4)));
        datosMascotas.add(new Mascota(R.drawable.dog4, getString(R.string.nombre5)));
        datosMascotas.add(new Mascota(R.drawable.cat2, getString(R.string.nombre6)));
        datosMascotas.add(new Mascota(R.drawable.turtle, getString(R.string.nombre7)));
        datosMascotas.add(new Mascota(R.drawable.hamster, getString(R.string.nombre8)));
        datosMascotas.add(new Mascota(R.drawable.rabbit, getString(R.string.nombre9)));
        datosMascotas.add(new Mascota(R.drawable.cat1, getString(R.string.nombre10)));
        datosMascotas.add(new Mascota(R.drawable.fish, getString(R.string.nombre11)));
    }
}