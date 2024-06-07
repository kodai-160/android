package es.exsample;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

//CardViewに提示するためのViewを扱うためのクラス
public class SampleViewHolder extends RecyclerView.ViewHolder {
    View view; //項目全体のビュー
    TextView title; //タイトルを提示するためのテキストビュー
    TextView tag; //タグを提示するためのテキストビュー
    TextView desc; //説明を提示するためのテキストビュー

    public SampleViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        this.title = (TextView) view.findViewById(R.id.title);
        this.tag = (TextView) view.findViewById(R.id.tag);
        this.desc = (TextView) view.findViewById(R.id.desc);
    }
}