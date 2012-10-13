package peacebe.common;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class HTTPGetTask extends AsyncTask<String, Integer, JSONObject> {
	@Override 
	protected JSONObject doInBackground(String... urls) {
 		String url = urls[0];
 		Log.i("HTTP", "httpGet " + url);
		//HttpGet httpRequest = new HttpGet("http://ec2-175-41-156-14.ap-southeast-1.compute.amazonaws.com/app/main/player/1/state");
		HttpGet httpRequest = new HttpGet(url); 
    	HttpResponse httpResponse = null;
		JSONObject result = null;
		try {
			httpResponse = new DefaultHttpClient().execute(httpRequest);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	if(httpResponse.getStatusLine().getStatusCode() == 200)  
    	{ 
    		String strResult = null;
			try {
				strResult = EntityUtils.toString(httpResponse.getEntity());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		        	 		

			try {
				result = new JSONObject(strResult);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	     
    	} 
    	else 
    	{ 
    		Log.d("http", "Error Response: " + httpResponse.getStatusLine().toString()); 
    	}
    	return result;
     }
}
