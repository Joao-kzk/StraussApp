package com.example.strauss_teste1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editTextDate;
    Spinner spinnerHorarios;
    Button btnAgendar;
    Button verAgendamentosBtn;
    Button btnVoltarMenu;

    private static final String PREFS_NAME = "AgendamentosPrefs";
    private static final String KEY_AGENDAMENTOS = "agendamentos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDate = findViewById(R.id.editTextDate);
        spinnerHorarios = findViewById(R.id.spinnerHorarios);
        btnAgendar = findViewById(R.id.button);
        verAgendamentosBtn = findViewById(R.id.verAgendamentosBtn);
        btnVoltarMenu = findViewById(R.id.btnVoltarMenu); // Novo botão

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String[] horarios = new String[]{"08:00", "09:00", "10:00", "11:00", "14:00", "15:00", "16:00", "17:00"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, horarios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorarios.setAdapter(adapter);

        editTextDate.setFocusable(false);
        editTextDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view, year1, month1, dayOfMonth) -> {
                        String dataSelecionada = String.format("%02d/%02d/%04d", dayOfMonth, month1 + 1, year1);
                        editTextDate.setText(dataSelecionada);
                    }, year, month, day);
            datePickerDialog.show();
        });

        btnAgendar.setOnClickListener(view -> {
            String data = editTextDate.getText().toString();
            String horario = spinnerHorarios.getSelectedItem().toString();
            String descricao = "Agendamento de aula com personal";

            if (data.isEmpty()) {
                Toast.makeText(MainActivity.this, "Selecione uma data", Toast.LENGTH_SHORT).show();
            } else {
                String mensagem = String.format("Agendado para: %s às %s (%s)", data, horario, descricao);

                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                Gson gson = new Gson();
                String json = prefs.getString(KEY_AGENDAMENTOS, null);
                Type type = new TypeToken<ArrayList<String>>() {}.getType();
                ArrayList<String> lista = (json != null) ? gson.fromJson(json, type) : new ArrayList<>();
                lista.add(0, mensagem);
                prefs.edit().putString(KEY_AGENDAMENTOS, gson.toJson(lista)).apply();

                Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_LONG).show();
            }
        });

        verAgendamentosBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AgendamentosActivity.class);
            startActivity(intent);
        });

        btnVoltarMenu.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HomeAlunoActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}








