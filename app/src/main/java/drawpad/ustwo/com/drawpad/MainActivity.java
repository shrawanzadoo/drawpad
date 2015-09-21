package drawpad.ustwo.com.drawpad;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import drawpad.ustwo.com.drawpad.customPager.CustomViewPager;
import drawpad.ustwo.com.drawpad.pagerAdapter.ViewPagerAdapter;
import drawpad.ustwo.com.drawpad.utilities.AnimHelper;
import drawpad.ustwo.com.drawpad.utilities.MainActivityHelper;
import io.karim.MaterialTabs;

public class MainActivity extends FragmentActivity {

    MaterialTabs tabs;
    CustomViewPager pager;
    ViewPagerAdapter viewPagerAdapter;
    RelativeLayout relativeLayout_options, relativeLayout_parent;
    ImageButton imageButton_toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ViewPager and set an adapter
        pager = (CustomViewPager) findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getApplicationContext(), getLayoutInflater());
        pager.setAdapter(viewPagerAdapter);


        // Bind the tabs to the ViewPager
        tabs = (MaterialTabs) findViewById(R.id.material_tabs);
        tabs.setViewPager(pager);

        relativeLayout_parent = (RelativeLayout) findViewById(R.id.relativeLayout_parent);
        relativeLayout_options = (RelativeLayout) findViewById(R.id.relativeLayout_options);

        imageButton_toggle = (ImageButton) findViewById(R.id.imageButton_toggle_menu);
        imageButton_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleViews(view);
            }
        });

        setOptionsLayout();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void toggleViews(View view){
        if(AnimHelper.isVisible){
            imageButton_toggle.setImageDrawable(getDrawable(R.drawable.expand));
        }else{
            imageButton_toggle.setImageDrawable(getDrawable(R.drawable.collapse));
        }
        AnimHelper.toggleViews(tabs, relativeLayout_options, view);

    }

    private void setOptionsLayout(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                relativeLayout_options.setY(relativeLayout_parent.getHeight()-relativeLayout_options.getHeight());
                imageButton_toggle.setX(relativeLayout_options.getWidth()/2 - imageButton_toggle.getWidth()/2);
                imageButton_toggle.setY(relativeLayout_options.getY() - imageButton_toggle.getHeight() - 1);
            }
        }, 500);

        //set onclick handlers for option buttons
        MainActivityHelper.rootView = this;
        MainActivityHelper.pager = pager;
        MainActivityHelper.viewPagerAdapter = viewPagerAdapter;
        MainActivityHelper.setOptionsButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
