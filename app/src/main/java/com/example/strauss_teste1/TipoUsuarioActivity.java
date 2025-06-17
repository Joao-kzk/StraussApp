package com.example.strauss_teste1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class TipoUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_usuario);

        Button btnAluno = findViewById(R.id.btnAluno);
        Button btnPersonal = findViewById(R.id.btnPersonal);

        btnAluno.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginAlunoActivity.class);
            startActivity(intent);
        });

        btnPersonal.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginPersonalActivity.class);
            startActivity(intent);
        });
    }
}

