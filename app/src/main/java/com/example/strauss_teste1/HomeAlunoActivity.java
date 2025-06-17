package com.example.strauss_teste1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class HomeAlunoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_aluno);
        Button btnAgendar = findViewById(R.id.btnAgendar);
        Button btnVerAgendamentos = findViewById(R.id.btnVerAgendamentos);
        Button btnFeedbacks = findViewById(R.id.btnFeedbacks);

        btnAgendar.setOnClickListener(v -> {
            Intent intent = new Intent(HomeAlunoActivity.this, MainActivity.class); // CERTO
            startActivity(intent);

        });

        btnVerAgendamentos.setOnClickListener(v -> {
            Intent intent = new Intent(HomeAlunoActivity.this,  AgendamentosActivity.class);
            startActivity(intent);
        });

        btnFeedbacks.setOnClickListener(v -> {
            Intent intent = new Intent(HomeAlunoActivity.this, FeedbackActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

    }
}