package drawpad.ustwo.com.drawpad.friendsDoodle;


import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import drawpad.ustwo.com.drawpad.R;
import drawpad.ustwo.com.drawpad.drawingView.DrawingView;
import drawpad.ustwo.com.drawpad.utilities.DoodleHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsDoodle extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";
    Paint mPaint;
    DrawingView drawingView;
    public DoodleHelper doodleHelper;

    public static FriendsDoodle newInstance(int sectionNumber) {
        FriendsDoodle fragment = new FriendsDoodle();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FriendsDoodle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends_doodle, container, false);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);

        drawingView = new DrawingView(getActivity(), mPaint);
        drawingView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        RelativeLayout relativeLayout_my_doodle = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_friends_doodle);
        relativeLayout_my_doodle.addView(drawingView);

        doodleHelper = new DoodleHelper(getActivity(), mPaint, drawingView);

        return rootView;
    }
}
