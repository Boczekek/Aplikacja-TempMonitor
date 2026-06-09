package com.example.tempmonitor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvCurrentTemp;
    private RecyclerView recyclerView;
    private ApiService apiService;
    private Handler handler = new Handler();
    private Runnable refreshRunnable;
    private String currentIp = ""; // Przechowuje aktualnie używane IP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 1. Wczytaj i zastosuj motyw zanim narysujesz UI
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        int savedTheme = prefs.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(savedTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. Inicjalizacja widoków
        tvCurrentTemp = findViewById(R.id.tvCurrentTemp);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 3. Konfiguracja Retrofit na podstawie zapisanego IP
        setupRetrofit();

        // 4. Przygotowanie pętli odświeżania
        refreshRunnable = new Runnable() {
            @Override
            public void run() {
                pobierzDaneZSerwera();

                // Pobierz czas odświeżania z ustawień
                int interval = getSharedPreferences("Settings", MODE_PRIVATE).getInt("interval", 10);
                handler.postDelayed(this, interval * 1000);
            }
        };
    }

    private void setupRetrofit() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        currentIp = prefs.getString("server_ip", "10.0.2.2"); // Domyślnie IP emulatora

        String baseUrl = "http://" + currentIp + ":8080/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    private void pobierzDaneZSerwera() {
        apiService.getWszystkiePomiary().enqueue(new Callback<List<Dane>>() {
            @Override
            public void onResponse(@NonNull Call<List<Dane>> call, @NonNull Response<List<Dane>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Dane> daneList = response.body();
                    if (!daneList.isEmpty()) {
                        // Wyświetl najnowszą temperaturę
                        Dane ostatni = daneList.get(daneList.size() - 1);
                        tvCurrentTemp.setText(ostatni.getTemperatura() + "°C");

                        // Odwróć listę, aby najnowsze były na górze
                        Collections.reverse(daneList);
                        recyclerView.setAdapter(new DaneAdapter(daneList));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Dane>> call, @NonNull Throwable t) {
                // Snackbar z przyciskiem ponowienia
                Snackbar.make(findViewById(android.R.id.content), "Błąd połączenia z serwerem: " + currentIp, Snackbar.LENGTH_INDEFINITE)
                        .setAction("PONÓW", v -> pobierzDaneZSerwera())
                        .show();
            }
        });
    }

    // Obsługa menu (ikona koła zębatego)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Sprawdź, czy IP w ustawieniach zostało zmienione
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String savedIp = prefs.getString("server_ip", "10.0.2.2");

        if (!savedIp.equals(currentIp)) {
            setupRetrofit(); // Przebuduj Retrofit, jeśli IP się zmieniło
        }

        handler.post(refreshRunnable); // Uruchom odświeżanie
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(refreshRunnable); // Zatrzymaj odświeżanie
    }
}