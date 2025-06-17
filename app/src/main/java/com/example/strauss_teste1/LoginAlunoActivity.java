package com.example.strauss_teste1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoginAlunoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_aluno);

        // Referência aos botões
        Button btnEntrar = findViewById(R.id.btnEntrar);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        // Ação do botão "Entrar"
        btnEntrar.setOnClickListener(v -> {
            Intent intent = new Intent(LoginAlunoActivity.this, HomeAlunoActivity.class);
            startActivity(intent);
            finish(); // Fecha a tela de login para não voltar nela ao clicar em "voltar"
        });

        // Ação do botão "Cadastrar"
        btnCadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(LoginAlunoActivity.this, CadastroActivity.class);
            startActivity(intent);
        });
    }
}



