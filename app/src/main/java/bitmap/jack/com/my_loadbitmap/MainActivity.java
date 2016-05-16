package bitmap.jack.com.my_loadbitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
    }

    public void clickButton(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "aa.jpg");
        String pathUrl = file.getAbsolutePath();
        //手机分辨率
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        //二次采样
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathUrl, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int widthSize = outWidth/widthPixels;
        int heightSize = outHeight/heightPixels;
        int simaple = widthSize;
        if (heightSize>widthSize) {
            simaple = heightSize;
        }
        options.inSampleSize = simaple;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(pathUrl);
        iv.setImageBitmap(bitmap);
    }
}
