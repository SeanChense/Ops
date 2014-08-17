package io.SeanChense.ops;

import io.SeanChense.ops.imageUtils.ImageHelper;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

public class HandlePicActivity extends Activity {
	private final static String Tag = "HandlePicActivity";
	private final static String Today = "I am here with you";

	@SuppressWarnings("static-access")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	     //无title            
        requestWindowFeature(Window.FEATURE_NO_TITLE);            
           //全屏            
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,                
                                       WindowManager.LayoutParams. FLAG_FULLSCREEN);
		setContentView(R.layout.activity_handle_pic);
		ImageButton btn_save = (ImageButton)findViewById(R.id.btn_save);
		final ImageView         iv = (ImageView)findViewById(R.id.image);
		SeekBar seekBar = (SeekBar)findViewById(R.id.seekbar); 
		String path = getPath();
	    final ImageHelper ih = new ImageHelper();
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 8;
		final Bitmap blurTemplate = BitmapFactory.decodeFile(path, options);
		iv.setImageBitmap(blurTemplate);
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 
				Bitmap bm;
			   @Override 
			   public void onProgressChanged(SeekBar seekBar, int progress, 
			     boolean fromUser) { 
			    // TODO Auto-generated method stub 
			    Log.i(Tag, Today+"seek bar"+""+progress);
			    if(progress != 0){
			    	bm = ih.blurRenderScript(HandlePicActivity.this, blurTemplate, (float)seekBar.getProgress());
				    iv.setImageBitmap(bm);
			    }else{
			    	iv.setImageBitmap(blurTemplate);
			    }
			    
			   } 

			   @Override 
			   public void onStartTrackingTouch(SeekBar seekBar) { 
			    // TODO Auto-generated method stub 
			   } 

			   @Override 
			   public void onStopTrackingTouch(SeekBar seekBar) { 
			    // TODO Auto-generated method stub 
			   } 
			       }); 
		btn_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Store the picture
				Log.i(Tag, Today+" :btn_Save clicked");
				
			}
			
			
		});
		
		
		
	}
	public String getPath(){
		Intent intent = getIntent();
		String path = (String) intent.getCharSequenceExtra("path");
		return path;
	}

	
}
