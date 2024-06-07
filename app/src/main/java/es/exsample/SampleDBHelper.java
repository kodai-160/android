//SampleDBHelper.java データベースのヘルパークラスに関するサンプル
package es.exsample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SampleDBHelper extends SQLiteOpenHelper {

    public SampleDBHelper(Context context) {  //データベースオブジェクト生成時の処理
        super(context, "sampletable", null , 1);
    }

    public void onCreate(SQLiteDatabase db) {  //データベース作成時の処理
        db.execSQL("CREATE TABLE sampletable (_id INTEGER PRIMARY KEY, sampletext TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}  //データベースアップデート時の処理
}
