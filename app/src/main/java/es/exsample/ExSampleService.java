//ExSampleService.java サービスとノーティフィケーションに関するサンプル
package es.exsample;

import android.app.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.*;
import android.widget.Toast;
import androidx.core.app.*;
import java.util.ArrayList;
import java.util.Random;

public class ExSampleService extends Service {

	Random r;
	String[] str = {"兼六園", "21世紀美術館", "近江町市場", "東茶屋街"};
	private static final int REQUEST_MULTI_PERMISSIONS = 101;

	public IBinder onBind(Intent it) {
		return null;
	}

	public void onCreate() {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  // API26以上でのノーティフィケーション提示にはノーティフィケーションチャネルの登録が必要
			NotificationChannel channel = new NotificationChannel("CHANNEL_ID", "ExSample", NotificationManager.IMPORTANCE_DEFAULT); //ノーティフィケーションチャネルの生成
			channel.setDescription("通知のサンプルです");  //チャネルの説明
			NotificationManager nm = getSystemService(NotificationManager.class);  //ノーティフィケーションマネージャーの生成
			nm.createNotificationChannel(channel);  //ノーティフィケーションマネージャーにノーティフィケーションチャネルを登録
		}

		r = new Random();  //ランダムな数値の生成用オブジェクト
	}

	public int onStartCommand(Intent i, int flag, int id) {
		ArrayList<String> requestPermissions = new ArrayList<>();

		Intent it = new Intent(this, ExSample.class);  //ノーティフィケーションからインテント元を呼び出し
		PendingIntent pi = PendingIntent.getActivity(this, 0, it, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);  //ペンディングインテントの生成
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID") //ノーティフィケーションビルダーの作成
				.setContentTitle("サンプル") //タイトルの設定
				.setContentText("設定画面に移動します。") //説明の作設定
				.setContentIntent(pi) //ペンディングインテントの登録
				.setSmallIcon(R.mipmap.ic_launcher) //小アイコンの登録
				.setWhen(System.currentTimeMillis()) //開始タイミングの設定
				.setPriority(NotificationCompat.PRIORITY_DEFAULT);  //ノーティフィケーションの重要度の設定

		NotificationManagerCompat nmc = NotificationManagerCompat.from(this); //ノーティフィケーションマネージャーの生成
		if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
			requestPermissions.add(android.Manifest.permission.POST_NOTIFICATIONS);
		}
		nmc.notify(0, builder.build());  //ノーティフィケーションの開始

		int m  = r.nextInt(str.length);  //ランダムな数値の生成
		Toast.makeText(this, str[m], Toast.LENGTH_LONG).show();  //トーストでテキストを表示
		return START_STICKY;
	}
	
}