package vn.edu.ueh.phuclu.btvn_buoi3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // ô nhập số n, hiển thị time, hiển thị fail
    EditText edtNumber, edtTime, edtFails;
    // button nhấn ranodm
    Button btnRandom;

    // 12 thẻ card từ trái qua phải 3x4 = 12
    Button[] cards = new Button[12];

    // 12 số nằm sau 12 thẻ
    int[] numbers = new int[12];

    // Vị trí thẻ đầu tiên
    int firstCard = -1;

    // Số lần sai
    int fail = 0;

    // Số cặp đã tìm được
    int pair = 0;

    // Đang chờ úp lại 2 thẻ sai
    boolean checking = false;

    // Game đã bắt đầu chưa
    boolean started = false;

    boolean timerStarted = false;

    // TIMER: chạy code sau mot khoảng thời gian hoặc lặp lại
    Handler handler = new Handler(Looper.getMainLooper());

    long startTime;


    Runnable timer = new Runnable() {

        @Override
        public void run() {

            // thời gian thuc tế - thời gian bắt đầu game
            long elapsed =
                    SystemClock.elapsedRealtime() - startTime;

            // chia lấy mili giây thành giây
            int totalSeconds =
                    (int) (elapsed / 1000);

            // 1 phút = 60 s => chia để lấy phút
            int minute =
                    totalSeconds / 60;

            // giây thì lấy đồng dư
            int second =
                    totalSeconds % 60;


            // hiển thị ra đúng định dạng string.format
            edtTime.setText(
                    String.format(
                            "%02d:%02d",
                            minute,
                            second
                    )
            );


            // Sau 1 giây chạy lại
            handler.postDelayed(this, 1000);
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


        // Ánh xạ EditText - lấy từ cái xml
        edtNumber = findViewById(R.id.edtNumber);
        edtTime = findViewById(R.id.edtTime);
        edtFails = findViewById(R.id.edtFails);

        // Ánh xạ nút Random - lấy từ cái xml
        btnRandom = findViewById(R.id.btnRandom);

        // Ánh xạ 12 thẻ - lấy từ cái xml
        cards[0] = findViewById(R.id.card0);
        cards[1] = findViewById(R.id.card1);
        cards[2] = findViewById(R.id.card2);
        cards[3] = findViewById(R.id.card3);
        cards[4] = findViewById(R.id.card4);
        cards[5] = findViewById(R.id.card5);
        cards[6] = findViewById(R.id.card6);
        cards[7] = findViewById(R.id.card7);
        cards[8] = findViewById(R.id.card8);
        cards[9] = findViewById(R.id.card9);
        cards[10] = findViewById(R.id.card10);
        cards[11] = findViewById(R.id.card11);

        btnRandom.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startGame();
                    }
                }
        );


        // BẤM 12 THẺ
        for (int i = 0; i < 12; i++) {
            final int index = i;

            cards[i].setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            flipCard(index);
                        }
                    }
            );
        }
    }


    private void startGame() {

        // lấy giá trị của người dùng nhập vào
        String input =
                edtNumber.getText().toString();


        // Chưa nhập số
        if (input.isEmpty()) {

            // Hiển thị thong báo
            Toast.makeText(
                    this,
                    "Vui lòng nhập số",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        // kiểm tra số nguyên
        int n =
                Integer.parseInt(input);

        // Phải có ít nhất 6 số khác nhau nhỏ hơn n
        if (n < 7) {

            Toast.makeText(
                    this,
                    "Nhập số từ 7 trở lên",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }


        // RANDOM 6 SỐ KHÁC NHAU
        int[] temp = new int[6];
        int count = 0;

        while (count < 6) {

            // Random từ 1 đến n - 1
            int x =
                    (int) (Math.random() * (n - 1)) + 1;

            boolean trung = false;

            // Kiểm tra trùng
            for (int i = 0; i < count; i++) {
                if (temp[i] == x) {
                    trung = true;
                    break;
                }
            }

            // Nếu không trùng thì thêm
            if (!trung) {
                temp[count] = x;
                count++;
            }
        }


        // MỖI SỐ XUẤT HIỆN 2 LẦN - Nhân đôi cái dống nãy
        for (int i = 0; i < 6; i++) {

            numbers[i * 2] =
                    temp[i];

            numbers[i * 2 + 1] =
                    temp[i];
        }


        // TRỘN 12 SỐ
        for (int i = 0; i < 12; i++) {

            // ex: i = 2 => random Index = 8
            int randomIndex = (int) (Math.random() * 12);
            // swap numbers[2] và numbers[8]
            int t = numbers[i];

            numbers[i] = numbers[randomIndex];

            numbers[randomIndex] = t;
        }


        // RESET GAME

        firstCard = -1;
        fail = 0;
        pair = 0;
        checking = false;
        started = true;

        edtFails.setText("0");


        // Úp lại tất cả thẻ
        for (int i = 0; i < 12; i++) {

            // Úp lại hiện ?
            cards[i].setText("?");
            cards[i].setEnabled(true);
        }


        // BẮT ĐẦU TIMER - dừng timer cũ
        handler.removeCallbacks(timer);

        edtTime.setText("00:00");
        timerStarted = false;
    }


    // LẬT THẺ
    private void flipCard(int index) {

        // Chưa bắt đầu game
        if (!started) {
            return;
        }

        // Đang chờ úp 2 thẻ sai
        if (checking) {
            return;
        }

        // Không cho bấm lại thẻ đầu tiên
        if (index == firstCard) {
            return;
        }

        // Hiện số của thẻ
        cards[index].setText(
                String.valueOf(numbers[index])
        );

        // THẺ ĐẦU TIÊN -- logic tính thời gian khi nhấn vào thẻ đầu tiên
        if (firstCard == -1) {

            if (!timerStarted) {

                startTime =
                        SystemClock.elapsedRealtime();

                handler.post(timer);

                timerStarted = true;
            }

            firstCard = index;

            return;
        }

        // THẺ THỨ HAI
        final int secondCard = index;

        // HAI THẺ GIỐNG NHAU
        if (numbers[firstCard]
                == numbers[secondCard]) {

            cards[firstCard].setEnabled(false);
            cards[secondCard].setEnabled(false);

            pair++;

            firstCard = -1;

            // Tìm đủ 6 cặp
            if (pair == 6) {

                started = false;

                // Dừng timer
                handler.removeCallbacks(timer);

                timerStarted = false;
                Toast.makeText(
                        this,
                        "Hoàn thành!",
                        Toast.LENGTH_LONG
                ).show();
            }
        }


        // HAI THẺ KHÁC NHAU

        else {
            fail++;
            edtFails.setText(
                    String.valueOf(fail)
            );

            checking = true;

            final int oldFirstCard =
                    firstCard;

            // Sau 0.8 giây thì úp lại
            cards[secondCard].postDelayed(
                    new Runnable() {

                        @Override
                        public void run() {
                            cards[oldFirstCard].setText("?");

                            cards[secondCard].setText("?");

                            firstCard = -1;
                            checking = false;
                        }
                    },

                    800
            );
        }
    }
}

