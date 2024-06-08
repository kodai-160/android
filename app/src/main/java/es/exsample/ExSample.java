package es.exsample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ExSample extends AppCompatActivity {

    TextView tv;
    Button buttonScanReceipt, buttonManualInput, buttonViewPastRecords;

    private static final int REQUEST_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.text_title);  // テキストビューの参照
        buttonScanReceipt = findViewById(R.id.button_scan_receipt);  // レシート読み取りボタンの参照
        buttonManualInput = findViewById(R.id.button_manual_input);  // 手入力ボタンの参照
        buttonViewPastRecords = findViewById(R.id.button_view_past_records);  // 過去の記録ボタンの参照

        // レシート読み取りボタンのクリックリスナー
        buttonScanReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ExSample.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ExSample.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                } else {
                    launchCamera();
                }
            }
        });

        // 手入力ボタンのクリックリスナー
        buttonManualInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExSample.this, EntryActivity.class);
                startActivity(intent);
            }
        });

        // 過去の記録を見るボタンのクリックリスナー
        buttonViewPastRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExSample.this, ResultActivity.class);
                startActivity(intent);
            }
        });

    }

    private void launchCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, REQUEST_CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // imageBitmapを使用して何か処理を行う（例：画像を表示するなど）
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchCamera();
            } else {
                Toast.makeText(this, "カメラ権限が必要です", Toast.LENGTH_LONG).show();
            }
        }
    }
}
