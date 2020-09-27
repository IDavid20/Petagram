package mx.idbv.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity {
    private ArrayList<Mascota> datosMascotas;
    private ArrayList<Mascota> mascotasFavoritas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);
        Toolbar customActionBar = findViewById(R.id.custom_action_bar);
        setSupportActionBar(customActionBar);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(true);

        // Get data from previous activity
        datosMascotas = getIntent().getParcelableArrayListExtra("ArrayList");

        initData();
        RecyclerView recyclerViewListaMascotas = findViewById(R.id.recycler_view_layout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewListaMascotas.setLayoutManager(layoutManager);
        CustomAdapter adapter = new CustomAdapter(mascotasFavoritas);
        recyclerViewListaMascotas.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this,getIntent());
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void initData() {
        // Create a new ArrayList to store the Sorted List
        mascotasFavoritas = new ArrayList<>();
        // Add an initial object to sort the list
        mascotasFavoritas.add(datosMascotas.get(0));
        boolean set;
        // Cycle through all the dataSet
        for (int firstList = 1; firstList < datosMascotas.size(); firstList++) {
            set = false;
            for (int secondList = 0; (secondList < mascotasFavoritas.size() && !set); secondList++) {
                // If the element i is bigger than every object on the new list add it to the top
                if (datosMascotas.get(firstList).getCalificacion() >= mascotasFavoritas.get(secondList).getCalificacion()) {
                    mascotasFavoritas.add(secondList, datosMascotas.get(firstList));
                    set = true;
                }
            }
            if (!set) mascotasFavoritas.add(datosMascotas.get(firstList));
        }
            mascotasFavoritas.subList(5, mascotasFavoritas.size()).clear();
    }
}