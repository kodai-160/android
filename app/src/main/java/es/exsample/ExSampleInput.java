//ExSampleInput.java 明示的なインテントを利用したTODOリストに関するサンプル

package es.exsample;

import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ExSampleInput extends AppCompatActivity {
	EditText et1, et2;
	Button bt;
	String title, todo;
	int pos;
	Intent it;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);
		
		et1 = new EditText(this);
		et2 = new EditText(this);
		bt = new Button(this);
		bt.setText("OK");
		
		it = getIntent();  //インテント元からのデータの受け取り
		title = it.getStringExtra("Title");
		todo = it.getStringExtra("Todo");
		pos = it.getIntExtra("Pos", 0);
		et1.setText(title);
		et2.setText(todo);
		
		ll.addView(et1);
		ll.addView(et2);
		ll.addView(bt);
		
		bt.setOnClickListener(new SampleClickListener());
	}
	
	class SampleClickListener implements OnClickListener{
		public void onClick(View v){
			it.putExtra("Title", et1.getText().toString());  //インテント元にデータを返す
			it.putExtra("Todo", et2.getText().toString());
			it.putExtra("Pos", pos);
			setResult(RESULT_OK, it);
			finish();
		}
	}
}