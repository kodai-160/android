//ExSampleSub1.java 2つのアクティビティを明示的なインテントで実現するサンプル

package es.exsample;

import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ExSampleSub1 extends AppCompatActivity {
    EditText et1, et2;
    Button bt;
    Intent it;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        setContentView(ll);

        et1 = new EditText(this);
        et2 = new EditText(this);
        bt = new Button(this);
        bt.setText("更新");

        it = getIntent(); //インテントを受け取る
        et1.setText(it.getStringExtra("Text1"));  //インテント元から受け取ったデータをエディットテキストに設定
        et2.setText(it.getStringExtra("Text2"));

        ll.addView(et1);
        ll.addView(et2);
        ll.addView(bt);

        bt.setOnClickListener(new SampleClickListener());
    }

    class SampleClickListener implements OnClickListener{
        public void onClick(View v){
            it.putExtra("Text1", et1.getText().toString());  //インテント元に返すデータの設定
            it.putExtra("Text2", et2.getText().toString());
            setResult(RESULT_OK, it);  //インテント元にデータを送る
            finish();  //アクティビティの終了
        }
    }
}