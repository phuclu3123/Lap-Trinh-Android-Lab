package vn.edu.ueh.phuclu.btvn_lab_4;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Switch;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edt_name, edt_mail, edt_dob;
    CheckBox cb_music, cb_sport, cb_art;
    Switch sw_gender;
    Button btn_register, btn_cancel, btn_show;

    ArrayList<User> userList = new ArrayList<>();

    private View.OnClickListener onclick_register = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = edt_name.getText().toString();
            String email = edt_mail.getText().toString();
            String dob = edt_dob.getText().toString();

            String hobby = "";

            if(cb_music.isChecked()) {
                hobby += "Music";
            }

            if(cb_sport.isChecked()) {
                hobby += "Sport";
            }

            if(cb_art.isChecked()) {
                hobby += "Art";
            }

            String gender;

            if(sw_gender.isChecked()) {
                gender = "F";
            }
            else {
                gender = "M";
            }

            User user = new User (username, email, dob, hobby, gender);

            userList.add(user);

            Toast.makeText(
                    MainActivity.this,
                    "Register successfully",
                    Toast.LENGTH_SHORT
            ).show();


            // Reset form
            edt_name.setText("");
            edt_mail.setText("");
            edt_dob.setText("");

            cb_music.setChecked(false);
            cb_sport.setChecked(false);
            cb_art.setChecked(false);

            sw_gender.setChecked(false);

            edt_name.requestFocus();
        }
    };

    // Cancel
    private View.OnClickListener onclick_cancel = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            edt_name.setText("");
            edt_mail.setText("");
            edt_dob.setText("");

            cb_music.setChecked(false);
            cb_sport.setChecked(false);
            cb_art.setChecked(false);

            sw_gender.setChecked(false);

            edt_name.requestFocus();
        }
    };

    // Show
    private View.OnClickListener onclick_show = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            // Tạo Intent để chuyển từ MainActivity sang ResultActivity.
            Intent intent =
                    new Intent(
                            MainActivity.this,
                            ResultActivity.class
                    );

            // Gửi danh sách userList sang ResultActivity
            // với key là "USER_LIST".
            intent.putExtra(
                    "USER_LIST",
                    userList
            );

            // Bắt đầu mở ResultActivity.
            startActivity(intent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constrain_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        edt_name = findViewById(R.id.edt_name);
        edt_mail = findViewById(R.id.edt_mail);
        edt_dob = findViewById(R.id.edt_dob);

        cb_music = findViewById(R.id.cb_music);
        cb_sport = findViewById(R.id.cb_sport);
        cb_art = findViewById(R.id.cb_art);

        sw_gender = findViewById(R.id.sw_gender);

        btn_register = findViewById(R.id.btn_register);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_show = findViewById(R.id.btn_show);


        // Bắt sự kiện
        btn_register.setOnClickListener(onclick_register);
        btn_cancel.setOnClickListener(onclick_cancel);
        btn_show.setOnClickListener(onclick_show);
    }
}