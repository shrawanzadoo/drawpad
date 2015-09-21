package drawpad.ustwo.com.drawpad.utilities;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import drawpad.ustwo.com.drawpad.R;
import drawpad.ustwo.com.drawpad.customPager.CustomViewPager;
import drawpad.ustwo.com.drawpad.pagerAdapter.ViewPagerAdapter;

/**
 * Created by Shrawan Zadoo on 21/09/15.
 */
public class MainActivityHelper {

    public static Activity rootView;
    public static CustomViewPager pager;
    public static ViewPagerAdapter viewPagerAdapter;

    public static void setOptionsButtons(){
        ImageView imageView_clear = (ImageView) rootView.findViewById(R.id.imageView_clear);
        imageView_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
        ImageView imageView_save = (ImageView) rootView.findViewById(R.id.imageView_save);
        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        ImageView imageView_stroke_color = (ImageView) rootView.findViewById(R.id.imageView_stroke_color);
        imageView_stroke_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPicker();
            }
        });
        ImageView imageView_fill_color = (ImageView) rootView.findViewById(R.id.imageView_fill_color);
        imageView_fill_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBackGroundColor();
            }
        });
        ImageView imageView_wallpaper = (ImageView) rootView.findViewById(R.id.imageView_wallpaper);
        imageView_wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWallpaper();
            }
        });
    }

    private static void clear(){
        if(pager.getCurrentItem() == 0){
            viewPagerAdapter.myDoodle.doodleHelper.clear();
        }else{
            viewPagerAdapter.friendsDoodle.doodleHelper.clear();
        }
    }

    private static void showColorPicker(){
        if(pager.getCurrentItem() == 0) {
            viewPagerAdapter.myDoodle.doodleHelper.showColorPicker();
        }else{
            viewPagerAdapter.friendsDoodle.doodleHelper.showColorPicker();
        }
    }

    private static void save(){
        if(pager.getCurrentItem() == 0) {
            viewPagerAdapter.myDoodle.doodleHelper.showSaveAlert();
        }else{
            viewPagerAdapter.friendsDoodle.doodleHelper.showSaveAlert();
        }
    }

    private static void setWallpaper(){
        if(pager.getCurrentItem() == 0) {
            viewPagerAdapter.myDoodle.doodleHelper.setAsWallpaper();
        }else{
            viewPagerAdapter.friendsDoodle.doodleHelper.setAsWallpaper();
        }
    }

    private static void setBackGroundColor(){
        if(pager.getCurrentItem() == 0) {
            viewPagerAdapter.myDoodle.doodleHelper.showColorPickerForBackground();
        }else{
            viewPagerAdapter.friendsDoodle.doodleHelper.showColorPickerForBackground();
        }
    }
}
