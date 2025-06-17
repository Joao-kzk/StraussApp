package com.example.strauss_teste1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FeedbackActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "FeedbackPrefs";
    private static final String KEY_FEEDBACKS = "feedbacks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // Ativa a toolbar com botão de voltar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        LinearLayout feedbackContainer = findViewById(R.id.feedbackContainer);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String json = prefs.getString(KEY_FEEDBACKS, null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> listaFeedbacks = gson.fromJson(json, type);

            for (String feedback : listaFeedbacks) {
                TextView tv = new TextView(this);
                tv.setText("✅ " + feedback);
                tv.setPadding(8, 8, 8, 8);
                tv.setBackgroundColor(0xFFEEEEEE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 0, 0, 16);
                tv.setLayoutParams(params);

                feedbackContainer.addView(tv);
            }
        }
    }

    // Função para o botão de voltar funcionar corretamente
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}



