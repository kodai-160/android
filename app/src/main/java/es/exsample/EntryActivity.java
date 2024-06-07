package es.exsample;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import android.content.Intent;

import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmResults;

public class EntryActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButtonFood, radioButtonUtilities, radioButtonRent, radioButtonTransport, radioButtonEntertainment, radioButtonClothing, radioButtonDrinks;
    private TextInputEditText amountInput;
    private Button confilmButton;  // 名前を修正
    private int radioButtonId = 0;  // クラスレベルの変数として使用

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        realm = Realm.getDefaultInstance();

        TextView categoryTextView = findViewById(R.id.textView7);
        radioGroup = findViewById(R.id.radioGroupCategories);
        radioButtonFood = findViewById(R.id.radioButton3);
        radioButtonUtilities = findViewById(R.id.radioButton4);
        radioButtonRent = findViewById(R.id.radioButton5);
        radioButtonTransport = findViewById(R.id.radioButton6);
        radioButtonEntertainment = findViewById(R.id.radioButton7);
        radioButtonClothing = findViewById(R.id.radioButton8);
        radioButtonDrinks = findViewById(R.id.radioButton9);
        amountInput = findViewById(R.id.textInputAmount);
        confilmButton = findViewById(R.id.button16);  // 名前を修正

        categoryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Kakeibo> kiboshes = realm.where(Kakeibo.class).findAll();
                for (Kakeibo kakeibo : kiboshes) {
                    Log.d("MainActivity", "Task: " + kakeibo.getAmount());
                }
            }
        });

        confilmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountInput.getText() != null) {
                    try {
                        int amount = Integer.parseInt(amountInput.getText().toString());
                        RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                        String category = selectedRadioButton.getText().toString();

                        realm.executeTransactionAsync(realm -> {
                            Kakeibo kakeibo = realm.createObject(Kakeibo.class, System.currentTimeMillis());
                            kakeibo.setAmount(amount);
                            kakeibo.setCategory(category);
                            kakeibo.setRadioButtonId(radioButtonId);
                            Log.d("amount", String.valueOf(amount));
                            Log.d("category", category);
                            Log.d("radioButtonId", String.valueOf(radioButtonId));
                        }, () -> {
                            Intent intent = new Intent(EntryActivity.this, ResultActivity.class);
                            startActivity(intent);
                        }, error -> {
                            Toast.makeText(EntryActivity.this, "Error saving data: " + error.getMessage(), Toast.LENGTH_LONG).show();
                            Log.e("RealmError", "Error saving data", error);
                        });
                    } catch (NumberFormatException e) {
                        Toast.makeText(EntryActivity.this, "Invalid number format", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                radioButtonId = checkedId;  // ここでIDを更新
                Toast.makeText(EntryActivity.this, "Selected: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
        }
    }
}
