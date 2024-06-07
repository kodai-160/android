package es.exsample;

import androidx.recyclerview.widget.RecyclerView;
import android.view.*;
import java.util.ArrayList;

//リサイクラービューに渡すデータとレイアウトを関連づけるクラス
public class SampleListAdapter extends RecyclerView.Adapter<SampleViewHolder> {
    private ArrayList<SampleListItem> data;

    public SampleListAdapter(ArrayList<SampleListItem> data) {
        this.data = data;
    }
    //ビューホルダーの生成
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new SampleViewHolder(v);
    }
    //データをビューホルダーに設定
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        holder.title.setText(this.data.get(position).getTitle());  //タイトルの設定
        holder.tag.setText(this.data.get(position).getTag());  //タグの設定
        holder.desc.setText(this.data.get(position).getDesc());  //説明文の設定
    }

    public int getItemCount() {
        return this.data.size();
    }  //項目の数を返す
}