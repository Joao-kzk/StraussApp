package com.example.strauss_teste1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AgendamentosActivity extends AppCompatActivity {

    private ListView agendamentosListView;
    private TextView emptyTextView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listaDeAgendamentos = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "AgendamentosPrefs";
    private static final String KEY_AGENDAMENTOS = "agendamentos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamentos);

        agendamentosListView = findViewById(R.id.agendamentosListView);
        emptyTextView = findViewById(R.id.emptyTextView);
        Button btnVoltar = findViewById(R.id.btnVoltar);


        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        listaDeAgendamentos = carregarAgendamentos();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDeAgendamentos);
        agendamentosListView.setAdapter(adapter);

        atualizarVisibilidade();



        btnVoltar.setOnClickListener(v -> finish());
    }

    private void salvarAgendamentos(List<String> lista) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(lista);
        editor.putString(KEY_AGENDAMENTOS, json);
        editor.apply();
    }

    private ArrayList<String> carregarAgendamentos() {
        String json = sharedPreferences.getString(KEY_AGENDAMENTOS, null);
        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }

    private void atualizarVisibilidade() {
        if (listaDeAgendamentos.isEmpty()) {
            emptyTextView.setVisibility(TextView.VISIBLE);
            agendamentosListView.setVisibility(ListView.GONE);
        } else {
            emptyTextView.setVisibility(TextView.GONE);
            agendamentosListView.setVisibility(ListView.VISIBLE);
        }
    }
}
