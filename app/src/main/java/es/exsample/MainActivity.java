package es.exsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button scanReceipt = findViewById(R.id.button_scan_receipt);
        scanReceipt.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TestActivity.class)));

        Button manualInput = findViewById(R.id.button_manual_input);
        manualInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });

        Button viewPastRecords = findViewById(R.id.button_view_past_records);
        viewPastRecords.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TestActivity.class)));
    }
}
