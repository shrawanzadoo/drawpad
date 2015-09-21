package drawpad.ustwo.com.drawpad.pagerAdapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import drawpad.ustwo.com.drawpad.R;
import drawpad.ustwo.com.drawpad.friendsDoodle.FriendsDoodle;
import drawpad.ustwo.com.drawpad.myDoodle.MyDoodle;
import io.karim.MaterialTabs;

/**
 * Created by Shrawan Zadoo on 20/09/15.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter implements MaterialTabs.CustomTabProvider{

    private final String[] TITLES = {"My Doodle", "Friends Doodle"};
    private final int[] ICONS = {R.drawable.profile_male, R.drawable.network};
    private Context context;
    private LayoutInflater layoutInflater;
    public MyDoodle myDoodle;
    public FriendsDoodle friendsDoodle;

    public ViewPagerAdapter(FragmentManager fm, Context context, LayoutInflater layoutInflater) {
        super(fm);
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: myDoodle = MyDoodle.newInstance(1);
                return myDoodle;
            case 1: friendsDoodle = FriendsDoodle.newInstance(2);
                return friendsDoodle;
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getCustomTabView(ViewGroup viewGroup, int i) {
        View tab_item = layoutInflater.inflate(R.layout.tab_item, null);

        ImageView imageView = (ImageView)tab_item.findViewById(R.id.iv_tab_icon);
        imageView.setImageDrawable(context.getResources().getDrawable(ICONS[i], null));

        TextView textView = (TextView) tab_item.findViewById(R.id.tv_tab_title);
        textView.setText(getPageTitle(i));

        return tab_item;
    }
}
