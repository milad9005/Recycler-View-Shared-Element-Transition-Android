package ir.matiran.poc.keysListView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import ir.matiran.poc.R;
import ir.matiran.poc.databinding.KeyItemModelBinding;

public class KeysAdapter extends RecyclerView.Adapter<KeysAdapter.KeysAdapterViewHolder> {

    public KeysAdapter.onItemClickListener clickListener;
    private KeyItemModelBinding binding;

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public KeysAdapterViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = KeyItemModelBinding.inflate(inflater, parent, false);
        return new KeysAdapterViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull KeysAdapter.KeysAdapterViewHolder holder, int position) {

        holder.bind(position,clickListener,holder.title);
        ViewCompat.setTransitionName(holder.title, "shareelement");
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    static class KeysAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private MaterialCardView cardView;



        public KeysAdapterViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.key_item_model_title);

            cardView = itemView.findViewById(R.id.key_item_model_root_layout);
        }


        public void bind(final int position, final onItemClickListener listener, TextView title) {


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position,title);
                }
            });
        }
    }

    public void setClickListener(KeysAdapter.onItemClickListener _listener) {
        this.clickListener = _listener;
    }

    public interface onItemClickListener {
        void onItemClick(int position, TextView textview);
    }
}
