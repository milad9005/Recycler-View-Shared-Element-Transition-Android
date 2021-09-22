package ir.matiran.poc.viewmodel;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    public boolean showHambergerMenu = false;
    public boolean mToolBarNavigationListenerIsRegistered = false;
    public void toggleHambergerMenu() {
        showHambergerMenu = !showHambergerMenu;
    }
}
