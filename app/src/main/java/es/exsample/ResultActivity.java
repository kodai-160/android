package es.exsample;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends Activity {

    private Realm realm;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Realmの初期化
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // データベースからすべてのトランザクションを取得して表示
        loadItemsFromDatabase();

        Button backButton = findViewById(R.id.button20);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // 現在のアクティビティを終了して前の画面に戻る
            }
        });
    }

    private void loadItemsFromDatabase() {
        RealmResults<Kakeibo> results = realm.where(Kakeibo.class)
                .findAll()
                .sort("timestamp", Sort.DESCENDING);

        if (results.isEmpty()) {
            Toast.makeText(this, "データが存在しません。", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("ResultActivity", "Items found: " + results.size());
        }

        // カテゴリと金額を格納するリスト
        List<String> categories = new ArrayList<>();
        List<Integer> amounts = new ArrayList<>();
        List<KakeiboItem> items = new ArrayList<>();

        for (Kakeibo kakeibo : results) {
            Log.d("ResultActivity", "Retrieving item: Category = " + kakeibo.getCategory() + ", Amount = " + kakeibo.getAmount());
            categories.add(kakeibo.getCategory());
            amounts.add(kakeibo.getAmount());
            KakeiboItem newItem = new KakeiboItem(kakeibo.getCategory(), kakeibo.getAmount());
            items.add(newItem);
            Log.d("ResultActivity", "Item added: " + newItem.toString());  // newItem.toString()が有効なオーバーライドをしている場合
        }


        // アダプター設定を一度だけ行う
        KakeiboAdapter adapter = new KakeiboAdapter(items);
        recyclerView.setAdapter(adapter);

        // categories と amounts リストを別途使用できます
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();  // Realmインスタンスを閉じる
        }
    }
}
