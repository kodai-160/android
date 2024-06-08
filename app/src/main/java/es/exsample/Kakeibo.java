package es.exsample;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Kakeibo extends RealmObject {
    @PrimaryKey
    private long id;  // トランザクションの一意識別子
    private String category;  // カテゴリ名を文字列で保存
    private int amount;  // 金額
    private Date date;
    private long timestamp;  // タイムスタンプ

    // カテゴリ名を取得するゲッター
    public String getCategory() {
        return category;
    }

    // カテゴリ名を設定するセッター
    public void setCategory(String category) {
        this.category = category;
    }

    // IDを取得するゲッター
    public long getId() {
        return id;
    }

    // IDを設定するセッター
    public void setId(long id) {
        this.id = id;
    }

    // 金額を取得するゲッター
    public int getAmount() {
        return amount;
    }

    // 金額を設定するセッター
    public void setAmount(int amount) {
        this.amount = amount;
    }

    // タイムスタンプを取得するゲッター
    public long getTimestamp() {
        return timestamp;
    }

    // タイムスタンプを設定するセッター
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Object getAmout() {
        return null;
    }

    public void setAmout(int amount) {
    }

    public void setRadioButtonId(int radioButtonId) {
    }
}
