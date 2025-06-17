package com.example.strauss_teste1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FeedbackPersonalActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "FeedbackPrefs";
    private static final String KEY_FEEDBACKS = "feedbacks";

    EditText editTextNomeAluno, editTextFeedback;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_personal);

        // Mostra o botão de voltar na barra superior

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Feedback");
        }


        editTextNomeAluno = findViewById(R.id.editTextNomeAluno);
        editTextFeedback = findViewById(R.id.editFeedback);
        btnEnviar = findViewById(R.id.btnEnviarFeedback);

        btnEnviar.setOnClickListener(v -> {
            String nome = editTextNomeAluno.getText().toString().trim();
            String feedback = editTextFeedback.getText().toString().trim();

            if (nome.isEmpty() || feedback.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            String mensagem = nome + ": " + feedback;

            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            Gson gson = new Gson();
            String json = prefs.getString(KEY_FEEDBACKS, null);
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> lista = (json != null) ? gson.fromJson(json, type) : new ArrayList<>();
            lista.add(0, mensagem);
            prefs.edit().putString(KEY_FEEDBACKS, gson.toJson(lista)).apply();

            Toast.makeText(this, "Feedback enviado!", Toast.LENGTH_SHORT).show();

            editTextNomeAluno.setText("");
            editTextFeedback.setText("");
        });
    }

    // Faz o botão de voltar da barra superior funcionar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

