package ir.matiran.poc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.jetbrains.annotations.NotNull;

import ir.matiran.poc.databinding.FragmentKeysRecyclerViewBinding;
import ir.matiran.poc.keysListView.BottomOffsetDecoration;
import ir.matiran.poc.keysListView.KeysAdapter;

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


        float offsetPx = getResources().getDimension(R.dimen.bottom_offset_dp);
        BottomOffsetDecoration bottomOffsetDecoration = new BottomOffsetDecoration((int) offsetPx);
        binding.recyclerView.addItemDecoration(bottomOffsetDecoration);

        adapter.setClickListener(new KeysAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textview) {



                postponeEnterTransition();
                binding.recyclerView.getViewTreeObserver()
                        .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                            @Override
                            public boolean onPreDraw() {

                                String shareelement = ViewCompat.getTransitionName(textview);
                                binding.recyclerView.getViewTreeObserver()
                                        .removeOnPreDrawListener(this);
                                startPostponedEnterTransition();
                                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder().addSharedElement(textview,shareelement).build();
                                Bundle bundle = new Bundle();
                                bundle.putString("EXTRA_TRANSITION_NAME",shareelement);
                                Navigation.findNavController(view).navigate(R.id.action_keysRecyclerViewFragment_to_showKeysDetailFragment, bundle, null, extras);



                                return true;
                            }
                        });

//                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder().addSharedElement(textview, "shareelement").build();
//                Navigation.findNavController(view).navigate(R.id.action_keysRecyclerViewFragment_to_showKeysDetailFragment, null, null, extras);

//                ViewCompat.setTransitionName(textview,"shareelement");
//                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder().addSharedElement(textview,"shareelement").build();
//                Navigation.findNavController(view).navigate(R.id.action_blankFragment_to_showKeysDetailFragment,null,null,extras);

            }
        });


    }
}