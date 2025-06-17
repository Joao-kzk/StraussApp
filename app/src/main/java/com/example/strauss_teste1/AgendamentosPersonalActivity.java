package com.example.strauss_teste1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AgendamentosPersonalActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "AgendamentosPrefs";
    private static final String KEY_AGENDAMENTOS = "agendamentos";

    ListView listViewAgendamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamentos_personal);

        listViewAgendamentos = findViewById(R.id.listViewAgendamentosPersonal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Feedback");
        }


        // Recupera os agendamentos
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String json = prefs.getString(KEY_AGENDAMENTOS, null);

        ArrayList<String> agendamentos = new ArrayList<>();

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            agendamentos = gson.fromJson(json, type);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, agendamentos);
        listViewAgendamentos.setAdapter(adapter);
    }

    // Ação do botão de voltar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}



