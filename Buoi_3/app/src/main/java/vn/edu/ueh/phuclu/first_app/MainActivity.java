package vn.edu.ueh.phuclu.first_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView txt_display;
    private Button btn_change;
    private Button btn_resset;
    private View.OnClickListener onclick_change = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btn_change) {
                txt_display.setText("You hit the button");
            }
        }
    };
    private View.OnClickListener onclick_resset = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btn_resset) {
                txt_display.setText("Default Text");
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_display = findViewById(R.id.txt_display);
        btn_change = findViewById(R.id.btn_change);
        btn_resset = findViewById(R.id.btn_resset);

        btn_change.setOnClickListener(onclick_change);
        btn_resset.setOnClickListener(onclick_resset);
    }
}