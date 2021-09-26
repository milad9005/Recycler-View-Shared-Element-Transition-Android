package ir.matiran.poc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;

import ir.matiran.poc.databinding.FragmentKeysShowDetailBinding;


public class KeysShowDetailFragment extends Fragment {

    FragmentKeysShowDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
//        Transition transition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.transition1);
//        setSharedElementEnterTransition(transition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentKeysShowDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        String transitionName = getArguments().getString("EXTRA_TRANSITION_NAME");
        binding.keyItemModelTitle.setTransitionName(transitionName);



        startPostponedEnterTransition();
//        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.slide_right));


    }
}