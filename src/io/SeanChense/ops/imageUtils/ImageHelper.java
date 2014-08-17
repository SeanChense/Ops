package io.SeanChense.ops.imageUtils;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

public class ImageHelper {
	
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) 
	{
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
	
	public static Bitmap blurRenderScript(Context context,Bitmap smallBitmap,float BLUR_RADIUS) 
	{

	    Bitmap output = Bitmap.createBitmap(smallBitmap.getWidth(), smallBitmap.getHeight(), smallBitmap.getConfig());
	    RenderScript rs = RenderScript.create(context);
	    final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create( rs, Element.U8_4( rs ) );
	    Allocation inAlloc = Allocation.createFromBitmap(rs, smallBitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_GRAPHICS_TEXTURE);
	    Allocation outAlloc = Allocation.createFromBitmap(rs, output);
	    script.setRadius(BLUR_RADIUS);
	    script.setInput(inAlloc);
	    script.forEach(outAlloc);
	    outAlloc.copyTo(output);

	    rs.destroy();
	    return output;
	}
	
	public static Boolean savePic(Bitmap bm)
	{
		Boolean flag;
		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/saved_images");    
		myDir.mkdirs();
		Random generator = new Random();
		int n = 10000;
		n = generator.nextInt(n);
		String fname = "Image-"+ n +".jpg";
		File file = new File (myDir, fname);
		if (file.exists ()) file.delete (); 
		try {
		       FileOutputStream out = new FileOutputStream(file);
		       bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
		       out.flush();
		       out.close();
		       flag = true;

		} catch (Exception e) {
		       e.printStackTrace();
		       flag = false;
		}
		
		new Intent(
				Intent.ACTION_MEDIA_MOUNTED,
				        Uri.parse("file://" + Environment.getExternalStorageDirectory()));
		//By using this line you can able to see saved images in the gallery view.
		return flag;
	}

}
