package com.example.strauss_teste1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomePersonalActivity extends AppCompatActivity {

    Button btnVerAgendamentos, btnEnviarFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_personal);

        btnVerAgendamentos = findViewById(R.id.btnVerAgendamentos);
        btnEnviarFeedback = findViewById(R.id.btnEnviarFeedback);

        btnVerAgendamentos.setOnClickListener(v -> {
            startActivity(new Intent(HomePersonalActivity.this, AgendamentosPersonalActivity.class));
        });

        btnEnviarFeedback.setOnClickListener(v -> {
            startActivity(new Intent(HomePersonalActivity.this, FeedbackPersonalActivity.class));
        });
    }
}


