package io.SeanChense.ops;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class HandlePicActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	     //无title            
        requestWindowFeature(Window.FEATURE_NO_TITLE);            
           //全屏            
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,                
                                       WindowManager.LayoutParams. FLAG_FULLSCREEN);
		setContentView(R.layout.activity_handle_pic);
		String path = getPath();
		Bitmap yourSelectedImage = BitmapFactory.decodeFile(path);
		((ImageView)findViewById(R.id.image)).setImageBitmap(yourSelectedImage);
		
	}
	public String getPath(){
		Intent intent = getIntent();
		String path = (String) intent.getCharSequenceExtra("path");
		return path;
	}

}
