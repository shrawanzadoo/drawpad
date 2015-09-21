package drawpad.ustwo.com.drawpad.myDoodle;


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
public class MyDoodle extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";
    Paint mPaint;
    RelativeLayout relativeLayout_my_doodle;
    DrawingView drawingView;

    public DoodleHelper doodleHelper;

    public static MyDoodle newInstance(int sectionNumber) {
        MyDoodle fragment = new MyDoodle();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MyDoodle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_doodle, container, false);

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
        drawingView.setDrawingCacheEnabled(true);

        relativeLayout_my_doodle = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_my_doodle);
        relativeLayout_my_doodle.addView(drawingView);
        doodleHelper = new DoodleHelper(getActivity(), mPaint, drawingView);
        return rootView;
    }
}
