package vn.edu.ueh.phuclu.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button ;
import android.widget.EditText;
import android.widget.TextView ;
import android.widget.Toast ;
import android.content.Intent;

public class SecondActivity extends AppCompatActivity {

    TextView txtMassage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtMassage = findViewById(R.id.txtMassage);

        Intent intent = getIntent();

        String name = intent.getStringExtra("Tag Name");

        txtMassage.setText(name);
    }
}