package ir.matiran.poc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.matiran.poc.databinding.FragmentBlank2Binding;


public class BlankFragment extends Fragment {

    FragmentBlank2Binding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       binding = FragmentBlank2Binding.inflate(inflater,container,false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewCompat.setTransitionName(binding.textView,"shareelement");
        binding.textView.setOnClickListener(v -> {

            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder().addSharedElement(binding.textView,"shareelement").build();

            Navigation.findNavController(v).navigate(R.id.action_blankFragment_to_showKeysDetailFragment,null,null,extras);
        });
    }
}