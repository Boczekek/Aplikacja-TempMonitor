package com.example.tempmonitor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private EditText etInterval;
    private EditText etServerIp; // NOWA ZMIENNA
    private RadioGroup radioGroupTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Ustawienia");
        }

        prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        etInterval = findViewById(R.id.etInterval);
        etServerIp = findViewById(R.id.etServerIp); // POWIĄZANIE Z UI
        radioGroupTheme = findViewById(R.id.radioGroupTheme);

        // Wczytaj aktualne ustawienia (IP domyślne to 10.0.2.2 dla emulatora)
        etInterval.setText(String.valueOf(prefs.getInt("interval", 10)));
        etServerIp.setText(prefs.getString("server_ip", "10.0.2.2"));

        int savedTheme = prefs.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        if (savedTheme == AppCompatDelegate.MODE_NIGHT_NO) {
            radioGroupTheme.check(R.id.rbLight);
        } else if (savedTheme == AppCompatDelegate.MODE_NIGHT_YES) {
            radioGroupTheme.check(R.id.rbDark);
        } else {
            radioGroupTheme.check(R.id.rbSystem);
        }

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            String intervalStr = etInterval.getText().toString().trim();
            String serverIp = etServerIp.getText().toString().trim(); // POBIERANIE IP

            // Walidacja pola czasu
            if (intervalStr.isEmpty()) {
                Toast.makeText(this, "Podaj czas odświeżania", Toast.LENGTH_SHORT).show();
                return;
            }

            // Walidacja pola IP
            if (serverIp.isEmpty()) {
                Toast.makeText(this, "Podaj adres IP serwera", Toast.LENGTH_SHORT).show();
                return;
            }

            int interval;
            try {
                int parsed = Integer.parseInt(intervalStr);
                interval = Math.max(parsed, 1);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Niepoprawny format liczby", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedTheme;
            int checkedId = radioGroupTheme.getCheckedRadioButtonId();
            if (checkedId == R.id.rbLight) {
                selectedTheme = AppCompatDelegate.MODE_NIGHT_NO;
            } else if (checkedId == R.id.rbDark) {
                selectedTheme = AppCompatDelegate.MODE_NIGHT_YES;
            } else {
                selectedTheme = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
            }

            // ZAPISYWANIE WSZYSTKICH USTAWIEŃ
            prefs.edit()
                    .putInt("interval", interval)
                    .putString("server_ip", serverIp) // ZAPIS IP
                    .putInt("theme", selectedTheme)
                    .apply();

            AppCompatDelegate.setDefaultNightMode(selectedTheme);
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        getOnBackPressedDispatcher().onBackPressed();
        return true;
    }
}