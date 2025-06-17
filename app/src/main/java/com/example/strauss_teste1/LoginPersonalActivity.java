package com.example.strauss_teste1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPersonalActivity extends AppCompatActivity {

    EditText edtEmailPersonal, edtSenhaPersonal;
    Button btnLoginPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_personal);

        // Referência aos elementos da tela
        edtEmailPersonal = findViewById(R.id.edtEmailPersonal);
        edtSenhaPersonal = findViewById(R.id.edtSenhaPersonal);
        btnLoginPersonal = findViewById(R.id.btnLoginPersonal);

        // Ação do botão de login
        btnLoginPersonal.setOnClickListener(v -> {
            String usuario = edtEmailPersonal.getText().toString().trim();
            String senha = edtSenhaPersonal.getText().toString().trim();

            if (usuario.equals("admin") && senha.equals("admin")) {
                // Login correto
                Intent intent = new Intent(LoginPersonalActivity.this, HomePersonalActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Login incorreto
                Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


