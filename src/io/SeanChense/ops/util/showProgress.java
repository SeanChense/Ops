package io.SeanChense.ops.util;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

public class showProgress {
	private abstract class ProgressTask extends AsyncTask <ProgressBar,Void,ProgressBar>{
	    protected void onPreExecute(ProgressBar bar){
	        bar.setVisibility(View.VISIBLE);
	    }

	    protected Void doInBackground(Void... arg0) {   
	    	return null;
	    }

	    @Override
	    protected void onPostExecute(ProgressBar bar) {
	          bar.setVisibility(View.GONE);
	    }

		
	}

}
