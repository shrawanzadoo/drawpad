package drawpad.ustwo.com.drawpad.utilities;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;

import drawpad.ustwo.com.drawpad.colorPicker.ColorPickerDialog;
import drawpad.ustwo.com.drawpad.drawingView.DrawingView;

/**
 * Created by Shrawan Zadoo on 21/09/15.
 */
public class DoodleHelper implements ColorPickerDialog.OnColorChangedListener{
    DrawingView drawingView;
    Paint mPaint;
    int option = 0;
    Activity context;

    public DoodleHelper(Activity context, Paint mPaint, DrawingView drawingView){
        this.context = context;
        this.mPaint = mPaint;
        this.drawingView = drawingView;
    }

    public void clear(){
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mPaint.setAlpha(0x80);
    }

    public void showColorPicker(){
        option = 1;
        new ColorPickerDialog(context, this, mPaint.getColor()).show();
    }

    public void showColorPickerForBackground(){
        option = 2;
        new ColorPickerDialog(context, this, mPaint.getColor()).show();
    }

    public void showSaveAlert(){
        AlertDialog.Builder editalert = new AlertDialog.Builder(context);
        editalert.setTitle("Please enter a file name");
        final EditText input = new EditText(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        editalert.setView(input);
        editalert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String name= input.getText().toString();
                Bitmap bitmap = drawingView.getDrawingCache();
                CapturePhotoUtils.insertImage(context.getContentResolver(), bitmap, name, "drawpad snapshot");
            }
        });

        editalert.show();
    }

    public void setAsWallpaper(){
        Thread th = new Thread(){
            public void run(){
                DisplayMetrics metrics = new DisplayMetrics();
                context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
                // get the height and width of screen
                int height = metrics.heightPixels;
                int width = metrics.widthPixels;

                WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
                try {
                    wallpaperManager.setBitmap(drawingView.getDrawingCache());

                    wallpaperManager.suggestDesiredDimensions(width, height);
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Wallpaper Set", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
    }


    @Override
    public void colorChanged(int color) {
        if (option == 1) {
            mPaint.setColor(color);
            mPaint.setAlpha(0xFF);
            mPaint.setXfermode(null);
        }
        else
            drawingView.setBackgroundColor(color);
    }
}
