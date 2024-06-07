package es.exsample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class KakeiboAdapter extends RecyclerView.Adapter<KakeiboAdapter.ViewHolder> {

    private List<KakeiboItem> kakeiboItems;

    public KakeiboAdapter(List<KakeiboItem> kakeiboItems) {
        this.kakeiboItems = kakeiboItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kakeibo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KakeiboItem item = kakeiboItems.get(position);
        holder.categoryTextView.setText(item.getCategory());
        holder.amountTextView.setText(String.format("%d円", item.getAmount()));
    }

    @Override
    public int getItemCount() {
        return kakeiboItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryTextView;
        public TextView amountTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
        }
    }
}
