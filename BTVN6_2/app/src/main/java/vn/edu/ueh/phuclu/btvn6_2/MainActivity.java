package vn.edu.ueh.phuclu.btvn6_2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    // DrawerLayout là layout chính chứa nội dung màn hình + menu kéo từ cạnh trái
    private DrawerLayout drawerLayout;

    // Quản lý nút hamburger ☰ trên Toolbar và trạng thái đóng/mở Drawer
    private ActionBarDrawerToggle drawerToggle;

    // NavigationView là phần menu bên trái chứa Group 1, Group 2, Group 3
    private NavigationView navigationView;

    // Các TextView dùng để hiển thị tên lớp và thông tin sinh viên
    private TextView textClassTitle;
    private TextView textStudent1;
    private TextView textGpa1;
    private TextView textStudent2;
    private TextView textGpa2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cho phép giao diện hiển thị sát vùng status bar/navigation bar
        EdgeToEdge.enable(this);

        // Gắn activity_main.xml vào MainActivity
        setContentView(R.layout.activity_main);

        // Thêm padding theo system bar để giao diện không bị status bar/navigation bar che
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                new OnApplyWindowInsetsListener() {
                    @NonNull
                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(
                            @NonNull View v,
                            @NonNull WindowInsetsCompat insets) {

                        // Lấy kích thước vùng status bar và navigation bar
                        Insets systemBars =
                                insets.getInsets(
                                        WindowInsetsCompat.Type.systemBars()
                                );

                        // Thêm padding tương ứng vào View chính
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

        // Lấy Toolbar trong XML
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Biến Toolbar thành ActionBar của Activity
        setSupportActionBar(toolbar);

        // Ánh xạ các TextView dùng để hiển thị dữ liệu lớp và sinh viên
        textClassTitle = findViewById(R.id.text_class_title);
        textStudent1 = findViewById(R.id.text_student_1);
        textGpa1 = findViewById(R.id.text_gpa_1);
        textStudent2 = findViewById(R.id.text_student_2);
        textGpa2 = findViewById(R.id.text_gpa_2);

        // Lấy NavigationView là menu kéo từ bên trái
        navigationView = findViewById(R.id.navigation_view);

        // Mặc định đánh dấu Group 1 đang được chọn khi app mở
        navigationView.setCheckedItem(R.id.nav_group1);

        // Xử lý khi người dùng chọn một item trong Navigation Drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(
                            @NonNull MenuItem item) {

                        // Lấy ID của item vừa được chọn
                        int itemId = item.getItemId();

                        // Chọn Group 1 -> hiển thị dữ liệu Group 1
                        if (itemId == R.id.nav_group1) {
                            showGroup1();

                            // Chọn Group 2 -> hiển thị dữ liệu Group 2
                        } else if (itemId == R.id.nav_group2) {
                            showGroup2();

                            // Chọn Group 3 -> hiển thị dữ liệu Group 3
                        } else if (itemId == R.id.nav_group3) {
                            showGroup3();
                        }

                        // Sau khi chọn menu thì đóng Navigation Drawer lại
                        if (drawerLayout != null) {
                            drawerLayout.closeDrawers();
                        }

                        // true = sự kiện chọn menu đã được xử lý
                        return true;
                    }
                }
        );

        // Lấy View gốc có id main
        View rootView = findViewById(R.id.main);

        // Kiểm tra rootView thật sự có phải DrawerLayout hay không
        if (rootView instanceof DrawerLayout) {

            // Ép kiểu View thành DrawerLayout để sử dụng các hàm của DrawerLayout
            drawerLayout = (DrawerLayout) rootView;

            // Tạo DrawerToggle để liên kết Toolbar với DrawerLayout
            drawerToggle = new ActionBarDrawerToggle(
                    this,
                    drawerLayout,
                    toolbar,
                    R.string.app_name,
                    R.string.app_name
            );

            // Cho DrawerLayout biết phải theo dõi drawerToggle
            drawerLayout.addDrawerListener(drawerToggle);

            // Đồng bộ trạng thái nút hamburger ☰ với trạng thái đóng/mở Drawer
            drawerToggle.syncState();
        }

        // Khi app vừa mở thì hiển thị dữ liệu Group 1 trước
        showGroup1();
    }

    // Hiển thị dữ liệu của lớp Group 1
    private void showGroup1() {
        textClassTitle.setText("Class G01");
        textStudent1.setText("Ng A");
        textGpa1.setText("7.8");
        textStudent2.setText("Le B");
        textGpa2.setText("6.5");
    }

    // Hiển thị dữ liệu của lớp Group 2
    private void showGroup2() {
        textClassTitle.setText("Class G02");
        textStudent1.setText("Tran C");
        textGpa1.setText("8.2");
        textStudent2.setText("Pham D");
        textGpa2.setText("7.0");
    }

    // Hiển thị dữ liệu của lớp Group 3
    private void showGroup3() {
        textClassTitle.setText("Class G03");
        textStudent1.setText("Vo E");
        textGpa1.setText("9.0");
        textStudent2.setText("Hoang F");
        textGpa2.setText("7.4");
    }

    // Cho nút hamburger/back trên Toolbar được drawerToggle xử lý
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Nếu drawerToggle xử lý được item này thì dừng tại đây
        if (drawerToggle != null
                && drawerToggle.onOptionsItemSelected(item)) {

            return true;
        }

        // Nếu không phải sự kiện của Drawer thì để class cha xử lý
        return super.onOptionsItemSelected(item);
    }
}