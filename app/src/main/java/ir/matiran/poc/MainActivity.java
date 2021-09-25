package ir.matiran.poc;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.appbar.MaterialToolbar;

import ir.matiran.poc.databinding.ActivityMainBinding;
import ir.matiran.poc.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements ToolBarController {


    private ActivityMainBinding binding;
    private MaterialToolbar toolbar;
    private MainActivityViewModel viewModel;
    private NavHostFragment navHost;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        setContentView(binding.getRoot());
        binding.getRoot().getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


        navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toggle = new ActionBarDrawerToggle(this, null, toolbar, R.string.nav_app_bar_open_drawer_description, R.string.nav_app_bar_open_drawer_description);


        toolbar.setNavigationOnClickListener(v -> {
            binding.hamberMenuLayout.setVisibility(viewModel.showHambergerMenu ? View.GONE : View.VISIBLE);
            toolbar.setNavigationIcon(viewModel.showHambergerMenu ? R.drawable.ic_baseline_menu_24 : R.drawable.ic_baseline_close_24);
            viewModel.toggleHambergerMenu();
        });


    }

    @Override
    public void setToolbar_Title(String title) {
        toolbar.setTitle(title);
        toolbar.setSubtitle("");
        //toolbar.setTitleTextAppearance(this, R.style.TitleCustomStyle);
        toolbar.setTitleTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
    }

    @Override
    public void setToolbar_SubTitle(String title) {
        toolbar.setSubtitle(title);
        //toolbar.setSubtitleTextAppearance(this, R.style.SubTitleCustomStyle);
    }

    @Override
    public void setBackButtonActionBar(boolean enable) {
        if (enable) {
            toggle.setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (!viewModel.mToolBarNavigationListenerIsRegistered) {
                toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
                viewModel.mToolBarNavigationListenerIsRegistered = true;
            }
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toggle.setToolbarNavigationClickListener(null);
            viewModel.mToolBarNavigationListenerIsRegistered = false;
        }
    }
}