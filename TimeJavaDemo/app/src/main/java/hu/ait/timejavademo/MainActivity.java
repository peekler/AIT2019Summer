package hu.ait.timejavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTime = findViewById(R.id.btnTime);
        final TextView tvTime = findViewById(R.id.tvTime);

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = new Date(System.currentTimeMillis()).toString();

                Toast.makeText(MainActivity.this,
                        time,
                        Toast.LENGTH_SHORT).show();

                tvTime.setText(time);
            }
        });

    }
}
