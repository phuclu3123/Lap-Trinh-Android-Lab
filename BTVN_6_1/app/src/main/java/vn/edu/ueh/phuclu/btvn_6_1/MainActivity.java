package vn.edu.ueh.phuclu.btvn_6_1;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // GridLayout dùng để chứa các button Employee
    private GridLayout gridEmployees;
    // final: không được gán employees sang List khác, nhưng vẫn add/remove phần tử bình thường
    private final List<Employee> employees = new ArrayList<>();
    // ID tự tăng cho Employee mới
    private int nextEmployeeId = 1;

    // Có cả hai: dùng để thay đổi giao diện và dùng để biết thông tin id.
    // Lưu button Employee đang được chọn bằng long press
    private Button focusedEmployeeButton = null;
    // Lưu object Employee tương ứng với button đang được chọn
    private Employee focusedEmployee = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Lấy Toolbar trong XML và biến nó thành ActionBar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);   // tìm toolbar trong xml
        setSupportActionBar(toolbar);   // thiết lập với android: dùng toolbar này làm ActionBar của màn hình

        // Ẩn title BTVN_6_1 trên Toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }

        // Ánh xạ GridLayout trong activity_main.xml
        gridEmployees = findViewById(R.id.gridEmployees);

        // Thêm padding theo status bar/navigation bar để giao diện không bị che
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                new OnApplyWindowInsetsListener() {
                    @NonNull
                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(
                            @NonNull View v,
                            @NonNull WindowInsetsCompat insets) {

                        Insets systemBars =
                                insets.getInsets(WindowInsetsCompat.Type.systemBars());

                        v.setPadding(
                                systemBars.left,
                                systemBars.top,
                                systemBars.right,
                                systemBars.bottom
                        );

                        return insets;
                    }
                }
        );
    }

    // Đọc main_menu.xml rồi đưa New / Change Color / About lên Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Xử lý sự kiện khi người dùng chọn item trên menu: có 3 sự kiện chính 1. New - 2. Change Color - 3. About
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        // New -> mở dialog thêm Employee
        if (itemId == R.id.menu_new) {
            showNewEmployeeDialog();
            return true;
        }

        // Change Color -> đổi màu Employee đang được chọn
        if (itemId == R.id.menu_change_color) {
            showChangeColorDialog();
            return true;
        }

        // About -> hiện thông tin/hướng dẫn sử dụng
        if (itemId == R.id.menu_about) {
            showAboutDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Hiển thị dialog nhập thông tin Employee mới
    private void showNewEmployeeDialog() {

        // Biến dialog_employee.xml thành View để đưa vào AlertDialog
        LayoutInflater inflater = LayoutInflater.from(this);
        final View dialogView = inflater.inflate(R.layout.dialog_employee, null);

        // Ánh xạ các control nằm trong dialog
        final EditText edtName = dialogView.findViewById(R.id.edtName);
        final EditText edtSalary = dialogView.findViewById(R.id.edtSalary);
        final SeekBar seekAge = dialogView.findViewById(R.id.seekAge);
        final TextView txtAgeValue = dialogView.findViewById(R.id.txtAgeValue);
        final RadioButton radioMale = dialogView.findViewById(R.id.radioMale);
        final Spinner spinnerHobby = dialogView.findViewById(R.id.spinnerHobby);
        final Button btnAdd = dialogView.findViewById(R.id.btnAdd);
        final Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        // Dữ liệu hiển thị trong Spinner Hobby
        String[] hobbies = {
                "Music",
                "Sport",
                "Traveling"
        };

        // Adapter làm cầu nối giữa mảng hobbies và Spinner
        ArrayAdapter<String> hobbyAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        hobbies
                );

        // Layout dùng khi Spinner xổ danh sách xuống
        hobbyAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        // Gắn dữ liệu vào Spinner
        spinnerHobby.setAdapter(hobbyAdapter);

        // Hiển thị tuổi hiện tại của SeekBar
        txtAgeValue.setText(
                String.valueOf(seekAge.getProgress())
        );

        // Khi kéo SeekBar thì cập nhật số tuổi trên TextView
        seekAge.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar,
                            int progress,
                            boolean fromUser) {

                        txtAgeValue.setText(
                                String.valueOf(progress)
                        );
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}
                }
        );

        // Tạo AlertDialog và nhét dialog_employee.xml vào
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("New Employee");
        builder.setView(dialogView);

        // create() tạo dialog, show() mới hiển thị ra màn hình
        final AlertDialog dialog = builder.create();

        dialog.show();

        // Xử lý khi nhấn Add
        btnAdd.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // Lấy Name và Salary từ EditText
                        String name = edtName.getText().toString().trim();

                        String salaryText = edtSalary.getText().toString().trim();

                        // Không cho để trống Name
                        if (name.isEmpty()) {
                            edtName.setError("Please enter name");
                            edtName.requestFocus();
                            return;
                        }

                        // Không cho để trống Salary
                        if (salaryText.isEmpty()) {
                            edtSalary.setError("Please enter C.Sal");
                            edtSalary.requestFocus();
                            return;
                        }

                        // Chuyển Salary từ String sang double
                        double salary;

                        try {
                            salary = Double.parseDouble(salaryText);

                        } catch (NumberFormatException e) {
                            edtSalary.setError("Invalid C.Sal");
                            edtSalary.requestFocus();
                            return;
                        }

                        // Lấy Age từ SeekBar
                        int age = seekAge.getProgress();
                        // Nếu radioMale được chọn -> Male, ngược lại -> Female
                        String gender = radioMale.isChecked() ? "Male" : "Female";
                        // Lấy Hobby đang được chọn trong Spinner
                        String hobby = spinnerHobby.getSelectedItem().toString();

                        // Tạo object Employee mới từ dữ liệu vừa nhập
                        Employee employee =
                                new Employee(
                                        nextEmployeeId,
                                        name,
                                        salary,
                                        age,
                                        gender,
                                        hobby
                                );

                        // Tăng ID để Employee tiếp theo có ID mới
                        nextEmployeeId++;
                        // Lưu Employee vào danh sách dữ liệu
                        employees.add(employee);
                        // Tạo button Employee và thêm vào GridLayout
                        addEmployeeToGrid(employee);
                        // Đóng dialog sau khi thêm thành công
                        dialog.dismiss();
                    }
                }
        );

        // Cancel -> đóng dialog và không lưu gì
        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                }
        );
    }

    // Tạo một Button đại diện cho Employee rồi thêm vào GridLayout
    private void addEmployeeToGrid(final Employee employee) {

        // Tạo Button bằng Java vì số Employee thay đổi lúc runtime
        final Button employeeButton = new Button(this);
        // Tạo ID tự động cho Button
        employeeButton.setId(View.generateViewId());

        // Ví dụ ID 1 -> Emp 01, ID 2 -> Emp 02
        employeeButton.setText(
                String.format(
                        Locale.getDefault(),
                        "Emp %02d",
                        employee.getId()
                )
        );

        // Không ép chữ trên Button thành chữ hoa
        employeeButton.setAllCaps(false);

        // Cho phép Button nhận focus khi long press
        employeeButton.setFocusable(true);
        employeeButton.setFocusableInTouchMode(true);

        // Tạo LayoutParams để Button nằm đúng trong GridLayout
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();

        // width = 0 kết hợp weight = 1f để các cột chia đều chiều rộng
        params.width = 0;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;

        // Button chiếm một cột và dùng weight 1
        params.columnSpec =
                GridLayout.spec(
                        GridLayout.UNDEFINED,
                        1f
                );

        // Tạo khoảng cách 8dp xung quanh Button
        params.setMargins(
                dpToPx(8),
                dpToPx(8),
                dpToPx(8),
                dpToPx(8)
        );

        employeeButton.setLayoutParams(params);

        // Click thường -> xem thông tin Employee
        employeeButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showEmployeeInfoDialog(employee);
                    }
                }
        );

        // Long press -> chọn Employee để sau đó dùng Change Color
        employeeButton.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        // Nếu trước đó đã chọn Employee khác thì bỏ focus cũ
                        if (focusedEmployeeButton != null && focusedEmployeeButton != employeeButton) {
                            focusedEmployeeButton.setSelected(false);
                            focusedEmployeeButton.clearFocus();
                        }

                        // Lưu Button và Employee hiện tại làm đối tượng đang được chọn
                        focusedEmployeeButton = employeeButton;
                        focusedEmployee = employee;

                        // Đánh dấu và focus Button hiện tại
                        employeeButton.setSelected(true);
                        employeeButton.requestFocus();

                        // true = long click đã được xử lý xong
                        return true;
                    }
                }
        );
        // Đưa Button Employee lên GridLayout
        gridEmployees.addView(employeeButton);
    }

    // Hiển thị toàn bộ thông tin của một Employee
    private void showEmployeeInfoDialog(Employee employee) {

        // Format Salary gọn, ví dụ 1500.00 -> 1500
        DecimalFormat salaryFormat = new DecimalFormat("0.##");

        // Ghép thông tin Employee thành chuỗi để hiển thị
        String message =
                "ID: " + employee.getId() + "\n" +
                        "Name: " + employee.getName() + "\n" +
                        "C.Sal: " + salaryFormat.format(employee.getCSal()) + "\n" +
                        "Age: " + employee.getAge() + "\n" +
                        "Gender: " + employee.getGender() + "\n" +
                        "Hobby: " + employee.getHobby();

        // Tạo dialog thông tin Employee
        new AlertDialog.Builder(this)
                .setTitle(
                        String.format(
                                Locale.getDefault(),
                                "Emp %02d",
                                employee.getId()
                        )
                )
                .setMessage(message).setPositiveButton("OK", null).show();
    }

    // Dialog đổi màu Button của Employee đã được long press
    private void showChangeColorDialog() {
        // Chưa chọn Employee nào thì không cho đổi màu
        if (focusedEmployeeButton == null || focusedEmployee == null) {
            new AlertDialog.Builder(this)
                    .setTitle("Change Color")
                    .setMessage(
                            "Please long press an employee to focus it first."
                    ).setPositiveButton("OK", null).show();

            return;
        }

        // Tên màu hiển thị trong dialog
        final String[] colorNames = {
                "Red",
                "Green",
                "Blue",
                "Yellow",
                "Gray"
        };

        // Mã màu tương ứng với từng phần tử trong colorNames
        final int[] colors = {
                Color.rgb(244, 67, 54),
                Color.rgb(76, 175, 80),
                Color.rgb(33, 150, 243),
                Color.rgb(255, 193, 7),
                Color.rgb(158, 158, 158)
        };

        // Ví dụ: Change Color - Emp 02
        String title = String.format(
                        Locale.getDefault(),
                        "Change Color - Emp %02d",
                        focusedEmployee.getId()
                );

        // Hiển thị danh sách màu để người dùng chọn
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setItems(
                        colorNames,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(
                                    DialogInterface dialog,
                                    int which) {

                                // which là vị trí màu mà người dùng vừa chọn
                                if (focusedEmployeeButton != null) {
                                    focusedEmployeeButton.setBackgroundTintList(
                                                    ColorStateList.valueOf(
                                                            colors[which]
                                                    )
                                            );
                                }
                            }
                        }
                )
                .setNegativeButton("Cancel", null).show();
    }

    // Dialog About mô tả cách sử dụng ứng dụng
    private void showAboutDialog() {
        String message =
                "Employee Manager\n\n" +
                        "New: add a new employee.\n" +
                        "Click employee: show employee information.\n" +
                        "Long press employee: focus/select employee.\n" +
                        "Change Color: change the focused employee button color.";

        new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    // Chuyển dp sang pixel để margin hiển thị ổn trên nhiều mật độ màn hình
    private int dpToPx(int dp) {
        float density =
                getResources()
                        .getDisplayMetrics()
                        .density;

        return Math.round(dp * density);
    }
}