package drawpad.ustwo.com.drawpad.utilities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.animation.TranslateAnimation;

/**
 * Created by Shrawan Zadoo on 20/09/15.
 */
public class AnimHelper {

    public static boolean isVisible = true;
    static float initialYTab, initialYOptions, initialYImageButton;
    public static void toggleViews(View tabBar, View optionBar, View imageButton){
        if(isVisible){
            initialYTab = tabBar.getY();
            initialYOptions = optionBar.getY();
            initialYImageButton = imageButton.getY();
            hideTabs(tabBar);
            hideOptions(optionBar, imageButton);
        }else{
            showTabs(tabBar);
            showOptions(optionBar, imageButton);
        }
        isVisible = !isVisible;
    }

    private static void hideTabs(View view) {
        view.animate()
                .translationY(view.getY() - view.getHeight())
                .alpha(0.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });
    }

    private static void hideOptions(View view, View imageButton) {
        view.animate()
                .translationY(view.getY()+view.getHeight())
                .alpha(0.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });

        imageButton.animate()
                .translationY(imageButton.getY()+view.getHeight())
                .alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });

    }

    private static void showTabs(View view) {
        view.animate()
                .translationY(initialYTab)
                .alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });
    }

    private static void showOptions(View view, View imageButton) {
        view.animate()
                .translationY(initialYOptions)
                .alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });

        imageButton.animate()
                .translationY(initialYImageButton)
                .alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });

    }
}
