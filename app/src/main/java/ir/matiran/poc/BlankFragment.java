package ir.matiran.poc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.jetbrains.annotations.NotNull;

import ir.matiran.poc.databinding.FragmentBlankBinding;
import ir.matiran.poc.keysListView.BottomOffsetDecoration;
import ir.matiran.poc.keysListView.KeysAdapter;

public class BlankFragment extends Fragment {

    private FragmentBlankBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBlankBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KeysAdapter adapter = new KeysAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
//        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.scheduleLayoutAnimation();

        NavController navController = Navigation.findNavController(view);

        float offsetPx = getResources().getDimension(R.dimen.bottom_offset_dp);
        BottomOffsetDecoration bottomOffsetDecoration = new BottomOffsetDecoration((int) offsetPx);
        binding.recyclerView.addItemDecoration(bottomOffsetDecoration);


        adapter.setClickListener(new KeysAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textview) {

//https://mikescamell.com/shared-element-transitions-part-4-recyclerview/
//                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
//                        .addSharedElement(itemView.findViewById(R.id.key_item_model_title), "keys_title_share_element")
//                        .addSharedElement(itemView.findViewById(R.id.key_item_model_root_layout), "keys_root_share_element")
//                        .build();

            }
        });
    }
}