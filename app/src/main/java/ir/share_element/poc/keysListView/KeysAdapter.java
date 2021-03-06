package ir.share_element.poc.keysListView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import ir.share_element.poc.R;

public class KeysAdapter extends RecyclerView.Adapter<KeysAdapter.KeysAdapterViewHolder> {

    public KeysAdapter.onItemClickListener clickListener;



    @NonNull
    @Override
    public KeysAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.key_item_model, parent, false);
        return new KeysAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeysAdapter.KeysAdapterViewHolder holder, int position) {

        holder.title.setText(String.valueOf(position));
        ViewCompat.setTransitionName(holder.cardView,String.valueOf(position));
        holder.bind(position, clickListener, holder.cardView);

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class KeysAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private MaterialCardView cardView;


        public KeysAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.key_item_model_title);
            cardView = itemView.findViewById(R.id.key_item_model_root_layout);
        }


        public void bind(final int position, final onItemClickListener listener, View view) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position, view);
                }
            });
        }
    }

    public void setClickListener(KeysAdapter.onItemClickListener _listener) {
        this.clickListener = _listener;
    }

    public interface onItemClickListener {
        void onItemClick(int position, View view);
    }
}
