package ir.share_element.poc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.jetbrains.annotations.NotNull;

import ir.share_element.poc.databinding.FragmentKeysRecyclerViewBinding;
import ir.share_element.poc.keysListView.BottomOffsetDecoration;
import ir.share_element.poc.keysListView.KeysAdapter;

public class KeysRecyclerViewFragment extends Fragment {

    private FragmentKeysRecyclerViewBinding binding;

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
        binding = FragmentKeysRecyclerViewBinding.inflate(inflater, container, false);
              return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        KeysAdapter adapter = new KeysAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        adapter.notifyDataSetChanged();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.scheduleLayoutAnimation();

        float offsetPx = getResources().getDimension(R.dimen.bottom_offset_dp);
        BottomOffsetDecoration bottomOffsetDecoration = new BottomOffsetDecoration((int) offsetPx);
        binding.recyclerView.addItemDecoration(bottomOffsetDecoration);

        postponeEnterTransition();
        view.getViewTreeObserver()
                .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        view.getViewTreeObserver()
                                .removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });


        adapter.setClickListener(new KeysAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View _view) {
                String shareelement = ViewCompat.getTransitionName(_view);
                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder().addSharedElement(_view, shareelement).build();
                Bundle bundle = new Bundle();
                bundle.putString("EXTRA_TRANSITION_NAME", shareelement);
                Navigation.findNavController(view).navigate(R.id.action_keysRecyclerViewFragment_to_showKeysDetailFragment, bundle, null, extras);
            }
        });


    }
}