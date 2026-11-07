package vn.edu.ueh.phuclu.btvn_lab_4;

import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    ConstraintLayout result_container;
    ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_activity);

        result_container = findViewById(R.id.result_container);

        // Lấy danh sách User được MainActivity gửi sang qua Intent.
        // Dữ liệu được gửi với key "USER_LIST", sau đó ép kiểu về ArrayList<User>.
        userList = (ArrayList<User>)
                getIntent().getSerializableExtra("USER_LIST");

        // Nếu MainActivity không gửi danh sách sang hoặc dữ liệu nhận được bị null
        // thì tạo một ArrayList rỗng để tránh lỗi NullPointerException khi sử dụng userList.
        if (userList == null) {
            userList = new ArrayList<>();
        }

        /*
            MainActivity
                ↓
            putExtra("USER_LIST", userList)
                ↓
            Intent mang dữ liệu sang Activity khác
                ↓
            ResultActivity
                ↓
            getSerializableExtra("USER_LIST")
                ↓
            Lấy lại userList
         Cho phép Java biến object thành dạng có thể truyền/lưu được, rồi sau đó khôi phục lại thành object.
         */


        // Header
        createRow(
                0,
                "No",
                "Username",
                "Email",
                "DoB",
                "Hobby",
                "Gender"
        );

        // Data
        for (int i = 0; i < userList.size(); i++) {

            User user = userList.get(i);

            createRow(
                    i + 1,
                    String.valueOf(i + 1),
                    user.username,
                    user.email,
                    user.dob,
                    user.hobby,
                    user.gender
            );
        }
    }


    private void createRow(
            int position,
            String no,
            String username,
            String email,
            String dob,
            String hobby,
            String gender
    ) {

        TableRow row = new TableRow(this);

        // Tạo các ô
        TextView txtNo = createCell(no, 1);
        TextView txtUsername = createCell(username, 2);
        TextView txtEmail = createCell(email, 3);
        TextView txtDob = createCell(dob, 2);
        TextView txtHobby = createCell(hobby, 2);
        TextView txtGender = createCell(gender, 1);

        // Thêm ô vào hàng
        row.addView(txtNo);
        row.addView(txtUsername);
        row.addView(txtEmail);
        row.addView(txtDob);
        row.addView(txtHobby);
        row.addView(txtGender);

        // Vị trí của cả hàng
        ConstraintLayout.LayoutParams params =
                new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                );

        params.topToTop =
                ConstraintLayout.LayoutParams.PARENT_ID;

        params.startToStart =
                ConstraintLayout.LayoutParams.PARENT_ID;

        params.endToEnd =
                ConstraintLayout.LayoutParams.PARENT_ID;

        params.topMargin = position * 55;

        row.setLayoutParams(params);

        result_container.addView(row);
    }


    private TextView createCell(String text, int weight) {

        TextView txt = new TextView(this);

        txt.setText(text);

        txt.setTextSize(11);

        txt.setPadding(4, 12, 4, 12);

        // Quan trọng:
        // width = 0 để TableRow chia chiều rộng theo weight
        TableRow.LayoutParams params =
                new TableRow.LayoutParams(
                        0,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        weight
                );

        txt.setLayoutParams(params);

        return txt;
    }
}